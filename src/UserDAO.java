import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/PeopleDAO")
public class UserDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public UserDAO() {

    }
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public List<People> listAllPeople() throws SQLException {
        List<People> listPeople = new ArrayList<People>();        
        String sql = "SELECT * FROM student";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String status = resultSet.getString("status");
             
            People people = new People(id,name, address, status);
            listPeople.add(people);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listPeople;
    }
    
    public List<User> listAllUser() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "SELECT * FROM user";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String id = resultSet.getString("EmailID");
            String name = resultSet.getString("First_name");
            String address = resultSet.getString("Last_name");
            String status = resultSet.getString("gender");
            String dob = resultSet.getString("DOB");
             
            User people = new User(id, name, address, dob, status);
            listUser.add(people);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insert(People people) throws SQLException {
    	connect_func();         
		String sql = "insert into  student(Name, Address, Status) values (?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, people.name);
		preparedStatement.setString(2, people.address);
		preparedStatement.setString(3, people.status);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }     
     
    public boolean delete(int peopleid) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, peopleid);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
     
    public boolean update(People people) throws SQLException {
        String sql = "update student set Name=?, Address =?,Status = ? where id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, people.name);
        preparedStatement.setString(2, people.address);
        preparedStatement.setString(3, people.status);
        preparedStatement.setInt(4, people.id);
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
	
    public People getPeople(int id) throws SQLException {
    	People people = null;
        String sql = "SELECT * FROM student WHERE id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String status = resultSet.getString("status");
             
            people = new People(id, name, address, status);
        }
         
        resultSet.close();
        statement.close();
         
        return people;
    }
    
    //############################## DIY ########################################
    
    public void createTableForRoot() throws SQLException{
    	 System.out.println("create table for root");
    	 Statement statement = null;
     	 String sql1 = "DROP TABLE IF EXISTS User";
     	 String sql2= "Drop Table IF EXISTS VIDEO";
     	 String sql3 = "Drop table if exists Question";
     	 String sql4 = "Drop table if exists Favourites";
     	 String sql5= "Drop table if exists Review";
     	 String sql5_= "Drop table if exists Question_tags";
     	 
         String sql6 = "create TABLE if not exists User(EmailID varchar(30), First_name varchar(30), " + 
         		    			 "Last_name varchar(30), Password varchar(30), DOB DATE, " + 
         				" Gender char(7), primary key(EmailID))"; 
         
         String sql7 = "create table if not exists Video(Url varchar(50), title varchar(100),"
        		 + " description varchar(200), posting_date DATE, posted_by varchar(30) not null, "
        		 + " answer_to integer not null, primary key(Url), foreign key(posted_by) references User(EmailID),"
        		 + " foreign key(answer_to) references question(QuestionID))";
         
         String sql8 = "create table if not exists Question (QuestionID integer not null auto_increment, question varchar(50),"
        		 + " tags varchar(50), posting_date DATE, asked_by varchar(30) not null, primary key(QuestionID),"
        		 + "foreign key(asked_by) references User(EmailID))";
         
         String sql9 = "create table if not exists favourites(EmailID varchar(30), Url varchar(50), "
        		 + " foreign key(EmailID) references User(EmailID), foreign key(Url) references Video(Url), "
        		 + "unique(EmailID, Url))";
         
         String sql10 = "create table if not exists review(review_date DATE, score char(10),"
        		 + " remark varchar(100), EmailID varchar(30), Url varchar(50), Unique(EmailID, Url), "
        		 + "foreign key(EmailID) references User(EmailID), foreign key(Url) references Video(Url))";
         
         String sql10_ = "create table if not exists Question_Tags (QuestionID integer, tag varchar(50), primary key("
         		+ " QuestionID, tag) , foreign key(QuestionID) references Question(QuestionID))";
         
//         String sql_trigger1= "create trigger video_poster after insert as " + 
//         		" begin " + 
//         		"    if exists ( select posting_date, posted_by, count(*) from video group by posting_date, posted_by > 3 ) " + 
//         		"    begin " + 
//         		"        rollback transaction " + 
//         		"        raiserror ('undone', 16, 1) " + 
//         		"    end " + 
//         		"end";
//  
         //insert into user table
         String sql11 = "INSERT INTO User VALUES ('sayma@wayne.edu', 'Sayma', 'Sultana', 'abcd123', '2004-01-22', 'Female') ";
         String sql12 = "INSERT INTO User VALUES ('dathri@wayne.edu', 'Dathri', 'Yeddula', 'abcd123', '2004-06-22', 'Female') ";
         String sql13 = "INSERT INTO User VALUES ('sean@gmail.com', 'Sean', 'Turner', 'abcd123', '2004-06-20', 'Male') ";
         String sql14 = "INSERT INTO User VALUES ('nick@yahoo.com', 'Nick', 'Jonas', 'abcd12356', '2000-03-22', 'Male') ";
         String sql15 = "INSERT INTO User VALUES ('aditi@gmail.com', 'Aditi', 'Chowdhury', 'abcd123', '2001-09-22', 'Female') ";
         String sql16 = "INSERT INTO User VALUES ('jack_1234@wayne.edu', 'Jackob', 'Nishanian', 'abcd123', '1998-06-22', 'Male') ";
         String sql17 = "INSERT INTO User VALUES ('ashley@gmail.com', 'Ashley', 'Chopra', 'abcd123', '2005-06-19', 'Female') ";
         String sql18 = "INSERT INTO User VALUES ('dexter@wayne.edu', 'Dexter', 'Robinson', 'abcd123', '2001-06-22', 'Male') ";
         String sql19 = "INSERT INTO User VALUES ('sarah@gmail.com', 'Sarah', 'Ahmed', 'abcd123', '2002-01-22', 'Female') ";
         String sql20 = "INSERT INTO User VALUES ('navin@wayne.edu', 'Navin', 'Chakrabarty', 'abcd123', '1997-08-15', 'Male') ";
         
         //insert into question table
         String sql21 = "INSERT INTO question (question, tags, posting_date, asked_by) values( 'How to paint a wall', 'paint, brush, wall', '2020-02-02', 'navin@wayne.edu')";
         String sql22 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint full room', 'paint,room,full', '2004-04-10', 'dathri@wayne.edu') ";
         String sql23 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint half a room', 'paint,room,half', '1990-10-05', 'sayma@wayne.edu') ";
         String sql24 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint glitter in a room', 'paint,room,glitter', '2002-11-10', 'sayma@wayne.edu') ";
         String sql25 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to painta matte finish room', 'paint,room,matte', '2002-10-17', 'dathri@wayne.edu') ";
         String sql26 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint a grafiti inroom', 'paint,room,grafiti', '2004-08-10', 'sean@gmail.com') ";
         String sql27 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint a abstract room', 'paint,room,abstract', '1990-10-10', 'aditi@gmail.com') ";
         String sql28 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint a babies room', 'paint,room,babies', '1997-02-03', 'aditi@gmail.com') ";
         String sql29 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint a dogs room', 'paint,room,dogs', '1995-02-10', 'ashley@gmail.com') ";
         String sql30 = "INSERT INTO question (question, tags, posting_date, asked_by) VALUES ( 'How to paint a mirror room', 'paint,room,mirror', '2002-01-22', 'navin@wayne.edu') ";
         
         //insert into video table
         String sql31 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/CRXCB_3gLok', 'How to paint a room', 'This video shows how to paint a room in detail', '2002-10-10', 'sayma@wayne.edu', 1) ";
         String sql32 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/X9q3FKNEyCw', 'How to paint full room', 'This video shows how to paint a room in detail', '2004-04-10', 'dathri@wayne.edu', 2) ";
         String sql33 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/jbG-fWFuEpc', 'How to paint half a room', 'This video shows how to paint half a room in detail', '1990-10-05', 'sayma@wayne.edu', 3) ";
         String sql34 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/PfoG04-2glc', 'How to paint glitter in a room', 'This video shows how to paint a room in detail with glitter', '2002-11-10', 'sayma@wayne.edu', 4) ";
         String sql35 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/Fdy9uRvpI-E', 'How to painta matte finish room', 'This video shows how to paint a room in detail with matte finish', '2002-10-17', 'dathri@wayne.edu', 5) ";
         String sql36 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/4oGVtzTZAuM', 'How to paint a grafiti room', 'This video shows how to paint a room in detail with grafiti', '2004-08-10', 'sean@gmail.com', 5) ";
         String sql37 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/RlNsz5eW6tk', 'How to paint a abstract room', 'This video shows how to paint a room in detail with abstract art', '1990-10-10','aditi@gmail.com', 4) ";
         String sql38 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/r7Ya3wwhDe8', 'How to paint a babies room', 'This video shows how to paint a babies room in detail', '1997-02-03', 'aditi@gmail.com', 9) ";
         String sql39 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/uwBT14dAdHg', 'How to paint a dogs room', 'This video shows how to paint a  dogs room in detail', '1995-02-10', 'ashley@gmail.com', 8) ";
         String sql40 = "INSERT INTO Video VALUES ('https://www.youtube.com/embed/R9AhRgyr61c', 'How to paint a mirror room', 'This video shows how to paint a mirror room in detail', '2002-01-22', 'navin@wayne.edu', 10) ";
         
         
         //insert into review table
         String sql41 = "INSERT INTO review VALUES ('2003-11-10', 'Poor','No remark','navin@wayne.edu', 'https://www.youtube.com/embed/CRXCB_3gLok') ";
         String sql42 = "INSERT INTO review VALUES ('2004-05-10', 'Fair','Their was no detailed explaination of the mixture','sayma@wayne.edu','https://www.youtube.com/embed/X9q3FKNEyCw') ";
         String sql43 = "INSERT INTO review VALUES ('1991-10-05', 'Good','No remarks','dathri@wayne.edu','https://www.youtube.com/embed/jbG-fWFuEpc') ";
         String sql44 = "INSERT INTO review VALUES ('2002-11-11', 'Excellent','The glitter and colour match was too pretty to watch','sean@gmail.com','https://www.youtube.com/embed/PfoG04-2glc') ";
         String sql45 = "INSERT INTO review VALUES ('2002-10-17', 'Poor' ,'Not to satisfaction level','sean@gmail.com','https://www.youtube.com/embed/Fdy9uRvpI-E') ";
         String sql46 = "INSERT INTO review VALUES ('2005-08-10', 'Excellent' ,'Their is grafiti in the paining','sayma@wayne.edu','https://www.youtube.com/embed/R9AhRgyr61c') ";
         String sql47 = "INSERT INTO review VALUES ('1991-10-10', 'Good','Could have used more colours','aditi@gmail.com','https://www.youtube.com/embed/Fdy9uRvpI-E') ";
         String sql48 = "INSERT INTO review VALUES ('1997-02-03', 'Fair','Colour combo is the best','sean@gmail.com','https://www.youtube.com/embed/r7Ya3wwhDe8') ";
         String sql49 = "INSERT INTO review VALUES ('1995-02-10', 'Excellent','My dog like it vry much','dathri@wayne.edu','https://www.youtube.com/embed/Fdy9uRvpI-E') ";
         String sql50 = "INSERT INTO review VALUES ('2002-01-23', 'Excellent','Really pretty and too good','ashley@gmail.com','https://www.youtube.com/embed/r7Ya3wwhDe8') ";
         
         
         //insert into favour table
         String sql51 = "INSERT INTO favourites VALUES ('navin@wayne.edu','https://www.youtube.com/embed/CRXCB_3gLok') ";
         String sql52 = "INSERT INTO favourites VALUES ('sayma@wayne.edu','https://www.youtube.com/embed/X9q3FKNEyCw') ";
         String sql53 = "INSERT INTO favourites VALUES ('dathri@wayne.edu','https://www.youtube.com/embed/jbG-fWFuEpc') ";
         String sql54 = "INSERT INTO favourites VALUES ('sean@gmail.com','https://www.youtube.com/embed/PfoG04-2glc') ";
         String sql55 = "INSERT INTO favourites VALUES ('sean@gmail.com','https://www.youtube.com/embed/Fdy9uRvpI-E') ";
         String sql56 = "INSERT INTO favourites VALUES ('sayma@wayne.edu','https://www.youtube.com/embed/4oGVtzTZAuM') ";
         String sql57 = "INSERT INTO favourites VALUES ('aditi@gmail.com','https://www.youtube.com/embed/RlNsz5eW6tk') ";
         String sql58 = "INSERT INTO favourites VALUES ('sean@gmail.com','https://www.youtube.com/embed/r7Ya3wwhDe8') ";
         String sql59 = "INSERT INTO favourites VALUES ('ashley@gmail.com', 'https://www.youtube.com/embed/uwBT14dAdHg') ";
         String sql60 = "INSERT INTO favourites VALUES ('ashley@gmail.com','https://www.youtube.com/embed/R9AhRgyr61c') ";
         
         
         //insert into tags table
         //String sql61 = "Insert into tags values ("
         //String sql61 = "create assertion at_most_three_ques as check (3 >= all (select count(questionID) as number "
         		//+ " from question group by asked_by, posting_date)) ";
         
         
         connect_func();
         statement =  (Statement) connect.createStatement();
        
         //drop tables
         statement.executeUpdate(sql5_);
         statement.executeUpdate(sql5);
         statement.executeUpdate(sql4);
         statement.executeUpdate(sql2);
         statement.executeUpdate(sql3);
         statement.executeUpdate(sql1);
         System.out.println("tables dropped successfully.....");
         
         //create tables
         statement.executeUpdate(sql6);
         statement.executeUpdate(sql8);
         statement.executeUpdate(sql7);
         statement.executeUpdate(sql9);
         statement.executeUpdate(sql10);
         statement.executeUpdate(sql10_);
         System.out.println("tables created successfully....");
         
         
         //create assertions
         
         
         
         //insert into user database
         statement.executeUpdate(sql11);
         statement.executeUpdate(sql12);
         statement.executeUpdate(sql13);
         statement.executeUpdate(sql14);
         statement.executeUpdate(sql15);
         statement.executeUpdate(sql16);
         statement.executeUpdate(sql17);
         statement.executeUpdate(sql18);
         statement.executeUpdate(sql19);
         statement.executeUpdate(sql20);
         
         //insert into question table
         statement.executeUpdate(sql21);
         statement.executeUpdate(sql22);
         statement.executeUpdate(sql23);
         statement.executeUpdate(sql24);
         statement.executeUpdate(sql25);
         statement.executeUpdate(sql26);
         statement.executeUpdate(sql27);
         statement.executeUpdate(sql28);
         statement.executeUpdate(sql29);
         statement.executeUpdate(sql30);
         
         
         //insert into video table
         statement.executeUpdate(sql31);
         statement.executeUpdate(sql32);
         statement.executeUpdate(sql33);
         statement.executeUpdate(sql34);
         statement.executeUpdate(sql35);
         statement.executeUpdate(sql36);
         statement.executeUpdate(sql37);
         statement.executeUpdate(sql38);
         statement.executeUpdate(sql39);
         statement.executeUpdate(sql40);
         
         
         ////////
         statement.executeUpdate(sql41);
         statement.executeUpdate(sql42);
         statement.executeUpdate(sql43);
         statement.executeUpdate(sql44);
         statement.executeUpdate(sql45);
         statement.executeUpdate(sql46);
         statement.executeUpdate(sql47);
         statement.executeUpdate(sql48);
         statement.executeUpdate(sql49);
         statement.executeUpdate(sql50);
         
         
         ///////
         statement.executeUpdate(sql51);
         statement.executeUpdate(sql52);
         statement.executeUpdate(sql53);
         statement.executeUpdate(sql54);
         statement.executeUpdate(sql55);
         statement.executeUpdate(sql56);
         statement.executeUpdate(sql57);
         statement.executeUpdate(sql58);
         statement.executeUpdate(sql59);
         statement.executeUpdate(sql60);
         
         
         System.out.println("insert into table succesful");
         
         
         //assertion and trigger
         //statement.executeUpdate(sql61);
//         System.out.println("read from table");
//        
//         while (resultSet.next()) {
//        	 
//             System.out.println("Remark??????//: " + resultSet.getString("number") );
//         }
//         
         

    	 
//    	 
    	 String sql100 = "select * from User ";
         ResultSet resultSet = statement.executeQuery(sql100);
         System.out.println("read from table");
        
         while (resultSet.next()) {
        	 String email, f_name, l_name;
             email = resultSet.getString("EmailID");
             f_name = resultSet.getString("First_name");
             l_name = resultSet.getString("Last_name");
             System.out.println("email : " + email + " first name: " + f_name + " last name: " + l_name);
         }
         
         String sql200 = "select * from question ";
         resultSet = statement.executeQuery(sql200);
         System.out.println("read from question table");
        
         while (resultSet.next()) {
        	 String email, f_name, l_name;
             email = resultSet.getString("questionID");
             f_name = resultSet.getString("question");
             l_name = resultSet.getString("tags");
             System.out.println("questionid : " + email + " question: " + f_name + " tags: " + l_name);
         }
         
         
         String sql300 = "select * from video ";
         resultSet = statement.executeQuery(sql300);
         System.out.println("read from video table");
        
         while (resultSet.next()) {
        	 String email, f_name, l_name;
             email = resultSet.getString("url");
             f_name = resultSet.getString("title");
             l_name = resultSet.getString("description");
             System.out.println("Url : " + email + " Title: " + f_name + " Description: " + l_name);
         }
         
         
         String sql400 = "select * from review ";
         resultSet = statement.executeQuery(sql400);
         System.out.println("read from review table");
        
         while (resultSet.next()) {
        	 String email, f_name, l_name;
             email = resultSet.getString("emailID");
             f_name = resultSet.getString("score");
             l_name = resultSet.getString("remark");
             System.out.println("email : " + email + " score: " + f_name + " review: " + l_name);
         }
         
         
         String sql500 = "select * from favourites ";
         resultSet = statement.executeQuery(sql500);
         System.out.println("read from favour table");
        
         while (resultSet.next()) {
        	 String email, f_name;
             email = resultSet.getString("emailID");
             f_name = resultSet.getString("url");
             System.out.println("email : " + email + " url: " + f_name );
         }
         
         resultSet.close();
         statement.close();         
         disconnect(); 
         //resultSet.close();
        
          
    }
    
    
    public boolean loginUser(String email, String password)throws SQLException, java.text.ParseException{
    	
    	connect_func();         
		String sql = "select * from  user where EmailID=?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        String pass="";
        if (resultSet.next()) {
            pass = resultSet.getString("password");
        }
         
        resultSet.close();
        preparedStatement.close();
     
    
    	return pass.length()>0 && password.equals(pass) ;
    }
    
    public boolean checkForExistingUser(String email) throws SQLException, java.text.ParseException {
    	connect_func();         
    	connect_func();         
		String sql = "select * from  user where EmailID=?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean doesExist = resultSet.next();
         
        resultSet.close();
        preparedStatement.close();
     
    
    	return doesExist ;
    }     
    
    public boolean insertNewUser(User user) throws SQLException, java.text.ParseException {
    	connect_func();         
		String sql = "insert into  user(EmailID, First_name, Last_name, Password, DOB, Gender) values (?, ?, ?, ?, ?, ?)";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		String dateInString = user.dob;
		java.util.Date date = (java.util.Date) formatter.parse(dateInString);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.EmailID);
		preparedStatement.setString(2, user.firstName); 
		preparedStatement.setString(3, user.lastName);
		preparedStatement.setString(4, user.password);
		preparedStatement.setDate(5, sqlDate);
		preparedStatement.setString(6, user.gender);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        
        
   	 	String sql21 = "select * from User ";
   	    statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql21);
        System.out.println("read from table");
       
        while (resultSet.next()) {
       	 String email, f_name, l_name;
            email = resultSet.getString("EmailID");
            f_name = resultSet.getString("First_name");
            l_name = resultSet.getString("Last_name");
            System.out.println("email : " + email + " first name: " + f_name + " last name: " + l_name);
        }
        
        resultSet.close();
        statement.close(); 
