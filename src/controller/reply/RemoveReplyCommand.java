package controller.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.article.ReplyDao;

public class RemoveReplyCommand implements Command{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int no = Integer.parseInt(request.getParameter("replyNo"));
		
		ReplyDao replyDao = ReplyDao.getInstance();
		replyDao.deleteReply(no);
		
		
		// 얘도 ajax
		return null;
	}

}
