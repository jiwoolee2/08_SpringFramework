package com.kh.spring.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.member.model.dto.MemberDTO;

@Mapper
public class MemberMapper {
	
	void signup(MemberDTO member);
	
	
	
}
