package com.kh.spring.member.model.service;


import org.springframework.stereotype.Service;

import com.kh.spring.exception.DuplicatedIdException;
import com.kh.spring.exception.NullException;
import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	final MemberDAO memberDao;
	
	@Override
	public MemberDTO login(MemberDTO member) {
		return null;
	}

	@Override
	public void signup(MemberDTO member) {
		
		/* ** DB 가기 전 유효성 검증 하기 
		 * 
		 * 예외를 처리할 클래스에
		 * @ControllerAdvice 애노테이션을 하면
		 * 발생한 모든 예외가 그 클래에서 처리됨
		 * 
		 * */
					
		 
		// id,pw,name,residence 값이 입력되었는지 아닌지 검사
		if(member.getMemberId() == null || member.getMemberPw() == null ||
				member.getMemberName() == null || member.getResidence() == null ||
				member.getMemberId().trim().isEmpty() || member.getMemberPw().trim().isEmpty() ||
				member.getMemberName().trim().isEmpty() || member.getResidence().trim().isEmpty()) {
			throw new NullException("값을 모두 입력해주세요");
		}
		
		
		// id가 중복인지 아닌지 검사 : 1이면 중복, 0이면 중복아님
		if(memberDao.idCheck(member) == 1) {
			throw new DuplicatedIdException("중복된 아이디 입니다."); 
		}
		
		memberDao.signup(member);
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
