package model.member;

import java.util.ArrayList;

public class MemberService {
	private static MemberService memberService;

	private MemberService() {

	}

	public static MemberService getInstance() {
		if (memberService == null)
			memberService = new MemberService();

		return memberService;
	}
	
	public MemberVo login(String email, String password) throws Exception {
		
		System.out.println("memberService login");
		return MemberDao.getInstance().selectMember(email, password);
	}
	
	
	// 회원 목록 조회
	public ArrayList<MemberVo> retrieveMemberList(int startRow, int memberPerPage) throws Exception {
		return  MemberDao.getInstance().selectMemberList(startRow, memberPerPage);
	}
	
	// 회원의 총 멤버 수 카운트
	public int  retrieveTotalMember() throws Exception {
		return MemberDao.getInstance().selectTotalMember();
	}
	
	// 회원 상세 조회
	public MemberVo retrieveMember(int no) throws Exception {
		return MemberDao.getInstance().selectMemberByManager(no);
	}
	
	// 회원 검색 조회
	public ArrayList<MemberVo> retrieveSearchMember(int startRow, int memberPerPage, String keyfield, String keyword) throws Exception {
		return MemberDao.getInstance().selectSearchMember(startRow, memberPerPage, keyfield, keyword);
	}
	
	// 검색된 회원 수 카운트
	public int retrieveTotalSearchMember(String keyfield, String keyword) throws Exception {
		return MemberDao.getInstance().selectTotalSearchMember(keyfield, keyword);
	}
}
