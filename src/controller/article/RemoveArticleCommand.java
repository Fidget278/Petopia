package controller.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.article.ArticleService;

public class RemoveArticleCommand implements Command {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("지울 게시글 번호:" + request.getParameter("articleNo"));
		
		int no = Integer.parseInt(request.getParameter("articleNo"));
		// 삭제후 원래 게시판으로 돌아가기 위한 게시판 번호
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		System.out.println("remove 실행 전");
		ArticleService service = ArticleService.getInstance();
		service.removeArticle(no);
		System.out.println("remove 실행 후");
		
		HttpSession session = request.getSession();
		session.setAttribute("boardNo", boardNo);
		
		return new ActionForward("/viewListArticleContent.do", true);
		
	}

}
