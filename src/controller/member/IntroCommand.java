package controller.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.category.CategoryService;
import model.category.CategoryVo;
import model.member.MemberDao;
import model.member.MemberService;
import model.member.MemberVo;

public class IntroCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("user");

		MemberVo memberProfile = MemberService.getInstance().retreiveMemberProfile(memberVo.getNo());

		session.setAttribute("user", memberProfile);
		
		request.setAttribute("content", "/viewHomeContent");
		
		CategoryService categoryService = CategoryService.getInstance();
		ArrayList<CategoryVo> categoryList = categoryService.retrieveCategoryList();
		
		//사이드바 리스트 출력(사이드바에 출력할 jsp, command 필요x)
		request.setAttribute("categoryList", categoryList);

		return new ActionForward("/homeIndex.jsp", false);
	}

}
