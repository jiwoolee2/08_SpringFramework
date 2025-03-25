package com.kh.spring.board.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
   
   private void validateUser(HttpSession session, BoardDTO board) {
   // 1. 권한 체크
      MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
      if(loginMember != null && !loginMember.getMemberId().equals(board.getBoardWriter())) {
         throw new AuthenticationException("권한 없는 접근입니다.");
      }
   }
   
   
   private void validateContent(BoardDTO board) {
	// 2. 유효성 검사
	      if(board.getBoardTitle() == null || board.getBoardTitle().trim().isEmpty() ||
	         board.getBoardContent() == null || board.getBoardContent().trim().isEmpty() ||
	         board.getBoardWriter() == null || board.getBoardWriter().trim().isEmpty()) {
	         throw new InvalidParameterException("유효하지 않은 요청입니다.");
	      }
	      
	      // 2_2)
	      /*  요런식으로 바꿔줘야함
	       * <  &lt 
	       * > 	&gt
	       * \
	       * &
	       */
	      String boardTitle = board.getBoardTitle()
	    		  				   .replaceAll("<", "&lt;")		   
	    		  				   .replaceAll(">", "&gt;")
	      						   .replaceAll("\n", "<br>");
	      
	      String boardContent = board.getBoardContent()
	    		  				     .replaceAll("<", "&lt;")
					   				 .replaceAll(">", "&gt;")
	      							 .replaceAll("\n", "<br>");
	      board.setBoardTitle(boardTitle);
	      board.setBoardContent(boardContent);
	      
   }
   
   private void transferFile(HttpSession session, MultipartFile file, BoardDTO board) {
	// 3) 파일유무체크 // 이름바꾸기 + 저장
	      if(!file.getOriginalFilename().isEmpty()) {
	         
	         // 이름바꾸기
	         // KH_현재시간+랜덤숫자+원본파일확장자
	         StringBuilder sb = new StringBuilder();
	         sb.append("KH_");
	         String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	         // log.info("현재시간 : {}", currentTime);
	         sb.append(currentTime);
	         sb.append("_");
	         int random = (int)(Math.random() * 900) + 100;
	         sb.append(random);
	         String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	         sb.append(ext);
	         // log.info("바뀐 파일명 : {}", sb.toString());
	         
	         ServletContext application = session.getServletContext();
	         
	         String savePath = application.getRealPath("/resources/upload_files/");
	         
	         try {
	            file.transferTo(new File(savePath + sb.toString()));
	         } catch (IllegalStateException | IOException e) {
	            e.printStackTrace();
	         }
	         board.setChangeName("/spring/resources/upload_files/" + sb.toString());
	      }
   }
   
   @Override
   public void insertBoard(BoardDTO board, MultipartFile file, HttpSession session) {
      
	   validateUser(session, board);
	   validateContent(board);
      
	   if(!file.getOriginalFilename().isEmpty()) {
		   transferFile(session,file,board);
	   }
      boardMapper.insertBoard(board);
   }

   
   // 게시판에서 페이지 정보와 한 페이지에 나오는 게시글의 정보를 반환하는 메서드
   @Override
   public Map<String, Object> selectBoardList(int currentPage) {
      
      List<BoardDTO> boards = new ArrayList();
      Map<String, Object> map = new HashMap();
      
      // 게시판에 있는 게시글의 게수 조회
      int count = boardMapper.selectTotalCount();
      
      // getPageInfo : 페이징 처리에 필요한 정보를 계산해서 리턴하는 메서드
      // 한페이지에 5개씪 보여주고, 하단에 보여지는 페이지 수는 5개로 설정
      PageInfo pi = Pagination.getPageInfo(count, currentPage, 5, 5);
      
      if(count != 0) {
         /* RowBounds(offset,limit) : MyBatis에서 메모리 기반 페이징 처리를 위한 도구
          * offset : 건너뛸 게시글 수 , limit : 보여줄 게시글 수
          * => 하단에 페이지 3을 누르면 앞에 1,2페이지의 게시글을 건너뛰고 
          *      3페이지의 게시글부터 보여주기 위해서 사용
          * 
          */
         RowBounds rb = new RowBounds((currentPage - 1) * 5, 5);
         
         // 현재 페이지가 3이라면 10개의 게시물을 건너뛰고 11번째 게시글부터 5개를 가져옴
         // 5개 게시물의 정보를 boards list에 담음
         boards = boardMapper.selectBoardList(rb);
      }
      
      map.put("boards", boards);
      map.put("pageInfo", pi);
      
      return map;
   }

   @Override
   public BoardDTO selectBoard(int boardNo) {
      
      // 1절
      //BoardDTO board = boardMapper.selectBoard(boardNo);
      
      // 2절
      //List<ReplyDTO> replyList = boardMapper.selectReply(boardNo);
      //board.setReplyList(replyList);
      // 3절
      
      // boardNO로 게시물의 정보를 조회한 후 BoardDTO객체에 담아서 반환
      BoardDTO board = boardMapper.selectBoardAndReply(boardNo);
      if(board == null) {
         throw new InvalidParameterException("존재하지 않는 게시글입니다.");
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
   
   
   // 게시글 검색 기능
   public Map<String, Object> doSearch(Map<String, String> map){
	 
	   // 유효성 검사 했다 침. map에서 get("condition") / get("keyword")
	   // 값이 비었나 안비었나 확인
	   
	   // 페이징 처리 -> 조회결과에 맞는 게시물의 총 개수를 구해야함. 그래야 몇페이지인지 알수 있음
	   // 조회결과에 따라 몇개의 게시물을 찾았는지 확인하는 메서드 수행 
	   int searchedCount = boardMapper.searchedCount(map);
	   log.info("몇갠데 : {}",searchedCount);
	   
	   // getPageInfo : 페이징 처리에 필요한 정보를 계산해서 리턴하는 메서드
	   // 한페이지에 3개씪 보여주고, 하단에 보여지는 페이지 수는 3개로 설정
	   PageInfo pi = Pagination.getPageInfo(searchedCount, 
			   								Integer.parseInt(map.get("currentPage")),
			   								3,
			   								3);
	   
	   RowBounds rb = new RowBounds((pi.getCurrentPage()-1)*3 ,3);
	   
	   List<BoardDTO> boards = boardMapper.selectSearchList(map,rb);
	   log.info("boards : {}",boards);
	   
	   Map<String, Object> returnValue = new HashMap();
	   returnValue.put("boards", boards);
	   returnValue.put("pageInfo", pi);
	   return returnValue;  
	 
   }
   
   
   // 댓글 추가 기능
   public int insertReply(ReplyDTO reply, HttpSession session) {
	   
	   String memberId = ((MemberDTO)session.getAttribute("loginMember")).getMemberId();
	   
	   reply.setReplyWriter(memberId);
	   
	   //SEQ_RNO
	   // 댓글 추가
	   return boardMapper.insertReply(reply);
   }

}