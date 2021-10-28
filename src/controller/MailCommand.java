package controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberService;
import model.member.MemberVo;

public class MailCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String email = request.getParameter("email");
		MemberVo member = MemberService.getInstance().isAlreadyMember(email);

		if (member == null) {
			request.setAttribute("isSuccess", 2);
			request.setAttribute("failText", "가입되지 않은 이메일 입니다.");
			return new ActionForward("/mailResult.jsp", false);
		} else {
			request.setAttribute("member", member);
		}

		String host = "smtp.naver.com";

		String user = "studydev1234@naver.com"; // �ڽ��� ���̹� ����
		String password = "Qwer1234+";// �ڽ��� ���̹� �н�����

		// ���� ���� �ּ�
		String to_email = email;

		// SMTP ���� ������ �����Ѵ�.
		Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		// props.put("defaultEncoding", "utf-8");
		props.put("mail.smtp.ssl.trust", host);

		// ���� ��ȣ ������
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String AuthenticationKey = temp.toString();
		System.out.println(AuthenticationKey);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		System.out.println(session);
		// email ����
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "PETOPIA"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			msg.setSubject("반려동물 카페 PETOPIA 비밀번호 재설정 인증 메일입니다.");
			msg.setText("인증번호 :" + temp + "입니다.");

			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);

		request.setAttribute("isSuccess", 1);
		request.setAttribute("code", AuthenticationKey);
		return new ActionForward("/mailResult.jsp", false);
	}

}
