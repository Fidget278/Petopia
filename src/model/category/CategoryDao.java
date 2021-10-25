package model.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.board.BoardVo;
import utill.DBConn;



public class CategoryDao {
	
	private static CategoryDao categoryDao;

	private CategoryDao() {

	}

	public static CategoryDao getInstance() {
		if (categoryDao == null) {
			categoryDao = new CategoryDao();
		}
		return categoryDao;
	}
	
	//카테고리 등록
	public int insertCategory(CategoryVo category) throws Exception {
		int no = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			// 1. 카테고리 이름 넣기
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO Category (categoryname) ");
			sql.append("VALUES (?) ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, category.getCategoryName());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return no;

	}
	
	//카테고리 및 게시판 목록 조회
		public ArrayList<CategoryVo> selectCategoryList() throws Exception {
			ArrayList<CategoryVo> categoryList = new ArrayList<CategoryVo>();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();

				stmt = conn.createStatement();
				
				StringBuffer sql = new StringBuffer();
				sql.append("select c.categoryname, b.boardname, c.category_no, b.board_no " );
				sql.append("from category as c left join board as b " );
				sql.append("on c.category_no = b.category_no ");
				sql.append("order by c.category_no, b.board_no " );
				rs = stmt.executeQuery(sql.toString());
		
				HashMap<String, CategoryVo> checkCategory = new HashMap<String, CategoryVo>();
				CategoryVo category = null;
				while (rs.next()) {
					if (checkCategory.containsKey(rs.getString(1)) ) {
						category = checkCategory.get(rs.getString(1));
					} else {
						
						category = new CategoryVo();
						category.setCategoryNo(rs.getInt(3));
						category.setCategoryName(rs.getString(1));
						checkCategory.put(category.getCategoryName(), category);
						categoryList.add(category);
					}
					
					category.addBoardList(new BoardVo(rs.getInt(4), rs.getString(2)));
				}

				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					throw e2;
				}
			}return categoryList;

		}
		
		
		
		
		
	
	//카테고리 번호에 해당하는 카테고리 정보 조회 - 미완
	public CategoryVo selectCategory(int no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CategoryVo category = new CategoryVo();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT categoryname ");
			sql.append("FROM category ");
			sql.append("WHERE category_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				category.setCategoryName(rs.getString(1));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return category;
	}
	
	//카테고리 정보 변경 - 미완
	public void updateCategory(CategoryVo category, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE category       ");
			sql.append("SET cateogoryname = \"?\"  ");
			sql.append("WHERE no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, category.getCategoryName());
			pstmt.setInt(2, category.getCategoryNo());

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	
	
}
