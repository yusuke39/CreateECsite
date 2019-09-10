package com.example.demo.domain;

import java.util.Collection;

public class LoginUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	
	public LoginUser(User user , Collection<GrantedAuthority> authorityList) {
		
		super(user.getEmail(),user.getPassword(),authorityList);
		this.user = user;
	}


	public User getUser() {
		return user;
	}
	

}
