package com.example.demo.domain;

import java.util.List;

public class Item {

	/** 商品ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** 商品説明 */
	private String description;
	/** Mサイズの値段 */
	private Integer priceM;
	/** Lサイズの値段 */
	private Integer priceL;
	/** 画像 */
	private String imagePath;
	/** 論理削除*/
	private boolean deleted;
	/** トッピングリスト*/
	private List<Topping> toppingList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

}