//        disconnect();
        System.out.println("data inserted in user table ");
        return rowInserted;
    } 
    
    public boolean insertNewQuestion(String question, String tags, String user) throws SQLException, java.text.ParseException {
    	connect_func();         
		String sql = "INSERT INTO question (question, tags, posting_date, asked_by) values( ?, ?, ?, ?)";
		long millis=System.currentTimeMillis();  
		//java.sql.Date date=new java.sql.Date(millis);  
		java.sql.Date sqlDate = new java.sql.Date(millis);
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, question);
		preparedStatement.setString(2, tags); 
		preparedStatement.setDate(3, sqlDate);
		preparedStatement.setString(4, user);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        
        
   	 	String sql21 = "select * from question ";
   	    statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql21);
        System.out.println("read from table");
       
        while (resultSet.next()) {
       	 String email, f_name, l_name, q_ID, asked_by;
       	 	q_ID = resultSet.getString("questionID");
            email = resultSet.getString("question");
            f_name = resultSet.getString("tags");
            l_name = resultSet.getDate("posting_date").toString();
            asked_by  = resultSet.getString("asked_by");
            System.out.println("ID: "+ q_ID + " question : " + email + " tags: " + f_name + " posting date: " + l_name
            		+ " asked by: " + asked_by );
        }
        
        resultSet.close();
        statement.close(); 
