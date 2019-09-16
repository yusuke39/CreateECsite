package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.LoginUser;
import com.example.demo.form.ShoppingCartForm;
import com.example.demo.service.ShoppingCartService;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
	
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
	
	@RequestMapping("/addCart")
	public String addCart(ShoppingCartForm form,  @AuthenticationPrincipal LoginUser loginUser) {
		
		Integer userId = loginUser.getLoginUser().getId();
		shoppingCartService.addShopingCart(form,userId);
		
		
		
		return "order_confirm";
		
	}
	
	

}
