package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;
import model.member.MemberVo;

public class DetailMemberCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		MemberVo member = MemberService.getInstance().retrieveMember(no);
		
		request.setAttribute("member", member);
		request.setAttribute("viewheader", "viewManagerHeader.jsp");	
		request.setAttribute("content", "viewDetailMember.jsp");
		return new ActionForward("/viewManagerTemplate.jsp", false);
	
	}

}
