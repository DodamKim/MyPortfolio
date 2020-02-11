package marcheVo;

public class ReviewVo {
	
	private int rno;
	private int score;
	private String rdate;
	private String rtext;
	private int odno;
	
	public ReviewVo() {
		super();
	}

	public ReviewVo(int rno, int score, String rdate, String rtext, int odno) {
		super();
		this.rno = rno;
		this.score = score;
		this.rdate = rdate;
		this.rtext = rtext;
		this.odno = odno;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getRtext() {
		return rtext;
	}

	public void setRtext(String rtext) {
		this.rtext = rtext;
	}

	public int getOdno() {
		return odno;
	}

	public void setOdno(int odno) {
		this.odno = odno;
	}
	

}