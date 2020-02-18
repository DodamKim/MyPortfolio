package com.port.craftMarche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarcheController {

	@RequestMapping("/test")
	public void test() {
		System.out.println("ok");
	}
	
}
