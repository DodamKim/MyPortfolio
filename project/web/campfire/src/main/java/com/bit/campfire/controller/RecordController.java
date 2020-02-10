package com.bit.campfire.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.campfire.dao.RecordDao;
import com.bit.campfire.dao.TempDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RecordController {
	
	@Autowired
	RecordDao dao = new RecordDao();

	@Autowired
	TempDao tdao = new TempDao();
	
	// 플레이 타임 기록을 가져오기 위한 통신
	@ResponseBody
	@RequestMapping("/sendRecord")
	public String sendRecord(@RequestParam String totalTime, HttpSession session) {
		
//		System.out.println("*통신성공!* totalTime : " + totalTime);
//		System.out.println("collback: " + callback);
		System.out.println("totalTime: " + totalTime);
		String r = "collback({'totalTime':"+totalTime+"})";
		System.out.println(r);
		
		String mno = (String) session.getAttribute("mno");
		
		return r;
	}
	
	
	// 플레이 타임 기록을 가져오기 위한 통신 시도
//		@ResponseBody
//		@RequestMapping("/sendRecord")
//		public String sendRecord(@RequestParam String callback, @RequestParam String id) {
//			
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("id", id);
//			paramMap.put("result", "success");
//			
//			String result = null;
//			ObjectMapper mapper = new ObjectMapper();
//			try {
//	            result = mapper.writeValueAsString(paramMap);
//	        } catch (JsonGenerationException e) {
//	            e.printStackTrace();
//	            
//	        } catch (JsonMappingException e) {
//	            e.printStackTrace();
//	            
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	         
//	        System.out.println(result);
//	        return callback + "(" + result + ")";
//		}
	
	
	@ResponseBody
	@RequestMapping("/insertTempPlay")
	public String insertTempPlay() {
		
		int r = tdao.insertTempPlay();
		
		return r+"";
	}
	
	@ResponseBody
	@RequestMapping("/todayRecord")
	public String todayRecord() {
		
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(dao.todayRecord());
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return str;
		
	}
	
	@ResponseBody
	@RequestMapping("/listRecord")
	public String listRecord() {
		
//		System.out.println("today : "+today);
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(dao.listRecord());
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return str;
		
	}
	
	@RequestMapping("/record")
	public void record() {
		
	}
	
}
