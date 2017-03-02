package com.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doc.domain.CategoryVO;
import com.doc.domain.DetailVO;
import com.doc.domain.DocVO;
import com.doc.service.DocService;
import com.mappers.DocMapper;

@Service
@Transactional
public class DocServiceImpl implements DocService {
	
	@Autowired 
	private DocMapper docMapper;
	
	@Override
	public List<DocVO> readDocList(String categoryCode) {
		return docMapper.readDocList(categoryCode);
	}

	@Override
	public DetailVO readDocDetail(DocVO docVO) {
		return docMapper.readDocDetail(docVO);
	}

	@Override
	public List<CategoryVO> readCategory(CategoryVO categoryVO) {
		return docMapper.readCategory(categoryVO);
	}

	@Override
	public int createDoc(DetailVO detailVO) {
		docMapper.createDocSummary(detailVO);
		detailVO.setDocIdx(detailVO.getDocIdx());
		return docMapper.createDocDetail(detailVO);
	}

	@Override
	public int updateDoc(DetailVO detailVO) {
		docMapper.updateDocSummary(detailVO);
		return docMapper.updateDocDetail(detailVO);
	}

	@Override
	public int deleteDoc(DetailVO detailVO) {
		docMapper.deleteDocDetail(detailVO);
		return docMapper.deleteDocSummary(detailVO);
	}

}
