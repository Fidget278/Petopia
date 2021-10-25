package utill;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import model.article.ArticleFileVo;

public class FileUploadUtils {
	
	// 파일이 저장될 폴더
	public static String UPLOAD_PATH = "";

	public static ArticleFileVo upload(Part part, HttpServletRequest req) throws Exception{
		UPLOAD_PATH = req.getServletContext().getRealPath("/upload");
		System.out.println("UPLOAD_PATH : " + UPLOAD_PATH);
		
		// 전송된 파일의 이름, 업로드한 파일의 이름을 구한다.
		String originalFileName = part.getSubmittedFileName();
		
		// file 객체 생성
		File file = new File(UPLOAD_PATH + "/" + originalFileName);
		String systemFileName = "";
		// File.exists(): 파일의 존재 여부 확인 메소드
		if(file.exists()) {
			// 파일이 있을 때
			systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" +
					UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));;
		} else {
			// 파일이 없을 때
			systemFileName = originalFileName;
		}
		
		// 파일크기를 담아놓을 객체
		int fileSize = (int)part.getSize();
		
		// part의 업로드 데이터를 filename이 지정한 파일에 복사(임시보관)
		part.write(UPLOAD_PATH + "/" + systemFileName);
		// part와 관련된 파일 삭제
		part.delete();
		
		ArticleFileVo articleFile = new ArticleFileVo(originalFileName, systemFileName, fileSize);
		return articleFile;
		
	}
	
}
