package com.kh.spring.member.controler;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	

	@GetMapping("log-in")
	public ModelAndView login() {
		
	}
	
	public ModelAndView logout() {
		
	}
	
	@GetMapping("sign-up-form")
	public String signupForm() {
		return "/member/signup-form";
	}
	
	@GetMapping("sign-up")
	public String signupForm() {
		return "/member/signup-form";
	}
	
	
	public String join() {
		
	}

}
