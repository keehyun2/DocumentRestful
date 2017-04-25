
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.khphub.Application;
import com.khphub.doc.domain.DetailVO;
import com.khphub.mappers.DocMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DocMapperTest {

	@Autowired 
	private SqlSession sqlSession;
	
	private DocMapper docMapper;
	
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
		
		docMapper = sqlSession.getMapper(DocMapper.class);
		
		docMapper.readDocList(null);
		
		//sqlSession.selectList("com.doc.mappreadDocList");
		
		
	}

}
