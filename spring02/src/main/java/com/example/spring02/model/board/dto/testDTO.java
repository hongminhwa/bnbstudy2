package com.example.spring02.model.board.dto;

import java.util.Date;

public class testDTO {  
	   private int SeQ; 
	   private String writer; 
	   private String title; 
	   private String content; 
	   private Date date;
	
	   public testDTO() {//기본 생성자 
	
	}
	 
	   //파라미터 5개 생성자  
	public testDTO(int seQ, String writer, String title, String content, Date date) {

		SeQ = seQ;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.date = date;
	
	}

	//getter, setter
	public int getSeQ() {
		return SeQ;
	}

	public void setSeQ(int seQ) {
		SeQ = seQ;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	   
	  

}
