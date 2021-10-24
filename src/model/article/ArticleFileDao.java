package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	
	// 파일 삭제
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
