package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

@Service
@Transactional
public class ShowItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	/**
	 * 商品詳細を取得する.
	 * 
	 * @return　順番を入れ替えたアイテムリストを返す
	 */
	public List<List<Item>> findAllItems() {
		List<List<Item>> itemList = sortItemList(itemRepository.findAll());
		return itemList;
	}
	
	/**
	 * 曖昧検索.
	 * 
	 * @param itemName
	 * @return 検索結果のリストを返す
	 */
	public List<List<Item>> serchItem(String itemName){
		
		List<List<Item>> serchItemLists = sortItemList(itemRepository.serchItems(itemName));
		
		
		if(serchItemLists.size() == 0) {
			return null;
		}
		return serchItemLists;
	}
	
	/**
	 * 取得したアイテムリストを横３を繰り返す
	 * 
	 * @param item
	 * @return
	 */
	public List<List<Item>> sortItemList(List<Item> item){
		List<Item> sortLists = new ArrayList<>();
		
		List<List<Item>> itemLists = new ArrayList<>();
		
		for(int i = 1; i <= item.size(); i++) {
			sortLists.add(item.get(i -1));
			
			if(i % 3 == 0) {
				itemLists.add(sortLists);
				sortLists = new ArrayList<>();
			}
		}
		itemLists.add(sortLists);
		return itemLists;
	}
}
