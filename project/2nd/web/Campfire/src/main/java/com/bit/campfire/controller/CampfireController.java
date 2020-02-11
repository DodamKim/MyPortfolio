package com.bit.campfire.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.campfire.dao.BoardDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CampfireController {

	@Autowired
	private BoardDao bdao;
	
	//@Scheduled(fixedRate = 1000)
	@RequestMapping("/test")
	public void test() {
		
		System.out.println("ok");
	}
	
	@RequestMapping("/index")
	public void index() {
		
	}

	@RequestMapping("/main")
	public void main() {
		
	}

	@RequestMapping("/admin")
	public void admin() {

	}

	@RequestMapping("/blog-single")
	public void blog_single() {

	}

	@RequestMapping("/blog")
	public void blog(HttpServletRequest request) {

		String image = request.getRealPath("image");
		System.out.println(image);
		
	}

	@RequestMapping("/community")
	public void community() {

	}

	@RequestMapping("/howto")
	public void howto() {

	}

	@RequestMapping("/contact")
	public void contact() {

	}

	@RequestMapping("/game")
	public void game() {

	}

	@RequestMapping("/properties-single")
	public void properties_single() {

	}

	@RequestMapping("/properties")
	public void properties() {

	}

	@RequestMapping("/ranking")
	public void ranking() {

	}

}
