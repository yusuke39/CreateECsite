package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderTopping;
import com.example.demo.domain.Topping;

/**
 * オーダーテーブルから値を取得したり、挿入するリポジトリー.
 * 
 * @author hiranoyuusuke
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	//リザルトセットエクストラクター
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) ->{
	
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		
		int preId = -1;
		int preOrderItemCheckId = -1;
		
		while(rs.next()) {
			
			Integer id = rs.getInt("o_id");
			
			if(id != preId) {
				Order order = new Order();
				order.setId(rs.getInt("o_id"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
				order.setDestinationAddress(rs.getString("o_destination_address"));
				order.setDestinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));
				
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}
			
			Integer orderItemCheckId = rs.getInt("oi_id");
			
			if(id != 0 && orderItemCheckId != preOrderItemCheckId) {
				
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				orderItem.setSize(rs.getString("oi_size"));
//				String str = rs.getString("oi_size");
//				char[] toChar = str.toCharArray();
//				Character toCharacter = toChar[0];
//				orderItem.setSize(toCharacter);
				
				Item item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));
				
				orderItem.setItem(item);
				
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				
				orderItemList.add(orderItem);
				
			}
			
			Integer orderToppingCheckId = rs.getInt("ot_id");
			
			if(orderToppingCheckId != 0) {
				
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_topping_id"));
				orderTopping.setToppingId(rs.getInt("ot_order_item_id"));
			
				
				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceM(rs.getInt("t_price_m"));
				topping.setPriceL(rs.getInt("t_price_l"));
				orderTopping.setTopping(topping);
				
				orderToppingList.add(orderTopping);
			}
			
			preId = id;
			preOrderItemCheckId = orderItemCheckId;
			
		}
		return orderList;
	};
	
	
	//テーブル名を変数に入れる
	private static final String ORDER = "orders";
	private static final String ORDER_ITEMS = "order_items";
	private static final String ORDER_TOPPINGS = "order_toppings";
	private static final String ITEMS = "items";
	private static final String TOPPINGS = "toppings";
	
	//５つのテーブル結合のSQL文をSQLに入れる
	public String joinFiveTables() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name,o.destination_email o_destination_email,o.destination_zipcode o_destination_zipcode,");
		sql.append("o.destination_address o_destination_address,o.destination_tel o_destination_tel,o.delivery_time o_delivery_time,o.payment_method o_payment_method,");
		sql.append("oi.id oi_id,oi.item_id oi_item_id,oi.order_id oi_order_id,oi.quantity oi_quantity,oi.size oi_size,");
		sql.append("ot.id ot_id,ot.topping_id ot_topping_id,ot.order_item_id ot_order_item_id,");
		sql.append("i.id i_id,i.name i_name,i.description i_description,i.price_m i_price_m,i.price_l i_price_l,i.image_path i_image_path,i.deleted i_deleted,");
		sql.append("t.id t_id,t.name t_name,t.price_m t_price_m,t.price_l t_price_l");
		sql.append(" FROM " + ORDER + " o LEFT OUTER JOIN " +  ORDER_ITEMS + " oi ON o.id = oi.order_id LEFT OUTER JOIN " + ITEMS + " i ON oi.item_id = i.id");
		sql.append(" LEFT OUTER JOIN " + ORDER_TOPPINGS + " ot ON oi.id = ot.order_item_id LEFT OUTER JOIN " + TOPPINGS + " t ON t.id = ot.topping_id");
		
		return sql.toString();
	}
	

	private SimpleJdbcInsert insert;

	// 自動採番取得用メソッド
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}
	

	/**
	 * オーダーテーブルにインサートする.
	 * 自動採番を取得する
	 * 
	 * @param order
	 * @return　自動採番のIDを返す
	 */
	public Order insert(Order order) {
		// ドメインの名前とSQLの？部分があっていれば自動的に入っていく
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		// executeAndReturnKeyが勝手にインサート文を実行してくれる
		Number key = insert.executeAndReturnKey(param);

		order.setId(key.intValue());

		return order;
	}
	
	
	/**
	 * ユーザーIDとStatusを使ってOrderテーブルの有無を探す.
	 * 
	 * @param userId
	 * @param status
	 * @return オーダーテーブルの検索結果を返す
	 */
	public List<Order> findOrderByUserIdAndStatus(Integer userId, Integer status){
		
		String sql = joinFiveTables();
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		
		List<Order> orderList = template.query(sql, param,ORDER_RESULT_SET_EXTRACTOR);
		
		return orderList;
	}
	
	/**
	 * 5つテーブルを結合して、ログインしている人のショッピングカートの中身を取得.
	 * 
	 * @return 全件検索した結果を返す
	 */
	public Order findAll(Integer orderId){
		StringBuilder sql = new StringBuilder();
		sql.append(joinFiveTables());
		sql.append(" WHERE o.id = :orderId");
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		
		List<Order> orderList = template.query(sql.toString(),param,ORDER_RESULT_SET_EXTRACTOR);
		
		return orderList.get(0);
	}

}
