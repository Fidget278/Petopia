package controller.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());

		System.out.println("strToday : " + strToday);
		System.out.println("getDailyDate : " + statisticsDailyList.get(0).getDailyDate());

		if (!strToday.equals(statisticsDailyList.get(0).getDailyDate())) {
			System.out.println("동일하지 않아서 인서트 실행");
			statisticsService.registerDailyData();
		} else {
			System.out.println("동일해서 업데이트 실행");
			statisticsService.modifyDailyData();
		}
		statisticsDailyList = statisticsService.retriveDailyData();

		request.setAttribute("dailyList", statisticsDailyList);

		request.setAttribute("viewheader", "viewManagerHeader");
		request.setAttribute("content", "viewStatisticsDailyContent");

		return new ActionForward("managerIndex.jsp", false);
	}

}
