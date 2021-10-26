package model.board;

public class BoardVo {
	private int boardNo;
	private String boardName;
	private int categoryNo;
	private String categoryName;
	
	public BoardVo() {
	}
	
	public BoardVo(String boardName) {
		this.boardName = boardName;
	}
	
	public BoardVo(int boardNo, String boardName) {
		this.boardNo = boardNo;
		this.boardName = boardName;
	}
	
	public BoardVo(String boardName, int categoryNo) {
		super();
		this.boardName = boardName;
		this.categoryNo = categoryNo;
	}

	public BoardVo(int boardNo, String boardName, int categoryNo) {
		super();
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.categoryNo = categoryNo;
	}

	public BoardVo(int boardNo, String boardName, int categoryNo, String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}

	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardName() {
		return boardName;
	}


	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	public int getCategoryNo() {
		return categoryNo;
	}


	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	
	
	
	
	
	
}


