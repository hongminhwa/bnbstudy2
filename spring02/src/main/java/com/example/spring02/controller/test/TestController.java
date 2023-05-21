package com.example.spring02.controller.test;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring02.controller.board.BoardController;
import com.example.spring02.model.test.dto.TestDTO;
import com.example.spring02.service.test.TestService;

@Controller("test/*")   
  public class TestController {
	//로깅을 위한 변수 
	private static final Logger logger=
			LoggerFactory.getLogger(BoardController.class);
	 @Inject
	 TestService testService; 
	 
	 @RequestMapping("insert.do") 
	 public String insert(@ModelAttribute TestDTO dto, HttpSession session) throws Exception {
		  String  BOARD_WRITER=(String)session.getAttribute("userid"); 
		  dto.setBOARD_WRITER(BOARD_WRITER);
		  testService.create(dto); 
		  
		  return "redirect:/test/list.do";
		  		
		  
		 
	 }
	 
	 
	 
	
}
