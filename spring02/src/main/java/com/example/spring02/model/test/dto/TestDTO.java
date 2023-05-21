package com.example.spring02.model.test.dto;

import java.util.Date;

public class TestDTO {
	  private int BOARD_NUM; 
	  private String BOARD_WRITER;
	 
	   private String BOARD_TITLE;

	   private String BOARD_CONTENT;

	   private Date BOARD_DATE;

	public int getBOARD_NUM() {
		return BOARD_NUM;
	}

	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}

	public String getBOARD_WRITER() {
		return BOARD_WRITER;
	}

	public void setBOARD_WRITER(String bOARD_WRITER) {
		BOARD_WRITER = bOARD_WRITER;
	}

	public String getBOARD_TITLE() {
		return BOARD_TITLE;
	}

	public void setBOARD_TITLE(String bOARD_TITLE) {
		BOARD_TITLE = bOARD_TITLE;
	}

	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}

	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}

	public Date getBOARD_DATE() {
		return BOARD_DATE;
	}

	public void setBOARD_DATE(Date bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}

	@Override
	public String toString() {
		return "TestDTO [BOARD_NUM=" + BOARD_NUM + ", BOARD_WRITER=" + BOARD_WRITER + ", BOARD_TITLE=" + BOARD_TITLE
				+ ", BOARD_CONTENT=" + BOARD_CONTENT + ", BOARD_DATE=" + BOARD_DATE + "]";
	}

	   

}
