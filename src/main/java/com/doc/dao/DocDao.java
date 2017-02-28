package com.doc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.doc.domain.CategoryVO;
import com.doc.domain.DetailVO;
import com.doc.domain.DocVO;

@Repository
public class DocDao {

	private final SqlSession sqlSession;
	
	public DocDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<DocVO> readDocList(CategoryVO categoryVO){
		return this.sqlSession.selectList("readDocList", categoryVO);
	}
	
	public DetailVO readDocDetail(DocVO docVO){
		return this.sqlSession.selectOne("readDocDetail", docVO);
	}
	
	public List<CategoryVO> readCategory(CategoryVO categoryVO){
		return this.sqlSession.selectList("readCategory", categoryVO);
	}
	
	public int createDocSummary(DetailVO detailVO){
		return this.sqlSession.insert("createDocSummary", detailVO);
	}
	
	public int updateDocSummary(DetailVO detailVO){
		return this.sqlSession.update("updateDocSummary", detailVO);
	}
	
	public int deleteDocSummary(DetailVO detailVO){
		return this.sqlSession.delete("deleteDocSummary", detailVO);
	}
	
	public int createDocDetail(DetailVO detailVO){
		return this.sqlSession.insert("createDocDetail", detailVO);
	}
	
	public int updateDocDetail(DetailVO detailVO){
		return this.sqlSession.update("updateDocDetail", detailVO);
	}
	
	public int deleteDocDetail(DetailVO detailVO){
		return this.sqlSession.delete("deleteDocDetail", detailVO);
	}
	
}
