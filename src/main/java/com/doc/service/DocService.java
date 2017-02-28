package com.doc.service;

import java.util.List;

import com.doc.domain.CategoryVO;
import com.doc.domain.DetailVO;
import com.doc.domain.DocVO;

public interface DocService {
	
	/**
	 * 문서 목록
	 * @param categoryVO
	 * @return
	 */
	public List<DocVO> readDocList(CategoryVO categoryVO);
	
	/**
	 * 문서 상세
	 * @param docVO
	 * @return
	 */
	public DetailVO readDocDetail(DocVO docVO);
	
	/**
	 * 분류 목록
	 * @param categoryVO
	 * @return
	 */
	public List<CategoryVO> readCategory(CategoryVO categoryVO);
	
	/**
	 * 문서 생성
	 * @param detailVO
	 * @return
	 */
	public int createDoc(DetailVO detailVO);
	
	/**
	 * 문서 수정
	 * @param docVO
	 * @return
	 */
	public int updateDoc(DetailVO detailVO);
	
	/**
	 * 문서 삭제
	 * @param docVO
	 * @return
	 */
	public int deleteDoc(DetailVO detailVO);
	
}
