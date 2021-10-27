package controller.category;
//카테고리 추가
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.category.CategoryService;
import model.category.CategoryVo;

public class WriteCategoryCommand implements Command {

	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("utf-8");

		// 1. 사용자가 입력한 카테고리 정보를 구한다.
		String categoryName = req.getParameter("categoryName");

		// 2. 사용자가 입력한 카테고리 정보를 DB에 등록하다.		
		CategoryService categoryService = CategoryService.getInstance();
		categoryService.registerCategory(new CategoryVo(categoryName));

		return new ActionForward("/listCategoryManager.do", true);

	}

}



