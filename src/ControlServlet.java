import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO peopleDAO;
    private String currentUserName = "";
 
    public void init() {
        peopleDAO = new UserDAO(); 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/create_tables":
            	System.out.println("This creates table for root in controlservlet"); 
                createTable(request, response);
                break;
            case "/login":
            	System.out.println("This is DIY login in controlservlet");
                userLogin(request, response);
                break;
            case "/sign_up":
            	System.out.println("This is DIY sign up in controlservlet");
                userSignup(request, response);
                break;
            case "/ask_new_question":
            	System.out.println("This is DIY asking new question in controlservlet");
                askNewQuestion(request, response);
                break;
            case "/list_all_question":
            	System.out.println("This is DIY listing all questions in controlservlet");
                listAllQuestions(request, response);
                break;
            case "/question_detail":
            	System.out.println("This is DIY gives all youtube videos with a question in controlservlet");
                getVideosOfQuestion(request, response);
                break;
            case "/insert_new_video":
            	System.out.println("This is DIY insert new youtube videos with a question in controlservlet");
                insertNewVideo(request, response);
                break;
            case "/search_video":
            	System.out.println("This is DIY insert new youtube videos with a question in controlservlet");
                searchVideo(request, response);
                break;
            case "/write_review":
            	System.out.println("This is DIY write reviews for video in controlservlet");
                writeReview(request, response);
                break;
            case "/add_favourite":
            	System.out.println("This is DIY add a video in favourite list in controlservlet");
                addFavourite(request, response);
                break;
            case "/remove_favourite":
            	System.out.println("This is DIY removes a video in favourite list in controlservlet");
                removeFavourite(request, response);
                break;
            case "/list_all_favourite":
            	System.out.println("This is DIY list all favourite video in controlservlet");
                listAllFavourite(request, response);
                break;
            case "/CoolYoutubes":
            	System.out.println("This is DIY list all cool video in controlservlet");
                listCoolYoutubes(request, response);
                break;
            case "/new_questions":
            	System.out.println("This is DIY list all new questions in controlservlet");
                listNewQuestions(request, response);
                break;
            case "/hot_videos":
            	System.out.println("This is DIY list all new questions in controlservlet");
                listHotVideos(request, response);
                break;
            case "/top_questions":
            	System.out.println("This is DIY list all top questions in controlservlet");
                listTopQuestions(request, response);
                break;
            case "/top_reviewer":
            	System.out.println("This is DIY list all top reviewers in controlservlet");
                listTopReviewers(request, response);
                break;
            case "/user_detail":
            	System.out.println("This is DIY list all top reviewers in controlservlet");
                listUserDetail(request, response);
                break;
            case "/new":
            	System.out.println("This is for creating new student in controlservlet"); // not for DIY
                showNewForm(request, response);
                break;
            case "/insert":
            	System.out.println("This is for inserting new student in controlservlet"); //not for DIY
            	insertPeople(request, response);
                break;
            case "/delete":
            	System.out.println("This is for deleting existing student in controlservlet"); //not for DIY
            	deletePeople(request, response);
                break;
            case "/edit":
            	System.out.println("This is for editing existing student in controlservlet"); //not for DIY
                showEditForm(request, response);
                break;
            case "/update":
            	System.out.println("This is for updating existing student in controlservlet"); //not for DIY
            	updatePeople(request, response);
                break;
            default:
            	System.out.println("This is showing student list in controlservlet");  //not for DIY
            	//listPeople(request, response);    
            	getVideosOfQuestion(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void listPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<People> listPeople = peopleDAO.listAllPeople();
        request.setAttribute("listPeople", listPeople);   
        System.out.println("size of peoples " + listPeople.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListPeople.jsp");       
        dispatcher.forward(request, response);
    }
    
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = peopleDAO.listAllUser();
        System.out.println("<<<<<<length:  " + listUser.size());
        request.setAttribute("listUser_", listUser);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListAllUser.jsp");       
        dispatcher.forward(request, response);
    }
 
    // to insert a people
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertPeopleForm.jsp");
        dispatcher.forward(request, response);
    }
 
    // to present an update form to update an  existing Student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        People existingPeople = peopleDAO.getPeople(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
 
    }
 
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        People newPeople = new People(name, address, status);
        
        peopleDAO.insert(newPeople);
        response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
    }
 
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        System.out.println(id);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        
        System.out.println(name);
        
        People people = new People(id,name, address, status);
        peopleDAO.update(people);
        response.sendRedirect("list");
    }
 
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //People people = new People(id);
        peopleDAO.delete(id);
        response.sendRedirect("list"); 
    }
    
    
    //############################### DIY METHODS###################################
    
    private void createTable(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
        peopleDAO.createTableForRoot();
        response.sendRedirect("AdminDashboard.jsp"); 
  
    }
    
    private void userLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, ServletException {
        String emailID = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("user login in control servelte");
        if(emailID.equalsIgnoreCase("root") && password.equalsIgnoreCase("pass1234")) response.sendRedirect("AdminDashboard.jsp");
        else {
        	boolean user = peopleDAO.loginUser(emailID, password);
        	if(user) {
        		currentUserName = emailID;
        		System.out.println("current user name: " + currentUserName);
        		RequestDispatcher dispatcher = request.getRequestDispatcher("HomepageForUser.jsp");
                request.setAttribute("userName", emailID);
                dispatcher.forward(request, response);
        		//response.setAttribute("name", "value");
        		//response.sendRedirect("HomepageForUser.jsp?userName=${emailID}");
        	}
        	else response.sendRedirect("SignIn.jsp");
        }
    }
    
    private void userSignup(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
    	
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String emailID = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("DOB");
        if(peopleDAO.checkForExistingUser(emailID)) response.sendRedirect("AlreadyUser.jsp");
        else {
        	User user = new User(emailID, firstName, lastName, password, dob, gender);
            System.out.println("user sign up in control servelte");
            boolean isSuccessful = peopleDAO.insertNewUser(user);
            if(isSuccessful) response.sendRedirect("Congratulations.jsp");
            else response.sendRedirect("Signup.jsp");
        }
        
        //response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
    }



    private void askNewQuestion(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ParseException {
	
    	String question = request.getParameter("question");
    	String tag_1 = request.getParameter("tag_1");
    	String tag_2 = request.getParameter("tag_2");
    	String tag_3 = request.getParameter("tag_3");
    	String userName = request.getParameter("userName");
    
    	
    	System.out.println("user asking question method in control servelte::");
    	System.out.println("question: " + question + " tags: " + tag_1 + " user: " + currentUserName);
    	boolean isSuccessful = peopleDAO.insertNewQuestion(question, tag_1 + ", " + tag_2 + ", " + tag_3, currentUserName );
    	if(isSuccessful) response.sendRedirect("ListAllQuestion.jsp");
    	else response.sendRedirect("Sorry.jsp");
    
    
    	//response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
		}
    
    private void listAllQuestions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, ServletException {
    	
    	List<Question> listQs = peopleDAO.listAllQuestion();
        System.out.println("<<<<<<length>>>>>>>>  " + listQs.size());
        request.setAttribute("listQs", listQs);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListAllQuestion.jsp");       
        dispatcher.forward(request, response);
        
        
        	//response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
    		}
    
    private void getVideosOfQuestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("get videos for question: " + id);
        
        List<Video> listOfVideos = peopleDAO.fetchVideosForQuestion(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("QuestionDetail.jsp");
        request.setAttribute("listOfVideos", listOfVideos);
        request.setAttribute("questionID", id);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
 
    }
    
    private void insertNewVideo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, ServletException {
    	
        	int id = Integer.parseInt(request.getParameter("questionID"));
        	String url = request.getParameter("video");
        	String title = request.getParameter("title");
        	String desc = request.getParameter("desc");
        	//String userName = request.getParameter("userName");
        
        	
        	System.out.println("user inserting new video in control servelte::");
        	System.out.println("questionID: " + id + " url: " + url + " title: " + title );
        	boolean isSuccessful = peopleDAO.insertNewVideo(id, url, title, desc, currentUserName );
        	if(isSuccessful) {
        		List<Video> listOfVideos = peopleDAO.fetchVideosForQuestion(id);
                RequestDispatcher dispatcher = request.getRequestDispatcher("QuestionDetail.jsp");
                request.setAttribute("listOfVideos", listOfVideos);
                request.setAttribute("questionID", id);
                dispatcher.forward(request, response);
        	}
        	else response.sendRedirect("Sorry.jsp");
        
        
        	//response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
    }
    private void searchVideo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String question = request.getParameter("ques");
        String userName = request.getParameter("userName");
        
        List<String> listOfVideos = peopleDAO.searchForVideos(question);
        
        System.out.println("______________size______" + listOfVideos.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchForVideo.jsp");
        request.setAttribute("listOfVideos", listOfVideos);
        request.setAttribute("userName", userName);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
 
    }
    
    private void writeReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String remark = request.getParameter("remark");
        String score = request.getParameter("review");
        String url = request.getParameter("url");
        String userName = request.getParameter("userName");
        boolean isSuccessful = peopleDAO.insertReview(remark, score, url, currentUserName);
        
        if(!isSuccessful) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Sorry.jsp");
        	dispatcher.forward(request, response);
        }else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("HomepageForUser.jsp");
            request.setAttribute("userName", userName);
            dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
        }
        
 
    }
    
    private void addFavourite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String url = request.getParameter("url");
        String userName = request.getParameter("userName");
        boolean isSuccessful = peopleDAO.addFavourite(url, currentUserName);
        
        if(!isSuccessful) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Sorry.jsp");
        	dispatcher.forward(request, response);
        }else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("HomepageForUser.jsp");
            request.setAttribute("userName", userName);
            dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
        }
        
 
    }
    
    private void listAllFavourite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
      
        String userName = request.getParameter("userName");
        List<String> listOfVideos = peopleDAO.listFavourites(currentUserName);
        System.out.println("size>>>>>>>>>>>>>>>" + listOfVideos.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowMyFavourites.jsp");
        request.setAttribute("listOfVideos", listOfVideos);
        request.setAttribute("userName", userName);
        dispatcher.forward(request, response); 
        
 
    }
    
    private void removeFavourite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String url = request.getParameter("url");
        String userName = request.getParameter("userName");
        boolean isSuccessful = peopleDAO.removeFavourite(url, currentUserName);
        
        if(!isSuccessful) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Sorry.jsp");
        	dispatcher.forward(request, response);
        }else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("HomepageForUser.jsp");
            request.setAttribute("userName", userName);
            dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
        }
        
 
    }
    
    private void listCoolYoutubes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
      
        //String userName = request.getParameter("userName");
        List<CoolYoutube> listOfVideos = peopleDAO.listCoolVideos();
        System.out.println("size>>>>>>>>>>>>>>>" + listOfVideos.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoolYoutubes.jsp");
        request.setAttribute("listOfVideos", listOfVideos);
        dispatcher.forward(request, response); 
        
 
    }
    
    private void listNewQuestions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
      
        //String userName = request.getParameter("userName");
        List<Question> listOfQs = peopleDAO.getNewQuestions();
        System.out.println("size>>>>>>>>>>>>>>>" + listOfQs.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("NewQuestions.jsp");
        request.setAttribute("listOfQs", listOfQs);
        dispatcher.forward(request, response); 
        
 
    }
    
    private void listHotVideos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
      
        //String userName = request.getParameter("userName");
        List<Video> listOfVideos = peopleDAO.getHotVideos();
        System.out.println("size>>>>>>>>>>>>>>>" + listOfVideos.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("HotYoutubes.jsp");
        request.setAttribute("listOfVideos", listOfVideos);
        dispatcher.forward(request, response); 
        
 
    }
    
    private void listTopQuestions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
      
        //String userName = request.getParameter("userName");
        List<Question> listOfQs = peopleDAO.getTopQuestions();
        System.out.println("size>>>>>>>>>>>>>>>" + listOfQs.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("TopQuestions.jsp");
        request.setAttribute("listOfQs", listOfQs);
        dispatcher.forward(request, response); 
        
 
    }
    
    private void listTopReviewers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
      
        //String userName = request.getParameter("userName");
        List<String> listOfRs = peopleDAO.getTopReviewers();
        System.out.println("size>>>>>>>>>>>>>>>" + listOfRs.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("TopReviewer.jsp");
        request.setAttribute("listOfRs", listOfRs);
        dispatcher.forward(request, response); 
        
 
    }
    
    private void listUserDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
      
        String userName = request.getParameter("user");
        List<Video> listOfRs = peopleDAO.getVideosByUser(userName);
        System.out.println("size>>>>>>>>>>>>>>>" + listOfRs.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListVideosByUser.jsp");
        request.setAttribute("listOfRs", listOfRs);
        dispatcher.forward(request, response); 
        
 
    }
 

}