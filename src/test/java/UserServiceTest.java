
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.Application;
import com.user.domain.UserVO;
import com.user.service.UserService;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired private UserService userService;
	
	private UserVO userVO;
	
	@Before
	public void setup(){
		userVO = new UserVO(); 
		userVO.setUsername("keehyun2");
		userVO.setPassword("1111");
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setName("keehyun Park");
		userVO.setCredentialsNonExpired(true);
		userVO.setEnabled(true);
		userVO.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
	}
	
	@Test
	public void createUserTest(){
		userService.deleteUser(userVO.getUsername());
		userService.createUser(userVO);
		UserVO userVO1 = userService.readUser(userVO.getUsername());
		assertThat(userVO.getUsername(), is(userVO1.getUsername()));
		userService.readAuthorities(userVO.getUsername());
		
		PasswordEncoder passwordEncoder = userService.passwordEncoder();
		assertThat(passwordEncoder.matches("1111", userVO.getPassword()), is(true));
		
		Collection<? extends GrantedAuthority> authorities1 = userVO1.getAuthorities();
		Iterator<? extends GrantedAuthority> it = authorities1.iterator();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)userVO.getAuthorities();
	}

}
