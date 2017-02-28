package com.doc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.doc.domain.CategoryVO;
import com.doc.domain.DetailVO;
import com.doc.domain.DocVO;
import com.doc.service.DocService;

/**
 * This Controller handles all requests 
 * 
 * | url     | method | consumes      | produces | description               |
 * |---------|--------|---------------|----------|---------------------------|
 * | /login | POST   | JSON: id, password    | JSON     |             |
 * | /list   | POST   | JSON: uuid, list idx    | JSON     |          |
 * | /detail  | POST   | JSON: move    | JSON     |              |
 * 
 * @author keehyun2
 */

@RestController
@RequestMapping(value="/doc")
public class DocController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DocController.class);
	
	@Autowired
	private DocService docService;

	/**
	 * 문서 목록 조회 (RequestMethod.GET)
	 * @param categoryVO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<DocVO> readDocList(@RequestBody CategoryVO categoryVO) {
		return docService.readDocList(categoryVO);
	}
	
	/**
	 * 문서 상세 조회 (RequestMethod.GET)
	 * @param docVO, detailVO
	 * @return
	 */
	@RequestMapping(value="/detail", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public DetailVO readDocDetail(@RequestBody DocVO docVO) {
		return docService.readDocDetail(docVO);
	}
	
	/**
	 * 문서 생성 (RequestMethod.POST)
	 * @param docVO, detailVO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public int createDoc(@RequestBody DetailVO detailVO) {
		return docService.createDoc(detailVO);
	}
	
	/**
	 * 문서 삭제 (RequestMethod.DELETE)
	 * @param docVO, detailVO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public int deleteDoc(@RequestBody DetailVO detailVO) {
		return docService.deleteDoc(detailVO);
	}

}