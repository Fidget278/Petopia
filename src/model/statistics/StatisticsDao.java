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
			sql.append("SELECT daily_visits , daily_article ");
			sql.append("FROM cafedb.daliystatistics ");
			sql.append("WHERE daily_date BETWEEN DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW();");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				statVo = new StatisticsVo();
				statVo.setDailyVisitorsCount(rs.getInt(1));
				statVo.setDailyBoardCount(rs.getInt(2));

				System.out.println(arrayStat);
				arrayStat.add(statVo);
			}
			System.out.println(arrayStat);
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return arrayStat;
	}

	public StatisticsVo selectTotalData() {
		StatisticsVo statVo = null;
		// 오늘 날짜 기준으로 데이터 총합 Vo에 저장후 리턴
		return statVo;
	}
}
