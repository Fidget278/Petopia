package controller.grade;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.grade.GradeService;

public class GradeBoardListCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int gradeNo = Integer.parseInt(request.getParameter("gradeNo"));
		
		try {
			HashMap<String, ArrayList<String>>boards = GradeService.getInstance().retrieveGradeBoardList(gradeNo);
			request.setAttribute("boards", boards);
			
			return new ActionForward("/ajaxGradeBoardList.jsp", false);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request,response);
			return null;
		}
	}

}
