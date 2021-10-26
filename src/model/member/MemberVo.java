package model.member;

public class MemberVo {

	private int no;
	private String email;
	private String password;
	private String nickname;
	private String regDate;
	private String lastDate;
	private String grade;
	private int gradeNo;
	private int docs;
	private int comms;
	private int visits;
	private String ban;
	private int isMember;
	
	
	public MemberVo() {
		super();
	}

	
	
	public int getGradeNo() {
		return gradeNo;
	}



	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}



	public MemberVo(int gradeNo, String email, String password, String nickname) {
		super();
		this.gradeNo = gradeNo;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}
	


	public MemberVo(String email, String grade, String nickname, String regDate, String lastDate, int docs, int comms,
			int visits, String ban, int isMember) {
		super();
		this.email = email;
		this.grade = grade;
		this.nickname = nickname;
		this.regDate = regDate;
		this.lastDate = lastDate;
		this.docs = docs;
		this.comms = comms;
		this.visits = visits;
		this.ban = ban;
		this.isMember = isMember;
	}



	public MemberVo(int no, String email, String regDate, String grade, int visits, String ban) {
		super();
		this.no = no;
		this.email = email;
		this.regDate = regDate;
		this.grade = grade;
		this.visits = visits;
		this.ban = ban;
	}



	

	public MemberVo(int no, String email, String password, String nickname, String regDate, String lastDate,
			String grade, int docs, int comms, int visits, String ban, int isMember) {
		super();
		this.no = no;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.regDate = regDate;
		this.lastDate = lastDate;
		this.grade = grade;
		this.docs = docs;
		this.comms = comms;
		this.visits = visits;
		this.ban = ban;
		this.isMember = isMember;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsMember() {
		return isMember;
	}



	public void setIsMember(int isMember) {
		this.isMember = isMember;
	}



	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getDocs() {
		return docs;
	}

	public void setDocs(int docs) {
		this.docs = docs;
	}

	public int getComms() {
		return comms;
	}

	public void setComms(int comms) {
		this.comms = comms;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}


}
