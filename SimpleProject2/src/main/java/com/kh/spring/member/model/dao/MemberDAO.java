package com.kh.spring.member.model.dao;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.kh.spring.member.model.dto.MemberDTO;

@Mapper
public interface MemberDAO {
	
	// 회원가입
	@Insert("INSERT INTO UNIKLO_MEMBER VALUES(#{memberId}, #{memberPw}, #{memberName}, #{memberEmail}, #{residence}, SYSDATE)")
	void signup(MemberDTO member);
	
	
	// 아이디 중복 검사 -> 중복이면 1, 중복아니면 0 반환
	@Select("SELECT COUNT(*) FROM UNIKLO_MEMBER WHERE MEMBER_ID = #{memberId}")
	int idCheck(MemberDTO member);
}
