
public class User {
	protected String EmailID;
	protected String firstName, lastName,  password, dob, gender;
	public String getEmailID() {
		return EmailID;
	}
	
	public User(String email, String f, String l, String p, String d, String g) {
		EmailID= email;
		firstName = f;
		lastName = l;
		password = p;
		dob = d;
		gender = g;
	}
	
	public User(String email, String f, String l, String d, String g) {
		EmailID= email;
		firstName = f;
		lastName = l;
		dob = d;
		gender = g;
	}
	 
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
