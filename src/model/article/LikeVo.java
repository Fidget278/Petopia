package model.article;

public class LikeVo {
	
	
	private int no;
	private int articleNo;
	private int memberNo;
	
	
	public LikeVo() {
		super();
	}


	public LikeVo(int articleNo, int memberNo) {
		super();
		this.articleNo = articleNo;
		this.memberNo = memberNo;
	}


	public LikeVo(int no, int articleNo, int memberNo) {
		super();
		this.no = no;
		this.articleNo = articleNo;
		this.memberNo = memberNo;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public int getArticleNo() {
		return articleNo;
	}


	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	
	
	

}
