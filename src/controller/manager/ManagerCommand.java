package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class ManagerCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//content는 자신이 사용할 커맨드에서 request.setAttribute("content","내용") 하고 ActionForward에 managerindex.do로 이 커맨드에 보내기
		request.setAttribute("viewheader", "viewManagerHeader.jsp");
		return new ActionForward("/viewManagerTemplate.jsp", false);
	}
	
}
