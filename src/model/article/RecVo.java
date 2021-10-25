package model.article;

public class RecVo {
	
	private int article_no;
	private String rec_id;
	private int good;
	
	public RecVo() {
		
	}
	
	public RecVo(int article_no, String rec_id, int good) {
		super();
		this.article_no = article_no;
		this.rec_id = rec_id;
		this.good = good;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}
	

}
