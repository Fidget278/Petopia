package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			sql.append(
					"SELECT member_no, email, password, ban, member.out FROM member where email = ? AND password = ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			System.out.println("멤버 dao 쿼리 실행하기 전");
			rs = pstmt.executeQuery();

			System.out.println("멤버 dao 쿼리 실행했음");
			if (rs.next()) {
				member = new MemberVo();
				member.setNo(rs.getInt(1));
				member.setEmail(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setBan(rs.getString(4));
				member.setMember(rs.getBoolean(5));
				System.out.println("DB에 멤버 정보 있음");
			}
			System.out.println("DB에 멤버 정보 없음");
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return member;
	}

	public void insertMember(MemberVo mVo) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO member(grade_no, email, password, nickname, regdate) ");
			sql.append("VALUES (?, ?, ?, ?, now()) ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, mVo.getGradeNo());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPassword());
			pstmt.setString(4, mVo.getNickname());

			pstmt.executeUpdate();
			System.out.println("회원가입 정보 입력");

		} finally {
			DBConn.close(conn, pstmt, null);
		}
	}

	public void updatePassword(int memNo, String newPassword) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();

			sql.append("UPDATE member ");
			sql.append("SET password = ? ");
			sql.append("WHERE member_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, newPassword);
			pstmt.setInt(2, memNo);
			System.out.println("memNo : " + memNo);
			pstmt.executeUpdate();

		} finally {
			DBConn.close(conn, pstmt, null);
		}
	}

	public boolean selectEmail(String email) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT email ");
			sql.append("FROM member ");
			sql.append("where email = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}

			return false;

		} finally {
			DBConn.close(conn, pstmt, rs);
		}
	}
	
	
	public void updateMember(int memberNo, String password) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();

			sql.append("UPDATE member ");
			sql.append("SET member.out = 1 ");
			sql.append("WHERE member_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, memberNo);
	
			System.out.println("memberNo : " + memberNo);
			pstmt.executeUpdate();

		} finally {
			DBConn.close(conn, pstmt, null);
		}
	}
	
	public boolean selectNickname(String nickname) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT nickname ");
			sql.append("FROM member ");
			sql.append("where nickname = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, nickname);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}

			return false;

		} finally {
			DBConn.close(conn, pstmt, rs);
		}
	}


	
}
