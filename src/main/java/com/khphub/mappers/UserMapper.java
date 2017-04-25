package com.khphub.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.khphub.doc.domain.CategoryVO;
import com.khphub.doc.domain.DetailVO;
import com.khphub.doc.domain.DocVO;
import com.khphub.user.domain.UserVO;

/**
 * @author keehyun2
 */
@Mapper
public interface UserMapper {

	public UserVO readUser(String username);
	
	public List<GrantedAuthority> readAuthorities(String username);
	
	public int createUser(UserVO userVO);
	
	public int createAuthorities(UserVO userVO);
	
	public int deleteUser(String username);
	
	public int deleteAuthorities(String username);
	
}
