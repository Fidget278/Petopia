package controller.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.article.ReplyDao;
import model.article.ReplyVo;

public class RemoveReplyCommand implements Command{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int no = Integer.parseInt(request.getParameter("replyNo"));
		
		ReplyDao replyDao = ReplyDao.getInstance();
		replyDao.deleteReply(no);
		
		List<ReplyVo> replyList = replyDao.selectReplyList();
		
		for (ReplyVo replyVo : replyList) {
			System.out.println(replyVo);
		}
		
		request.setAttribute("replyList", replyList);
		// 얘도 ajax
		
		return new ActionForward("/listReply.jsp",false);
	}

}
