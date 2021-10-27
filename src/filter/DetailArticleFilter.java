package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardGradeDao;
import model.board.BoardGradeVo;

@WebFilter("/viewDetailArticleContent.do")
public class DetailArticleFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int memberGrade = Integer.parseInt(request.getParameter("gradeNo"));
		BoardGradeDao boardGrade = BoardGradeDao.getInstance();
		System.out.println("boardNo:" + boardNo);
		

		// 등급 확인
		try {
			System.out.println("필터접속");
			BoardGradeVo gradeVo = boardGrade.selectBoardGrade(boardNo, 0);
			int grade = gradeVo.getReadGrade();
			System.out.println("회원 등급: " + memberGrade);
			System.out.println("게시글의 등급: " + grade);
			
			if (memberGrade <= grade || memberGrade == 1) {
				chain.doFilter(request, response);

			} else if(grade == 0){ // 권한이 없을 때
				System.out.println("else if");
				((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/ReadAccess.do");
			} else {
				System.out.println("else");
				((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/ReadAccess.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("세부조회 필터!");
	}

}
