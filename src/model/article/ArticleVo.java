package model.article;

import java.util.ArrayList;

public class ArticleVo {

	private int articleNo; // 게시글 일련번호
	private int memberNo; // 사용자 일련번호
	private int boardNo; // 게시판 일련번호
	private String subject; // 제목
	private String nickname; // 회원명
	private String writedate; // 작성일
	private String content; // 내용
	private int viewcount; // 조회수
	private int likecount; // 좋아요(추천) 수
	private ArrayList<ArticleFileVo> fileList = new ArrayList<ArticleFileVo>(); // 첨부파일 List
	private ArrayList<ReplyVo> replyList = new ArrayList<ReplyVo>(); // 댓글 List

	public ArticleVo() {

	}
	
	
	
	
	// 게시글 작성
	public ArticleVo(int memberNo, int boardNo, String subject, String nickname, String content) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.subject = subject;
		this.nickname = nickname;
		this.content = content;
	}





	// ArticleDao.selectArticleList() 생성자 (게시판 목록 조회)
		public ArticleVo(int articleNo, String subject, String nickname, String writedate, int viewcount, int likecount, int memberNo) {
			super();
			this.articleNo = articleNo;
			this.subject = subject;
			this.nickname = nickname;
			this.writedate = writedate;
			this.viewcount = viewcount;
			this.likecount = likecount;
			this.memberNo = memberNo;
		}


	public ArticleVo(int articleNo, int memberNo, int boardNo, String nickname, String subject, String content,
			String writedate, int viewcount, int likecount) {
		super();
		this.articleNo = articleNo;
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.nickname = nickname;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.viewcount = viewcount;
		this.likecount = likecount;
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	public ArrayList<ArticleFileVo> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<ArticleFileVo> file) {
		this.fileList = file;
	}

	public ArrayList<ReplyVo> getReplyList() {
		return replyList;
	}

	public void setReplyList(ArrayList<ReplyVo> reply) {
		this.replyList = reply;
	}



	@Override
	public String toString() {
		return "ArticleVo [articleNo=" + articleNo + ", subject=" + subject + ", nickname=" + nickname + ", writedate="
				+ writedate + ", viewcount=" + viewcount + ", likecount=" + likecount + "]";
	}
	
	public void addArticleFile(ArticleFileVo file) {
		this.fileList.add(file);
	}
	
	public void  clearFileList() {
		this.fileList.clear();
	}
	

}