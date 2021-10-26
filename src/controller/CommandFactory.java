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
    
     
    // 회원 목록
		map.put("/viewMemberList.do", "controller.manager.MemberListCommand");
		
		// 회원 검색 시
		map.put("/searchMember.do", "controller.manager.SearchMemberCommand");
		
		// 회원 상세 정보
		map.put("/viewDetailMember.do", "controller.manager.DetailMemberCommand");
		
		// 회원 정지 기간 적용
		map.put("/modifyBan.do", "controller.manager.ModifyBanCommand");
		
		
		//등급 조회 시
		map.put("/viewGradeList.do", "controller.grade.GradeListCommand");
		
		// 등급 수정 시
		map.put("/modifyGradeList.do", "controller.grade.ModifyGradeListCommand");
		
		// 등급 확인 시
		map.put("/viewGradeBoardList.do", "controller.grade.GradeBoardListCommand");
		
		// 쪽지
		map.put("/noteList.do", "controller.NoteListFormCommand");
		map.put("/noteDetailBoard.do", "controller.NoteDetailFormCommand");
		map.put("/writeNote.do", "controller.WriteNoteCommand");
		map.put("/deleteNote.do", "controller.NoteDeleteCommand");
		map.put("/sendMail.do", "controller.MailCommand");
		
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
		
		//게시글 검색
		map.put("/searchAjax.do", "controller.article.SearchAjaxCommand");
		

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
		//게시판 삭제
		map.put("/removeBoard.do", "controller.board.RemoveBoardCommand");
		

				

		/* ----------------------댓글------------------------------------- */
		map.put("/writeReply.do", "controller.reply.RegisterReplyCommand");

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
			e.printStackTrace();
			throw e;
		}

	}
}

