package com.bit.campfire.vo;

public class CommentsVo {
	
	private int cno;
	private String ctext;
	private String cdate;
	private int bno;
	private int c_ref;
	private int c_level;
	private int c_step;
	private String mno;
	private String nickname;
	private String img;
	
	public CommentsVo() {
		super();
	}

	public CommentsVo(int cno, String ctext, String cdate, int bno, int c_ref, int c_level, int c_step, String mno,
			String nickname, String img) {
		super();
		this.cno = cno;
		this.ctext = ctext;
		this.cdate = cdate;
		this.bno = bno;
		this.c_ref = c_ref;
		this.c_level = c_level;
		this.c_step = c_step;
		this.mno = mno;
		this.nickname = nickname;
		this.img = img;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCtext() {
		return ctext;
	}

	public void setCtext(String ctext) {
		this.ctext = ctext;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getC_ref() {
		return c_ref;
	}

	public void setC_ref(int c_ref) {
		this.c_ref = c_ref;
	}

	public int getC_level() {
		return c_level;
	}

	public void setC_level(int c_level) {
		this.c_level = c_level;
	}

	public int getC_step() {
		return c_step;
	}

	public void setC_step(int c_step) {
		this.c_step = c_step;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}
