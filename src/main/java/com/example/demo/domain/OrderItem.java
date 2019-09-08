package com.example.demo.domain;

/**
 * 注文商品のドメイン
 * 
 * @author hiranoyuusuke
 *
 */
public class OrderItem {
	
	/**ID */
	private Integer id;
	/**商品ID */
	private Integer itemId;
	/**注文ID*/
	private Integer orderId;
	/**数量*/
	private Integer quantity;
	/**サイズ*/
	private String size;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
}
