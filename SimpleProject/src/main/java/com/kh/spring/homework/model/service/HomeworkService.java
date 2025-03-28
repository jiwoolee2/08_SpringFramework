package com.kh.spring.homework.model.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HomeworkService {

	public String getMatGyp(int pageNo) {
		
		StringBuilder sb = new StringBuilder();
		
		String url = "https://openapi.gg.go.kr/PlaceThatDoATasteyFoodSt";
		
		String KEY = "65cc69f1605e421194814830d5df9502";
		
		// 페이지 위치
		Integer pIndex = pageNo;
		
		// 페이지당 보이는 개수
		Integer pSize = 10;
		
		String Type = "json";
		
		sb.append(url);
		sb.append("?KEY="+KEY);
		sb.append("&pIndex="+pIndex);
		sb.append("&pSize="+pSize);
		sb.append("&Type="+Type);
		
		System.out.println(sb);
		URI uri = null;
		
		try {
			uri = new URI(sb.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
		
		String returnData = new RestTemplate().getForObject(uri, String.class);
		log.info(returnData);
		
		return returnData;
	}
}
