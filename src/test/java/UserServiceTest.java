
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
import com.doc.domain.DetailVO;
import com.doc.domain.DocVO;
import com.doc.service.DocService;
import com.user.domain.UserVO;
import com.user.service.UserService;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired private UserService userService;
	
	@Autowired private DocService docService;
	
	private UserVO userVO;
	
	private DetailVO detailVO;
	
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
		
		detailVO = new DetailVO(); 
		detailVO.setWriter("keehyun2");
		detailVO.setTitle("제목 제목 제목 제목");
		detailVO.setViewCount(0);
		detailVO.setDetail("문서 상세 문서 상세 문서 상세 문서 상세 문서 상세 문서 상세 ");
	}
	
	@Test
	public void createUserTest(){
		
		for(DocVO vo : docService.readDocList(null)){
			DetailVO detailVO1 = new DetailVO(); 
			detailVO1.setDocIdx(vo.getDocIdx());
			docService.deleteDoc(detailVO1);
		}
		
		userService.deleteUser(userVO.getUsername());
		userService.createUser(userVO);
		
		docService.createDoc(detailVO);
		
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
