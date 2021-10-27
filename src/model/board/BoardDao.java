package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.category.CategoryVo;
import utill.DBConn;


public class BoardDao {
	private static BoardDao boardDao;

	private BoardDao() {

	}

	public static BoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}

	// 게시판 추가 
	public int insertBoard(BoardVo board) throws Exception {
		int no = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			// 1. 게시판 이름과 카테고리 번호 넣기
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO Board (boardname, category_no) ");
			sql.append("VALUES (?, ?) ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, board.getBoardName());
			pstmt.setInt(2, board.getCategoryNo());
			pstmt.executeUpdate();
			pstmt.close();

			// DB에 저장된 게시판의 게시판 번호를 구한다.
			stmt = conn.createStatement();

			sql.delete(0, sql.length());
			sql.append("SELECT LAST_INSERT_ID()");
			rs = stmt.executeQuery(sql.toString());

			if (rs.next()) {
				no = rs.getInt(1);
			}

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
	
	//게시판 번호에 해당하는 게시판 정보 조회 - 수정 페이지에 사용할 예정 (현재 미사용)
	public BoardVo selectBoard(int boardNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo board = new BoardVo();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT board_no, boardName ");
			sql.append("FROM board ");
			sql.append("WHERE board_no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				board.setBoardNo(rs.getInt(1));
				board.setBoardName(rs.getString(2));
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
		return board;
	}
	
	
	//게시판 수정
	public int updateBoard(BoardVo board, Connection conn) throws Exception {
		int boardNo = 0;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE board ");
			sql.append("SET boardname = ?, category_no = ? ");
			sql.append("WHERE board_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, board.getBoardName());
			pstmt.setInt(2, board.getCategoryNo());
			pstmt.setInt(3, board.getBoardNo());
			pstmt.executeUpdate();
			pstmt.close();

			boardNo = board.getBoardNo();

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
		return boardNo;

	}
	
	
	//게시판 삭제 - 1. 해당 게시판에 속한 게시글 유무를 조회 //BoardVo board board = boardNo
	public int selectConnectArticle(int boardNo) throws Exception { 
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			stmt = conn.createStatement();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(article_no)   ");
			sql.append("FROM article ");
			sql.append("WHERE board_no = ? ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return count;
	}
	
	//게시판 삭제 - 3. 게시판 번호에 해당하는 게시판 삭제
	public void deleteBoard(int boardNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM board  ");
			sql.append("WHERE board_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw e2;
			}
		}
	}
	
	//게시판명 중복 체크 - jsp에서 들어온 boardName을 boardName에 넣기
	public int selectDuplicateBoardName(BoardVo boardName) throws Exception {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(boardname)    ");
			sql.append("FROM board   ");
			sql.append("WHERE boardname = ? "); 
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, boardName.getBoardName());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return count;
	}
	
}
