package controller.reply;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.article.ReplyVo;

public class RegisterReplyCommand implements Command{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
//		HttpSession session = request.getSession();
//		
//		BufferedReader br = request.getReader();
//		String reqData = br.readLine();
//		//Gson gson = new GSon();
//		ReplyVo reply = gson.fromJson(reqData, ReplyVo.class);
		
		return null;
	}

}