//        disconnect();
        System.out.println("data inserted in user table ");
        return rowInserted;
    }
    
    public List<Question> listAllQuestion() throws SQLException {
    	System.out.println(" hello vaiji ,, return question list");
        List<Question> listQuestion = new ArrayList<Question>();        
        String sql = "SELECT * FROM question";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        
         
        while (resultSet.next()) {
            int id = resultSet.getInt("QuestionID");
            String question = resultSet.getString("question");
            String tags = resultSet.getString("tags");
            String date = resultSet.getDate("posting_date").toString();
            String user = resultSet.getString("asked_by");
            System.out.println(" Id: " + id + " Question: " + question);
            Question q = new Question(id, question, tags, date, user);
            listQuestion.add(q);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listQuestion;
    }
    
    public Question fetchQuestion(int id) throws SQLException {
    
    	Question q = null;
        String sql = "SELECT * FROM question WHERE id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String question = resultSet.getString("question");
            String tags = resultSet.getString("tags");
            String date = resultSet.getDate("posting_date").toString();
             
            q = new Question(id, question, tags, date);
        }
         
        resultSet.close();
        statement.close();
         
        return q;
    }
    
    public List<Video> fetchVideosForQuestion(int id) throws SQLException {
        
    	List<Video> videos = new ArrayList<Video>();
        String sql = "SELECT * FROM video WHERE answer_to = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            String url = resultSet.getString("Url");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String date = resultSet.getDate("posting_date").toString();
            String user = resultSet.getString("posted_by");
            
            System.out.println("Url: " + url + " title: " + title);
            Video v = new Video(url, title, description, date, user);
            videos.add(v);
        }
         
        resultSet.close();
        preparedStatement.close();
         
        return videos;
    }
    
    
    public boolean insertNewVideo(int questionID, String url, String title, String desc, String user) throws SQLException, java.text.ParseException {
    	connect_func();    
    	
		String sql = "INSERT INTO video (url, title, description, posting_date, posted_by, answer_to ) values( ?, ?, ?, ?, ?, ?)";
		long millis=System.currentTimeMillis();  
		//java.sql.Date date=new java.sql.Date(millis);  
		java.sql.Date sqlDate = new java.sql.Date(millis);
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, url);
		preparedStatement.setString(2, title); 
		preparedStatement.setString(3, desc); 
		preparedStatement.setDate(4, sqlDate);
		preparedStatement.setString(5, user);
		preparedStatement.setInt(6, questionID);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        
        
   	 	String sql21 = "select * from question ";
   	    statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql21);
        System.out.println("read from table");
       
        while (resultSet.next()) {
       	 String email, f_name, l_name, q_ID, asked_by;
       	 	q_ID = resultSet.getString("questionID");
            email = resultSet.getString("question");
            f_name = resultSet.getString("tags");
            l_name = resultSet.getDate("posting_date").toString();
            asked_by  = resultSet.getString("asked_by");
            System.out.println("ID: "+ q_ID + " question : " + email + " tags: " + f_name + " posting date: " + l_name
            		+ " asked by: " + asked_by );
        }
        
        resultSet.close();
        statement.close(); 
