package com.kh.spring.member.model.service;

import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.exception.MemberNotFoundException;

import org.springframework.stereotype.Component;

import com.kh.spring.exception.TooLargeValueException;
import com.kh.spring.member.model.dto.MemberDTO;

@Component // 일반 클래스 bean 등록
public class MemberValidator {
	
	private void validatedLength(MemberDTO member) {
		if(member.getMemberId().length()>10) {
			throw new TooLargeValueException("아이디가 10자 이상임..");
		}
		
	}
	
	
	private void validatedValue(MemberDTO member) {
		if(member == null || 
		   member.getMemberId() == null ||
		   member.getMemberId().trim().isEmpty() ||
		   member.getMemberPw() == null ||
		   member.getMemberPw().trim().isEmpty()) {
			throw new InvalidParameterException("아이디 또는 패스워드가 존재하지 않습니다.");
		}	
	}
	

	
	public void validatedLoginMember(MemberDTO member) {
		validatedLength(member);
		validatedValue(member);
	}
	
	
	
}
