package controller.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class WriteArticleFormCommand implements Command{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		
		request.setAttribute("content", "viewWriteArticleContent.jsp");
		
		// 글쓰기 요청이 오면 게시글 작성 폼으로 이동
		ActionForward forward = new ActionForward("/homeIndex.jsp", false);
		return forward;
	}

}
