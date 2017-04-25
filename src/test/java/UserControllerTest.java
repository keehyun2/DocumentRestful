
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khphub.Application;
import com.khphub.user.domain.AuthenticationRequestVO;
import com.khphub.user.domain.UserVO;
import com.khphub.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
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
        /*userVO = new UserVO(); 
 		userVO.setUsername("keehyun21X1");
 		userVO.setPassword("1111");
 		userVO.setAccountNonExpired(true);
 		userVO.setAccountNonLocked(true);
 		userVO.setName("KEEHYUN");
 		userVO.setCredentialsNonExpired(true);
 		userVO.setEnabled(true);
 		userVO.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
 		
 		userService.delete(userVO);
 		userService.createUser(userVO);*/
    }
    
    @Test
    public void loginTest() throws Exception {
         AuthenticationRequestVO request = new AuthenticationRequestVO();
         request.setUsername("keehyun21X1");
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
