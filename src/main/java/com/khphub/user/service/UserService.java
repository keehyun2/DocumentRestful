package com.khphub.user.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.khphub.user.domain.UserVO;

public interface UserService extends UserDetailsService {
	
	/**
	 * 스프링 시큐리티 사용시 필요한 메서드 (UserDetailsService 에서 강제 상속받음) 
	 * @param username
	 * @return
	 */
	public Collection<GrantedAuthority> readAuthorities(String username);
	
	/**
	 * encode password
	 * @return PasswordEncoder
	 */
	public PasswordEncoder passwordEncoder();
	
	/**
	 * create user
	 * @param userVO
	 * @return
	 */
	public int createUser(UserVO userVO); 
	
	/**
	 * read user 
	 * @param username
	 * @return
	 */
	public UserVO readUser(String username);
	
	/**
	 * delete user
	 * @param username
	 * @return
	 */
	public int deleteUser(String username);
	
}
