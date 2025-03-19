package com.kh.spring.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.exception.MemberNotFoundException;
import com.kh.spring.exception.PasswordNotMatchException;
import com.kh.spring.exception.TooLargeValueException;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log를 간편하게 조회
@ControllerAdvice // 발생한 예외가 여기로 옴
public class ExceptionHandlingController {
	
	private ModelAndView createErrorResponse(String errorMsg, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",e.getMessage())
		  .setViewName("include/error_page");
		log.info("발생 예외 : {}",errorMsg,e);
		return mv;
	}
	
	@ExceptionHandler(InvalidParameterException.class) // 특정 예외 처리
	protected ModelAndView InvalidParameterError(InvalidParameterException e) {
		return createErrorResponse(e.getMessage(),e);
	}
		
	
	
	@ExceptionHandler
	(TooLargeValueException.class)
	protected ModelAndView tooLargeValueError(TooLargeValueException e) {
		return createErrorResponse(e.getMessage(),e);
	}
	
	
	@ExceptionHandler
	(MemberNotFoundException.class)
	protected ModelAndView tooLargeValueError(MemberNotFoundException e) {
		return createErrorResponse(e.getMessage(),e);
	}
	
	
	@ExceptionHandler
	(PasswordNotMatchException.class)
	protected ModelAndView tooLargeValueError(PasswordNotMatchException e) {
		return createErrorResponse(e.getMessage(),e);
	}
	
}
