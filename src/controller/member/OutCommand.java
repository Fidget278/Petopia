package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;


public class OutCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String password = request.getParameter("password");
		
		System.out.println("Out command " + password);
		System.out.println("Out command " + memberNo);
		
		MemberService.getInstance().modifyMember(memberNo, password);
		
		
		return new ActionForward("/logout.do", true);
	}

}
