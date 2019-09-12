package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ToppingRepository;

@Service
@Transactional
public class ShowItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	
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
		
		return serchItemLists;
	}
	
	
	/**
	 * アイテムを１件検索する.
	 * 
	 * @param itemId
	 * @return　アイテム情報が１件詰まったドメイン
	 */
	public Item findItemByItemId(Integer itemId) {
		List<Item> itemList = itemRepository.load(itemId);
		
		if(itemList.size() == 0) {
			return null;
		}
		
		Item item = itemList.get(0);
		item.setToppingList(toppingRepository.findAll());
		return item;
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
			
			//もしiが３で割り切れたら、itemListにsortlistをaddする
			if(i % 3 == 0) {
				itemLists.add(sortLists);
				sortLists = new ArrayList<>();
			}
		}
		
		itemLists.add(sortLists);
		return itemLists;
	}
	
}
