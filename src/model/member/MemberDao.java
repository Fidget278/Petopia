package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utill.DBConn;

public class MemberDao {

	private static MemberDao memberDao;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (memberDao == null)
			memberDao = new MemberDao();

		return memberDao;
	}

	public void test() throws Exception {

		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM Member");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				System.out.println("member : " + rs.getInt(1) + rs.getInt(2) + rs.getString(3));
			}

		} finally {
			DBConn.close(conn, pstmt, rs);
		}
	}
	
	
	public MemberVo selectMember(String email, String password) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVo member = null;
		

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT member_no, email, password, ban, member.out FROM member where email = ? AND password = ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			System.out.println("멤버 dao 쿼리 실행하기 전");
			rs = pstmt.executeQuery();

			System.out.println("멤버 dao 쿼리 실행했음");
			if(rs.next()) {
				member = new MemberVo();
				member.setNo(rs.getInt(1));
				member.setEmail(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setBan(rs.getString(4));
				member.setIsMember(rs.getInt(5));
				System.out.println("DB에 멤버 정보 있음");
			}
			System.out.println("DB에 멤버 정보 없음");
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return member;
	}
	
	// 회원 목록을 DB에서 조회한다.
	public ArrayList<MemberVo> selectMemberList(int startRow, int  memberPerPage) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberVo> members = new ArrayList<MemberVo>();
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT member_no, email, name, regdate, visits, ban    ");
			sql.append("FROM member M RIGHT JOIN grade G    ");
			sql.append("ON M.grade_no = G.grade_no     ");
			sql.append("WHERE email IS NOT NULL     ");
			sql.append("ORDER BY member_no ASC    ");
			sql.append("LIMIT ? OFFSET ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, memberPerPage);
			pstmt.setInt(2, startRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String email = rs.getString(2);
				String grade = rs.getString(3);
				String regDate = rs.getString(4);
				int visits = rs.getInt(5);
				String ban = rs.getString(6);
				members.add(new MemberVo(no, email, regDate, grade,  visits, ban));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, pstmt, rs);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return members;
	}
	
	// 회원의 총 수를 DB에서 구한다.
	public int selectTotalMember() throws Exception {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT COUNT(*)     ");
			sql.append("FROM member");
			
			rs = stmt.executeQuery(sql.toString());
			
			if(rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	// 회원의 상세 정보를 DB에서 조회
	public MemberVo selectMemberByManager(int no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVo member = new MemberVo();
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT email, name, nickname, regdate, lastdate, M.docs, M.comms, visits, ban, M.out    ");
			sql.append("FROM member M RIGHT JOIN grade G     ");
			sql.append("ON M.grade_no = G.grade_no     ");
			sql.append("WHERE member_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setEmail(rs.getString(1));
				member.setGrade(rs.getString(2));
				member.setNickname(rs.getString(3));
				member.setRegDate(rs.getString(4));
				member.setLastDate(rs.getString(5));
				member.setDocs(rs.getInt(6));
				member.setComms(rs.getInt(7));
				member.setVisits(rs.getInt(8));
				member.setBan(rs.getString(9));
				member.setIsMember(rs.getInt(10));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, pstmt, rs);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return member;
	}
	
	// DB에서 해당 검색 회원 조회 
	public ArrayList<MemberVo> selectSearchMember(int startRow, int memberPerPage, String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberVo> members = new ArrayList<MemberVo>();
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			
			if(keyfield.equals("email")) {
				sql.append("SELECT member_no, email, name, regdate, visits, ban    ");
				sql.append("FROM member M RIGHT JOIN grade G    ");
				sql.append("ON M.grade_no = G.grade_no     ");
				sql.append("WHERE email LIKE CONCAT('%', ?, '%')     ");
				sql.append("ORDER BY member_no ASC    ");
			}
			
			else {
				sql.append("SELECT member_no, email, name, regdate, visits, ban    ");
				sql.append("FROM member M RIGHT JOIN grade G    ");
				sql.append("ON M.grade_no = G.grade_no     ");
				sql.append("WHERE M.grade_no = ");
				sql.append("ANY( SELECT grade_no    ");
				sql.append("FROM grade G    ");
				sql.append("WHERE name LIKE CONCAT('%', ?, '%'))      ");
				sql.append("ORDER BY member_no ASC    ");
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, keyword);
			/*
			 * pstmt.setInt(2, memberPerPage); pstmt.setInt(3, startRow);
			 */
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String email = rs.getString(2);
				String grade = rs.getString(3);
				String regDate = rs.getString(4);
				int visits = rs.getInt(5);
				String ban = rs.getString(6);
				members.add(new MemberVo(no, email, regDate, grade,  visits, ban));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, pstmt, rs);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return members;
	}
	
	// 검색에 해당되는 회원의 수 카운트
	public int selectTotalSearchMember(String keyfield, String keyword) throws Exception {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			
			if(keyfield == "email") {
				sql.append("SELECT COUNT(*)     ");
				sql.append("FROM member    ");
				sql.append("WHERE email = ?");
			}
			
			else {
				sql.append("SELECT COUNT(*)     ");
				sql.append("FROM member    ");
				sql.append("WHERE grade_no =     ");
				sql.append("(SELECT grade_no    ");
				sql.append("FROM grade G    ");
				sql.append("WHERE name = ? )");
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				DBConn.close(conn, pstmt, rs);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return result;
	}
	
	// 회원 관리 정지 기간 DB 적용
	public void updateBan(String banSelect, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE ");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
