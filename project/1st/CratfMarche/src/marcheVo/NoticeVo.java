package marcheVo;

public class NoticeVo {
	
	private int nno;
	private String ntitle;
	private String ndate;
	private int mno;
	private String ntext;
	
	public NoticeVo() {
		super();
	}
	

	public NoticeVo(int nno, String ntitle, String ndate, int mno) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ndate = ndate;
		this.mno = mno;
	}



	public NoticeVo(int nno, String ntitle, String ndate, int mno, String ntext) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ndate = ndate;
		this.mno = mno;
		this.ntext = ntext;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNdate() {
		return ndate;
	}

	public void setNdate(String ndate) {
		this.ndate = ndate;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getNtext() {
		return ntext;
	}

	public void setNtext(String ntext) {
		this.ntext = ntext;
	}
	
	
}