//        disconnect();
        System.out.println("data inserted in user table ");
        return rowInserted;
    }
    
public List<String> searchForVideos(String question) throws SQLException {
        
    	List<String> videos = new ArrayList<String>();
    	HashSet<String> listOfIDs = new HashSet<String>();
    	connect_func();
    	statement =  (Statement) connect.createStatement();
    	ResultSet resultSet = null;
    	String[] arrOfStr = question.split(" ", -2); 
    	for(int i = 0; i<arrOfStr.length; i++) {
    		//String subQ = "SELECT questionID FROM question WHERE tags like '%" + arrOfStr[i] + "%'";
    		String sql = "select url from video where answer_to IN (SELECT questionID FROM question WHERE tags like '%" + arrOfStr[i] + "%')";
    		//System.out.println("current sql: " + sql);
    		
    		 resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
            	String url = resultSet.getString("url");
            	listOfIDs.add(url);
//                String url = resultSet.getString("Url");
//                String title = resultSet.getString("title");
//                String description = resultSet.getString("description");
//                String date = resultSet.getDate("posting_date").toString();
//                String user = resultSet.getString("posted_by");
//                
//                System.out.println("Url: " + url + " title: " + title);
            	System.out.println("------------ID------" + url);
                //Video v = new Video(url, title, description, date, user);
                //videos.add(v);
                
                
            }
    	       
    	}
    	Iterator<String> i = listOfIDs.iterator(); 
        while (i.hasNext()) {
        	videos.add(i.next());
        }
            
    	
    	if(resultSet != null) resultSet.close();
    	statement.close();
         
        return videos;
    }

