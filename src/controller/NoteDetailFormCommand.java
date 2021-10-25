package controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.NoteService;
import model.NoteVo;
import model.member.MemberVo;

public class NoteDetailFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo)session.getAttribute("user");
		
		int noteNo = Integer.parseInt(request.getParameter("no"));
		int userNo = user.getNo();
		int isRecieve = Integer.parseInt(request.getParameter("isRecieve"));
		NoteService.getInstance().updateRead(noteNo, userNo, isRecieve);
		
		NoteVo note = NoteService.getInstance().retriveNote(noteNo, userNo, isRecieve);
		
		request.setAttribute("note", note);
		
		return new ActionForward("noteDetailBoard.jsp", false);
	}

}
