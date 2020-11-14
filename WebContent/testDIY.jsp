<link rel="stylesheet" type="text/css" href="css/style.css"/>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 5px;
  margin: 5px 0 10px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 10px;
}

/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 2px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}



/* Float cancel and signup buttons and add an equal width */
.upload_video  {
  float: center;
  width: 20%;
  align: center;
}

.searchbtn  {
  float: center;
  width: 20%;
  align: center;
}
</style>

<html>
<head>
    <title>Enter Number of Videos</title>
</head>
<body>
    <center>
        <h1>Enter Number of Videos</h1>
       
    </center>
    <div align="center">
	    	<c:if test="${not empty errorMessage}">
	    	<span class="error"><font color="red">
			   <c:out value="${errorMessage}"/>
			</c:if>
			 </font></span>  
    	
        <form action="post_N_video">
        <br><br>
	        <table border="1" cellpadding="5">
	           
	            <tr>
	                <th>Video Number: </th>
	                <td>
	                    <input type="text" name="video_number" size="45" />
	                </td>
	            </tr>
	                <td colspan="2" align="center">      	
      					<input type="submit" value="Submit" />
	                </td>
	            </tr>
	        </table>
        </form>
        
        <form action="back_to_root_special_features">
     <button type="submit" class="searchbtn"> Back</button>
      </form>
    </div>   
</body>
</html>