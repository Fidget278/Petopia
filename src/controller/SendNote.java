package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NoteService;
import model.NoteVo;
import model.member.MemberVo;


@WebServlet("/sendNote")
public class SendNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo)session.getAttribute("user");
		
		NoteVo note = new NoteVo();
		note.setCounterpart_no(Integer.parseInt(request.getParameter("counterpartNo")));
		note.setCounterpart_nickname(request.getParameter("counterpartNickname"));
		note.setContent(request.getParameter("content"));
		try {
			//NoteService.getInstance().registerNote(note, user.getNo());
			NoteService.getInstance().registerNote(note, 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
