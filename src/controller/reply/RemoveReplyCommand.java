package controller.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.article.ReplyDao;
import model.article.ReplyVo;

public class RemoveReplyCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("리무브 컴 진입");
		int no = Integer.parseInt(request.getParameter("no")); // replyNo
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		System.out.println("리무커 아넘: " + articleNo);
		
		ReplyDao replyDao = ReplyDao.getInstance();
		replyDao.deleteReply(no);
		
		List<ReplyVo> replyList = replyDao.selectReplyList(articleNo);
		
		
		for (ReplyVo replyVo : replyList) {
			System.out.println(replyVo);
		
		request.setAttribute("replyList", replyList);
		// 얘도 ajax
		}
		return new ActionForward("/listReply.jsp",false);
				

	}
}
