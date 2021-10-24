package controller.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.article.ArticleService;

public class RemoveArticleCommand implements Command {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("지울 게시글 번호:" + request.getParameter("articleNo"));
		
		int no = Integer.parseInt(request.getParameter("articleNo"));
		
		System.out.println("remove 실행 전");
		ArticleService service = ArticleService.getInstance();
		service.removeArticle(no);
		System.out.println("remove 실행 후");
		
//		request.setAttribute("content", "/viewListArticleContent");
		
//		return new ActionForward("/homeIndex.jsp", false);
		
		return new ActionForward("/viewListArticleContent.do", true);
		
	}

}
