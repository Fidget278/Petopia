package controller.grade;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.category.CategoryService;
import model.category.CategoryVo;
import model.grade.GradeService;
import model.grade.GradeVo;

public class GradeListForBoardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<GradeVo> grades = GradeService.getInstance().retrieveGradeList();
		
		request.setAttribute("grades", grades);
		System.out.println("grades" + grades);
		System.out.println("GradeListForBoardCommand-------------------------------------------------------------------");
		
		return new ActionForward("/listCategoryManager.do", false);
		
	}
	
}
