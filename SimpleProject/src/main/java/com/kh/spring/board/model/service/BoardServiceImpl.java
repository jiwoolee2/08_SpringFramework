package com.kh.spring.board.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.mapper.BoardMapper;
import com.kh.spring.exception.AuthenticationException;
import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.reply.model.dto.ReplyDTO;
import com.kh.spring.util.model.dto.PageInfo;
import com.kh.spring.util.template.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
 
	private final BoardMapper boardMapper;
	
	@Override
	public void insertBoard(BoardDTO board, MultipartFile upfile, HttpSession session) {
		
		// 1. 권한 체크
		MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
		if(loginMember != null && !loginMember.getMemberId().equals(board.getBoardWriter())) {
			throw new AuthenticationException("권한 없는 접근입니다.");
		}
		log.info("게시글 제목: {}", board.getBoardTitle());
		log.info("게시글 내용: {}", board.getBoardContent());
		log.info("작성자: {}", board.getBoardWriter());
		// 2. 유효성 검사
		if(board.getBoardTitle() == null || board.getBoardTitle().trim().isEmpty() ||
		   board.getBoardContent() == null || board.getBoardContent().trim().isEmpty() ||
		   board.getBoardWriter() == null || board.getBoardWriter().trim().isEmpty()) {
			throw new InvalidParameterException("유효하지 않은 요청입니다.");
		}
		
		// 2_2)
		
		// 3) 파일유무체크 // 이름바꾸기 + 저장
		if(!upfile.getOriginalFilename().isEmpty()) {
			
			// 이름바꾸기
			// KH_현재시간+원본파일확장자
			
			StringBuilder sb = new StringBuilder();
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					log.info("현재시간 : {}", currentTime);
			int random = (int)(Math.random()*900) + 100;
			String ext = upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf("."));
			sb.append("KH_");
			sb.append(currentTime);
			sb.append("_");
			sb.append(random);
			sb.append(ext);
			
			log.info("바뀐 파일명 : {}", sb.toString());
			
			// applicationScope(전체에서 사용가능한 저장공간)를 반환 
			ServletContext application = session.getServletContext();
			
			// 저장할 경로
			String savePath = application.getRealPath("/resources/upload_files/");
			
			try {
				upfile.transferTo(new File(savePath+sb.toString()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			board.setChangeName("/spring/resources/upload_files/"+sb.toString());
		}
		
		boardMapper.insertBoard(board);
		

	}

	@Override
	public Map<String, Object> selectBoardList(int currentPage) {
		List<BoardDTO> boards = new ArrayList();
		
		
		// 게시글 개수가 없을 경우 DB에 안가고 바로 controller로 예외발생x 로 돌아감
		int count = boardMapper.selectTotalCount();
		PageInfo pi = Pagination.getPageInfo(count, currentPage, 5 ,5);
		
		if(count != 0) {
			RowBounds rb = new RowBounds((currentPage -1)*5,5);
			boards = boardMapper.selectBoardList(rb);
		}
		
		
		// map에다가 게시글이 있다면 게시글을 리스트에 담고, 페이지에대한 정보를 같이 담아서 리턴
		Map<String, Object> map = new HashMap();
		
		map.put("boards", boards);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public BoardDTO selectBoard(int boardNo) {
		
		// 1절
		//BoardDTO board = boardMapper.selectBoard(boardNo);
		
		/* 게시글이 존재하지 않으면 예외 발생*/ 
		//if(board == null) {
			//throw new InvalidParameterException("존재하지 않는 게시글입니다.");
		
		// 2절
		//List<ReplyDTO> replyList = boardMapper.selectReply(boardNo);
		//board.setReplyList(replyList);
		
		
		
		
		//3절
	BoardDTO board = boardMapper.selectBoardAndReply(boardNo);
	if(board == null) {
		throw new InvalidParameterException("존재하지않는게시글입니다.");
		
	}	
		
	
		return board;
	}

	@Override
	public BoardDTO updateBoard(BoardDTO board, MultipartFile file) {
		return null;
	}

	@Override
	public void deleteBoard(int boardNo) {

	}

}