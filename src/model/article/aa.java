package model.article;

public class aa {
	public static void main(String[] args) {
		ArticleVo art = new ArticleVo(7, 3, "기린", "긱", "ㅁ");
		
		System.out.println(art.getMemberNo());
		System.out.println(art.getBoardNo());
		System.out.println(art.getNickname());
		System.out.println(art.getSubject());
		System.out.println(art.getContent());
		
	}

}
