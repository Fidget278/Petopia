package controller.manager;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;
import model.member.MemberVo;

public class SearchMemberCommand implements Command{

	private static final int PAGE_BLOCK = 2;
	private  static final int MEMBER_PER_PAGE = 3;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		System.out.println(keyfield);
		System.out.println(keyword);
		
		int currentPage = 0;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}
		
		try {
			int startRow = (currentPage - 1) * MEMBER_PER_PAGE;
			
			ArrayList<MemberVo> searchMembers = new ArrayList<MemberVo>();
			
			searchMembers = MemberService.getInstance().retrieveSearchMember(startRow, MEMBER_PER_PAGE, keyfield, keyword);
			
			int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;
			
			int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
			int endPage = startPage + (PAGE_BLOCK - 1);
			
			int searchTotalMember = MemberService.getInstance().retrieveTotalSearchMember(keyfield, keyword);
			
			int totalPage = searchTotalMember % MEMBER_PER_PAGE == 0 ? searchTotalMember / MEMBER_PER_PAGE : searchTotalMember/ MEMBER_PER_PAGE + 1;
			
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			request.setAttribute("searchMembers", searchMembers);
			request.setAttribute("pageBlock", PAGE_BLOCK);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("totalPostCount", searchTotalMember);
			request.setAttribute("postPerPage", MEMBER_PER_PAGE);
			
			
			return new ActionForward("/viewSearchMemberList.jsp", false);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request,response);
			return null;
		}
	}

}