public boolean insertReview (String remark, String score, String url, String userName) throws SQLException, SQLIntegrityConstraintViolationException {
    
	connect_func();    
	
	String sql2 = "select * from video where url like '%" + url + "%'";
	statement =  (Statement) connect.createStatement();
    ResultSet resultSet = statement.executeQuery(sql2);
    System.out.println("read from table");
    String posted_by = "";
    while (resultSet.next()) {
   	 	
   	 	
        posted_by = resultSet.getString("posted_by");
       
        System.out.println("posted by "+ posted_by + " userName" + userName );
    }
    if(posted_by.length() > 0 && posted_by.equals(userName)) {
    	System.out.println("Sorry, its your own video ");
    	return false;
    }
	
	String sql = "INSERT INTO review values( ?, ?, ?, ?, ?)";
	long millis=System.currentTimeMillis();  
	java.sql.Date sqlDate = new java.sql.Date(millis);
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setDate(1, sqlDate);
	preparedStatement.setString(2, score); 
	preparedStatement.setString(3, remark); 
	preparedStatement.setString(4, userName);
	preparedStatement.setString(5, url);
//	preparedStatement.executeUpdate();
	
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
    
    
	String sql21 = "select * from review ";
	statement =  (Statement) connect.createStatement();
    resultSet = statement.executeQuery(sql21);
    System.out.println("read from table");
   
    while (resultSet.next()) {
   	 String email, f_name, l_name, q_ID, asked_by;
   	 	q_ID = resultSet.getString("remark");
        email = resultSet.getString("score");
        f_name = resultSet.getString("url");
        l_name = resultSet.getDate("review_date").toString();
        asked_by  = resultSet.getString("EmailID");
        System.out.println("Remark: "+ q_ID + " score : " + email + " url: " + f_name + " review date: " + l_name
        		+ " user: " + asked_by );
    }
    
    resultSet.close();
    statement.close(); 
//    disconnect();
    System.out.println("data inserted in user table ");
    return rowInserted;
	    
}

