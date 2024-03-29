package com.example.demo.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderTopping;
import com.example.demo.form.OrderForm;
import com.example.demo.form.ShoppingCartForm;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderToppingRepository;


@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	/**
	 * order,order_item,order_toppingにアイテムをインサートをする.
	 * 
	 * @param form
	 * @param userId
	 */
	public void addShopingCart(ShoppingCartForm form, Integer userId) {
		// formから受け取った値をドメインに詰める
		Order order = new Order();
		order.setUserId(userId);
		order.setStatus(0);
		order.setTotalPrice(0);

		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));

		List<Order> orderList = orderRepository.findOrderByUserIdAndStatus(userId, 0);

		if (orderList.size() == 0) {

			Order orderDomain = orderRepository.insert(order);
			orderItem.setOrderId(orderDomain.getId());

			OrderItem orderItemDomain = orderItemRepository.insert(orderItem);

			insertOrderTopping(form, orderItemDomain);

		} else {

			Order orderDomain = orderList.get(0);

			orderItem.setOrderId(orderDomain.getId());
			OrderItem orderItemDomain = orderItemRepository.insert(orderItem);

			insertOrderTopping(form, orderItemDomain);
		}

	}

	/**
	 * オーダートッピングにインサートする部分をメソッド化.
	 * 
	 * @param form
	 * @param orderItemDomain
	 */
	public void insertOrderTopping(ShoppingCartForm form, OrderItem orderItemDomain) {
		List<OrderTopping> orderToppingList = new ArrayList<>();
		OrderTopping orderTopping = new OrderTopping();
		if (form.getOrderToppingList() != null) {
			for (Integer toppingId : form.getOrderToppingList()) {
				orderTopping.setToppingId(toppingId);
				orderToppingList.add(orderTopping);
				orderTopping.setOrderItemId(orderItemDomain.getId());
				orderToppingRepository.insert(orderTopping);
			}
		}
	}
	
	/**
	 * 全件検索用のメソッド.
	 * 
	 * @param order
	 * @return 検索した結果を返す
	 */
	public Order findAll(Integer userId){
		
		List<Order> orderList = orderRepository.findOrderByUserIdAndStatus(userId, 0);
		Order order = orderList.get(0);
		Integer orderId = order.getId();
		Order orderDomain = orderRepository.findAll(orderId);
		
		return orderDomain;
	}
	
	
	/**
	 * 注文した商品とトッピングを削除する.
	 * 
	 * @param itemId
	 */
	public void delete(Integer itemId) {
		
		orderItemRepository.deleteByOrderId(itemId);
		orderToppingRepository.deleteByOrderItemId(itemId);
	}
	
	
	/**
	 * orderテーブルを更新する.
	 * 
	 * @param order
	 * @throws ParseException 
	 */
	public void updateOrderTable(OrderForm form) {
		Order order = new Order();
		//現在の日付を取得して、Date型に変換する
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);

			//String型からTimestamp型へ変換をする		
			String delivery = form.getDeliveryDate() + " " + form.getDeliveryTime();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH");
	        LocalDateTime time = LocalDateTime.parse(delivery, formatter);
	        Timestamp timestamp = Timestamp.valueOf(time);

//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh");
//		    java.util.Date parsedDate = null;
//			try {
//				parsedDate = dateFormat.parse(delivery);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

		
		//statusが0なら２に変更（入金済みの意味)
		if(Integer.parseInt(form.getStatus()) == 0) {
			order.setStatus(2);
		} 
		
		
		order.setId(Integer.parseInt(form.getId()));
		order.setOrderDate(date);
		order.setTotalPrice(Integer.parseInt(form.getTotalPrice()));
		order.setDestinationName(form.getDestinationName());
		order.setDestinationEmail(form.getDestinationEmail());
		order.setDestinationZipcode(form.getDestinationZipcode());
		order.setDestinationAddress(form.getDestinationAddress());
		order.setDestinationTel(form.getDestinationTel());
		order.setDeliveryTime(timestamp);
		order.setPaymentMethod(Integer.parseInt(form.getPaymentMethod()));
		
		orderRepository.update(order);
	}

}
