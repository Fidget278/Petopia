package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

public class NicknameCheckCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nickname = request.getParameter("nickname");

		boolean isNickname = MemberService.getInstance().retrieveNickname(nickname);
		request.setAttribute("isNickname", isNickname);
		return new ActionForward("/checkNickname.jsp", false);
	}

}