public boolean addFavourite (String url, String userName) throws SQLException {
    
	connect_func(); 
	
	String sql = "INSERT INTO favourites values( ?, ?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1, userName); 
	preparedStatement.setString(2, url); 
	
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
    
    
	String sql21 = "select * from favourites ";
	statement =  (Statement) connect.createStatement();
    resultSet = statement.executeQuery(sql21);
    System.out.println("read from table");
   
    while (resultSet.next()) {
   	 String email, f_name, l_name, q_ID, asked_by;
   	 	
        f_name = resultSet.getString("url");
       
        asked_by  = resultSet.getString("EmailID");
        System.out.println(" url: " + f_name + 
        		 " user: " + asked_by );
    }
    
    resultSet.close();
    statement.close(); 
//    disconnect();
    System.out.println("data inserted in favourites table ");
    return rowInserted;
}

public List<String> listFavourites(String user ) throws SQLException {
    
	List<String> videos = new ArrayList<String>();
    String sql = "SELECT * FROM favourites WHERE emailID = ?";
     
    connect_func();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    preparedStatement.setString(1, user);
     
    
    ResultSet resultSet = preparedStatement.executeQuery();
     
    while (resultSet.next()) {
        String url = resultSet.getString("Url");
        
        System.out.println("Url: " + url );
        videos.add(url);
    }
    System.out.println("size::::" + videos.size());
    resultSet.close();
    preparedStatement.close();
     
    return videos;
}

