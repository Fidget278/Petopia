package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class IntroCommand implements Command {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ActionForward("/homeIndex.jsp", false);
	}
	
}
