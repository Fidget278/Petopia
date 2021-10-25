package model.statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			sql.append("SELECT daily_date, daily_visits , daily_article ");
			sql.append("FROM cafedb.daliystatistics ");
			sql.append("WHERE daily_date BETWEEN DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW();");

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

	public StatisticsVo selectTotalData() throws Exception{
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
}
