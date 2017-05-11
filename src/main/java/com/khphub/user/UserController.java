package com.khphub.user;

import java.security.Principal;

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

import com.khphub.doc.domain.DetailVO;
import com.khphub.user.domain.AuthenticationRequestVO;
import com.khphub.user.domain.AuthenticationToken;
import com.khphub.user.domain.UserVO;
import com.khphub.user.service.UserService;

/**
 * This Controller handles all requests 
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
	 * login 
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
	
	/**
	 * Create Document (RequestMethod.POST)
	 * @param detailVO
	 * @return
	 */
	/*@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public int createDoc(@RequestBody DetailVO detailVO, Principal principal) {
		LOGGER.info(principal.getName());
		detailVO.setWriter(principal.getName());
		//SecurityContextHolder.getContext().getAuthentication().getPrincipal().
		return docService.createDoc(detailVO);
	}*/

}