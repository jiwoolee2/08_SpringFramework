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

@Slf4j // Logger 객체를 생성하지 않고도 로그를 찍을 수 있게 해주는 애노테이션
@Service // 서비스 클래스 bean에 등록해주는 애노테이션
@RequiredArgsConstructor // final 키워드가진 생성자 자동 생성 애노테이션
public class MemberServiceImpl implements MemberService {
	private final MemberDAO memberDao;
	private final SqlSessionTemplate sqlSession;
	private final BCryptPasswordEncoder passwordEncoder;
	private final MemberValidator validator;
	/*
	 * public MemberServiceImpl(MemberDAO memberDao ,SqlSessionTemplate
	 * sqlSession) { this.memberDao = memberDao; this.sqlSession = sqlSession; }
	 */
	
	
	
	@Override
	public MemberDTO login(MemberDTO member) {
		// 제약조건 생각
		// 1. 아이디가 10자거 넘으면 안되겠네?
		// 2. 아이디/비밀번호가 빈문자열 or null이면 안되겠네?
		// 3. 둘다 통과면 정상적으로 조회할 수 있도록 해주어야겠다.
		validator.validatedLoginMember(member);
		
		// 1. Table에 아이디가 존재해야한다.
		// 2. 비밀번호가 일치해야한다.
		// 3. 둘다 통과면 정상적으로 조회할 수 있도록 해주어야겠다.
		
		/* 기존 방식
		 * SqlSession sqlSession = getSqlSession();
		 * MemberDTO loginMember = new MemberDAO().login(sqlSession, member)
		 * sqlSession.close();
		 * return loginMember;
		 */
		/* new 방식
		 * 1. dao,sqlSession 타입 선언만해둠
		 * 2. Autowired annotation
		 * 
		 */
		MemberDTO loginMember = memberDao.login(sqlSession,member);
		// 아이디만 일치해도 해의 정보를 필드에 담아옴
		
		// 1. loginMember가 null값과 동일하다면 아이디가 존재하지 않는다.
		if(loginMember == null) {
			throw new MemberNotFoundException("존재하지 않는 아이디입니다.");
		}
		
		// 2. 아이디만 가지고 조회를 하기 때문에
		// 비밀번호를 검증 후
		// 비밀번호가 유효하다면 회원의 정보를 session에 담기
		// 비밀번호가 유효하지않다면 비밀번호 이상한데??
		if(passwordEncoder.matches(member.getMemberPw(), loginMember.getMemberPw())) {
			return loginMember;
		} else {
			throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
		}
		
		
	}

	
	
	@Override
	public void signUp(MemberDTO member) {
		if(member == null || 
		   member.getMemberId() == null ||
		   member.getMemberId().trim().isEmpty() ||
		   member.getMemberPw() == null ||
		   member.getMemberPw().trim().isEmpty()) return;
		
		int result = memberDao.checkId(sqlSession, member.getMemberId());
		if(result>0) return;
		
		log.info("사용자가 입력한 비밀번호 평문 : {}",member.getMemberPw());
		// 암호화 하는법  :  .encode() 호출
		log.info("평문을 암호문으로 바꾼것 : {}",passwordEncoder.encode(member.getMemberPw()));
		
		String encPwd = passwordEncoder.encode(member.getMemberPw());
		member.setMemberPw(encPwd);
		int consequence = memberDao.signUp(sqlSession, member);
		
		if(consequence > 0) {
			return;
			
		} else {
			return;
		}
	
	}		


	
	
	
	
	@Override
	public MemberDTO update(MemberDTO member) {
		
		return null;
	}
	
	@Override
	public int delete(MemberDTO member) {
		// TODO Auto-generated method stub
		return 0;
	}


}
