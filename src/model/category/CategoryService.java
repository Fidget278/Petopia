package model.category;

import java.sql.Connection;
import java.util.ArrayList;

import utill.DBConn;


public class CategoryService {

	
	private static CategoryService categoryService;

	private CategoryService() {

	}

	public static CategoryService getInstance() {
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		return categoryService;
	}
	
	//카테고리 정보 등록
	public void registerCategory(CategoryVo category) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection(); //connection pool에서 connection 구하기
			CategoryDao categoryDao = CategoryDao.getInstance();
			categoryDao.insertCategory(category);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) conn.close(); //connection pool에 connection 반환
			} catch (Exception ex) {
				throw ex;
			}

		}
	}
	
	//카테고리 및 게시판 목록 조회
	public ArrayList<CategoryVo> retrieveCategoryList() throws Exception {
		CategoryDao categoryDao = CategoryDao.getInstance();
		return categoryDao.selectCategoryList();		
	}
	
	
	
	
	//카테고리 정보 조회 - 미완
		public CategoryVo retrieveCategory(int no) throws Exception {
			CategoryDao categoryDao = CategoryDao.getInstance();
			return categoryDao.selectCategory(no);
		}
		
	//카테고리정보 변경 - 미완
		public void modifyBoard(CategoryVo category) throws Exception {

			Connection conn = null;
			try {
				conn = DBConn.getConnection();

				CategoryDao categoryDao = CategoryDao.getInstance();
				categoryDao.updateCategory(category, conn);

			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (conn != null) conn.close();
				} catch (Exception e2) {
					throw e2;
				}
			}
		}
		
	


}
