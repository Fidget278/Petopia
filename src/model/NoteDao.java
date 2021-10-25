package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utill.DBConn;

public class NoteDao {
	public static NoteDao noteDao;

	private NoteDao() {
		super();
	}

	public static NoteDao getInstance() {
		if (noteDao == null)
			noteDao = new NoteDao();

		return noteDao;
	}

	public void insertNoteContent(NoteVo note, Connection conn) throws Exception {

		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO noteContent(content) VALUES(?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, note.getContent());

			pstmt.executeUpdate();

			pstmt.close();

			stmt = conn.createStatement();

			sql.delete(0, sql.length());
			sql.append("SELECT LAST_INSERT_ID()");
			rs = stmt.executeQuery(sql.toString());

			if (rs.next()) {
				note.setNote_no(rs.getInt(1));
			}

		} finally {
			DBConn.close(null, stmt, rs);
		}
	}

	public void insertNote(NoteVo note, int memberNo, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO note(note_no, member_no, counterpart_no, counterpart_nickname, sendrecieve) ");
			sql.append("value(?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, note.getNote_no());
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, note.getCounterpart_no());
			pstmt.setString(4, note.getCounterpart_nickname());
			pstmt.setInt(5, note.getSendrecieve());

			pstmt.executeUpdate();

			sql.delete(0, sql.length());

			sql.append("INSERT INTO note(note_no, member_no, counterpart_no, counterpart_nickname, sendrecieve) ");
			sql.append("value(?, ?, ?, ?, ?)");

			pstmt.setInt(1, note.getNote_no());
			pstmt.setInt(2, note.getCounterpart_no());
			pstmt.setInt(3, memberNo);
			pstmt.setString(4, note.getCounterpart_nickname());

			if (note.getSendrecieve() == 1)
				pstmt.setInt(5, 0);
			else
				pstmt.setInt(5, 1);

			pstmt.executeUpdate();

		} finally {
			DBConn.close(null, pstmt, rs);
		}
	}

	public NoteVo selectNote(int noteNo, int memberNo, int isRecieve) throws Exception {
		NoteVo note = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT note.note_no, note.counterpart_no, note.counterpart_nickname, notecontent.content, ");
			sql.append("note.sendDate, note.readDate, note.sendrecieve   ");
			sql.append("FROM note, notecontent ");
			sql.append("where note.member_no = ? and note.sendrecieve = ? and note.note_no = ? and note.note_no = notecontent.note_no");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, isRecieve);
			pstmt.setInt(3, noteNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				note = new NoteVo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}

		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return note;
	}

	public ArrayList<NoteVo> selectNoteList(int userNo, int isUserReciever) throws Exception {
		ArrayList<NoteVo> notelist = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT note.note_no, note.counterpart_no, note.counterpart_nickname, notecontent.content, ");
			sql.append("note.sendDate, note.readDate, note.sendrecieve  ");
			sql.append("FROM note, notecontent ");
			sql.append("where note.member_no = ? and note.sendrecieve = ? and note.note_no = notecontent.note_no");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, userNo);
			pstmt.setInt(2, isUserReciever);
			rs = pstmt.executeQuery();

			notelist = new ArrayList<NoteVo>();

			while (rs.next()) {
				String content = rs.getString(4);
				// content = content.substring(0, content.length()-(content.length() - 3));
				// content.concat(".................");

				NoteVo note = new NoteVo(rs.getInt(1), rs.getInt(2), rs.getString(3), content, rs.getString(5),
						rs.getString(6), rs.getInt(7));

				notelist.add(note);
				// content = content.substring(0, content.length()-(content.length() - 3));
				// content.concat(".................");

			}
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return notelist;
	}

	public void updateRead(int noteNo, int userNo, int isRecieve) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE note ");
			sql.append("SET readDate = NOW() ");
			sql.append("WHERE note.note_no = ? and member_no = ? and note.sendrecieve = ?");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, noteNo);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, isRecieve);
			

			pstmt.executeUpdate();
			pstmt.close();
			
			sql.delete(0,  sql.length());
			sql.append("UPDATE note ");
			sql.append("SET readDate = NOW() ");
			sql.append("WHERE note.note_no = ? and counterpart_no = ? and note.sendrecieve = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, noteNo);
			pstmt.setInt(2, userNo);
			if(isRecieve == 1)
				pstmt.setInt(3, 0);
			else
				pstmt.setInt(3, 1);
			
			pstmt.executeUpdate();
			
		} finally {
			DBConn.close(conn, pstmt, null);
		}

	}

	public void deleteNote(ArrayList<NoteVo> notelist, Connection conn) throws Exception {

		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM note WHERE note_no = ? and counterpart_no = ? and sendrecieve = ? ");
			pstmt = conn.prepareStatement(sql.toString());

			for (NoteVo note : notelist) {
				pstmt.setInt(1, note.getNote_no());
				pstmt.setInt(2, note.getCounterpart_no());
				pstmt.setInt(3, note.getSendrecieve());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
		} finally {
			DBConn.close(null, pstmt, null);
		}
	}

	public void updateNoteDeleteOnNoteContent(ArrayList<NoteVo> notelist, int isRecieve, Connection conn)
			throws Exception {

		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			if (isRecieve == 1)
				sql.append("UPDATE notecontent SET recieveDelete = 1 WHERE note_no = ?");
			else if (isRecieve == 0)
				sql.append("UPDATE notecontent SET sendDelete = 1 WHERE note_no = ?");

			pstmt = conn.prepareStatement(sql.toString());

			for (NoteVo note : notelist) {
				pstmt.setInt(1, note.getNote_no());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
		} finally {
			DBConn.close(null, pstmt, null);
		}
	}
	
	public void deleteNoteContent(Connection conn)
			throws Exception {

//		Statement stmt = null;
//
//		try {
//			conn = DBConn.getConnection();
//
//			StringBuffer sql = new StringBuffer();
//			
//			sql.append("DELETE FROM notecontent WHERE recieveDelete = 1 and sendDelete = 1 ");
//
//			stmt = conn.createStatement();
//			stmt.executeQuery(sql.toString());
//			
//		} finally {
//			DBConn.close(null, stmt, null);
//		}
	}
}
