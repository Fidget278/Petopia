package controller.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.article.ArticleDao;
import model.article.ArticleVo;
import model.member.MemberVo;
import utill.DBConn;

public class WriteArticleCommand implements Command {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		HttpSession session = request.getSession();
		
		try {
//			System.out.println("boardNo:" + request.getParameter("boardSelect"));
//			System.out.println("subjcet: " + request.getParameter("subject"));
//			System.out.println("content: " + request.getParameter("content"));
			
			
			MemberVo member = (MemberVo) session.getAttribute("user");
			int memberNo = member.getNo(); 
//			System.out.println("memberNo: " + memberNo);
			int boardNo = Integer.parseInt(request.getParameter("boardSelect"));
//			System.out.println(boardNo);
			String subject = request.getParameter("subject");
			String nickname = member.getNickname();
			System.out.println("nickName: " + nickname);
			String content = request.getParameter("content");
			
			ArticleDao articleDao = ArticleDao.getInsatnce();
			articleDao.insertArticle(new ArticleVo(memberNo, boardNo, subject, nickname, content), DBConn.getConnection());
			
			return new ActionForward("/listArticle.do", true);
		} catch(Exception e) {
			throw e;
		}
		
	}
}
