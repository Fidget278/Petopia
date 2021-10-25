package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoteService;
import model.NoteVo;

public class NoteDeleteCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int isRecieve = Integer.parseInt(request.getParameter("isRecieve"));
		int noteNo = Integer.parseInt(request.getParameter("noteNo"));
		int counterpartNo = Integer.parseInt(request.getParameter("counterpartNo"));
		
		ArrayList<NoteVo> notelist = new ArrayList<NoteVo>();
	
		NoteVo note =  new NoteVo();
		
		note.setSendrecieve(isRecieve);
		note.setNote_no(noteNo);
		note.setCounterpart_no(counterpartNo);
		
		notelist.add(note);
		
		NoteService.getInstance().removeNote(notelist, isRecieve);
		
		System.out.println("받았나요? : " + isRecieve);
		return new ActionForward("/noteList.do?isRecieve="+isRecieve, false);
	}

}
