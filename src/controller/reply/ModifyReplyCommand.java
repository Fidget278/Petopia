package controller.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.article.ReplyDao;
import model.article.ReplyVo;

public class ModifyReplyCommand implements Command{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int replyNo = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		
		
		ReplyVo reply = new ReplyVo(replyNo, content);
		System.out.println("수정 내용: " + content);
		ReplyDao replyDao = ReplyDao.getInstance();
		
		replyDao.updateReply(reply);
		
		List<ReplyVo> replyList = replyDao.selectReplyList(articleNo);
		
		for (ReplyVo replyVo: replyList) {
			System.out.println(replyVo);
		}
		
		request.setAttribute("replyList", replyList);
		
		return new ActionForward("/listReply.jsp", false);
	}
}
