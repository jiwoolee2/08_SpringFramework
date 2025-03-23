package com.kh.spring.util.template;

import com.kh.spring.util.model.dto.PageInfo;


public class Pagination {
   
   // 페이징 처리에 필요한 정보를 계산해서 리턴하는 메서드
   public static PageInfo getPageInfo(int count,  // 전체 게시글 수
                              int currentPage, // 현재 페이지 번호
                              int boardLimit, // 한 페이지에 보여줄 게시글 수
                              int pageLimit) { // 하단에 표시할 페이지 수
      
      int maxPage = (int)Math.ceil((double)count/boardLimit);
      int startPage = (currentPage-1)/pageLimit*pageLimit+1;
      int endPage = startPage + pageLimit -1;
      
      if(endPage > maxPage) {endPage = maxPage;}
      
      //new PageInfo(count,currentPage,boardLimit..)
      return PageInfo.builder().boardLimit(boardLimit)  // 한 페이지당 게시글 수
                         .count(count)           // 전체 게시글 수
                         .currentPage(currentPage)// 현재 페이지 번호
                         .startPage(startPage)    // 하단 페이지 시작 번호
                         .endPage(endPage)         // 하단 페이지 끝 번호
                         .maxPage(maxPage)        // 전체 페이지 수
                         .pageLimit(pageLimit)     // 페이지 하단에 보여줄 페이지 수
                         .build();
   }
}
