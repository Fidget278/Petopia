package controller.board;
//게시판 추가
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.board.BoardDao;
import model.board.BoardGradeVo;
import model.board.BoardService;
import model.board.BoardVo;

public class WriteBoardCommand implements Command {

		public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {


			// 1. 사용자가 입력한 게시판 정보를 구한다.
			int categoryNo = Integer.parseInt(req.getParameter("categoryNameForBoard"));
			String boardName = req.getParameter("boardName");
			int readGrade = Integer.parseInt(req.getParameter("readGrade"));
			int writeGrade = Integer.parseInt(req.getParameter("writeGrade"));

			// 2. 사용자가 입력한 게시판 정보를 DB에 등록하다.		
			BoardService boardService = BoardService.getInstance();
			boardService.registerBoard(new BoardVo(boardName, categoryNo), new BoardGradeVo(readGrade, writeGrade));
			

			return new ActionForward("/listCategoryManager.do", true);

		}

	}


