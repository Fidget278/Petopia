package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NoteService;
import model.member.MemberVo;

public class NoteListFormCommand implements Command {
	private static final int POST_PER_PAGE = 3;
	private static final int PAGE_BLOCK = 3;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVo user = (MemberVo) session.getAttribute("user");
//		request.setAttribute("NoteList", NoteService.getInstance().retriveNoteList(user.getNo(),
//				Integer.parseInt(request.getParameter("isRecieve"))));

		int currentPage = 0;

		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int startRow = (currentPage - 1) * POST_PER_PAGE;
		request.setAttribute("NoteList", NoteService.getInstance().retriveNoteList(user.getNo(),
				Integer.parseInt(request.getParameter("isRecieve")), POST_PER_PAGE, startRow));

		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;

		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		int totalPostCount = NoteService.getInstance().retriveTotalNoteCount(user.getNo(), Integer.parseInt(request.getParameter("isRecieve")));

		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : totalPostCount / POST_PER_PAGE + 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalPostCount);
		request.setAttribute("postSize", POST_PER_PAGE);
		
		return new ActionForward("/noteList.jsp?currentPage=" + currentPage, false);
	}

}
