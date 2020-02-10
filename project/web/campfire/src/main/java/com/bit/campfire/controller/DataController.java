package com.bit.campfire.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.campfire.dao.DataDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DataController {
	
	@Autowired
	DataDao dao;
		
	@ResponseBody
	@RequestMapping("/dataList")
	public String dataList(String start) {
		String str="";
		HashMap map = new HashMap();
		map.put("start",start);
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(dao.dataList(map));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return str;	
	}
	@ResponseBody
	@RequestMapping("/getTotalPlay")
	public int getTotalPlay() {
		int re = dao.getTotalPlay();
		return re;		
	}
	
	@ResponseBody
	@RequestMapping("/getTotalVisit")
	public int getTotalVisit() {
		int re = dao.getTotalVisit();
		return re;		
	}
}
