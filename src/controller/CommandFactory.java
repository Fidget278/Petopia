package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	
	private static CommandFactory factory = null;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
		// 관리자 화면
		map.put("/managerIndex.do", "controller.manager.ManagerCommand");
		map.put("/managerStatisticsDaily.do", "controller.statistics.statisticsDailyFormCommand");
		map.put("/managerStatisticsTotal.do", "controller.statistics.statisticsTotalFormCommand");
		
		map.put("/petopia.do", "controller.member.IntroCommand");
		map.put("/login.do", "controller.member.LoginCommand");
		map.put("/logout.do", "controller.member.LogOutCommand");
		map.put("/side.do", "controller.SideCommand");
		
		// 게시글 목록 조회
		map.put("/viewListArticleContent.do", "controller.article.ListArticleCommand");
				
		// 게시글 상세 조회
		map.put("/viewDetailArticleContent.do", "controller.article.DetailArticleCommand");
		
		// 게시글 작성 페이지로 이동
		map.put("/viewWriteArticleForm.do", "controller.article.WriteArticleFormCommand");

		// 게시글 삭제
		map.put("/removeArticle.do", "controller.article.RemoveArticleCommand");

		// 게시글 수정폼이동
		map.put("/viewModifyArticleForm.do", "controller.article.ModifyArticleFormCommand");
		
		// 댓글 목록 불러오기
		map.put("/getReply.do", "controller.reply.RetrieveReplyCommand");
		
		// 댓글 작성
		map.put("/addReply.do", "controller.reply.RegisterReplyCommand");
		// 댓글 수정
		map.put("/modifyReply.do", "controller.reply.ModifyReplyCommand");
		// 댓글 삭제
		map.put("/removeReply.do", "controller.RemoveReplyCommand");
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
