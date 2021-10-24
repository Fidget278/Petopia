package model.article;

public class ReplyService {

	private static ReplyService replyService;
	
	public static ReplyService getInstance() {
		if(replyService == null) {
			replyService = new ReplyService();
		}
		return replyService;
	}
	
	// 댓글 작성
	public void registerReply(ReplyVo reply) throws Exception{
		try {
			ReplyDao replyDao = ReplyDao.getInstance();
			replyDao.insertReply(reply);
		} catch (Exception e) {
			throw e;
		}
	}
}
