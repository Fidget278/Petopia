package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	public ReplyVo selectReply(int replyNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReplyVo reply = null;
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT * FROM reply WHERE reply_no=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, replyNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reply = new ReplyVo();
				reply.setReplyNo(rs.getInt(1));
				reply.setArticleNo(rs.getInt(2));
				reply.setMemberNo(rs.getInt(3));
				reply.setNickname(rs.getString(4));
				reply.setWritedate(rs.getString(5));
				reply.setContent(rs.getString(6));
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			DBConn.close(conn, pstmt, rs);
			
		}
		// 조회한 게시글의 정보를 ReplyVo 객체로 반환
		return reply;
		
		
	}
	
	// 댓글 작성 후 저장행의 개수를 반환
	public int insertReply(ReplyVo reply) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rows= 0;
		
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
    		
    		rows = pstmt.executeUpdate();
    		System.out.println("insertReply의 rows: " + rows);
			
    		
		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return rows;
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
