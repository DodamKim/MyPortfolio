package com.bit.campfire.vo;

public class tempVo {

	private int no;
	private String tdate;
	private int lv;
	
	public tempVo() {
		super();
	}

	public tempVo(int no, String tdate, int lv) {
		super();
		this.no = no;
		this.tdate = tdate;
		this.lv = lv;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	@Override
	public String toString() {
		return "tempVo [no=" + no + ", tdate=" + tdate + ", lv=" + lv + "]";
	}

	
}
