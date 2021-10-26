package model;

import java.sql.Connection;
import java.util.ArrayList;

import model.member.MemberVo;
import utill.DBConn;

public class NoteService {
	private static NoteService service;

	private NoteService() {
		
	}
	
	public static NoteService getInstance() {
		if (service == null) {
			service = new NoteService();
		}
		return service;
	}
	
	public void registerNote(NoteVo note, MemberVo user) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			NoteDao notedao = NoteDao.getInstance();
			notedao.insertNoteContent(note, conn);
			notedao.insertNote(note, user, conn);
			
			isSuccess = true;
			
		} finally {
			if (conn != null) {
				if (isSuccess) {
					conn.commit();
				} else {
					conn.rollback();
				
				}
				conn.close();
			}
		}
	}
	
	public ArrayList<NoteVo> retriveNoteList(int userNo, int isUserReciever, int pageSize, int startOffset) throws Exception {
		return NoteDao.getInstance().selectNoteList(userNo, isUserReciever, pageSize, startOffset);
	}
	
	public NoteVo retriveNote(int noteNo, int userNo, int isRecieve) throws Exception {
		return NoteDao.getInstance().selectNote(noteNo, userNo, isRecieve);
	}

	public void updateRead(int noteNo, int userNo, int isRecieve) throws Exception {
		NoteDao.getInstance().updateRead(noteNo, userNo, isRecieve);
	}
	
	public int retriveTotalNoteCount(int userNo, int isRecieve) throws Exception {
		return NoteDao.getInstance().selectTotalNoteCount(userNo, isRecieve);
	}
	
	public void removeNote(ArrayList<NoteVo> notelist, int isRecieve) throws Exception {
		Connection conn = null;
		
		boolean isSuccess = false;
		
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			NoteDao notedao = NoteDao.getInstance();
			
			notedao.deleteNote(notelist, conn);
			notedao.updateNoteDeleteOnNoteContent(notelist, isRecieve, conn);
			notedao.deleteNoteContent(conn);
			
			isSuccess = true;
			
		} finally {
			if (conn != null) {
				if (isSuccess) {
					conn.commit();
				} else {
					conn.rollback();
				
				}
				conn.close();
			}
		}

	}
}
