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
		System.out.println("userNo : " + Integer.parseInt(request.getParameter("userNo")));
		if(user != null)
			MemberService.getInstance().modifyPassword(user.getNo(), newpassword);
		else
			MemberService.getInstance().modifyPassword(Integer.parseInt(request.getParameter("userNo")), newpassword);
			
		return new ActionForward("/petopia.do", true);
	
	}
	
	

}
