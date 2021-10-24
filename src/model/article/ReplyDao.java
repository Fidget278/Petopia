package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utill.DBConn;

public class ReplyDao {

	private static ReplyDao replyDao;
	
	public static ReplyDao getInstance() {
		if (replyDao == null) {
			replyDao = new ReplyDao();
		}
		return replyDao;
	}
	// 세부조회에서 띄워줄 때 불러와햐 할 것 같은데
	public ReplyVo selectReply() throws Exception{
		return new ReplyVo();
	}
	
	// 댓글 작성
	// 오퍼레이션에 리턴이 int인데 왜 그랬을까?
	public void insertReply(ReplyVo reply) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO reply (article_no, member_no, nickname, content)   ");
    		sql.append("VALUES(?, ?, ?, ?)");
    		
    		pstmt = conn.prepareStatement(sql.toString());
    		pstmt.setInt(1, reply.getArticleNo()); // article_no
    		pstmt.setInt(2, reply.getMemberNo()); // memeber_no
    		pstmt.setString(3, reply.getNickname()); // nickname
    		pstmt.setString(4, reply.getContent()); // content
    		
    		pstmt.executeUpdate();
    				
			
    		
		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
	}
	
	// 댓글 수정
	public void updateReply(ReplyVo reply) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			
		} catch (Exception e) {
		
		}
		
	}
	
	// 댓글 삭제
	public void deleteReply(int replyNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELET FROM reply  ");
			sql.append("WHERE reply_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, replyNo);
			
			pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
	}
	
}
