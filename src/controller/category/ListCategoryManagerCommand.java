package controller.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class ListCategoryManagerCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("content", "listCategory.jsp");
		request.setAttribute("viewheader", "viewManagerHeader.jsp");
		
		return new ActionForward("viewManagerTemplate.jsp", false);
	}
	
	

}
