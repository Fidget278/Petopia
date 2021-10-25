package controller.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.category.CategoryService;
import model.category.CategoryVo;
import model.member.MemberService;
import model.member.MemberVo;

public class IntroCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("user");

		MemberVo memberProfile = MemberService.getInstance().retreiveMemberProfile(memberVo.getNo());

		session.setAttribute("user", memberProfile);
		

		CategoryService categoryService = CategoryService.getInstance();
		ArrayList<CategoryVo> categoryList = categoryService.retrieveCategoryList();
		
		//리스트 출력페이지로 
		request.setAttribute("categoryList", categoryList);

		
		request.setAttribute("side", "/viewFrameSidebar.jsp");
		request.setAttribute("content", "/viewHomeContent.jsp");

		return new ActionForward("/viewHomeTemplate.jsp", false);
	}

}
