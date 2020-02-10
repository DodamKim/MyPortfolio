package com.bit.campfire.vo;

public class NoticeVo {
	private int bno;
	private String title;
	private String bdate;
	private String bimg;
	private String mno;
	private String text;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public NoticeVo(int bno, String title, String bdate, String bimg, String mno, String text) {
		super();
		this.bno = bno;
		this.title = title;
		this.bdate = bdate;
		this.bimg = bimg;
		this.mno = mno;
		this.text = text;
	}
	
	public NoticeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
