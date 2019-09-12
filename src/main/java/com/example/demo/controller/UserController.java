package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.UserForm;
import com.example.demo.service.UserDetailService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailService UserDetailService;
	
	
	/**
	 * ログインページを表示する.
	 * 
	 * @param model
	 * @return　ログインページを表示
	 */
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	
	@RequestMapping("/checkLogin")
	public String checkLogin(String email) {
		UserDetailService.loadUserByUsername(email);
		return "forward:/";
	}
	
	
	/**
	 * ログインした際に値があってるかチェックする.
	 * 
	 * @param email
	 * @param password
	 * @param form
	 * @param model
	 * @return 商品詳細を表示する
	 */
//	@RequestMapping("/cheeckLogin")
//	public String checkLogin(String email, String password, UserForm form, Model model) {
//		
//		List<User> userList = userService.findUserByMailAddressAndPassword(email, password,form);
//		
//		if(userList == null) {
//			model.addAttribute("loginError", "メールアドレスかパスワードが一致しません");
//			return login(model);
//		}
//		
//		User user = new User();
//		user = userList.get(0);
//		model.addAttribute("user", user);
//		
//		return "forward:/";
//		
//	}
	
	
	/**
	 * ユーザー登録画面を表示する.
	 * 
	 * @param model
	 * @return ユーザー登録画面の表示
	 */
	@RequestMapping("/register")
	public String register(Model model) {
		return "register_user";
	}
	
	
	
	/**
	 * 入力された内容をuserstableにインサートする.
	 * 
	 * @param form
	 * @param model
	 * @return 商品商品一覧を表示する
	 */
	@RequestMapping("/registerUser")
	public String registerUser(UserForm form, Model model) {
		
		if(!(form.getPassword().equals(form.getConfirmPassword()))) {
			model.addAttribute("insertError", "パスワードが一致しません");
			return register(model);
		}
		
		userService.insertUserInfomation(form);
		
		return "forward:/";
	}

}
