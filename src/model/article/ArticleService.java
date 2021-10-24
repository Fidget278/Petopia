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
			
			// 게시글 등록 -----------------------------------------------
			ArticleDao articleDao = ArticleDao.getInsatnce();
			int no = articleDao.insertArticle(article, conn);
			
			// 파일 등록-------------------------------------------------
			ArticleFileDao fileDao = ArticleFileDao.getInstance();
			
			// ArticleVo에 있는 ArrayList<ArticleFileVo> 객체를 받아온다.
			for (ArticleFileVo file : article.getFileList()) {
				// ArticleFileVo에 게시글 번호를 바인딩. 
				file.setArticleNo(no);
				// DB에 파일을 저장
				fileDao.insertArticleFile(file, conn);
			}
			isSuccess = true;
			
		} catch(Exception e) {
			throw e;
		} finally {
			try{
				if(conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
					conn.close();
				}
			} catch(Exception e2) {
				throw e2;
			}
		}
		
	}
	// 게시글 수정
	public void modifyArticle(ArticleVo article) throws Exception{
		
		Connection conn = null;
		boolean isSuccess = false;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDao articleDao = ArticleDao.getInsatnce();
			articleDao.updateArticle(article, conn);
			
			ArticleFileDao fileDao = ArticleFileDao.getInstance();
			
			// 전달받은 ArticleVo 객체에서 파일들을 꺼내 추가한다.
			for (ArticleFileVo file : article.getFileList()) {
				file.setArticleNo(article.getArticleNo());
				fileDao.insertArticleFile(file, conn);
			}
			
			isSuccess=true;
			
		} catch(Exception e){
			throw e;
		} finally {
			try{
				if(conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}
			} catch(Exception ex) {
				throw ex;
			}
		}
	}
	
	
	// 게시글 삭제
	public void removeArticle(int articleNo) throws Exception{
		// 트랜잭션 처리
		boolean isSuccess = false;
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			// 쿼리문당 자동으로 커밋되는 일 방지
			conn.setAutoCommit(false);
			
			// 해당 게시글의 첨부파일 제거
			ArticleFileDao articleFileDao = ArticleFileDao.getInstance();
			articleFileDao.deleteFile(articleNo, conn);
			System.out.println("파일 삭제 통과");
			
			// 게시글 제거
			ArticleDao articleDao = ArticleDao.getInsatnce();
			articleDao.deleteArticle(articleNo, conn);
			System.out.println("게시글 삭제");
			
			isSuccess = true;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (conn != null) {
					if(isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}
			} catch(Exception e2) {
				e2.printStackTrace();
				throw e2;
			}
		}
	}
	
	
}
