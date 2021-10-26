package model.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utill.DBConn;

public class GradeDao {

	private static GradeDao gradeDao;
	
	private GradeDao() {
		
	}
	
	public static GradeDao getInstance() {
		if(gradeDao == null) {
			gradeDao = new GradeDao();
		}
		return gradeDao;
	}
	
	
	// 등급 조회
	public ArrayList<GradeVo> selectGradeList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<GradeVo> grades = new ArrayList<GradeVo>();
		
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT G.grade_no, name, G.docs, G.comms, count(*) AS person               ");
			sql.append("FROM member M RIGHT JOIN grade G   ");
			sql.append("ON M.grade_no = G.grade_no     ");
			sql.append("GROUP BY name     ");
			sql.append("ORDER BY G.grade_no ASC");
			
			
			rs = stmt.executeQuery(sql.toString());
			
			while(rs.next()) {
				int gradeNo = rs.getInt(1);
				String name = rs.getString(2);
				int docs = rs.getInt(3);
				int comms = rs.getInt(4);
				int person = rs.getInt(5);
				grades.add(new GradeVo(gradeNo, name, docs, comms, person));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, stmt, rs);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return grades;
	}
	
	// 등급 수정
	public void updateGrade(GradeVo grade, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO grade(grade_no, name, docs, comms)     ");
			sql.append("VALUES(?, ?, ?, ?)     ");
			sql.append("ON DUPLICATE KEY UPDATE    ");
			sql.append("name = ?, docs = ?, comms = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, grade.getGradeNo());
			pstmt.setString(2, grade.getName());
			pstmt.setInt(3, grade.getDocs());
			pstmt.setInt(4, grade.getComms());
			pstmt.setString(5, grade.getName());
			pstmt.setInt(6, grade.getDocs());
			pstmt.setInt(7, grade.getComms());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
	
	// 등급에 맞는 게시판 조회
	public HashMap<String, ArrayList<String>> selectGradeBoardList(int gradeNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> readers = new ArrayList<String>();
		ArrayList<String> writers = new ArrayList<String>();
		
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT boardName      ");
			sql.append("FROM board       ");
			sql.append("WHERE board_no =       "); 
			sql.append("ANY(SELECT board_no     ");
			sql.append("FROM board_grade BG LEFT JOIN grade G      ");
			sql.append("ON BG.grade_no = G.grade_no      ");
			sql.append("WHERE(readwrite = 0) AND (G.grade_no < ?))");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, gradeNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				readers.add(rs.getString(1));
			}
			
			pstmt.close(); rs.close();
			pstmt = null; rs = null;
			
			sql.delete(0, sql.length());
			sql.append("SELECT boardName      ");
			sql.append("FROM board       ");
			sql.append("WHERE board_no =       "); 
			sql.append("ANY(SELECT board_no     ");
			sql.append("FROM board_grade BG LEFT JOIN grade G      ");
			sql.append("ON BG.grade_no = G.grade_no      ");
			sql.append("WHERE(readwrite = 1) AND (G.grade_no < ?))");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, gradeNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				writers.add(rs.getString(1));
			}
			
			map.put("read", readers);
			map.put("write", writers);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, pstmt, rs);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return map;
	}
	
	// 등급 삭제시
	public void deleteGrade(int no, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("DELETE FROM grade      ");
			sql.append("WHERE grade_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1,  no);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
	// 등급 번호 DB 조회
	public ArrayList<Integer> selectGradeNumber() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			stmt = conn.createStatement();
			
			sql.append("SELECT grade_no    ");
			sql.append("FROM grade");
			
			rs = stmt.executeQuery(sql.toString());
			
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, stmt, rs);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return list;
	}
	
	// 등급이름 조회
		public ArrayList<GradeVo> selectGradeName() throws Exception{
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<GradeVo> grades = new ArrayList<GradeVo>();
			
			
			try {
				conn = DBConn.getConnection();
				stmt = conn.createStatement();
				
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT * FROM grade");
				
				rs = stmt.executeQuery(sql.toString());
				
				while(rs.next()) {
					int gradeNo = rs.getInt(1);
					String name = rs.getString(2);
					int docs = rs.getInt(3);
					int comms = rs.getInt(4);
					grades.add(new GradeVo(gradeNo, name, docs, comms));
				}
				return grades;
				
			} catch(Exception e) {
				throw e;
			}
		}
}
