package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;
import model.member.MemberVo;


public class OutCommand implements Command {


	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		MemberVo member = (MemberVo)session.getAttribute("user");
		
		System.out.println("Out command " + password);
		System.out.println("Out command " + member.getNo());
		
		MemberService.getInstance().modifyMember(member.getNo(), password);
		
		
		return new ActionForward("/logout.do", true);
	}

}
