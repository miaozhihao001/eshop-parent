package com.zhihao.miao.cache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * http://localhost:8081/hello?name=zhihao.miao
 */
@Controller
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(String name) {
		return "hello, " + name;
	}
	
}
