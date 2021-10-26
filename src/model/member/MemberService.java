package model.member;

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

	public MemberVo retreiveMemberProfile(int member_no) throws Exception {
		System.out.println("retreiveMemberProfile");
		return MemberDao.getInstance().selectMemberProfile(member_no);
	}
	
	public boolean isAlreadyMember(String email) throws Exception {
		
		if( MemberDao.getInstance().selectMember(email) == null )
			return false;
		
		return true;
	}
}
