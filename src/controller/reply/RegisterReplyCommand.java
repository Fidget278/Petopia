package controller.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.article.ReplyService;
import model.article.ReplyVo;
import model.member.MemberVo;

public class RegisterReplyCommand implements Command{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		HttpSession session = request.getSession();
		MemberVo member = (MemberVo) session.getAttribute("user");
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		int memberNo = member.getNo();
		String nickname = member.getNickname();
		String content = request.getParameter("content");
		
		ReplyVo reply = new ReplyVo(articleNo, memberNo, nickname, content);
		
		ReplyService service = ReplyService.getInstance();
		service.registerReply(reply);
		
		// ajax로 바로 작성되고 밑에 보이도록 해야됨.
		return null;
	}

}
