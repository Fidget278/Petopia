package model.member;

public class PhotoVo {
	
	private int no;
	private String originalFileName;
	private String systemFileName;
	private int fileSize;
	
	public PhotoVo() {
		super();
	}

	public PhotoVo(int no, String originalFileName, String systemFileName, int fileSize) {
		super();
		this.no = no;
		this.originalFileName = originalFileName;
		this.systemFileName = systemFileName;
		this.fileSize = fileSize;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getSystemFileName() {
		return systemFileName;
	}

	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	

}