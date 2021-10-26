package model.statistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import model.member.MemberVo;
import utill.DBConn;

public class StatisticsDao {
	private static StatisticsDao statisticsDao;

	private StatisticsDao() {

	}

	public static StatisticsDao getInstance() {
		if (statisticsDao == null) {
			statisticsDao = new StatisticsDao();
		}
		return statisticsDao;
	}

	public ArrayList<StatisticsVo> selectDailyData() throws Exception {
		ArrayList<StatisticsVo> arrayStat = null;
		StatisticsVo statVo = null;

		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;

		arrayStat = new ArrayList<StatisticsVo>();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT date(daily_date), daily_visits , daily_article ");
			sql.append("FROM cafedb.daliystatistics ");
			sql.append("WHERE daily_date BETWEEN DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW() ");
			sql.append("order by daily_date desc;");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				statVo = new StatisticsVo();
				statVo.setDailyDate(rs.getString(1));
				statVo.setDailyVisitorsCount(rs.getInt(2));
				statVo.setDailyBoardCount(rs.getInt(3));

				arrayStat.add(statVo);

			}

		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return arrayStat;
	}

	public StatisticsVo selectTotalData() throws Exception {
		StatisticsVo statVo = null;
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total_article, total_member, total_view, total_visits ");
			sql.append("FROM cafedb.totalstatistics ");
			sql.append("ORDER BY totalstatistics.total_no DESC LIMIT 1;");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			if (rs.next()) {
				statVo = new StatisticsVo();
				statVo.setTotalBoardCount(rs.getInt(1));
				statVo.setTotalMemberCount(rs.getInt(2));
				statVo.setTotalViewCount(rs.getInt(3));
				statVo.setTotalVisitorsCount(rs.getInt(4));
			}

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return statVo;
	}

	public void insertDailyData(Connection conn) throws Exception {
		StatisticsVo statVo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c1 = Calendar.getInstance();
			String strToday = sdf.format(c1.getTime());

			StringBuffer sql = new StringBuffer();
			sql.append("insert into daliystatistics(daily_date, daily_visits , daily_article ) ");
			sql.append(
					"values(?,(select count(*) from member where curdate()=member.lastdate),(select count(*) from cafedb.article where curdate()=(DATE_FORMAT(article.writedate, '%y-%m-%d'))))");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, strToday);
			pstmt.execute();

			conn.commit();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
	}

	public void updateDailyData(Connection conn) throws Exception {
		StatisticsVo statVo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c1 = Calendar.getInstance();
			String strToday = sdf.format(c1.getTime());

			StringBuffer sql = new StringBuffer();

			sql.append("update daliystatistics ");
			sql.append(
					"set daliystatistics.daily_visits = (select count(*) from member where curdate()=member.lastdate), ");
			sql.append(
					"daliystatistics.daily_article = (select count(*) from cafedb.article where curdate()=(DATE_FORMAT(article.writedate, '%y-%m-%d'))) ");
			sql.append("where daily_date = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, strToday);
			pstmt.executeUpdate();

		} finally {
			DBConn.close(conn, pstmt, rs);
		}
	}

	public void updateTotalData(Connection conn) throws Exception {
		StatisticsVo statVo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			StringBuffer sql = new StringBuffer();

			sql.append("update totalstatistics ");
			sql.append("set totalstatistics.total_member = (select count(member_no) from member), ");
			sql.append("totalstatistics.total_article = (select count(article.article_no) from article), ");
			sql.append("totalstatistics.total_view = (select sum(viewcount) from article), ");
			sql.append("totalstatistics.total_visits = (select sum(visits) from member) ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.executeUpdate();

		} finally {
			DBConn.close(conn, pstmt, rs);
		}
	}
}
