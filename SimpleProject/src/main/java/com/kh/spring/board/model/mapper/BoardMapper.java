package com.kh.spring.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.reply.model.dto.ReplyDTO;

@Mapper
public interface BoardMapper {
   
   void insertBoard(BoardDTO board);
   
   // 게시판에 있는 게시글의 개수 조회
   @Select("SELECT COUNT(*) FROM TB_SPRING_BOARD WHERE STATUS ='Y'")
   int selectTotalCount();
   
   // 게시판에 있는 게시글의 정보 리스트에 담아서 가져오기
   // RowBounds를 통해 몇번째 게시물부터 몇개를 가져올지 정하면
   // DB에서 select한 후 resultset에 담은 후 선택한 것만 슬라이싱처리
   List<BoardDTO> selectBoardList(RowBounds rowBounds);
   
   // boardNO로 게시물의 정보를 조회한 후 BoardDTO객체에 담아서 반환
   BoardDTO selectBoard(int boardNo);
   
   List<ReplyDTO> selectReply(int boardNo);
   
   BoardDTO selectBoardAndReply(int board);
   
   // --------- 여기까지 1절
   
   int updateBoard(BoardDTO board);
   
   int deleteBoard(int boardNo);

}