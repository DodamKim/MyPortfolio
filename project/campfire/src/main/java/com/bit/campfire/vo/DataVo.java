package com.bit.campfire.vo;

public class DataVo {
	
	private int dno;
	private String ddate;
	private int visit;
	private int play;
	private String mno;
	
	public DataVo() {
		super();
	}

	public DataVo(int dno, String ddate, int visit, int play, String mno) {
		super();
		this.dno = dno;
		this.ddate = ddate;
		this.visit = visit;
		this.play = play;
		this.mno = mno;
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getDdate() {
		return ddate;
	}

	public void setDdate(String ddate) {
		this.ddate = ddate;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public int getPlay() {
		return play;
	}

	public void setPlay(int play) {
		this.play = play;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	@Override
	public String toString() {
		return "DataVo [dno=" + dno + ", ddate=" + ddate + ", visit=" + visit + ", play=" + play + ", mno=" + mno + "]";
	}
	

}
