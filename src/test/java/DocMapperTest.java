
import org.apache.ibatis.session.SqlSession;
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
import com.mappers.DocMapper;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
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
		
		docMapper.readDocList(new CategoryVO());
		
		//sqlSession.selectList("com.doc.mappreadDocList");
		
		
	}

}
