package com.khphub.doc;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.khphub.doc.domain.DetailVO;
import com.khphub.doc.domain.DocVO;
import com.khphub.doc.service.DocService;

/**
 * This Controller handles all requests 
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
	 * View document list (RequestMethod.GET)
	 * @param categoryVO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<DocVO> readDocList(String categoryCode ) {
		LOGGER.info(categoryCode );
		return docService.readDocList(categoryCode);
	}
	
	/**
	 * View document details (RequestMethod.GET)
	 * @param docVO, detailVO
	 * @return
	 */
	@RequestMapping(value="/detail", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public DetailVO readDocDetail(@RequestBody DocVO docVO) {
		return docService.readDocDetail(docVO);
	}
	
	/**
	 * Create Document (RequestMethod.POST)
	 * @param detailVO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public int createDoc(@RequestBody DetailVO detailVO, Principal principal) {
		LOGGER.info(principal.getName());
		detailVO.setWriter(principal.getName());
		//SecurityContextHolder.getContext().getAuthentication().getPrincipal().
		return docService.createDoc(detailVO);
	}
	
	/**
	 * Delete Document (RequestMethod.DELETE)
	 * @param docVO, detailVO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public int deleteDoc(@RequestBody DetailVO detailVO, Principal principal) {
		
		LOGGER.info(principal.getName());
		return docService.deleteDoc(detailVO);
	}

}