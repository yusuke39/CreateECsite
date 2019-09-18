package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.form.OrderForm;
import com.example.demo.form.ShoppingCartForm;
import com.example.demo.service.ShoppingCartService;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * ショッピングカートの中身を表示する.
	 * 
	 * @param loginUser
	 * @param model     リクエストスコープ
	 * @return ショッピングカートの中身を表示する
	 */
	@RequestMapping("/showCart")
	public String showCart(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		Integer userId = loginUser.getLoginUser().getId();

		Order order = shoppingCartService.findAll(userId);
		if(userId == order.getUserId()) {
			List<OrderItem> orderItemList = order.getOrderItemList();
			model.addAttribute("order", order);
			model.addAttribute("orderItemList", orderItemList);
		}
		return "order_confirm";
	}

	/**
	 * ショッピングカートに商品を追加する.
	 * 
	 * @param form
	 * @param loginUser
	 * @param model     リクエストスコープ
	 * @return ショッピングカートの表示をする
	 */
	@RequestMapping("/addCart")
	public String addCart(ShoppingCartForm form, @AuthenticationPrincipal LoginUser loginUser, Model model) {	

		Integer userId = loginUser.getLoginUser().getId();
		shoppingCartService.addShopingCart(form, userId);

		return "redirect:/shopping/showCart";

	}

	/**
	 * 注文した商品、トッピングを削除する.
	 * 
	 * @param userId
	 * @param orderId
	 * @param orderitemId
	 * @return ショッピングカートに戻る.
	 */
	@RequestMapping("/delete")
	public String delete(Integer itemId) {
		shoppingCartService.delete(itemId);
		return "redirect:/shopping/showCart";
	}

	@RequestMapping("/update")
	public String update(OrderForm form) {

		shoppingCartService.updateOrderTable(form);

		return "order_finished";
	}

}
