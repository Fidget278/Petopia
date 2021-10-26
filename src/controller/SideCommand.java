//package controller;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import model.category.CategoryService;
//import model.category.CategoryVo;
//
//public class SideCommand implements Command{
//
//	@Override
//	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//		CategoryService categoryService = CategoryService.getInstance();
//		ArrayList<CategoryVo> categoryList = categoryService.retrieveCategoryList();
//		
//		//리스트 출력페이지로 
//		request.setAttribute("categoryList", categoryList);
//
//		request.setAttribute("side", "/viewFrameSidebar.jsp");
//		
//		return new ActionForward("/viewFrameSidebar.jsp", false);
//	}
//
//}
package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.category.CategoryService;
import model.category.CategoryVo;

public class SideCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		/*
		 * request.setAttribute("side", "/viewFrameSidebar.jsp");
		 * request.setAttribute("content", "/viewHomeContent.jsp");
		 */
		
		CategoryService categoryService = CategoryService.getInstance();
		ArrayList<CategoryVo> categoryList = categoryService.retrieveCategoryList();
		
		//리스트 출력페이지로 
		request.setAttribute("categoryList", categoryList);
		
		request.setAttribute("side", "/viewFrameSidebar.jsp");
		return new ActionForward("/viewHomeTemplate.jsp", false);
	}

}
