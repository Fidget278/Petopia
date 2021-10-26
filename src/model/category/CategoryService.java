package model.category;

import java.util.ArrayList;

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
			CategoryDao categoryDao = CategoryDao.getInstance();
			categoryDao.insertCategory(category);
	}
	
	//카테고리 및 게시판 목록 조회 
	public ArrayList<CategoryVo> retrieveCategoryList() throws Exception {
		CategoryDao categoryDao = CategoryDao.getInstance();
		return categoryDao.selectCategoryList();		
	}
	
	//카테고리 정보 조회
		public CategoryVo retrieveCategory(int categoryNo) throws Exception {
			CategoryDao categoryDao = CategoryDao.getInstance();
			return categoryDao.selectCategory(categoryNo);
		}
		
	//카테고리 정보 변경
		public void modifyCategory(CategoryVo category) throws Exception {
				CategoryDao categoryDao = CategoryDao.getInstance();
				categoryDao.updateCategory(category);
		}
		
		
	//카테고리에 속한 게시판 조회
		public int retrieveConnectBoard(int categoryNo) throws Exception {
			
			CategoryDao categoryDao = CategoryDao.getInstance();
			int count = categoryDao.selectConnectBoard(categoryNo);

			return count;
		}
	//카테고리 삭제
	public void removeCategory(int categoryNo) throws Exception {
		
		CategoryDao categoryDao = CategoryDao.getInstance();
		categoryDao.deleteCategory(categoryNo);
		
		
	}
		
}
