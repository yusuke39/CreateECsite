package com.example.demo.form;

import java.util.List;


import com.example.demo.domain.OrderTopping;

public class ShoppingCartForm {

	/** アイテムID */
	private String itemId;
	/** サイズ */
	private String size;
	/** トッピングリスト */
	private List<Integer> orderToppingList;
	/** 数量 */
	private String quantity;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", size=" + size + ", orderToppingList=" + orderToppingList
				+ ", quantity=" + quantity + "]";
	}

	public ShoppingCartForm(String itemId, String size, List<Integer> orderToppingList, String quantity) {
		super();
		this.itemId = itemId;
		this.size = size;
		this.orderToppingList = orderToppingList;
		this.quantity = quantity;
	}

	public ShoppingCartForm() {
	}

}
