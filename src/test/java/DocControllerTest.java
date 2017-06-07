
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khphub.Application;
import com.khphub.user.domain.AuthenticationRequestVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DocControllerTest {

	@Autowired private WebApplicationContext wac;
    private MockMvc mvc;
    
    @Before
    public void setup() {
         mvc = MockMvcBuilders
                   .webAppContextSetup(wac)
                   .build();
    }
    
    @Test
    public void loginTest() throws Exception {
         AuthenticationRequestVO request = new AuthenticationRequestVO();
         request.setUsername("admin	");
         request.setPassword("admin");
         
         ObjectMapper om = new ObjectMapper();
         
         MvcResult result = mvc.perform(post("/user/login")
                   .contentType(MediaType.APPLICATION_JSON_UTF8)
                   .content(om.writeValueAsString(request)))
              .andExpect(status().isAccepted())
              .andDo(print())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
              .andExpect(jsonPath("$.username", is(request.getUsername())))
              .andExpect(jsonPath("$.authorities[*].authority", hasItem("USER")))
              .andReturn()
              ;
         
         /*mvc.perform(get("/doc")
        		 .header("x-auth-token", "1")
        		 //.param("categoryCode", "1")
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
                 .content(om.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print())
            .andReturn()
            ;
         
         mvc.perform(get("/doc/detail")
        		 .header("x-auth-token", "1")
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
                 .content("{\"docIdx\":\"0000000012\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print())
            ;*/
         
         mvc.perform(post("/doc")
        		 .header("x-auth-token", "1")
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
                 .content("{\"title\":\"한글 테스트\",\"detail\":\"한글 테스트\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print())
            ;
    }

}
