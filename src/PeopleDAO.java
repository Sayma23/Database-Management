import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

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
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/PeopleDAO")
public class PeopleDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public PeopleDAO() {

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
    
    public void createTableForRoot() throws SQLException{
    	 System.out.println("create table for root");
    	 Statement statement = null;
     	 String sql1 = "DROP TABLE IF EXISTS User";
     	 String sql2= "Drop Table IF EXISTS VIDEO";
     	 String sql3 = "Drop table if exists Question";
     	 String sql4 = "Drop table if exists Favour";
     	 String sql5= "Drop table if exists Review";
     	 
         String sql6 = "create TABLE if not exists User(EmailID varchar(30), First_name varchar(30), " + 
         		    			 "Last_name varchar(30), Password varchar(30), DOB DATE, " + 
         				" Gender char(7), primary key(EmailID))"; 
         
         String sql7 = "create table if not exists Video(Url varchar(50), title varchar(30),"
        		 + " description varchar(30), posting_date DATE, posted_by varchar(30) not null, "
        		 + " answer_to integer not null, primary key(Url), foreign key(posted_by) references User(EmailID))";
         
         String sql8 = "create table if not exists question (QuestionID integer, question varchar(50),"
        		 + " tags varchar(50), posting_date DATE, asked_by varchar(30) not null, primary key(QuestionID),"
        		 + "foreign key(asked_by) references User(EmailID))";
         
         String sql9 = "create table if not exists favour(EmailID varchar(30), Url varchar(50), "
        		 + " foreign key(EmailID) references User(EmailID), foreign key(Url) references Video(Url), "
        		 + "unique(EmailID, Url))";
         
         String sql10 = "create table if not exists review(review_date DATE, score char(10),"
        		 + " remark varchar(100), EmailID varchar(30), Url varchar(50), Unique(EmailID, Url), "
        		 + "foreign key(EmailID) references User(EmailID), foreign key(Url) references Video(Url))";
  
         
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
         
         
         
         connect_func();
         statement =  (Statement) connect.createStatement();
        
         //drop tables
         statement.executeUpdate(sql5);
         statement.executeUpdate(sql4);
         statement.executeUpdate(sql3);
         statement.executeUpdate(sql2);
         statement.executeUpdate(sql1);
         System.out.println("tables dropeed successfully.....");
         
         //create tables
         statement.executeUpdate(sql6);
         statement.executeUpdate(sql7);
         statement.executeUpdate(sql8);
         statement.executeUpdate(sql9);
         statement.executeUpdate(sql10);
         System.out.println("tables craeted successfully....");
         
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
         System.out.println("insert into table");
         
         

    	 
//    	 
    	 String sql21 = "select * from User ";
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
         disconnect(); 
         //resultSet.close();
        
          
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
     
}
