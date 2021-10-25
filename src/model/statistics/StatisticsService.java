package model.statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utill.DBConn;

public class StatisticsService {
	private static StatisticsService statisticsService;

	private StatisticsService() {

	}

	public static StatisticsService getInstance() {
		if (statisticsService == null) {
			statisticsService = new StatisticsService();
		}
		return statisticsService;
	}

	public ArrayList<StatisticsVo> retriveDailyData() throws Exception{
		return StatisticsDao.getInstance().selectDailyData();
	}

	public StatisticsVo retriveTotalData()  throws Exception{
		return StatisticsDao.getInstance().selectTotalData();
	}
}
