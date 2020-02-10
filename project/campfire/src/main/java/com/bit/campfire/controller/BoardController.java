package com.bit.campfire.controller;

import java.util.HashMap;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.campfire.dao.BoardDao;
import com.bit.campfire.vo.BoardVo;
import com.bit.campfire.vo.CommentsVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardDao bdao;
	
	@ResponseBody
	@RequestMapping("/resetReport")
	public String resetReport(int bno) {
		
		HashMap map = new HashMap();
		map.put("bno", bno);
		int r = bdao.resetReport(map);
		
		return r+"";
	}
	
	@ResponseBody
	@RequestMapping("/listAdminBoard")
	public String listAdminBoard() {
		
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(bdao.listAdminBoard());
					
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return str;
	}
	
	
	@ResponseBody
	@RequestMapping("/updateRP")
	public String updateRP(int bno) {
		
		System.out.println("updateRP : "+bno);
		HashMap map = new HashMap();
		map.put("bno", bno);
		
		int r = bdao.updateRP(map);
		int rp = 0;
		
		if(r==1) {
			rp = bdao.getBoard(map).getReport();
		}
		
		return rp+"";
	}
	
	@ResponseBody
	@RequestMapping("/updateDC")
	public String updateDC(int bno) {
		
		System.out.println("updateDC : "+bno);
		HashMap map = new HashMap();
		map.put("bno", bno);
		
		int r = bdao.updateDC(map);
		int dc = 0;
		
		if(r==1) {
			dc = bdao.getBoard(map).getDc();
		}
		
		return dc+"";
	}
	
	@ResponseBody
	@RequestMapping("/updateRC")
	public String updateRC(int bno) {
		
		System.out.println("updateRC : "+bno);
		HashMap map = new HashMap();
		map.put("bno", bno);
		

		int r = bdao.updateRC(map);
		int rc = 0;
		
		if(r==1) {
			rc = bdao.getBoard(map).getRc();
		}
		
		return rc+"";
	}
	
	@ResponseBody
	@RequestMapping("/getCommentNum")
	public String getCommentNum(int bno) {
		
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = new HashMap();
		map.put("bno", bno);
		try {
			str = mapper.writeValueAsString(bdao.getCommentNum(map));
					
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return str;
	}
	
	@ResponseBody
	@RequestMapping("/listComments")
	public String listComments(int bno) {
		
		System.out.println("listComments의 bno :" + bno);
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = new HashMap();
		map.put("bno", bno);
		try {
			str = mapper.writeValueAsString(bdao.listComments(map));
			System.out.println("listComments의 list :" +bdao.listComments(map));
			
		}catch (Exception e) {
			System.out.println("listComments : "+e.getMessage());
		}
		
		return str;
	}
	
	@ResponseBody
	@RequestMapping("/insertComment")
	public String insertComment(CommentsVo vo, HttpSession session) {
		
		int cno = bdao.getCno();
		int bno = vo.getBno();
		int c_ref = cno;
		int c_level = 0;
		int c_step = 0;
		String nickname = vo.getNickname();
		String ctext = vo.getCtext();
		
		HashMap map1 = new HashMap();
		
		System.out.println("cno:"+ cno);
		System.out.println("bno:"+ bno);
		System.out.println("nickname:"+ nickname);
		System.out.println("ctext:"+ ctext);
		
		if(vo.getCno() != 0) {
			System.out.println("cno != 0 : c_ref =>"+ c_ref );
			map1.put("cno", vo.getCno());
			CommentsVo cvo = bdao.getComment(map1);
			c_ref = cvo.getC_ref();
			c_level = cvo.getC_level();
			c_step = cvo.getC_step();
			
			HashMap map2 = new HashMap();
			map2.put("bno", bno);
			map2.put("c_step", c_step);
			map2.put("c_ref", c_ref);
			bdao.updateStep(map2);
			
			c_level ++;
			c_step ++;
			System.out.println("댓글의 댓글");
		}
		
		System.out.println("c_ref:"+ c_ref);
		System.out.println("c_level:"+ c_level);
		System.out.println("c_step:"+ c_step);
		
		session.setAttribute("level", c_level);
		
		System.out.println("첫번째 댓글");
		vo.setBno(bno);
		vo.setCno(cno);
		vo.setC_ref(c_ref);
		vo.setC_level(c_level);
		vo.setC_step(c_step);
		vo.setCtext(ctext);
		vo.setNickname(nickname);
		
		int r = bdao.insertCommets(vo);
		
		return r+"";
	}
	

	@ResponseBody
	@RequestMapping("/deleteBoard")
	public String deleteBoard(int bno) {
		
		HashMap map = new HashMap();
		map.put("bno", bno);
		int r = bdao.deleteBoard(map);

		return r + "";
	}

	@ResponseBody
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(int bno, String title, String text, String tag) {
		// System.out.println("제목: "+title+" 내용: "+text+" 태그: "+tag);
		BoardVo vo = new BoardVo();
		vo.setBno(bno);
		vo.setTitle(title);
		vo.setTag(tag);
		vo.setText(text);
		int r = bdao.updateBoard(vo);

		return r + "";
	}

	@ResponseBody
	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public ModelAndView updateBoardForm(int bno) {

		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("bno", bno);
		mav.addObject("vo", bdao.getBoard(map));

		// System.out.println(bdao.getBoard(map));

		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public String insertBoard(String nickname, String title, String text, String tag) {

		BoardVo vo = new BoardVo();
		vo.setNickname(nickname);
		vo.setTitle(title);
		vo.setTag(tag);
		vo.setText(text);
		System.out.println(nickname);
		System.out.println(title);
		System.out.println(tag);
		System.out.println(text);
		System.out.println(vo);
		int r = bdao.insertBoard(vo);

		return r + "";
	}

	@RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
	public void insertBoardForm() {

	}

	@RequestMapping("/boardDetail")
	public ModelAndView boardDetail(int bno) {

		ModelAndView mav = new ModelAndView();
		HashMap map = new HashMap();
		map.put("bno", bno);
		mav.addObject("vo", bdao.getBoard(map));

		System.out.println(bdao.getBoard(map));

		return mav;
	}

	@ResponseBody
	@RequestMapping("/getCnt")
	public String getboardCnt() {

		int boardCnt = bdao.getBoardCnt();

		return boardCnt + "";
	}

	@ResponseBody
	@RequestMapping("/listBoard")
	public String listBoard(int start, int end, String search, String keyword, HttpSession session) {
		
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = new HashMap();

		
		String s_keyword = null;
        if(session.getAttribute("keyword") != null){
            s_keyword = (String) session.getAttribute("keyword");
//            System.out.println("session keyword :" + s_keyword);
        }
        
        if(s_keyword != null) {
        	
//        	System.out.println("s_keyword이 null이 아닐때");
//        	System.out.println("session search : " + (String) session.getAttribute("search"));
//        	System.out.println("session keyword : " + (String)session.getAttribute("keyword"));
        	
        	map.put("search", session.getAttribute("search"));
    		map.put("keyword", session.getAttribute("keyword"));
        }
		
		if(!keyword.equals("null")) {
			
//			System.out.println("keyword값 있을때 / 세션 등록");
			session.setAttribute("search", search);
			session.setAttribute("keyword", keyword);
			
			map.put("search", search);
			map.put("keyword", keyword);
		}

//		System.out.println("start: " + start);
//		System.out.println("end: " + end);
//		System.out.println("search: " + search);
//		System.out.println("keyword: " + keyword);
		
		map.put("start", start);
		map.put("end", end);

		List<BoardVo> list = bdao.listBoard(map);
//		System.out.println(list);
		
		try {
			str = mapper.writeValueAsString(list);

		} catch (Exception e) {
			System.out.println("listBoard() 예외발생 : " + e.getMessage());
		}

		return str;
	}

	@RequestMapping("/board")
	public void board() {

	}

}
