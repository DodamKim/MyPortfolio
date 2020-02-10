package com.bit.campfire.vo;

public class RecordVo {
	
	public int rno;
	public String mno;
	public String nickname;
	public String img;
	public String rdate;
	public int rtime;
	
	public RecordVo() {
		super();
	}
	
	public RecordVo(int rno, String mno, String nickname, String img, String rdate, int rtime) {
		super();
		this.rno = rno;
		this.mno = mno;
		this.nickname = nickname;
		this.img = img;
		this.rdate = rdate;
		this.rtime = rtime;
	}
	
	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
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

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public int getRtime() {
		return rtime;
	}

	public void setRtime(int rtime) {
		this.rtime = rtime;
	}

	@Override
	public String toString() {
		return "RecordVo [rno=" + rno + ", mno=" + mno + ", nickname=" + nickname + ", img=" + img + ", rdate=" + rdate
				+ ", rtime=" + rtime + "]";
	}
	

}
