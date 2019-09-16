package com.example.demo.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.OrderItem;

/**
 * オーダーアイテムリポジトリーから値を取得したり、挿入したりする.
 * 
 * @author hiranoyuusuke
 *
 */
@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	private SimpleJdbcInsert insert;
	
	// 自動採番取得用メソッド
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	public OrderItem insert(OrderItem orderItem) {
		
		// ドメインの名前とSQLの？部分があっていれば自動的に入っていく
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		// executeAndReturnKeyが勝手にインサート文を実行してくれる
		Number key = insert.executeAndReturnKey(param);

		orderItem.setId(key.intValue());

		return orderItem;
		
	}
	
}
