package com.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.doc.domain.CategoryVO;
import com.doc.domain.DetailVO;
import com.doc.domain.DocVO;

/**
 * @author keehyun2
 */
@Mapper
public interface DocMapper {

	List<DocVO> readDocList(CategoryVO categoryVO);
	
	public DetailVO readDocDetail(DocVO docVO);
	
	public List<CategoryVO> readCategory(CategoryVO categoryVO);
	
	public int createDocSummary(DetailVO detailVO);
	
	public int updateDocSummary(DetailVO detailVO);
	
	public int deleteDocSummary(DetailVO detailVO);
	
	public int createDocDetail(DetailVO detailVO);
	
	public int updateDocDetail(DetailVO detailVO);
	
	public int deleteDocDetail(DetailVO detailVO);
	
}
