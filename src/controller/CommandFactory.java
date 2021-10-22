package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	
	private static CommandFactory factory = null;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
		map.put("/managerIndex.do", "controller.manager.ManagerCommand");
		
		map.put("/petopia.do", "controller.member.IntroCommand");
		map.put("/login.do", "controller.member.LoginCommand");
		
		// 게시글 목록 조회
		map.put("/listArticle.do", "controller.article.ListArticleCommand");
				
		// 게시글 상세 조회
		map.put("/detailArticle.do", "controller.article.DetailArticleCommand");
		
		// 게시글 작성 페이지로 이동
		map.put("/writeArticleForm.do", "controller.article.WriteArticleFormCommand");
		
		map.put("/writeArticle.do", "controller.article.WriteArticleCommand");
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
			Class<?> cls = Class.forName(commandClass);
			Constructor<?> constructor = cls.getConstructor();
			Command command = (Command)constructor.newInstance();
			
			return command;
		} catch(Exception e) {
			throw e;
		}

	}
}
