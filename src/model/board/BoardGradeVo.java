package model.board;
//깃 - 
import java.util.ArrayList;

public class BoardGradeVo {

	private int boardNo;
	private int readGrade;
	private int WriteGrade;
	private int readwrite;
	ArrayList<BoardGradeVo> baordGradeList = new ArrayList<>();

	public BoardGradeVo() {
	}
	
	public BoardGradeVo(int readGrade, int writeGrade) {
		this.readGrade = readGrade;
		this.WriteGrade = writeGrade;
	}

	public BoardGradeVo(int boardNo, int readGrade, int writeGrade, int readwrite,
			ArrayList<BoardGradeVo> baordGradeList) {
		super();
		this.boardNo = boardNo;
		this.readGrade = readGrade;
		WriteGrade = writeGrade;
		this.readwrite = readwrite;
		this.baordGradeList = baordGradeList;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getReadGrade() {
		return readGrade;
	}

	public void setReadGrade(int readGrade) {
		this.readGrade = readGrade;
	}

	public int getWriteGrade() {
		return WriteGrade;
	}

	public void setWriteGrade(int writeGrade) {
		WriteGrade = writeGrade;
	}

	public int getReadwrite() {
		return readwrite;
	}

	public void setReadwrite(int readwrite) {
		this.readwrite = readwrite;
	}

	public ArrayList<BoardGradeVo> getBaordGradeList() {
		return baordGradeList;
	}

	public void setBaordGradeList(ArrayList<BoardGradeVo> baordGradeList) {
		this.baordGradeList = baordGradeList;
	}

}
