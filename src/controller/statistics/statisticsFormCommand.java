package controller.statistics;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.statistics.StatisticsService;
import model.statistics.StatisticsVo;

public class statisticsFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			StatisticsService statisticsService = StatisticsService.getInstance();
			
			ArrayList<StatisticsVo> statisticsDailyList = statisticsService.retriveDailyData();
			
			request.setAttribute("dailyList", statisticsDailyList);
			request.setAttribute("viewheader", "viewManagerHeader");
			request.setAttribute("content", "viewStatisticsContent");
			
			return new ActionForward("managerIndex.jsp", false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
