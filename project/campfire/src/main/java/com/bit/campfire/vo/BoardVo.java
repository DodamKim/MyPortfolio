package com.bit.campfire.vo;

public class BoardVo {

	private int bno;
	private String title;
	private String bimg;
	private int rc;
	private int dc;
	private int report;
	private String tag;
	private String bdate;
	private String text;
	private String nickname;
	private String img;
	
	public BoardVo() {
		super();
	}

	public BoardVo(int bno, String title, String bimg, int rc, int dc, int report, String tag, String bdate,
			String text, String nickname, String img) {
		super();
		this.bno = bno;
		this.title = title;
		this.bimg = bimg;
		this.rc = rc;
		this.dc = dc;
		this.report = report;
		this.tag = tag;
		this.bdate = bdate;
		this.text = text;
		this.nickname = nickname;
		this.img = img;
	}
	

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

	public String getBimg() {
		return bimg;
	}

	public void setBimg(String bimg) {
		this.bimg = bimg;
	}

	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}

	public int getDc() {
		return dc;
	}

	public void setDc(int dc) {
		this.dc = dc;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	@Override
	public String toString() {
		return "BoardVo [bno=" + bno + ", title=" + title + ", bimg=" + bimg + ", rc=" + rc + ", dc=" + dc + ", report="
				+ report + ", tag=" + tag + ", bdate=" + bdate + ", text=" + text + ", nickname=" + nickname + ", img="
				+ img + "]";
	}
	
	
}
