package com.bit.campfire.vo;

import org.springframework.web.multipart.MultipartFile;

public class MemberVo {
	
	private String mno;
	private String id;
	private String pw;
	private String nickname;
	private String img;
	private MultipartFile upload;
	private int mlv;
	
	public MemberVo() {
		super();
	}

	public MemberVo(String mno, String id, String pw, String nickname, String img, MultipartFile upload, int mlv) {
		super();
		this.mno = mno;
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.img = img;
		this.upload = upload;
		this.mlv = mlv;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}

	public int getMlv() {
		return mlv;
	}

	public void setMlv(int mlv) {
		this.mlv = mlv;
	}

	@Override
	public String toString() {
		return "MemberVo [mno=" + mno + ", id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", img=" + img
				+ ", upload=" + upload + ", mlv=" + mlv + "]";
	}
	
}
