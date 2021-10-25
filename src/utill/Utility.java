package utill;

public class Utility {

	// JSOn 텍스트 데이터를 전달받아 자바스크립트 객체의 값으로 표현 불가능한 문자를 회피문자로 변환
	public static String toJSON(String source) {
		return source.replace("\\", "\\\\").replace("\"","\\\"").replace("\n", "\\n").replace("\r\n", "\\n");
	}
}
