
public class CoolYoutube {
	String url;
	String question;
	String title;
	int questionID;
	
	public CoolYoutube(String url, String question, String title, int id) {
		this.url = url;
		this.question = question;
		this.title = title;
		questionID = id;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
