package model;

public class NoteVo {
	private int note_no;
	private int counterpart_no;
	private String counterpart_nickname;
	private String content;
	private String sendDate;
	private String readDate;
	private int sendrecieve;
	private boolean read = false;
	
	
	public NoteVo() {
		super();
	}
	public boolean checkRead() {
		if(readDate != null)
			read = true;
		
		return read;
	}
	public int getSendrecieve() {
		return sendrecieve;
	}


	public void setSendrecieve(int sendrecieve) {
		this.sendrecieve = sendrecieve;
	}


	public NoteVo(int note_no, int counterpart_no, String counterpart_nickname, String content, String sendDate,
			String readDate) {
		super();
		this.note_no = note_no;
		this.counterpart_no = counterpart_no;
		this.counterpart_nickname = counterpart_nickname;
		this.content = content;
		this.sendDate = sendDate;
		this.readDate = readDate;
		checkRead();
		
	}

public NoteVo(int note_no, int counterpart_no, String counterpart_nickname, String content, String sendDate,
		String readDate, int sendrecieve) {
	super();
	this.note_no = note_no;
	this.counterpart_no = counterpart_no;
	this.counterpart_nickname = counterpart_nickname;
	this.content = content;
	this.sendDate = sendDate;
	this.readDate = readDate;
	this.sendrecieve = sendrecieve;
	checkRead();
}


	public int getNote_no() {
		return note_no;
	}


	public void setNote_no(int note_no) {
		this.note_no = note_no;
	}


	public int getCounterpart_no() {
		return counterpart_no;
	}


	public void setCounterpart_no(int counterpart_no) {
		this.counterpart_no = counterpart_no;
	}


	public String getCounterpart_nickname() {
		return counterpart_nickname;
	}


	public void setCounterpart_nickname(String counterpart_nickname) {
		this.counterpart_nickname = counterpart_nickname;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSendDate() {
		return sendDate;
	}


	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}


	public String getReadDate() {
		return readDate;
	}


	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	
	
}
