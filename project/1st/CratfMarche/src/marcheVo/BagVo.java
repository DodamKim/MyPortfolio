package marcheVo;

public class BagVo {
	
	private int bagno;
	private int bagnum;
	private int mno;
	private int ino;
	
	public BagVo() {
		super();
	}

	public BagVo(int bagno, int bagnum, int mno, int ino) {
		super();
		this.bagno = bagno;
		this.bagnum = bagnum;
		this.mno = mno;
		this.ino = ino;
	}

	public int getBagno() {
		return bagno;
	}

	public void setBagno(int bagno) {
		this.bagno = bagno;
	}

	public int getBagnum() {
		return bagnum;
	}

	public void setBagnum(int bagnum) {
		this.bagnum = bagnum;
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