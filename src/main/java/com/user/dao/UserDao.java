package com.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import com.user.domain.UserVO;

@Repository
public class UserDao {

	private final SqlSession sqlSession;
	
	public UserDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public UserVO readUser(String username){
		return this.sqlSession.selectOne("readUser", username);
	}
	
	public List<GrantedAuthority> readAuthorities(String username){
		return this.sqlSession.selectList("readAuthorities", username);
	}
	
	public int createUser(UserVO userVO){
		return this.sqlSession.insert("createUser", userVO);
	}
	
	public int createAuthorities(UserVO userVO){
		return this.sqlSession.insert("createAuthorities", userVO);
	}
	
	public int deleteUser(String username){
		return this.sqlSession.delete("deleteUser", username);
	}
	
	public int deleteAuthorities(String username){
		return this.sqlSession.delete("deleteAuthorities", username);
	}
}
