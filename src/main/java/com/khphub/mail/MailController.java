package com.khphub.mail;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Controller handles all requests 
 * 
 * | url     | method | consumes      | produces | description               |
 * |---------|--------|---------------|----------|---------------------------|
 * | /mail    | POST   | JSON: mail list    | JSON     |       |
 * 
 * @author keehyun2
 */

@RestController
@RequestMapping(value="/mail")
public class MailController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MailController.class);
	
	/**
	 * 메일 목록을 받아서 각 메일로 고정된 문구를 전송
	 * @param loginVO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public boolean mail(@RequestBody String[] arrMail) {
		LOGGER.info(Arrays.toString(arrMail));
		return true;
	}
	
}