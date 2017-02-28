package com.user.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mappers.UserMapper;
import com.user.domain.UserVO;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	//@Autowired userMapper userMapper;
	
	@Autowired UserMapper userMapper;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = userMapper.readUser(username);
		userVO.setAuthorities(this.readAuthorities(username));
		
		return userVO;
	}

	@Override
	public Collection<GrantedAuthority> readAuthorities(String username) {
		Collection<GrantedAuthority> authorities = userMapper.readAuthorities(username); 
		
		return authorities;
	}

	@Override
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}

	@Override
	public int createUser(UserVO userVO) {
		String rawPassword = userVO.getPassword();
		String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		userVO.setPassword(encodedPassword);
		userMapper.createUser(userVO);
		userMapper.createAuthorities(userVO);
		return 0;
	}

	@Override
	public UserVO readUser(String username) {
		UserVO userVO = userMapper.readUser(username);
		userVO.setAuthorities(this.readAuthorities(username));
		return userVO;
	}

	@Override
	public int deleteUser(String username) {
		userMapper.deleteAuthorities(username);
		return userMapper.deleteUser(username);
	}

}
