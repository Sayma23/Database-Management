
public class Video {

	protected String url, title, description, userName, date;
	protected int questionID;
	
	public Video( String u, String t, String d, String user, String date, int q) {

		url = u;
		title = t;
		description = d;
		userName = user;
		this.date = date;
		questionID = q;
	}
	
	public Video( String u, String t, String d, String user, String date) {
		url = u;
		title = t;
		description = d;
		userName = user;
		this.date = date;
	}
	
	public Video(String url, String title) {
		this.url = url;
		this.title = title;
	}




	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	
}
