package controller.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.article.ReplyDao;
import model.article.ReplyVo;
import model.member.MemberVo;

public class RegisterReplyCommand implements Command{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		HttpSession session = request.getSession();
		MemberVo member = (MemberVo) session.getAttribute("user");
		
		try {
			// 받아온 데이터 변수에 저장
			int articleNo = Integer.parseInt(request.getParameter("articleNo")); // 게시글 번호 getAjax 값을 받는다.
			System.out.println("리무커 아넘: " + articleNo);
			
			int memberNo = member.getNo(); // 회원 번호
			String nickname = member.getNickname();
			String content = request.getParameter("content");
			
			// DB insert
			ReplyDao replyDao = ReplyDao.getInstance();
			replyDao.insertReply(new ReplyVo(articleNo, memberNo, nickname, content));
			
			
			List<ReplyVo> replyList = replyDao.selectReplyList(articleNo);
			request.setAttribute("replyList", replyList);
			
			return new ActionForward("/listReply.jsp", false);
			
		} catch(Exception e) {
			throw e;
		}
	}

}
