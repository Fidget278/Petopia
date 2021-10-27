package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utill.DBConn;

public class ArticleFileDao {
	
	private static ArticleFileDao articleFileDao;
	
	
	public static ArticleFileDao getInstance() {
		if (articleFileDao == null) {
			articleFileDao = new ArticleFileDao();
		}
		return articleFileDao;
	}
	
	// 파일 추가
	public void insertArticleFile(ArticleFileVo file, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		String fileType = "Jpg";
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO file (article_no, originalFileName, systemFileName, fileSize, filetype) ");
			sql.append("VALUES (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, file.getArticleNo());
			pstmt.setString(2, file.getOriginalFileName());
			pstmt.setString(3, file.getSystemFileName());
			pstmt.setInt(4, file.getFileSize());
			// 타입값은 임시로 지정해줬음.(아직 미구현)
			pstmt.setString(5, fileType);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch(Exception e2) {
				throw e2;
			}
		}
	}
	
	// 게시글 파일 동시삭제시 사용되는 메서드
	public void deleteFile(int articleNo, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
				
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM file               ");
			sql.append("WHERE article_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, articleNo);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch(Exception e2) {
				throw e2;
			}
			
		}
	}
	
	// 파일 1개만 지우기
	public void selectDeleteFile(int fileNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM file    ");
			sql.append("WHERE file_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, fileNo);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw e;
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
