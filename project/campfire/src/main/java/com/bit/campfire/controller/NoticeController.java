package com.bit.campfire.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.campfire.dao.NoticeDao;
import com.bit.campfire.vo.NoticeVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class NoticeController {

	@Autowired
	private NoticeDao dao;
	

	@ResponseBody
	@RequestMapping(value = "/updateNotice", method = RequestMethod.GET)
	public ModelAndView updateNoticeForm(int bno) {

		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("bno", bno);
		mav.addObject("vo", dao.getNotice(map));

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
	public String updateNotice(int bno, String title, String text) {
		// System.out.println("제목: "+title+" 내용: "+text+" 태그: "+tag);
		NoticeVo vo = new NoticeVo();
		vo.setBno(bno);
		vo.setTitle(title);
		vo.setText(text);
		int r = dao.updateNotice(vo);

		return r + "";
	}
	
	
	@RequestMapping(value = "/insertNotice", method = RequestMethod.GET)
	public void insertNoticeForm() {
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertNotice", method = RequestMethod.POST)
	public String insertNotice(String title, String text, String tag) {

		NoticeVo vo = new NoticeVo();
		vo.setTitle(title);
		vo.setText(text);
		int r = dao.insertNotice(vo);

		return r + "";
	}
	
	@ResponseBody
	@RequestMapping("/deleteNotice")
	public String deleteNotice(int bno) {
		
		HashMap map = new HashMap();
		map.put("bno", bno);
		int r = dao.deleteNotice(map);

		return r + "";
	}
	
	@RequestMapping("/noticeDetail")
	public ModelAndView noticeDetail(int bno) {

		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("bno", bno);
		mav.addObject("vo", dao.getNotice(map));

		//System.out.println(dao.getBoard(map));

		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/listNotice")
	public String listNotice(int start, int end, String search, String keyword, HttpSession session) {
		String str = "";
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = new HashMap();
		
		String s_keyword = null;
        if(session.getAttribute("keyword") != null){
            s_keyword = (String) session.getAttribute("keyword");
        }
        
        if(s_keyword != null) {
          	map.put("search", session.getAttribute("search"));
    		map.put("keyword", session.getAttribute("keyword"));
        }
        
        if(!keyword.equals("null")) {
        	session.setAttribute("search", search);
			session.setAttribute("keyword", keyword);
			
			map.put("search", search);
			map.put("keyword", keyword);
		}
        
        map.put("start", start);
		map.put("end", end);
		
		List<NoticeVo> list = dao.listNotice(map);
		
		try {
			str = mapper.writeValueAsString(list);

		} catch (Exception e) {
			System.out.println("listBoard() 예외발생 : " + e.getMessage());
		}
		
		return str;
	}
	
	@ResponseBody
	@RequestMapping("/getCnt_notice")
	public String getnoticeCnt() {

		int noticeCnt = dao.getNoticeCnt();

		return noticeCnt + "";
	}
	
	@RequestMapping("/notice")
	public void notice() {
		
	}	
}
