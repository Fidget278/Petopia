package controller.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.category.CategoryService;

public class RemoveCategoryCommand implements Command {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	//1. 삭제할 카테고리 번호 구하기
	int categoryNo = Integer.parseInt(req.getParameter("categoryNo"));
	
	//2. 연결된 게시판 여부 체크
	CategoryService categoryService = CategoryService.getInstance();
	int count = categoryService.retrieveConnectBoard(categoryNo);
	
	if (count != 0) { //소속된 게시판이 없는 경우
		req.setAttribute("connectBoardResult", 1);
	} else {
		req.setAttribute("connectBoardResult", 0);
		categoryService.removeCategory(categoryNo);
	}
	
	return new ActionForward("/connectBoardAjax.jsp", false);
	}
}
