package com.kh.spring.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.homework.model.service.HomeworkService;

import lombok.RequiredArgsConstructor;

@RestController 
@CrossOrigin("*")
@RequestMapping(value = "matGyp", produces="application/json; charset=UTF-8")
@RequiredArgsConstructor
public class HomeworkController {
	
	final HomeworkService homeworkService;
	
	@GetMapping
	public ResponseEntity<String> getMatGyp(@RequestParam(name="pageNo" ,defaultValue="1") int pageNo){
		
		String returnData = homeworkService.getMatGyp(pageNo);
		
		return ResponseEntity.ok(returnData);
	}
	
	
	
}
