package controller.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.article.ArticleService;
import model.article.ArticleVo;

public class DetailArticleCommand implements Command{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		
		ArticleService service = ArticleService.getInstance();
		
		ArticleVo articles = service.retrieveArticle(articleNo);
		
		request.setAttribute("articles", articles);
		
		return new ActionForward("/jsp/boards/article/detailArticle.jsp", false);
	}

}
