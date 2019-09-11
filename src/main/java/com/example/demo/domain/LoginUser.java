package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * ユーザーのログイン情報を格納するエンティティ.
 * 
 * @author hiranoyusuke
 * 
 *
 */
public class LoginUser  extends User{

	private static final long serialVersionUID = 1L;
	/** ユーザー情報 */
	private final com.example.demo.domain.User user;
	

	/**
	 * 通常の管理者情報に加え、認可用ロールを設定する。
	 * 
	 * @param user
	 * @param authorityList
	 */
	public LoginUser(com.example.demo.domain.User user, Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
	}

	public com.example.demo.domain.User getLoginUser() {
		return user;
	}
	
}
