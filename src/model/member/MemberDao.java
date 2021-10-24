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
				member.setMember(rs.getBoolean(5));
				System.out.println("DB에 멤버 정보 있음");
			}
			System.out.println("DB에 멤버 정보 없음");
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return member;
	}
	
	// 회원인지 아닌지 확인
		public int userCheck(String email, String password) {
			int result = -1;

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT pwd FROM member WHERE email=?");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setString(1, email);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getString("password") != null && rs.getString("password").equals(password)) {
						result = 1; // 회원
					} else {
						result = 0; // 비번 틀림
					}
				} else {
					result = -1; // 회원아님
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 아이디로 회원 정보 가져오는 메소드
		public MemberVo getMember(String email) throws Exception {
			MemberVo mVo = null;

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT * FROM member WHERE userid=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, email);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					mVo = new MemberVo();
					mVo.setEmail(rs.getString("email"));
					mVo.setPassword(rs.getString("password"));
					mVo.setNickname(rs.getString("nickname"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return mVo;
		} // getMember end

		// 회원가입시 아이디 중복 체크하는 메소드, 아이디 중복=1, 중복아니면 -1
		public int selectEmail(String email) throws Exception {
			int result = -1;

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT userid FROM member WHERE userid=?");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setString(1, email);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					result = 1;
				} else {
					result = -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 회원가입 메소드
		public int insertMember(MemberVo mVo) throws Exception {
			int result = -1;

			String sql = "insert into member values(?,?,?)";
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mVo.getEmail());
				pstmt.setString(2, mVo.getPassword());
				pstmt.setString(3, mVo.getNickname());

				result = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 프로필 수정
		public int updateProfile(MemberVo mVo) {

			int result = -1;

			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("UPDATE member SET photo=?, password=?,");

				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setString(1, mVo.getEmail());
				pstmt.setString(2, mVo.getPassword());

				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;

		}
	
	

}
