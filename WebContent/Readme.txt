DBMS_PROJECT_CSC6710_WSU

Project part 1
Group information
Sayma Sultana he9660@wayne.edu
Dathri Yeddula hc6849@wayne.edu

Steps and software to run the project:
 
Software requirements:
-Install Java JDK 15.0.1
-Install MySQL Server
-Install Eclipse
-Install Tomcat Server 9.0

After installing the software run the project as follows:

-Open the eclipse software->launch the workspace in your desired location->Window->Preferences->Server->Runtime environment->Choose the Apache Tomcat Server installed.

-Unzip the csc6710_sayma_dathri_part1.war file in its location and it should contain only the src file and webcontent file and all the rest of the folders should be deleted.

-Import the unzipped file in eclipse.

-Run the "Welcome.jsp" on server by right click->run as->run on server. On run this JSP file it will show a two links for "Login" and "sign up".

Description of Part 1 of the project is given below with citation of individuals who did what: 

Step 1(Dathri Yeddula): A "Welcome.jsp" where there are hyperlinks. One is "Log in" and "Sign up".

Step 2(Sayma Sultana): Log in for root or existing user.

Step 3(Sayma Sultana): If you successfully log in as root(username= "root", pass= "pass1234", you will get "AdminDashboard.jsp" page, where there is a "Initialize database" button.

Step 4(Sayma Sultana & Dathri Yeddula): If you click on initialize database all existing tables will be dropped, 5 database tables(User, Question, Video, Favour, review) will be created and 10 tuples in each table will be inserted.

Step 5(Sayma Sultana): For sign up, you have to enter username(emailid), first , last name, password, gender, date of birth. Any duplicate username or email id is checked while signing in. it will redirect to "AlreadyUser.jsp" and ask user if he wants to login.
For new User, email id should be in valied form, password shoould be same to confirm password and date of birth should be in valied form.

Step 6(Dathri Yeddula): After successful sign up, you can log into the system.

You can watch it here too: https://www.youtube.com/watch?v=8EvDXuZMwWA

