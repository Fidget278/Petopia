package controller.grade;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.grade.GradeService;

public class GradeBoardListCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int gradeNo = Integer.parseInt(request.getParameter("gradeNo"));
		
		HashMap<String, ArrayList<String>>boards = GradeService.getInstance().retrieveGradeBoardList(gradeNo);
		request.setAttribute("boards", boards);
		

		request.setAttribute("content", "viewGradeBoardList");
		return new ActionForward("/managerIndex.jsp", false);
	}

}
