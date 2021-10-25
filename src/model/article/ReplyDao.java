package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public List<ReplyVo> selectReplyList() throws Exception{
		List<ReplyVo> replyList = new ArrayList<ReplyVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println("리플셀렉 진입");
			conn = DBConn.getConnection();
			
			stmt = conn.createStatement();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT reply_no, article_no, member_no, nickname,");
			sql.append("DATE_FORMAT(writedate, '%Y/%m/%d') as writedate, content  ");
			sql.append("FROM reply  ");
			sql.append("ORDER BY writedate DESC");
			
			rs = stmt.executeQuery(sql.toString());
			System.out.println(rs);
			while(rs.next()) {
				ReplyVo reply = new ReplyVo();
				reply.setReplyNo(rs.getInt(1));
				reply.setArticleNo(rs.getInt(2));
				reply.setMemberNo(rs.getInt(3));
				reply.setNickname(rs.getString(4));
				reply.setWritedate(rs.getString(5));
				reply.setContent(rs.getString(6));
				replyList.add(reply);
			}
			return replyList;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, stmt, rs);
			} catch(Exception e2) {
				throw e2;
			}
			
		}
		
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
    		
    		pstmt.executeUpdate();
			
    		
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
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE reply      ");
			sql.append("SET content = ?   ");
			sql.append("WHERE reply_no= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getReplyNo());
			
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
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
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
}
