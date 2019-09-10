package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

/**
 * 商品を操作する.
 * 
 * @author hiranoyuusuke
 *
 */
@Repository
public class ItemRepository {
	
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private RowMapper<Item> ITEM_ROW_MAPPER = (rs,i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};
	
	
	
	/**
	 * 商品を全件取得する.
	 * 
	 * @return　商品全件取得結果を返す
	 */
	public List<Item> findAll(){
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items";
		
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		
		return itemList;
	}
	
	/**
	 * 曖昧検索.
	 * 
	 * @param itemName
	 * @return　検索結果をリストで返す
	 */
	public List<Item> serchItems(String itemName){
		String sql = "SELECT * FROM items WHERE name LIKE :itemName";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemName", '%' + itemName + '%');
		
		List<Item> itemSerchList = template.query(sql, param,ITEM_ROW_MAPPER);
		
		return itemSerchList;
	}

}
