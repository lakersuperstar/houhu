package com.houhucun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class MainIndexController {
	
	@RequestMapping("")
	public String index() {
		return "/common/index";
	}
}
