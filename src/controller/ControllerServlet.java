package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDao;

@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
//		try {
//			MemberDao.getInstance().test();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("컨트롤러 진입");
		
		String requestURI = request.getRequestURI(); //��û ���� URI (/mvcfileWebApp/writeBoardForm.do)
		String contextPath = request.getContextPath(); // contextPath (/mvcfileWebApp)
		String commandURI = requestURI.substring(contextPath.length()); // contextPath�� �߶� (/writeBoardForm.do)
		
		/*
		 * System.out.printf("requestURI : %s%n", requestURI);
		 * System.out.printf("requestPath : %s%n", contextPath);
		 * System.out.printf("commandURI : %s%n", commandURI);
		 */
		
		try {
			CommandFactory factory = CommandFactory.getInstance();
			Command command = factory.createCommand(commandURI);
			ActionForward forward = command.execute(request, response);
			if(forward.isRedirect()) {
				response.sendRedirect(request.getContextPath() + forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}
	}	
}
