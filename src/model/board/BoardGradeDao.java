package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import utill.DBConn;

public class BoardGradeDao {
	private static BoardGradeDao boardGradeDao;

	private BoardGradeDao() {
	}

	public static BoardGradeDao getInstance() {
		if (boardGradeDao == null) {
			boardGradeDao = new BoardGradeDao();
		}
		return boardGradeDao;
	}

	// 게시판별 등급 정보를 입력한다.
	public void insertBoardGrade(int no, BoardGradeVo boardGrade) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			// 2. 카테고리 번호에 따른 읽기쓰기 등급 입력 : 읽기
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO Board_grade (board_no, grade_no, readwrite) ");
			sql.append("VALUES (?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, no);
			pstmt.setInt(2, boardGrade.getReadGrade());
			pstmt.setInt(3, 0);
			pstmt.executeUpdate();
			pstmt.close();

			// 3.카테고리 번호에 따른 읽기쓰기 등급 입력 : 쓰기
			sql.delete(0, sql.length());
			sql.append("INSERT INTO board_grade (board_no, grade_no, readwrite) ");
			sql.append("VALUES (?, ?, ?) ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, no);
			pstmt.setInt(2, boardGrade.getWriteGrade());
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// 게시판 번호에 해당하는 게시판별 읽기/쓰기 등급 정보 조회 - 수정페이지에 띄울 때 사용
	// 종훈님도 수정하셨음 둘이 통일 시켜야
	// 함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public BoardGradeVo selectBoardGrade(int boardNo, int readwrite) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardGradeVo boardReadGrade = new BoardGradeVo();

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT board_no, grade_no, readwrite ");
			sql.append("FROM board_grade ");
			sql.append("WHERE board_no = ? and readwrite = ? ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, readwrite);
			System.out.println("BoardGradeDao 읽기/쓰기 등급 조회");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardReadGrade.setBoardNo(rs.getInt(1));
				boardReadGrade.setReadGrade(rs.getInt(2));
				boardReadGrade.setReadwrite(rs.getInt(3));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {

			} catch (Exception e2) {
				throw e2;
			}
		}
		return boardReadGrade;
	}

	// 게시판별 등급 정보를 수정하다
	public void updateBoardGrade(int boardNo, BoardGradeVo boardGrade, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			// 2. 카테고리 번호에 따른 읽기쓰기 등급 입력 : 읽기
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE board_grade ");
			sql.append("SET grade_no = ? ");
			sql.append("WHERE board_no = ? and readwrite = ?  ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardGrade.getReadGrade());
			pstmt.setInt(2, boardNo);
			pstmt.setInt(3, 0);
			pstmt.executeUpdate();
			pstmt.close();

			// 3.카테고리 번호에 따른 읽기쓰기 등급 입력 : 쓰기
			sql.delete(0, sql.length());
			sql.append("UPDATE board_grade ");
			sql.append("SET grade_no = ? ");
			sql.append("WHERE board_no = ? and readwrite = ?  ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardGrade.getWriteGrade());
			pstmt.setInt(2, boardNo);
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// 게시판 삭제 - 2.게시판 번호를 받아서 해당하는 board_grade 삭제
	public void deleteBoardGrade(int boardNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM board_grade ");
			sql.append("WHERE board_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);

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

	// 작성폼 카테고리 선택 표시할 때 사용
	//public ArrayList<BoardGradeVo> selectAllBoardGrade(int readwrite) throws Exception {
	public HashMap<Integer, BoardGradeVo> selectAllBoardGrade(int readwrite) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardGradeVo boardReadGrade = new BoardGradeVo();
		ArrayList<BoardGradeVo> grades = new ArrayList<BoardGradeVo>();
		HashMap<Integer, BoardGradeVo> gradeMap = new HashMap<Integer, BoardGradeVo>();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT board_no, grade_no, readwrite ");
			sql.append("FROM board_grade ");
			sql.append("WHERE readwrite = ? ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, readwrite);
			System.out.println("BoardGradeDao 읽기/쓰기 등급 조회");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int board_no = rs.getInt(1);
				int grade_no = rs.getInt(2);
				int write = rs.getInt(3);
				//grades.add(new BoardGradeVo(board_no, grade_no, write));
				gradeMap.put(board_no, new BoardGradeVo(board_no, grade_no, write));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {

			} catch (Exception e2) {
				throw e2;
			}
		}
		return gradeMap;
	}

}
