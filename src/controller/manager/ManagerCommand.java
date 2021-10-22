package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class ManagerCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("viewheader", "viewManagerHeader");
		request.setAttribute("content", "viewManagerContent");
		return new ActionForward("managerIndex.jsp", false);
	}
	
}
