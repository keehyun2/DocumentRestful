package com.khphub.doc.domain;

import java.io.Serializable;

/**
 * An DocVO  
 * @author keehyun2
 */
public class DocVO implements Serializable {

	private static final long serialVersionUID = -4685277182525147530L;
	
	private String docIdx;
	private String title;
	private String writer;
	private String writeDate;
	private int viewCount;
	private String category;
	
	public String getDocIdx() {
		return docIdx;
	}
	public void setDocIdx(String docIdx) {
		this.docIdx = docIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
