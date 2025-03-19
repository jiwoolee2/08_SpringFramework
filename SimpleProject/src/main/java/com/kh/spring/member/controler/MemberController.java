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

@Slf4j 
@Controller
@RequiredArgsConstructor // 의존성 주입 생성자 annotation
public class MemberController {
	
	/*
	 * // request요청을 받았을 때 처리할 핸들러를 붙이는 방법
	 * 
	 * @RequestMapping(value="login") public String login(HttpServletRequest
	 * request) {
	 * 
	 * System.out.println("나는 로그인 요청오면 출동함"); 
	 * String id = request.getParameter("id"); 
	 * String pw = request.getParameter("pw");
	 * log.info("id : {}, pw : {}",id,pw); return "main_page"; }
	 */
	/*
	 * @RequestMapping("login") public String login(@RequestParam(value="id") String
	 * id,
	 * 
	 * @RequestParam(value="pw") String pw) {
	 * log.info("이렇게도 넘어오나요? id:{} / pw:{}",id,pw);
	 * 
	 * return "main_page"; }
	 */
	/*
	 * @PostMapping("login") public String login(String id, String pw) {
	 * log.info("이렇게도 넘어오나요?? id : {} / pw : {}",id,pw);
	 * 
	 * MemberDTO member = new MemberDTO(); member.setMemberId(id);
	 * member.setMemberPw(pw);
	 * 
	 * return "main_page"; }
	 */
	/*
	 * 커맨드 객체 방식
	 * 
	 * 1. 매개변수 자료형에 반드시 기본 생성자가 존재할 것
	 * 2. 전달되는 키 값과 객체의 필드명이 동일할 것
	 * 3. 필드에 setter메서드가 반드시 존재할 것
	 * 
	 * 스프링에서 해당 객체를 기본생성자를 통해서 내부적으로 setter메서드를 찾아서
	 * 요청시 전달값을 해당 필드에 대입해줌
	 * (Setter Injection)
	 */

  //서비스를 직접생성하지 않고 필드로 타입만 선언해둠
  //Autowired 어노테이팅을 해두면 스프링이 자신에게 등록된 빈들 중에서 
  //선언된 타입을 찾아서 필드에 대입해줌
  // 방법 1
	@Autowired
	private MemberService memberService;

	
//	방법 2
//	@Autowired
//	public void setMemberService(MemberService memberService){
//		this.memberService = memberService;
//	}
	
 /* 방법 3
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}  */
	 
	
	/*@PostMapping("login")
	public String login(MemberDTO member, HttpSession session, Model model) {
		//log.info("이런건 안돼요~ {}",member);
		
		 데이터 가공 => 패스
		 * 요청 처리 =>
		 * 서비스 객체생성 후 메서드를 호출해서 값을 넘겨야 함.
		 * 근데 서비스 메서드가 바뀔때마다 controller도 영향이 받음.
		 * 이를 해결하기 위해 서비스를 인터페이스로 만듦(계약서)
		 * 
		 * 의존성 주입 (Dependency Injection) :
		 * 객체간의 의존성을 개발자가 직접관리하는 것이 아니라 외부에서 의존성을 주입받아서 사용
		 * -> 어노테이션을 이용해서 객체간의 의존성을 줄일 수 있음 -> 유지보수 편하게함

		 * 
		 * 응답화면 지정
		 
		System.out.println(member.getMemberId());
		System.out.println(member.getMemberPw());
		
		MemberDTO loginMember = memberService.login(member);
		
		
		if(loginMember != null) { // 로그인 성공했을 때
			// sessionScope에 로그인정보를 담아줌
			session.setAttribute("loginMember", loginMember);
			
			// view 리죨버가 접두사에 /WEB-INF/view/를 붙여주고 접미사에 .JSP를 붙여줌 => forwarding
			// return "main_page";
			
			// response.sendRedirect(request.getcontextPath()) 방식
			return "redirect:/";
			
		} else { // 로그인 실패했을 때
			// error_page
			// requestScope에 에러문구를 담아서 포워딩  reequest.setAttribute()이런식으로 했음
			// spring에서는 model객체를 이용해서 requestScope에 값을 담음
			model.addAttribute("message","로그인 실패!");
			
			// forwarding
			// /WEB-INF/views/
			// include/error_page
			// .jsp
			return "include/error_page";
		}
		return "main_page";*/
	
	// 두 번쨰 방법 ModelAndView로 돌아가기
	@PostMapping("login")
	public ModelAndView login(MemberDTO member,
							  HttpSession session,
							  ModelAndView mv) {
		
		MemberDTO loginMember = memberService.login(member);
		
		System.out.println(member.getMemberId());
		System.out.println(member.getMemberPw());
		
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("message","로그인실패!")
			  .setViewName("include/error_page");
		}
		
		return mv;
	}
	
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session,
							   ModelAndView mv) {
		
		session.removeAttribute("loginMember");
		mv.setViewName("redirect:/");
		
		return mv;
	}
	
	@GetMapping("signup-form")
	public String signupForm() {
		// /WEB-INF/views/  member/signup-form  .jsp
		return "member/signup-form";
	}
	
	
	/*
	 * 
	 */
	@PostMapping("signup")
	public String join(MemberDTO member, HttpServletRequest request) {
		
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		log.info("멤버 필드 찍어보기 : {}",member);
		memberService.signUp(member);
		return "main_page";
	}

}
