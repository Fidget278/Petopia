package controller.statistics;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.statistics.StatisticsService;
import model.statistics.StatisticsVo;

public class statisticsFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		StatisticsService statisticsService = StatisticsService.getInstance();

		ArrayList<StatisticsVo> statisticsDailyList = statisticsService.retriveDailyData();

		StatisticsVo statisticsTotalList = statisticsService.retriveTotalData();

		request.setAttribute("dailyList", statisticsDailyList);
		request.setAttribute("totalList", statisticsTotalList);
		request.setAttribute("viewheader", "viewManagerHeader");
		request.setAttribute("content", "viewStatisticsDailyContent");

		return new ActionForward("managerIndex.jsp", false);
	}

}
