package com.khphub.doc.domain;

import java.io.Serializable;

/**
 * An CategoryVO  
 * @author keehyun2
 *
 */
public class CategoryVO implements Serializable {

	private static final long serialVersionUID = 8425185709720920427L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
