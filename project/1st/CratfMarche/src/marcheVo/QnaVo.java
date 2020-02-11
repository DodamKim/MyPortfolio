package marcheVo;

public class QnaVo {

	private int qno;
	private String qtitle;
	private String qtext;
	private String qdate;
	private int qcheck;
	private int secret;
	private int mno;
	private int ino;
	
	public QnaVo() {
		super();
	}

	public QnaVo(int qno, String qtitle, String qtext, String qdate, int qcheck, int secret, int mno, int ino) {
		super();
		this.qno = qno;
		this.qtitle = qtitle;
		this.qtext = qtext;
		this.qdate = qdate;
		this.qcheck = qcheck;
		this.secret = secret;
		this.mno = mno;
		this.ino = ino;
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getQtext() {
		return qtext;
	}

	public void setQtext(String qtext) {
		this.qtext = qtext;
	}

	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
	}

	public int getQcheck() {
		return qcheck;
	}

	public void setQcheck(int qcheck) {
		this.qcheck = qcheck;
	}

	public int getSecret() {
		return secret;
	}

	public void setSecret(int secret) {
		this.secret = secret;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}
	
	
}