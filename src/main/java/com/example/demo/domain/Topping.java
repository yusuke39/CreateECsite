package com.example.demo.domain;

public class Topping {

	/** トッピングID */
	private Integer id;
	/** トッピング名前 */
	private String name;
	/** Mサイズの値段 */
	private Integer priceM;
	/** Lサイズの値段 */
	private Integer priceL;
	

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

}
