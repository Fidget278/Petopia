package model.article;

public class ArticleFileVo {
	private int fileNo;
	private int articleNo;
	private String originalFileName;
	private String systemFileName;
	private int fileSize;
	private String fileType;
	
	public ArticleFileVo() {
		
	}

	public ArticleFileVo(int fileNo, int articleNo, String originalFileName, String systemFileName, int fileSize,
			String fileType) {
		super();
		this.fileNo = fileNo;
		this.articleNo = articleNo;
		this.originalFileName = originalFileName;
		this.systemFileName = systemFileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
}
