package marcheVo;

public class BoardVo {
	
	private int bno;
	private String btitle;
	private String bloc;
	private String bdate;
	private String btime;
	private int bhit;
	private int mno;
	private int blv;
	private String bimg;
	private String btext;
	
	public BoardVo() {
		super();
	}
	

	public BoardVo(int bno, String btitle, String bloc, String bdate, String btime, int bhit, int mno, int blv,
			String bimg) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bloc = bloc;
		this.bdate = bdate;
		this.btime = btime;
		this.bhit = bhit;
		this.mno = mno;
		this.blv = blv;
		this.bimg = bimg;
	}



	public BoardVo(int bno, String btitle, String bloc, String bdate, String btime, int bhit, int mno, int blv,
			String bimg, String btext) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bloc = bloc;
		this.bdate = bdate;
		this.btime = btime;
		this.bhit = bhit;
		this.mno = mno;
		this.blv = blv;
		this.bimg = bimg;
		this.btext = btext;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBloc() {
		return bloc;
	}

	public void setBloc(String bloc) {
		this.bloc = bloc;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBtime() {
		return btime;
	}

	public void setBtime(String btime) {
		this.btime = btime;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getBlv() {
		return blv;
	}

	public void setBlv(int blv) {
		this.blv = blv;
	}

	public String getBimg() {
		return bimg;
	}

	public void setBimg(String bimg) {
		this.bimg = bimg;
	}

	public String getBtext() {
		return btext;
	}

	public void setBtext(String btext) {
		this.btext = btext;
	}


}