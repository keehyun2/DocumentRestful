
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.domain.AuthenticationRequestVO;
import com.user.domain.UserVO;
import com.user.service.UserService;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerTest {

	@Autowired private WebApplicationContext wac;
    private MockMvc mvc;
    
    private UserVO userVO;
    @Autowired private UserService userService;

    @Before
    public void setup() {
         mvc = MockMvcBuilders
                   .webAppContextSetup(wac)
                   .build();
        userVO = new UserVO(); 
 		userVO.setUsername("keehyun21X");
 		userVO.setPassword("1111");
 		userVO.setAccountNonExpired(true);
 		userVO.setAccountNonLocked(true);
 		userVO.setName("KEEHYUN");
 		userVO.setCredentialsNonExpired(true);
 		userVO.setEnabled(true);
 		userVO.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
 		
 		//userService.delete(userVO);
 		userService.createUser(userVO);
    }
    
    @Test
    public void loginTest() throws Exception {
         AuthenticationRequestVO request = new AuthenticationRequestVO();
         request.setUsername("keehyun21X");
         request.setPassword("1111");
         
         ObjectMapper om = new ObjectMapper();
         
         mvc.perform(post("/user/login")
                   .contentType(MediaType.APPLICATION_JSON_UTF8)
                   .content(om.writeValueAsString(request)))
              .andExpect(status().isAccepted())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
              //.andExpect(jsonPath("$.username", is(request.getUsername().toUpperCase())))
              .andExpect(jsonPath("$.authorities[*].authority", hasItem("USER")))
              ;
    }

}
