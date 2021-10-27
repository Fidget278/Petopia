package model.grade;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utill.DBConn;

public class GradeService {

	private static GradeService gradeService;
	
	private GradeService() {
		
	}
	
	public static GradeService getInstance() {
		if(gradeService == null) {
			gradeService = new GradeService();
		}
		return gradeService;
	}
	
	// 등급 조회
	public ArrayList<GradeVo> retrieveGradeList() throws Exception {
		return GradeDao.getInstance().selectGradeList();
	}
	
	// 등급 수정
	public void modifyGrade(GradeVo grade) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			GradeDao.getInstance().updateGrade(grade, conn);
			
			isSuccess = true;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if(isSuccess) {
						conn.commit();
					}
					else {
						conn.rollback();
					}
					conn.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// 등급 게시판 열람
	public HashMap<String, ArrayList<String>> retrieveGradeBoardList(int gradeNo) throws Exception {
		return GradeDao.getInstance().selectGradeBoardList(gradeNo);
	}
	
	// 등급 삭제 시
	public void  removeGrade(int no) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			GradeDao.getInstance().deleteGrade(no, conn);
			isSuccess = true;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if(isSuccess) {
						conn.commit();
					}
					else {
						conn.rollback();
					}
					conn.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
	// 등급 번호 조회
	public ArrayList<Integer> retrieveGradeNumber() throws Exception {
		return GradeDao.getInstance().selectGradeNumber();
	}
}
