package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * ユーザー関係の操作を行う.
 * 
 * @author hiranoyuusuke
 *
 */
@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	private RowMapper<User> USER_ROW_MAPPER = (rs,i) -> {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setZipcode(rs.getString("zipcode"));
			user.setAddress(rs.getString("address"));
			user.setTelephone(rs.getString("telephone"));
			return user;
	};
	
	
	
	/**
	 * メールアドレスとパスワードでユーザーを検索する.
	 * 
	 * @param email
	 * @param password
	 * @return ユーザーのリスト
	 */
	public User findUser(String email){
		String sql = "SELECT id,name,email,password,zipcode,address,telephone FROM users WHERE email = :email";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		
		return user;
	}
	
	/**
	 * ユーザー情報をインサートする.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		String sql ="INSERT INTO users(name,email,password,zipcode,address,telephone) VALUES(:name,:email,:password,:zipcode,:address,:telephone)";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		
		template.update(sql, param);
		
	}

}
