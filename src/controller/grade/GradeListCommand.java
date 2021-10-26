package controller.grade;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.grade.GradeService;
import model.grade.GradeVo;

public class GradeListCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<GradeVo> grades = GradeService.getInstance().retrieveGradeList();
		
		request.setAttribute("grades", grades);
		
		

		request.setAttribute("content", "viewGradeList.jsp");
		return new ActionForward("/managerIndex.jsp", false);
	}
	
}
