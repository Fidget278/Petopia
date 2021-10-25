package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NoteService;
import model.member.MemberVo;

public class NoteListFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo)session.getAttribute("user");
		request.setAttribute("NoteList", NoteService.getInstance().retriveNoteList(user.getNo(), 
				Integer.parseInt(request.getParameter("isRecieve"))));
		return new ActionForward("noteList.jsp", false);
	}
	

}
