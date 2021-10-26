package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;
import model.member.MemberVo;

public class ResetPasswordCommand implements Command{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String newpassword = request.getParameter("newpassword");
		
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo)session.getAttribute("user");
		MemberService.getInstance().modifyPassword(user.getNo(), newpassword);
		return new ActionForward("/petopia.do", true);
	
	}
	
	

}
