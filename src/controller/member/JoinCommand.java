package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;
import model.member.MemberVo;

public class JoinCommand implements Command{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		
		System.out.println("join command " + email);
		MemberVo memberVo = new MemberVo(5, email, password, nickname);
		MemberService.getInstance().registerMember(memberVo);
		
		
		return new ActionForward("/petopia.do", true);
	}

}
