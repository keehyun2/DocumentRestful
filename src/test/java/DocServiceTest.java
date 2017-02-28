
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.Application;
import com.doc.domain.CategoryVO;
import com.doc.domain.DetailVO;
import com.doc.domain.DocVO;
import com.doc.service.DocService;
import com.user.domain.UserVO;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DocServiceTest {

	@Autowired private DocService docService;
	
	private DetailVO detailVO;
	
	@Before
	public void setup(){
		detailVO = new DetailVO(); 
		detailVO.setWriter("keehyun2");
		detailVO.setTitle("제목 제목 제목 제목");
		detailVO.setViewCount(0);
		detailVO.setDetail("문서 상세 문서 상세 문서 상세 문서 상세 문서 상세 문서 상세 ");
	}
	
	@Test
	public void createDocTest(){
		
		docService.readDocList(new CategoryVO());
		
//		docService.createDoc(detailVO);
		
//		docService.readDocDetail(detailVO);
		
//		DetailVO detailVO1 = docService.readDocDetail(detailVO);
//		assertThat(detailVO.getTitle(), is(detailVO1.getTitle()));
		
	}

}
