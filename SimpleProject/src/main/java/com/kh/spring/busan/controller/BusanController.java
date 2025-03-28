package com.kh.spring.busan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.busan.model.dto.Comment;
import com.kh.spring.busan.model.service.BusanService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
// @Controller + @ResponseBody를 합친 것으로 모든 메서드가 JSON이나 String 같은 데이터 자체를 응답함
@CrossOrigin("*") // 리액트(프론트엔드)가 다른 포트(예: localhost:5173)에서 요청해도 거부하지 않고 받아줌
@RequestMapping(value="busans",produces="application/json; charset=UTF-8") //이 컨트롤러가 처리할 기본 URL 경로를 정해줌
@RequiredArgsConstructor
public class BusanController {

	private final BusanService busanService;
	{
		
	}
	@GetMapping
	public ResponseEntity<String> getBusanFoods(@RequestParam(name="pageNo",defaultValue="1")int pageNo) {
		
		log.info("page number : {}",pageNo);
		
		String responseData = busanService.requestGetBusan(pageNo);
		// 응답이 잘 갔는지 아닌지 앞단에서 확인할 수 있도록 사용
		return ResponseEntity.ok(responseData);
	}
	
	// 2절하기
	// 요청 url이 /busans/abc -> @GetMapping("/ddd")
	//		    /busans/ddd -> @GetMapping("/ddd")
	// @PathVariable : Mapping으로 넘어온 값을 매개변수로 쓸 때 사용
	@GetMapping("/{id}")  // /busans/임의의숫자
	public ResponseEntity<String> getBusanDetail(@PathVariable(name="id") int id){
		log.info("식당 번호 : {}",id);
		String response = busanService.requestGetBusanDetail(id);
		return ResponseEntity.ok(response); // builderpattern이용해서 상태코드 200으로 전달??
	}
	
	
	// 3절하기 식당에 댓글달기 및 조회
	// @RequestBody : 요청시 넘어오는 값들을 comment에 담겠다는 말!!
	@PostMapping("/comments")
	public ResponseEntity<?> save(@RequestBody Comment comment){
		log.info("cooment={}",comment);
		busanService.saveComment(comment);
		return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<List<Comment>>  getComments(@PathVariable(name="id") Long id){
		List<Comment> comments = busanService.selectCommentList(id);
		return ResponseEntity.ok(comments);
	}
	
	
	
	
	
	
	
	
}
