package model.article;

public class ReplyVo {
	private int replyNo;
	private int articleNo;
	private int memberNo;
	private String nickname;
	private String writedate;
	private String content;
	
	public ReplyVo() {
		
	}
	

	public ReplyVo(int articleNo, int memberNo, String nickname, String content) {
		super();
		this.articleNo = articleNo;
		this.memberNo = memberNo;
		this.nickname = nickname;
		this.content = content;
	}


	public ReplyVo(int replyNo, int articleNo, int memberNo, String nickname, String writedate, String content) {
		super();
		this.replyNo = replyNo;
		this.articleNo = articleNo;
		this.memberNo = memberNo;
		this.nickname = nickname;
		this.writedate = writedate;
		this.content = content;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
