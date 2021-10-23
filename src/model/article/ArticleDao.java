package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utill.DBConn;

public class ArticleDao {

    private static ArticleDao articleDao;

    private ArticleDao(){

    }

    // 싱글톤 패턴으로 관리.
    public static ArticleDao getInsatnce(){
        if (articleDao == null){
            articleDao = new ArticleDao();
        }
        return articleDao;
    }


    // 게시글 목록 조회
    public ArrayList<ArticleVo> selectArticleList(int startRow, int postSize) throws Exception{
        ArrayList<ArticleVo> articles = new ArrayList<ArticleVo>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
        	System.out.println("Dao 접속");
        	// DB접속 정보
            conn = DBConn.getConnection();

//            System.out.println("쿼리문 시작");
            // 쿼리문이 담길 StringBuffer 객체 생성.
            StringBuffer sql = new StringBuffer();
            // Article DB에 접근하여 (게시글 번호, 제목, 별명, 작성일, 조회수, 추천(좋아요)수)를 받아오는 쿼리문
            sql.append("SELECT article_no, subject, nickname,                                 ");
            sql.append("DATE_FORMAT(writedate, '%Y/%m/%d') as writedate, viewcount, likecount ");
            sql.append("FROM article                                                          ");
            sql.append("ORDER BY writedate DESC                                               ");
            // LIMIT: 몇 개를 출력할지, OFFSET: 어디서 부터 시작할지
            sql.append("LIMIT ? OFFSET ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            
            pstmt.setInt(1, postSize);
            pstmt.setInt(2, startRow);
            
            rs = pstmt.executeQuery();
            
//            System.out.println("쿼리문 끝");

            // rs에 받아놓은 데이터를 ArrayList 객체에 담아준다.
            while (rs.next()) {
            	int articleNo = rs.getInt(1);
            	String subject = rs.getString(2);
            	String nickname = rs.getString(3);
            	String writedate = rs.getString(4);
            	int viewcount = rs.getInt(5);
            	int likecount = rs.getInt(6);
            	articles.add(new ArticleVo(articleNo, subject, nickname, writedate, viewcount, likecount));
            }
//            System.out.println("while종료");

        } catch(Exception e){
            throw e;
        } finally{
            
        	try{
        		DBConn.close(conn, pstmt, rs);
        	} catch(Exception e2) {
        		throw e2;
        	}
            
        }
        // DB에 접근하여 가져온 Data가 저장된 ArrayList<ArticleVo> 리턴.
        return articles;
    }

    
    // 게시글 목록 조회 시 총 게시글 수를 구한다.
    public int selectTotalPostCount() throws Exception{
    	int count = 0;
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		conn = DBConn.getConnection();
    		stmt = conn.createStatement();
    		
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT COUNT(*)    ");
    		sql.append("FROM article");
    		
    		rs = stmt.executeQuery(sql.toString());
    		if(rs.next()) {
    			count = rs.getInt(1);
    		}
    		
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		DBConn.close(conn, stmt, rs);
    	}
    	return count;
    }
    
    // 게시글 상세 조회
    public ArticleVo selectArticle(int articleNo) throws Exception{
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	ArticleVo articleVo = new ArticleVo();
    	
    	try {
    		conn = DBConn.getConnection();
    		
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT subject, nickname,                                  ");
    		sql.append("DATE_FORMAT(writedate, '%Y%m%d') as writedate, content     ");
    		sql.append("FROM article                                               ");
    		sql.append("WHERE article_no = ?");
    		
    		pstmt = conn.prepareStatement(sql.toString());
    		pstmt.setInt(1, articleNo);
    		
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
    			articleVo.setSubject(rs.getString(1));
    			articleVo.setNickname(rs.getString(2));
    			articleVo.setWritedate(rs.getString(3));
    			articleVo.setContent(rs.getString(4));
    		}
    		
    		
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		try {
    			DBConn.close(conn, pstmt, rs);
    		} catch(Exception e2) {
    			throw e2;
    		}
    	}
    	return articleVo;
    }
    
    // 게시글 등록
    public int insertArticle(ArticleVo article, Connection conn) throws Exception{
    	
    	int no = 0;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		StringBuffer sql = new StringBuffer();
    		sql.append("INSERT INTO article (member_no, board_no, nickname, subject, content)   ");
    		sql.append("VALUES(?, ?, ?, ?, ?)");
    		
    		pstmt = conn.prepareStatement(sql.toString());

    		pstmt.setInt(1, article.getMemberNo());
    		pstmt.setInt(2, article.getBoardNo());
    		pstmt.setString(3, article.getNickname());
    		pstmt.setString(4, article.getSubject());
    		pstmt.setString(5, article.getContent());
    		System.out.println("pstmt");
    		System.out.println(pstmt);
    		System.out.println(pstmt.toString());
    		
    		pstmt.executeUpdate();
//    		pstmt.executeQuery();
    		pstmt.close();
    		
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		try {
    			if (rs != null) rs.close();
    			if (pstmt != null) pstmt.close();
    			if (conn != null) conn.close();
    		} catch(Exception e2) {
    			throw e2;
    		}
    	}
    	return no;
    }
    
    // 게시글 수정
    public void updateArticle(ArticleVo article, Connection conn) throws Exception{
    	
    	PreparedStatement pstmt = null;
    	
    	try {
    		StringBuffer sql = new StringBuffer();
    		sql.append("UPDATE article                               ");
    		sql.append("SET board_no = ?, subject = ?, content = ?    ");
    		sql.append("WHERE article_no = ?");
    		
    		pstmt = conn.prepareStatement(sql.toString());
    		pstmt.setInt(1, article.getBoardNo());
    		pstmt.setString(2, article.getSubject());
    		pstmt.setString(3, article.getContent());
    		
    		pstmt.executeUpdate();
    		
    		
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		if(pstmt != null) pstmt.close();
    		if(conn != null) conn.close();
    	}
    	
    }
    
    
    
    
    
}