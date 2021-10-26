package model.grade;

public class GradeVo {
	private int gradeNo;
	private String name;
	private int docs;
	private int comms;
	private int person;
	
	
	public GradeVo() {
		super();
	}

	public GradeVo(int gradeNo, String name, int docs, int comms, int person) {
		super();
		this.gradeNo = gradeNo;
		this.name = name;
		this.docs = docs;
		this.comms = comms;
		this.person = person;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}
	
	
}
