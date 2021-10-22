package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;
import model.member.MemberVo;

public class LoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVo member = MemberService.getInstance().login(request.getParameter("email"), request.getParameter("password"));

		if(member == null) {
			// 로그인 실패
			System.out.println("로그인 실패");
			request.setAttribute("failText", "아이디와 비밀번호를 확인해주세요.");
			request.setAttribute("isSuccess", 0);
			
		}
		// 현재 날짜랑 비교
//		else if(member.getBan().length() > 0 && member.getban() 오늘 날짜랑 비교) {
//		}
		// 탈퇴 여부
//		else if(member.isMember()) {
//			request.setAttribute("failText", "탈퇴한 회원입니다.");
//			request.setAttribute("isSuccess", 0);
//		}
		else {
			// 로그인 성공
			System.out.println("로그인 성공");
			request.setAttribute("isSuccess", 1);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", member);
		}
		
		return new ActionForward("/loginAjax.jsp", false);
	}

}
