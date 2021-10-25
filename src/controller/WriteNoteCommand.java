package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberVo;

public class WriteNoteCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request.getParameter("counterpartNo");
		
		// 멤버 서비스에서 counterpartNo로  조회한 정보를 memberVo 로 만들어서 request에 set 해주기
		// 지금은 임시로
		MemberVo counterpart = new MemberVo();
		counterpart.setNo(2);
		counterpart.setNickname("이이이"); // 원래는 닉네임은 Dao에서 조회해서 담아야함.
		
		request.setAttribute("counterpart", counterpart);
		return new ActionForward("writeNote.jsp", false);
	}

}
