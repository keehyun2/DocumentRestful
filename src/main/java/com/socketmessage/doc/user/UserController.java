package com.socketmessage.doc.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socketmessage.doc.user.domain.AuthenticationRequestVO;
import com.socketmessage.doc.user.domain.AuthenticationToken;
import com.socketmessage.doc.user.domain.UserVO;
import com.socketmessage.doc.user.service.UserService;

/**
 * This Controller handles all requests 
 * 
 * | url     | method | consumes      | produces | description               |
 * |---------|--------|---------------|----------|---------------------------|
 * | /login | POST   | JSON: username, password    | JSON     |             |
 * 
 * @author keehyun2
 */

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private UserService userService;

	/**
	 * 로그인 
	 * @param loginVO
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public AuthenticationToken login(@RequestBody AuthenticationRequestVO authenticationRequestVO,
			HttpSession session) {
		String username = authenticationRequestVO.getUsername();
        String password = authenticationRequestVO.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                  SecurityContextHolder.getContext());
        
        UserVO user = userService.readUser(username);
        return new AuthenticationToken(user.getUsername(), user.getName(), user.getAuthorities(), session.getId());
	}
	

}