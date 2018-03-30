package com.example.txzq.bookservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
@RefreshScope
public class HelloController {
	
	@Value("${message}")
	private String message;
	
	@RequestMapping("/hello")
	public String sayHello() 
	{
		return message;
	}

}
