package com.example.demo.form;

/**
 * ログインの際にリクエストパラメーターを受け取る.
 * 
 * @author hiranoyuusuke
 *
 */
public class UserForm {

	/** ユーザーID */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/**確認用パスワード */
	private String confirmPassword;
	/** 郵便番号 */
	private String zipcode;
	/** 住所 */
	private String address;
	/** 電話 */
	private String telephone;


	public UserForm() {}

	public UserForm(Integer id, String name, String email, String password, String confirmPassword, String zipcode,
			String address, String telephone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.zipcode = zipcode;
		this.address = address;
		this.telephone = telephone;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "UserForm [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", zipcode=" + zipcode + ", address=" + address
				+ ", telephone=" + telephone + "]";
	}

	
}
