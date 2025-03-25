package com.kh.spring.exception;

public class NullException extends RuntimeException{
	
	// 매개변수 있는 생성자
	public NullException(String message) {
		super(message);		
	}
	
}
