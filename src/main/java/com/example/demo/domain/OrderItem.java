package com.example.demo.domain;

import java.util.List;

/**
 * 注文商品のドメイン
 * 
 * @author hiranoyuusuke
 *
 */
public class OrderItem {

	/** ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private String size;
	/** オーダートッピングリスト */
	private List<OrderTopping> orderToppingList;
	/**アイテムドメイン*/
	private Item item;
	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

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
	
	/**
	 * 商品とトッピングの合計金額を計算する.
	 * 
	 * @return　商品とトッピングの合計金額
	 */
	public Integer getSubTotal() {
		
		int itemPrice = 0;
		
		if(size.equals("m")) {
			itemPrice = item.getPriceM() + 200 * orderToppingList.size();
		} else {
			itemPrice = item.getPriceL() + 300 * orderToppingList.size();
		}
		
		int allPrice = itemPrice * quantity;
		
		return allPrice;

	}
}
