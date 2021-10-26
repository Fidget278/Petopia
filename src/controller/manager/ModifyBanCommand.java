package controller.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;
import model.member.MemberVo;

public class ModifyBanCommand implements Command{
	private static final int PAGE_BLOCK = 2;
	private  static final int MEMBER_PER_PAGE = 3;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ban = request.getParameter("banSelect");
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println(ban);
		System.out.println(no);
		
		MemberService service = MemberService.getInstance();
		service.modifyBan(ban, no);
		
		int currentPage = 0;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}
		
		int startRow = (currentPage - 1) * MEMBER_PER_PAGE;
		
		ArrayList<MemberVo> members = service.retrieveMemberList(startRow, MEMBER_PER_PAGE);
		
		request.setAttribute("members", members);
		
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;
		
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);
		
		int totalMember = service.retrieveTotalMember();
		
		int totalPage = totalMember % MEMBER_PER_PAGE == 0 ? totalMember / MEMBER_PER_PAGE : totalMember/ MEMBER_PER_PAGE + 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalMember);
		request.setAttribute("postPerPage", MEMBER_PER_PAGE);
		
		
		request.setAttribute("content", "viewMemberList.jsp");
		return new ActionForward("/managerIndex.do", false);
	}
}
