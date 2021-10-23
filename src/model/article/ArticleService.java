package model.article;

import java.sql.Connection;
import java.util.ArrayList;

import utill.DBConn;

public class ArticleService {

	private static ArticleService service;
	
	public ArticleService() {
		
	}
	
	// 싱글톤 패턴
	// ArticleService 객체를 반환.
	public static ArticleService getInstance() {
		if (service == null) {
			service = new ArticleService();
		}
		return service;
	}
	
	// 게시글 목록 조회
	public ArrayList<ArticleVo> retrieveArticleList(int startRow, int postSize) throws Exception{
		ArticleDao articleDao = ArticleDao.getInsatnce();	
		return articleDao.selectArticleList(startRow, postSize);
		
	}
	
	// 총 게시글 수를 구한다.
	public int retrieveTotalPostCount() throws Exception{
		return ArticleDao.getInsatnce().selectTotalPostCount();
		
	}
	
	// 게시글 세부 조회.
	public ArticleVo retrieveArticle(int articleNo) throws Exception{
		ArticleDao articleDao = ArticleDao.getInsatnce();
		return articleDao.selectArticle(articleNo);
	}
	
	// 게시글 작성
	public void registerArticle(ArticleVo article) throws Exception{
		Connection conn = null;
		boolean isSuccess = false;
		
		try {
			conn = DBConn.getConnection();
			// tx.begin
			conn.setAutoCommit(false);
			
			// 게시글 등록
			ArticleDao articleDao = ArticleDao.getInsatnce();
			int no = articleDao.insertArticle(article, conn);
			
			// 파일 등록(미구현)
			
		} catch(Exception e) {
			throw e;
		} finally {
			try{
				if(conn != null) conn.close();
			} catch(Exception e2) {
				throw e2;
			}
		}
		
	}
	// 게시글 수정
	public void modifyArticle(ArticleVo article) throws Exception{
		
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			ArticleDao articleDao = ArticleDao.getInsatnce();
			articleDao.updateArticle(article, conn);
			
		} catch(Exception e){
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	
	
	// 게시글 삭제
	public void removeArticle(int articleNo) throws Exception{
		// 트랜잭션 처리
		boolean isSuccess = false;
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			ArticleDao articleDao = ArticleDao.getInsatnce();
			articleDao.deleteArticle(articleNo, conn);
			
			
		}catch (Exception e) {
			throw e;
		} finally {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