public boolean removeFavourite (String url, String userName) throws SQLException {
    
	connect_func(); 
	
	String sql = "Delete from favourites where url = '" + url + "' and emailid = '" + userName + "'";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
    
    
	String sql21 = "select * from favourites ";
	statement =  (Statement) connect.createStatement();
    resultSet = statement.executeQuery(sql21);
    System.out.println("read from table");
   
    while (resultSet.next()) {
   	 String email, f_name, l_name, q_ID, asked_by;
   	 	
        f_name = resultSet.getString("url");
       
        asked_by  = resultSet.getString("EmailID");
        System.out.println(" url: " + f_name + 
        		 " user: " + asked_by );
    }
    
    resultSet.close();
    statement.close(); 
//    disconnect();
    System.out.println("data deleted from favourites table ");
    return rowInserted;
}


public List<CoolYoutube> listCoolVideos() throws SQLException {
    
	List<CoolYoutube> coolVideos = new ArrayList<CoolYoutube>();
    String sql = "SELECT distinct R.url as url , V.title as title, Q.question as ques, Q.QuestionID as id from review R, Video V, Question Q where"
    		+ " R.url = V.url and R.score = 'Excellent' and V.answer_to = Q.QuestionID ";
     
    connect_func();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
   
     
    
    ResultSet resultSet = preparedStatement.executeQuery();
     
    while (resultSet.next()) {
        String url = resultSet.getString("Url");
        String ques = resultSet.getString("ques");
        String title = resultSet.getString("title");
        int id = resultSet.getInt("id");
        System.out.println("Url: " + url + " Question: " + ques + " title: " + title +" id: " + id);
        CoolYoutube c = new CoolYoutube(url, ques, title, id);
        coolVideos.add(c);
    }
    System.out.println("size::::" + coolVideos.size());
    resultSet.close();
    preparedStatement.close();
     
    return coolVideos;
}

