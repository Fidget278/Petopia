package controller.statistics;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.statistics.StatisticsService;
import model.statistics.StatisticsVo;

public class statisticsDailyFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		StatisticsService statisticsService = StatisticsService.getInstance();

		ArrayList<StatisticsVo> statisticsDailyList = statisticsService.retriveDailyData();

		request.setAttribute("dailyList", statisticsDailyList);

		request.setAttribute("viewheader", "viewManagerHeader");
		request.setAttribute("content", "viewStatisticsDailyContent");

		return new ActionForward("managerIndex.jsp", false);
	}

}
