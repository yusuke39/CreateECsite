package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.service.ShowItemService;

/**
 * 商品一覧表示のコントローラー.
 * 
 * @author hiranoyuusuke
 *
 */
@Controller
@RequestMapping("")
public class ShowItemController {
	
	@Autowired
	private ShowItemService showItemService;
	
	
	
	/**
	 *商品一覧表示.
	 * 
	 * @param model　リクエストスコープ
	 * @return　商品一覧画面
	 */
	@RequestMapping("")
	public String showItems(Model model) {
//		List<List<Item>> itemList = new ArrayList<>();
		List<List<Item>> itemList = showItemService.findAllItems();
		model.addAttribute("itemList",itemList);
		return "item_list";
	}

}
