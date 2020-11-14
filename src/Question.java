
public class Question {
	protected int questionID;
	protected String question, tags, date, userName;
	public Question(int id, String q, String t, String d, String u) {
		questionID= id;
		question = q;
		tags = t;
		date = d;
		userName = u;
	}
	
	public Question(int id, String q, String t, String d) {
		questionID= id;
		question = q;
		tags = t;
		date = d;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
