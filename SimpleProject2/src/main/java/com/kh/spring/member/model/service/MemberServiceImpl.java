package com.kh.spring.member.model.service;

import java.security.InvalidParameterException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.exception.MemberNotFoundException;
import com.kh.spring.exception.PasswordNotMatchException;
import com.kh.spring.exception.TooLargeValueException;
import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@
public class MemberServiceImpl implements MemberService {
	
	final MemberDTO memberDto;
	
	@Override
	public MemberDTO login(MemberDTO member) {
		return null;
	}

	@Override
	public void signup(MemberDTO member) {
		
	}

	@Override
	public MemberDTO update(MemberDTO member) {
		return null;
	}

	@Override
	public int delete(MemberDTO member) {
		return 0;
	}
	
	


}
