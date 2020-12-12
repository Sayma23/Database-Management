<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SearchVideo</title>
    <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
</head>
<body>

 <form action="search_video" method="post">
	
        <h3>Hello <%= request.getParameter("userName")%>! Search for videos...</h3>
        <h3>
            <a href="ListAllQuestion.jsp">Show all questions</a>
            &nbsp;&nbsp;&nbsp;
             
        </h3>

           
            
            <fieldset>     
          <label for="ques">Ask question:</label>
          <input type="text" name="ques" size = "100" placeholder="brush" required>
          
     
          
          <input type="hidden" name="userName" value="<%= request.getParameter("userName")%>" required>
          </fieldset>
          
        <button type="submit">Post</button>
        </form>
        
         <div align="center">
        <table border="1" cellpadding="5">
        
            <tr>
               
                <th>Preview</th>
            </tr>
            <c:forEach var="video" items="${listOfVideos}">
                <tr>
              		<td><iframe width="420" height="315" src="${video}"> </iframe>
             		<td>
                        <a href="VodeiDetail.jsp?video_url=<c:out value='${video}'/>&userName=<%= request.getParameter("userName")%>">Detail</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                                    
                    </td>
                
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
h3 {
  margin: 0 0 30px 0;
  text-align: center;
}

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
 
  
    
