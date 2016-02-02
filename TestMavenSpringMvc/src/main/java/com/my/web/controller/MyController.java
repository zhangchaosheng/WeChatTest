package com.my.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	public MyController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = { "/", "/login" })
	// 用来处理前台的login请求
	private ModelAndView hello(String username, String password) {
		ModelAndView model = new ModelAndView();
		model.addObject("username", username);
		model.addObject("password", password);
		model.setViewName("hello");
		return model;
	}
}
