package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.member.MemberDao;
import model.member.MemberService;
import model.member.MemberVo;

public class IntroCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("user");

		MemberVo memberProfile = MemberService.getInstance().retreiveMemberProfile(memberVo.getNo());

		session.setAttribute("user", memberProfile);

		return new ActionForward("/homeIndex.jsp", false);
	}

}
