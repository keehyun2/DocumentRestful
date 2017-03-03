
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.socketmessage.doc.Application;
import com.socketmessage.doc.doc.domain.DetailVO;
import com.socketmessage.doc.doc.domain.DocVO;
import com.socketmessage.doc.doc.service.DocService;
import com.socketmessage.doc.user.domain.UserVO;
import com.socketmessage.doc.user.service.UserService;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DocServiceTest {

	@Autowired private DocService docService;
	
	@Autowired private UserService userService;
	
	private DetailVO detailVO;
	
	@Before
	public void setup(){
		detailVO = new DetailVO(); 
		detailVO.setWriter("keehyun2");
		detailVO.setTitle("제목 제목 제목 제목");
		detailVO.setDetail("문서 상세 문서 상세 문서 상세 문서 상세 문서 상세 문서 상세 ");
	}
	
	@Test
	public void createDocTest(){
		//UserVO userVO = userService.readUser("keehyun2");
		
		// 모든 문서 삭제
		detailVO = new DetailVO(); 
		for(DocVO vo : docService.readDocList(null)){
			detailVO.setDocIdx(vo.getDocIdx());
			docService.deleteDoc(detailVO);
		}
		
		// 모든 생성
//		docService.createDoc(detailVO);
		
//		docService.readDocDetail(detailVO);
		
//		DetailVO detailVO1 = docService.readDocDetail(detailVO);
//		assertThat(detailVO.getTitle(), is(detailVO1.getTitle()));
		
	}

}
