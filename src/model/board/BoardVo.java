package model.board;

import java.util.ArrayList;

public class BoardVo {
	private int boardNo;
	private String boardName;
	private int categoryNo;
	private int readGrade;
	private int writeGrade;
	private ArrayList<BoardVo> boradList = new ArrayList<>();
	
	public BoardVo() {
	}
	
	public BoardVo(String boardName) {
		this.boardName = boardName;
	}

	public BoardVo(int boardNo, String boardName, int categoryNo, int readGrade, int writeGrade) {
		super();
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.categoryNo = categoryNo;
		this.readGrade = readGrade;
		this.writeGrade = writeGrade;
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

	public int getReadGrade() {
		return readGrade;
	}

	public void setReadGrade(int readGrade) {
		this.readGrade = readGrade;
	}

	public int getWriteGrade() {
		return writeGrade;
	}

	public void setWriteGrade(int writeGrade) {
		this.writeGrade = writeGrade;
	}

	public ArrayList<BoardVo> getBoradList() {
		return boradList;
	}

	public void setBoradList(ArrayList<BoardVo> boradList) {
		this.boradList = boradList;
	}
	
	
	
	
	
	
	
}


