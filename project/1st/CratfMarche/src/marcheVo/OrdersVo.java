package marcheVo;

public class OrdersVo {

	private int ono;
	private int total;
	private String odate;
	private String coc;
	private int mno;
	private String oname;
	private String otel;
	private String oaddr;
	
	public OrdersVo() {
		super();
	}

	public OrdersVo(int ono, int total, String odate, String coc, int mno, String oname, String otel, String oaddr) {
		super();
		this.ono = ono;
		this.total = total;
		this.odate = odate;
		this.coc = coc;
		this.mno = mno;
		this.oname = oname;
		this.otel = otel;
		this.oaddr = oaddr;
	}

	public int getOno() {
		return ono;
	}

	public void setOno(int ono) {
		this.ono = ono;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public String getCoc() {
		return coc;
	}

	public void setCoc(String coc) {
		this.coc = coc;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getOtel() {
		return otel;
	}

	public void setOtel(String otel) {
		this.otel = otel;
	}

	public String getOaddr() {
		return oaddr;
	}

	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}
	
	
}