package controller.category;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.category.CategoryService;
import model.category.CategoryVo;
import model.grade.GradeService;
import model.grade.GradeVo;

public class ListCategoryCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ArrayList<GradeVo> grades = GradeService.getInstance().retrieveGradeList();
		req.setAttribute("grades", grades);

		CategoryService categoryService = CategoryService.getInstance();
		ArrayList<CategoryVo> categoryList = categoryService.retrieveCategoryList();
		req.setAttribute("categoryList", categoryList);

		return new ActionForward("/listCategoryManager.do", false);

	}

}