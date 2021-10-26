package model.board;
import java.sql.Connection;

import utill.DBConn;

public class BoardService {
	
	private static BoardService boardService;

	private BoardService() {
	}

	public static BoardService getInstance() {
		if (boardService == null) {
			boardService = new BoardService();
		}
		return boardService;
	}
	
	// 게시판을 등록 - 완
		public void registerBoard(BoardVo board, BoardGradeVo boardGrade ) throws Exception {
			Connection conn = null;
			try {
				conn = DBConn.getConnection();
				
				BoardDao boardDao = BoardDao.getInstance();
				int no = boardDao.insertBoard(board);
				
				BoardGradeDao boardGradeDao = BoardGradeDao.getInstance();
				boardGradeDao.insertBoardGrade(no, boardGrade);
				
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (conn != null) conn.close();
				} catch (Exception ex) {
					throw ex;
				}
			}
		}
		
		//게시판 수정 - 완
		public void modifyBoard(BoardVo board, BoardGradeVo boardGrade ) throws Exception {
			Connection conn = null;
			boolean isSuccess = false;
			try {
				conn = DBConn.getConnection();
				conn.setAutoCommit(false);
				
				BoardDao boardDao = BoardDao.getInstance();
				int boardNo = boardDao.updateBoard(board, conn);
				
				BoardGradeDao boardGradeDao = BoardGradeDao.getInstance();
				boardGradeDao.updateBoardGrade(boardNo, boardGrade, conn);
				
				isSuccess=true;
				
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (conn != null) {
						if (isSuccess) {
							conn.commit();
						} else {
							conn.rollback();
						}
						conn.close();
					}
				} catch (Exception ex) {
					throw ex;
				}

			}
		}
		
		//게시판에 속한 게시글 조회
		public int retrieveConnectArticle(int boardNo) throws Exception {
			
			BoardDao boardDao = BoardDao.getInstance();
			int count = boardDao.selectConnectArticle(boardNo);
			return count;
		}
		
		//게시판 삭제
		public void removeBoard(int boardNo) throws Exception {
			Connection conn = null;
			boolean isSuccess = false;
			try {
				conn = DBConn.getConnection();
				conn.setAutoCommit(false);
	
				//게시판_등급 삭제
				BoardGradeDao boardGradeDao = BoardGradeDao.getInstance();
				boardGradeDao.deleteBoardGrade(boardNo, conn);
				
				//게시판 삭제
				BoardDao boardDao = BoardDao.getInstance();
				boardDao.deleteBoard(boardNo, conn);
				isSuccess=true;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				try {
					if (conn != null) {
						if (isSuccess) {
							conn.commit();
						} else {
							conn.rollback();
						}
						conn.close();
					}
				} catch (Exception ex) {
					throw ex;
				}

			}
		}
		
}
