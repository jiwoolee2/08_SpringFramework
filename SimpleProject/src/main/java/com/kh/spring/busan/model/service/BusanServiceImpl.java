package com.kh.spring.busan.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.busan.model.dto.Comment;
import com.kh.spring.busan.model.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService {
	
	private final CommentMapper mapper;
	
	private String apiRequest(String uriPath) {
		
		URI uri = null;
		
		try {
			uri = new URI(uriPath);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// uri, 반환받고싶은 타입을 인자로 넣어서 응답
		String apiResponseData = new RestTemplate().getForObject(uri,String.class);
		return apiResponseData;
	}
	
	final String SERVICE_KEY = "OCnstM4H6ClUbeCi0hdmBjh24csJdLleI4N7Ye0ad%2BMpRUG6bYNkeZ%2BfIRsoOnlujiKgXoRQXPZNNFr5pEd%2BLw%3D%3D&";
	
	
	
	
	
	@Override
	public String requestGetBusan(int pageNo) {
		
		/*
		 * StringBuilder는 쓰레드세이프기능없음 . 근데 왜씀?
		 * tomcat이 쓰레드 세이프기능 지원해줌
		 */
		if(pageNo<1) {pageNo=1;}
		StringBuilder sb = new StringBuilder();
			
		sb.append("http://apis.data.go.kr/6260000/FoodService/getFoodKr");
		sb.append("?serviceKey="+SERVICE_KEY);
		sb.append("&pageNo="+pageNo);
		sb.append("&numOfRows=9");
		sb.append("&&resultType=json");
		//log.info("API 서버에서 응답온거 : {}",apiResponseDate);
		return apiRequest(sb.toString());
	}

	
	
	@Override
	public String requestGetBusanDetail(int pk) {
		
		if(pk < 1 ) return null;
		StringBuilder sb = new StringBuilder();
		sb.append("http://apis.data.go.kr/6260000/FoodService/getFoodKr");
		sb.append("?serviceKey="+SERVICE_KEY);
		sb.append("&pageNo=1");
		sb.append("&numOfRows=1");
		sb.append("&resultType=json");
		sb.append("&UC_SEQ="+pk);
		
		return apiRequest(sb.toString());
	}



	@Override
	public void saveComment(Comment comment) {
		mapper.saveComment(comment);
	}



	@Override
	public List<Comment> selectCommentList(Long seq) {
		return mapper.selectCommentList(seq);
	}
	
	

}



















