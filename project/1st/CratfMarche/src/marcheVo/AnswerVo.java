package marcheVo;

public class AnswerVo {

	private int ano;
	private String atext;
	private int qno;
	
	public AnswerVo() {
		super();
	}
	
	public AnswerVo(int ano, String atext, int qno) {
		super();
		this.ano = ano;
		this.atext = atext;
		this.qno = qno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getAtext() {
		return atext;
	}

	public void setAtext(String atext) {
		this.atext = atext;
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}
	
	
}