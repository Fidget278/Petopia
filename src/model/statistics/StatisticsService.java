package model.statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.article.ArticleDao;
import model.article.ArticleVo;
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
	
	public void registerDailyData() throws Exception{
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			StatisticsDao statisticsDao = StatisticsDao.getInstance();
			conn.setAutoCommit(false);
			statisticsDao.insertDailyData(conn);
			
		} catch(Exception e) {
			throw e;
		} finally {
			try{
				if(conn != null) conn.close();
			} catch(Exception e2) {
				throw e2;
			}
		}
		
	}
	public void modifyDailyData() throws Exception{
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			StatisticsDao statisticsDao = StatisticsDao.getInstance();
			statisticsDao.updateDailyData(conn);
			
		} catch(Exception e) {
			throw e;
		} finally {
			try{
				if(conn != null) conn.close();
			} catch(Exception e2) {
				throw e2;
			}
		}
		
	}
	public void modifyTotalData() throws Exception{
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			StatisticsDao statisticsDao = StatisticsDao.getInstance();
			statisticsDao.updateTotalData(conn);
			
		} catch(Exception e) {
			throw e;
		} finally {
			try{
				if(conn != null) conn.close();
			} catch(Exception e2) {
				throw e2;
			}
		}
		
	}
}
