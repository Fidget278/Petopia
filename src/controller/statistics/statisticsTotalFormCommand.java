package controller.statistics;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.statistics.StatisticsService;
import model.statistics.StatisticsVo;

public class statisticsTotalFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		StatisticsService statisticsService = StatisticsService.getInstance();

		statisticsService.modifyTotalData();
		
		StatisticsVo statisticsTotalList = statisticsService.retriveTotalData();

		request.setAttribute("totalList", statisticsTotalList);

		request.setAttribute("content", "viewStatisticsTotalContent");

		return new ActionForward("/managerIndex.do", false);
	}

}
