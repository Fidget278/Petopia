package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	
	private static CommandFactory factory = null;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
		map.put("/managerIndex.do", "controller.manager.ManagerCommand");
		map.put("/managerStatisticsDaily.do", "controller.statistics.statisticsDailyFormCommand");
		map.put("/managerStatisticsTotal.do", "controller.statistics.statisticsTotalFormCommand");
		

		
		map.put("/petopia.do", "controller.member.IntroCommand");
		map.put("/login.do", "controller.member.LoginCommand");
		map.put("/logout.do", "controller.member.LogOutCommand");
		
		// 게시글 목록 조회
		map.put("/listArticle.do", "controller.article.ListArticleCommand");
				
		// 게시글 상세 조회
		map.put("/detailArticle.do", "controller.article.DetailArticleCommand");
		
		// 게시글 작성 페이지로 이동
		map.put("/writeArticleForm.do", "controller.article.WriteArticleFormCommand");
		
		map.put("/writeArticle.do", "controller.article.WriteArticleCommand");
		
		
		//2021. 10. 25 이후 수정내용
		//카테고리+게시판 목록 조회 (관리자페이지용)
		map.put("/listCategory.do", "controller.category.ListCategoryCommand");
		//관리자페이지로 
		map.put("/listCategoryManager.do", "controller.category.ListCategoryManagerCommand");
		//카테고리 추가 
		map.put("/writeCategory.do", "controller.category.WriteCategoryCommand");
		//카테고리 수정
		map.put("/modifyCategory.do", "controller.category.ModifyCategoryCommand");
		//카테고리 삭제
		map.put("/removeCategory.do", "controller.category.RemoveCategoryCommand");
		
		//게시판 추가
		map.put("/writeBoard.do", "controller.board.WriteBoardCommand");
		//게시판 수정
		map.put("/modifyBoard.do", "controller.board.ModifyBordCommand");
		//게시판 삭제 - 미완
		map.put("/removeBoard.do", "controller.board.RemoveBoardCommand");
		

				
	}
	
	public static CommandFactory getInstance() {
		if(factory == null) 
			factory = new CommandFactory();
		
		return factory;
	}
	
	public Command createCommand(String commandURI) throws Exception {
		
		System.out.println("팩진입");
		String commandClass = map.get(commandURI);
		System.out.println("fac : " + commandURI);
		if(commandClass == null)
			return null;
		
		System.out.println(commandClass);
		try {
			System.out.println("여기는 오니?");
			Class<?> cls = Class.forName(commandClass);
			Constructor<?> constructor = cls.getConstructor();
			Command command = (Command)constructor.newInstance();
			
			return command;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
