package controller.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.category.CategoryService;
import model.category.CategoryVo;

public class ModifyCategoryCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. 카테고리 번호를 구한다.
		
		int categoryNo = Integer.parseInt(req.getParameter("categoryNo"));
		String categoryName = req.getParameter("categoryName");
		
		CategoryService category = CategoryService.getInstance();
		category.modifyCategory(new CategoryVo (categoryNo, categoryName));		
		
		return new ActionForward("/listCategoryManager.do", false);
		
	}
	

}
