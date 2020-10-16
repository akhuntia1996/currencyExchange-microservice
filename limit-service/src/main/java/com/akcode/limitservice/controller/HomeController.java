package com.akcode.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akcode.limitservice.model.MaxMin;
import com.akcode.limitservice.model.MaxMinConf;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HomeController {
	
	@Autowired
	private MaxMinConf maxMinConf;
	
	@GetMapping("/limits")
	public MaxMin getData() {
		return new MaxMin(10,20);
	}
	
	@GetMapping("/myfault")
	@HystrixCommand(fallbackMethod="myfault")
	public Exception showFault() {
		return new RuntimeException("Here is fault");
	}
	
	public Exception myfault() {
		System.out.println(" This is Inner fault");
		return new RuntimeException("Got fault");
	}
	
	@GetMapping("/limitconf")
	public MaxMinConf getDataConf() {
		return new MaxMinConf(maxMinConf.getMax(),maxMinConf.getMin());
	}
}