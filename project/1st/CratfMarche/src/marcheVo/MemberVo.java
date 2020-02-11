package marcheVo;

public class MemberVo {
	
	private int mno;
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String tel;
	private String birth;
	private String addr;
	private String qns;
	private String anw;
	private String email;
	private String account;
	private int lv;
	
	public MemberVo(int mno, String id, String pw, String name, String nickname, String tel, String birth, String addr,
			String qns, String anw, String email, String account, int lv) {
		super();
		this.mno = mno;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.tel = tel;
		this.birth = birth;
		this.addr = addr;
		this.qns = qns;
		this.anw = anw;
		this.email = email;
		this.account = account;
		this.lv = lv;
	}

	public MemberVo() {
		super();
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getQns() {
		return qns;
	}

	public void setQns(String qns) {
		this.qns = qns;
	}

	public String getAnw() {
		return anw;
	}

	public void setAnw(String anw) {
		this.anw = anw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}
	

}