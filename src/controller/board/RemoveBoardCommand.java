package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.board.BoardService;

public class RemoveBoardCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//1. 삭제하고자 하는  게시판의 번호를 구한다.
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));

		//2. 포함된 게시글 여부 체크
		BoardService boardService = BoardService.getInstance();
		int count = boardService.retrieveConnectArticle(boardNo);
		
		if(count != 0) {
			//삭제 불가
			req.setAttribute("connectArticleResult", 1);
		} else if (count == 0) {
			req.setAttribute("connectArticleResult", 0);
			boardService.removeBoard(boardNo);
			
		}
			
		return new ActionForward("/connectArticleAjax.jsp", false);
		}
		
	}





