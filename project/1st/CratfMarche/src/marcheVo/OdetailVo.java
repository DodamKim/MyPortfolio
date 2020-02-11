package marcheVo;

public class OdetailVo {

	private int odno;
	private int odnum;
	private int ono;
	private int ino;
	private int odprice;
	private String ship;
	private int rcheck;
	
	public OdetailVo() {
		super();
	}

	public OdetailVo(int odno, int odnum, int ono, int ino, int odprice, String ship, int rcheck) {
		super();
		this.odno = odno;
		this.odnum = odnum;
		this.ono = ono;
		this.ino = ino;
		this.odprice = odprice;
		this.ship = ship;
		this.rcheck = rcheck;
	}

	public int getOdno() {
		return odno;
	}

	public void setOdno(int odno) {
		this.odno = odno;
	}

	public int getOdnum() {
		return odnum;
	}

	public void setOdnum(int odnum) {
		this.odnum = odnum;
	}

	public int getOno() {
		return ono;
	}

	public void setOno(int ono) {
		this.ono = ono;
	}

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public int getOdprice() {
		return odprice;
	}

	public void setOdprice(int odprice) {
		this.odprice = odprice;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public int getRcheck() {
		return rcheck;
	}

	public void setRcheck(char rcheck) {
		this.rcheck = rcheck;
	}
	
	
}