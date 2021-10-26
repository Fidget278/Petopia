package model.category;

import java.util.ArrayList;

import model.board.BoardVo;


public class CategoryVo {
	
	private int categoryNo;
	private String categoryName;
	private String boardName;
	private ArrayList<CategoryVo> categoryList = new ArrayList<>();
	private ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
	
	public CategoryVo() {
	}
	
	public CategoryVo(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public CategoryVo(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}

	public CategoryVo(String categoryName, String boardName) {
		this.categoryName = categoryName;
		this.boardName = boardName;
	}
	
	public CategoryVo(int categoryNo, String categoryName, String boardName, ArrayList<CategoryVo> categoryList) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.boardName = boardName;
		this.categoryList = categoryList;
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
	
	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public ArrayList<CategoryVo> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList<CategoryVo> categoryList) {
		this.categoryList = categoryList;
	}
	
	public ArrayList<BoardVo> getBoardList() {
		return boardList;
	}

	public void setBoardList(ArrayList<BoardVo> boardList) {
		this.boardList = boardList;
	}

	public void addBoardList(BoardVo board) {
		this.boardList.add(board);
	}
	

	
}
