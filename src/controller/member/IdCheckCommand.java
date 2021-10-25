package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

public class IdCheckCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("email");
		boolean isEmail = MemberService.getInstance().retrieveEmail(email);
		request.setAttribute("isEmail", isEmail);
		return new ActionForward("/checkEmail.jsp", false);
	}

	
	
	
}
