package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	/**
	 * login時にユーザー情報を探す.
	 * 
	 * @param email
	 * @param password
	 * @param form
	 * @return
	 */
//	public List<User> findUserByMailAddressAndPassword(String email, String password,UserForm form){
//		
//		List<User> userList = userRepository.findUser(email, password);
//		
//		if(email.equals(form.getEmail()) && password.equals(form.getPassword())) {
//			return userList;	
//		} else { 
//			return null;
//		}
//	}
	
	
	public void insertUserInfomation(UserForm form) {
		User user = new User();
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setZipcode(form.getZipcode());
		user.setZipcode(form.getZipcode());
		user.setAddress(form.getAddress());
		user.setTelephone(form.getTelephone());
		userRepository.insert(user);
	}

}
