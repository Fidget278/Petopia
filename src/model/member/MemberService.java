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
	
	public void registerMember(MemberVo mVo) throws Exception {
		MemberDao.getInstance().insertMember(mVo);	
	}
	
	public void modifyPassword(int memNo, String newPassword) throws Exception {
		MemberDao.getInstance().updatePassword(memNo, newPassword);	
	}


	}

