package com.socketmessage.doc.doc.domain;

import java.io.Serializable;

/**
 * An DetailVO  
 * @author keehyun2
 */
public class DetailVO extends DocVO implements Serializable {

	private static final long serialVersionUID = -6893818544621446969L;
	
	private String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
