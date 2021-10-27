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

		try {
		System.out.println("수정 커맨드 진입");
		int replyNo = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		int articleNo = Integer.parseInt(request.getParameter("articleNo")); // 게시글 번호 getAjax 값을 받는다.
		
		
		System.out.println("수정 커맨드 진입2");
		ReplyVo reply = new ReplyVo(replyNo, content);
		System.out.println("수정 내용: " + content);
		ReplyDao replyDao = ReplyDao.getInstance();
		
		replyDao.updateReply(reply);
		System.out.println("수정 커맨드 진입3");
		List<ReplyVo> replyList = replyDao.selectReplyList(articleNo);
		
		
		
		for (ReplyVo replyVo: replyList) {
			System.out.println(replyVo);
		}
		System.out.println("수정 커맨드 진입4");
		request.setAttribute("replyList", replyList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ActionForward("/listReply.jsp", false);
	}
}
