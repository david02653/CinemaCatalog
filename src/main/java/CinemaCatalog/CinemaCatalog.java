package CinemaCatalog;

public class CinemaCatalog {
	public static String getCinemaCatalog(String userID) {
		String result = "{\"status\":\"200\", \"name\":\"使用者名字\", \"notification\":[{\"content\":\"通知內容1\", \"status\":\"unread \"},{\"content\":\"通知內容2\", \"status\":\"unread \"},{\"content\":\"通知內容3\", \"status\":\"unread \"}], \"items\":[{\"ID\":\"1\", \"name\":\"電影名稱1\", \"content\":\"電影介紹1\", \"quantity\":\"30\", \"pic\":\"電影照片url\"},{\"ID\":\"1\", \"name\":\"電影名稱2\", \"content\":\"電影介紹2\", \"quantity\":\"40\", \"pic\":\"電影照片url\"},{\"ID\":\"1\", \"name\":\"電影名稱2\", \"content\":\"電影介紹2\", \"quantity\":\"50\", \"pic\":\"電影照片url\"}]}";
		
		return result;
	}
}