public List<Question> getNewQuestions() throws SQLException {
    
	List<Question> newQs = new ArrayList<Question>();
    String sql = "SELECT * from question Q where Q.posting_date = ?";
     
    connect_func();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    long millis=System.currentTimeMillis();  
	java.sql.Date sqlDate = new java.sql.Date(millis);
    preparedStatement.setDate(1, sqlDate);
   
     
    
    ResultSet resultSet = preparedStatement.executeQuery();
     
    while (resultSet.next()) {
        String user  = resultSet.getString("asked_by");
        String ques = resultSet.getString("question");
        String tags = resultSet.getString("tags");
        int id = resultSet.getInt("questionID");
        System.out.println("asked by " + user + " Question: " + ques + " tags: " + tags +" id: " + id);
        Question c = new Question(id, ques, tags, sqlDate.toString(), user);
        newQs.add(c);
    }
    System.out.println("size::::" + newQs.size());
    resultSet.close();
    preparedStatement.close();
     
    return newQs;
}

public List<Video> getHotVideos() throws SQLException {
    
	List<Video> hotVideos = new ArrayList<Video>();
    String sql = "SELECT R.url, V.title as title, count(score) as ranking from review R, video V where R.url = V.url group by R.url order by ranking desc limit 3 ";
     
    connect_func();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
   
    ResultSet resultSet = preparedStatement.executeQuery();
     
    while (resultSet.next()) {
        String url  = resultSet.getString("url");
        String title = resultSet.getString("title");
        int count = resultSet.getInt("ranking");
        System.out.println("url " + url + " count: " + count );
        Video v = new Video(url,title);
        hotVideos.add(v);
    }
    System.out.println("size::::" + hotVideos.size());
    resultSet.close();
    preparedStatement.close();
     
    return hotVideos;
}

public List<Question> getTopQuestions() throws SQLException {
    
	List<Question> newQs = new ArrayList<Question>();
    String sql = "SELECT q.questionid as id, q.question as question, " + 
    		" q.asked_by as user, count(answer_to) as a from question q, " + 
    		" video v where v.answer_to = q.questionid group by v.answer_to having count(v.answer_to) = ("
    		+ " select count(answer_to) as ans " + 
    		" from  video group by answer_to order by ans desc limit 1) ";
     
    connect_func();
      
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    
     
    
    ResultSet resultSet = preparedStatement.executeQuery();
     
    while (resultSet.next()) {
        String user  = resultSet.getString("user");
        String ques = resultSet.getString("question");
        int id = resultSet.getInt("id");
        int answers = resultSet.getInt("a");
        System.out.println("asked by " + user + " Question: " + ques +" id: " + id + " count: " + answers);
        Question c = new Question(id, ques, user);
        newQs.add(c);
    }
    System.out.println("size::::" + newQs.size());
    resultSet.close();
    preparedStatement.close();
     
    return newQs;
}

public List<String> getTopReviewers() throws SQLException {
    
	List<String> topRs = new ArrayList<String>();
    String sql = "SELECT r.emailID, count(r.score) as review_count from review r  "
    		+ " group by r.emailID having count(r.score) = ("
    		+ " select count(score) as rev " + 
    		" from review group by emailID order by rev desc limit 1) ";
     
    connect_func();
      
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    
     
    
    ResultSet resultSet = preparedStatement.executeQuery();
     
    while (resultSet.next()) {
        String user  = resultSet.getString("emailID");
        int answers = resultSet.getInt("review_count");
        System.out.println("USer: " + user + " count: " + answers);
        
        topRs.add(user);
    }
    System.out.println("size::::" + topRs.size());
    resultSet.close();
    preparedStatement.close();
     
    return topRs;
}

public List<Video> getVideosByUser(String user) throws SQLException {
    
	List<Video> hotVideos = new ArrayList<Video>();
    String sql = "SELECT url, title from video where posted_by =? ";
     
    connect_func();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    preparedStatement.setString(1, user);
   
    ResultSet resultSet = preparedStatement.executeQuery();
     
    while (resultSet.next()) {
        String url  = resultSet.getString("url");
        String title = resultSet.getString("title");
        Video v = new Video(url,title);
        hotVideos.add(v);
    }
    System.out.println("size::::" + hotVideos.size());
    resultSet.close();
    preparedStatement.close();
     
    return hotVideos;
}

     
}


