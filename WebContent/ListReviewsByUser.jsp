<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% response.addHeader("X-Frame-Options", "SAMEORIGIN"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    </head>
<body>

<%-- need to debut this part of the code to make it work, ideally we would like to see 
 all people are listed initially when the page is run as the entry page.
--%> 
<%--
if(request.getParameter("listQs") == null) { // we want to make sure that we already have all the people
	UserDAO userDAO = new UserDAO();        // listed in attribute 'listPeople'
	List<Question> listQs = userDAO.listAllQuestion();
	request.setAttribute("listQs", listQs);       
}
--%>


  
        <h2>
            <a href="AdminDashboard.jsp">Dashboard</a>
            &nbsp;&nbsp;&nbsp;
            
        </h2>
   
    <div align="center">
        <table border="1" cellpadding="5">
        
            <tr>
               
                <th>Preview</th>
                <th>Score</th>
                <th>Remark</th>
            </tr>
            <c:forEach var="review" items="${listOfRs}">
                <tr>
              		<td><iframe width="420" height="315" src="${review.url}"> </iframe>
                    <td><c:out value="${review.score}" /></td>
                    <td><c:out value="${review.remark}" /></td>
                 
                                    
                
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>

<style>
*, *:before, *:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
	background-image: radial-gradient(ivory, lightyellow);
  font-family: 'Nunito', sans-serif;
  color: #384047;
}

form {
  max-width: 350px;
  margin: 10px auto;
  padding: 10px 20px;
  background: #f4f7f8;
  border-radius: 8px;
}

h1 {
  margin: 0 0 30px 0;
  text-align: center;
}

h2 {
  margin: 0 0 30px 0;
  text-align: center;
}

table {
  border: 1px solid black;
  border-collapse: collapse;
}

th {
  background-color: #4CAF50;
  color: white;
}

tr:nth-child(even) {background-color: #f2f2f2;}

input[type="text"],
input[type="password"],
input[type="date"],
input[type="datetime"],
input[type="email"],
input[type="number"],
input[type="search"],
input[type="tel"],
input[type="time"],
input[type="url"],
textarea,
select {
  background: rgba(255,255,255,0.1);
  border: none;
  font-size: 16px;
  height: auto;
  margin: 0;
  outline: 0;
  padding: 15px;
  width: 100%;
  background-color: #e8eeef;
  color: #8a97a0;
  box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
  margin-bottom: 30px;
}

input[type="radio"],
input[type="checkbox"] {
  margin: 0 4px 8px 0;
}

select {
  padding: 6px;
  height: 32px;
  border-radius: 2px;
}

button {
  padding: 19px 39px 18px 39px;
  color: #FFF;
  background-color: #4bc970;
  display: block;
  font-size: 18px;
  text-align: center;
  font-style: normal;
  border-radius: 5px;
  width: 60%;
  border: 1px solid #3ac162;
  border-width: 1px 1px 3px;
  box-shadow: 0 -1px 0 rgba(255,255,255,0.1) inset;
  margin: 0 auto;
  margin-bottom: 10px;
}

fieldset {
  margin-bottom: 30px;
  border: none;
}

legend {
  font-size: 1.4em;
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 8px;
}

label.light {
  font-weight: 300;
  display: inline;
}

.number {
  background-color: #5fcf80;
  color: #fff;
  height: 30px;
  width: 30px;
  display: inline-block;
  font-size: 0.8em;
  margin-right: 4px;
  line-height: 30px;
  text-align: center;
  text-shadow: 0 1px 0 rgba(255,255,255,0.2);
  border-radius: 100%;
}

@media screen and (min-width: 480px) {

  form {
    max-width: 580px;
  }

}
 </style>
 
  
    
