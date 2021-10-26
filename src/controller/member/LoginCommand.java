package controller.member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
			request.setAttribute("failText", "아이디 또는 비밀번호가 잘못 입력 되었습니다.");
			request.setAttribute("isSuccess", 0);
		}
		// 현재 날짜랑 비교
		else if(member.getBan() != null && member.getBan().length() > 0 && 
				!(LocalDateTime.now().isAfter(LocalDateTime.parse(member.getBan(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))) ) {
			request.setAttribute("failText", "활동 정지 당한 회원입니다.");
			request.setAttribute("isSuccess", 2);
		}
		//탈퇴 여부
		else if(member.getIsMember() == 1) {
			request.setAttribute("failText", "탈퇴한 회원입니다.");
			request.setAttribute("isSuccess", 3);
		}
		else {
			request.setAttribute("isSuccess", 1);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", member);
			session.setMaxInactiveInterval(60*60); // 세션 유지 시간 1시간으로 설정
		}
		
		return new ActionForward("/loginAjax.jsp", false);
	}

}
