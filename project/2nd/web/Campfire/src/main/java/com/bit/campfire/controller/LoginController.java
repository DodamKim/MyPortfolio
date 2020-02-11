package com.bit.campfire.controller;

import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.campfire.dao.LoginDao;
import com.bit.campfire.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController {

	@Autowired
	LoginDao dao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	//contact 메일 보내기
//	@RequestMapping("/sendMe")
//	public void sendMe(String from, String subject, String text) {
//		
//		String to = "sundaycitron@gmail.com";
//		String sub = subject;
//		String txt = text;
//		
//		try {
//			javaMailSender.send(new MimeMessagePreparator() {
//				   public void prepare(MimeMessage mimeMessage) throws MessagingException {
//				     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//				     message.setFrom(from);
//				     message.setTo(to);
//				     message.setSubject(sub);
//				     message.setText(txt, true);
//				     //message.addInline("myLogo", new ClassPathResource("img/mylogo.gif"));
//				     //message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
//				   }
//				 });
//			
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		String f = to;
//		String title = "고객님의 소중한 의견이 접수되었습니다.";
//		text = "안녕하세요, 캠프파이어 관리자입니다. \n 고객님의 소중한 의견 감사합니다.\n 확인 후 답변 드리겠습니다.\n 감사합니다 :) ";
//		sendYou(f, to, title, text);
//		
//	}
	
	@ResponseBody
	@RequestMapping("/send")
	public int sendMe(String email, String subject, String text) {
		
		int r = 0;
		System.out.println("email:"+email);
		System.out.println("subject:"+subject);
		System.out.println("text:"+text);
		
		String to = "sundaycitron@gmail.com";
		String from = email;
		String tt = text;
		String su = subject;
		
		// 고객이 우리에게 메일 보내기
		sendMail(from, to, tt, su);
		
		// 우리가 고객에게 메일 보내기
		String f = to;
		String t = email;
		String sub = "고객님의 소중한 의견이 접수되었습니다.";
		String txt = "안녕하세요, 캠프파이어 관리자입니다. \n 고객님의 소중한 의견 감사합니다.\n 확인 후 답변 드리겠습니다.\n 감사합니다 :) ";
		sendMail(f, t, sub, txt);
		r = 1;
		
		return r;
	}
	
	
	public void sendMail(String from, String to, String subject, String text) {
		
		 SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setSubject(subject);
	        mailMessage.setFrom(from);
	        mailMessage.setText(text);
	        mailMessage.setTo(to);
		
		try {
			
			javaMailSender.send(mailMessage);
			
//			javaMailSender.send(new MimeMessagePreparator() {
//				public void prepare(MimeMessage mimeMessage) throws MessagingException {
//					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//					message.setFrom(from);
//					message.setTo(to);
//					message.setSubject(subject);
//					message.setText(text, true);
//					//message.addInline("myLogo", new ClassPathResource("img/mylogo.gif"));
//					//message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
//				}
//			});
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/getCountType")
	public int getCountType(String type) {
		int re = dao.getCountType(type);
		System.out.println("controller.getCountType 결과 : "+re);	
		return re;
	}

	// 총 회원수
	@ResponseBody
	@RequestMapping("/getTotal")
	public int getTotalMember(HttpSession session) {
		int re = dao.getTotal();
		session.setAttribute("totalMember", re);
		System.out.println("controller.getTotal 결과 : "+re);	
		return re;
	}
	
	@RequestMapping("/adminStats")
	public void adminStats() {
		
	}
	

	// 관리자 페이지 관련
	@ResponseBody
	@RequestMapping("/deleteMember")
	public String deleteMember(String mno) {
		
		HashMap map = new HashMap();
		map.put("mno", mno);
		int r = dao.deleteMember(map);
		
		return r+"";
	}
	
	
	@ResponseBody
	@RequestMapping("/resetMember")
	public String resetMember(String mno) {
		
		HashMap map = new HashMap();
		map.put("mno", mno);
		int r = dao.resetMember(map);
		
		return r+"";
	}
	
	
	@ResponseBody
	@RequestMapping("/stopMember")
	public String stopMember(String mno) {
		
		System.out.println("회원번호:"+ mno);
		HashMap map = new HashMap();
		map.put("mno", mno);
		int r = dao.stopMember(map);
		
		return r+"";
	}
	
	
	@ResponseBody
	@RequestMapping("/listMember")
	public String listMember(String search, String keyword, @RequestParam(value = "nowPage", defaultValue = "1")int nowPage, HttpSession session) {
		
		int totalRecord = dao.getTotal();
		int pageSize = 10;
		int totalPage = (int) Math.ceil(totalRecord / pageSize);
		int start = (nowPage - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		
		System.out.println("nowPage : "+nowPage);
		System.out.println("start : "+start);
		System.out.println("end : "+end);
		
		String str = "";
		HashMap map = new HashMap();
//		System.out.println("컨트롤러 search : "+ search);
//		System.out.println("컨트롤러 keyword : "+ keyword);
		
		String s_keyword = null;
        if(session.getAttribute("keyword") != null){
            s_keyword = (String) session.getAttribute("keyword");
            System.out.println("session keyword :" + s_keyword);
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
		
		//session.setAttribute("pageSize", pageSize);
		map.put("start", start);
		map.put("end", end);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(dao.listMember(map));
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return str;
	}
	
	
	@RequestMapping("/adminMember")
	public void adminMember() {
		
	}
	
	@RequestMapping("/adminBoard")
	public void adminBoard() {
		
	}
	
	
	// 로그인 관련
	@ResponseBody
	@RequestMapping("/gmLogin")
	public int gmLogin(String gmPw, HttpSession session ) {
		int r = 0;
		
		HashMap map = new HashMap();
		map.put("mno", "c_1");
		
		String pw = dao.getMember(map).getPw();
		
		if(gmPw.equals(pw)) {

			r = 1;
			session.setAttribute("mno", "c_1");
			session.setAttribute("nickname", "관리자");
			
			
		} else {
			r = 0;
		}
		
		return r;
	}
	
	
	@ResponseBody
	@RequestMapping("/logout")
	public String logout(String mno, HttpSession session) {
		
		int r = 0;
		
		if(mno != null) {
			
			session.removeAttribute("mno");
			session.removeAttribute("nickname");
			r = 1;
		}
		
		return r+"";
	}
	
	@ResponseBody
	@RequestMapping("/isMember")
	public String isMember(String mno, String nickname, HttpSession session) {
		//System.out.println("컨트롤러에 정보 오는지 확인, "+ mno + " , "+ nickname);
		String str = "";
		int r = 0;
			
		  HashMap map = new HashMap();
		  map.put("mno", mno);
		  map.put("nickname", nickname);
		  
		  MemberVo vo = dao.getMember(map);
		  
		  if(vo == null) { 
			r = dao.insertMember(map); 
			//System.out.println("컨트롤러, db에 정보가 없어 멤버 인서트."); 
		  }
		  
		  
		  if (vo != null || r == 1  ) { 
			session.setAttribute("mno", mno);
			session.setAttribute("nickname", nickname);
			if(vo.getImg() != null) {
				session.setAttribute("img", vo.getImg());
			}
			//System.out.println("컨트롤러 로그인 정보가 들어 있음.");
		  }
		 

		return str;
	}
}
