package com.example.spring02.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.admin.AdminService;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("admin/*") //공통 url mapping
public class AdminController {
	@Inject
	AdminService adminService;

	@Inject 
	ProductService productService; 
	
	@RequestMapping("login.do")//세부 url
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, HttpSession session, 
			ModelAndView mav) {
		String name=adminService.loginCheck(dto);//로그인 체크
		if(name != null) {//로그인 성공
			//관리자용 세션변수
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			//일반 사용자용 세션변수
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("admin/admin");
		}else {
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		
		return mav;
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();//세션 초기화
		//관리자 로그인 페이지로 이동
		return "redirect:/admin/login.do";
	}
	@RequestMapping("write.do")
	  public String write( ) {
		return "admin/admin_product_write";
	}
	@RequestMapping("list.do")
	   public ModelAndView list(ModelAndView mav) {
			mav.setViewName("/admin/admin_product_list");
			mav.addObject("list", productService.listProduct()); 
			return mav; 
		
	}
	
	
	
}
