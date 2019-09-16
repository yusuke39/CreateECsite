package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderTopping;
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

}
