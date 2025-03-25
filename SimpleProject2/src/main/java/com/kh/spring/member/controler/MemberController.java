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
	
	final MemberService memberService;
	
	@GetMapping("log-in")
	public ModelAndView login() {
		return null;
	}
	
	public ModelAndView logout() {
		return null;
	}
	
	@GetMapping("signup-form")
	public String signupForm() {
		return "member/signup-form";
	}

	/* 회원가입
	 * 성공시 -> alert메세지 띄우기
	 * 실패시 -> 예외처리
	 */
	@PostMapping("sign-up")
	public String signup(MemberDTO member) {
		
		
		memberService.signup(member);
		
		return "redirect:/";
	}
	
	
	public String join() {
		return null;
	}

}
