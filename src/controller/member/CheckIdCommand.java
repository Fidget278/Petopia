package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

public class CheckIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		boolean isEmail = MemberService.getInstance().retrieveEmail(email);
		req.setAttribute("isEmail", isEmail);
		return new ActionForward("/checkEmail.jsp", false);
	}

}
