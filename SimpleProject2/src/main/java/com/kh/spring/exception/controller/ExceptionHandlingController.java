package com.kh.spring.exception.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.exception.DuplicatedIdException;
import com.kh.spring.exception.NullException;


import lombok.extern.slf4j.Slf4j;

@Slf4j // log를 간편하게 조회
@ControllerAdvice // 발생한 예외가 여기로 옴
public class ExceptionHandlingController {
	
	


	/*
	 * 에러 메세지와 예외를 인자로 받아
	 * ModelAndView에 에러머세지를 담고, 포워딩할 경로 설정
	 */
	private ModelAndView createErrorResponse(Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",e.getMessage())
		  .setViewName("member/signup-form");
		return mv;
	}
	
	// @ExceptionalHandler : 특정 에외를 처리
	// 중복된 아이디로 인한 예외
	@ExceptionHandler(DuplicatedIdException.class) 
	protected ModelAndView DuplicatedIdExceptionError(DuplicatedIdException e) {
		return createErrorResponse(e);
	}
		
	
	// 회원가입 시 id, pw, name, residence 중 하나라도 미입력으로 인한 예외
	@ExceptionHandler
	(NullException.class)
	protected ModelAndView NullExceptionError(NullException e) {
		return createErrorResponse(e);
	}
	
	
	
	
	
}
