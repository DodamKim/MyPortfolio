package marcheVo;

public class ItemVo {

	private int ino;
	private String iname;
	private int iprice;
	private int ihit;
	private int inum;
	private String img;
	private int mno;
	private int tno;
	private String ilv;
	private int condition;
	private String idtext;
	
	public ItemVo() {
		super();
	}
	
	

	public ItemVo(int ino, String iname, int iprice, int ihit, int inum, String img, int mno, int tno, String ilv,
			int condition) {
		super();
		this.ino = ino;
		this.iname = iname;
		this.iprice = iprice;
		this.ihit = ihit;
		this.inum = inum;
		this.img = img;
		this.mno = mno;
		this.tno = tno;
		this.ilv = ilv;
		this.condition = condition;
	}



	public ItemVo(int ino, String iname, int iprice, int ihit, int inum, String img, int mno, int tno, String ilv,
			int condition, String idtext) {
		super();
		this.ino = ino;
		this.iname = iname;
		this.iprice = iprice;
		this.ihit = ihit;
		this.inum = inum;
		this.img = img;
		this.mno = mno;
		this.tno = tno;
		this.ilv = ilv;
		this.condition = condition;
		this.idtext = idtext;
	}

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public int getIprice() {
		return iprice;
	}

	public void setIprice(int iprice) {
		this.iprice = iprice;
	}

	public int getIhit() {
		return ihit;
	}

	public void setIhit(int ihit) {
		this.ihit = ihit;
	}

	public int getInum() {
		return inum;
	}

	public void setInum(int inum) {
		this.inum = inum;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getIlv() {
		return ilv;
	}

	public void setIlv(String ilv) {
		this.ilv = ilv;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public String getIdtext() {
		return idtext;
	}

	public void setIdtext(String idtext) {
		this.idtext = idtext;
	}
	
	
}