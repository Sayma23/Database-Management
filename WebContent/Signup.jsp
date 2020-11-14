<head>
	<script> 
	
		
			function ValidateEmail(form) 
			{
    			mail = form.email.value;
				var re = /\S+@\S+\.\S+/;
 				if (!re.test(mail))
  				{
 					alert("\nInvalid email address!! Please try again...");
 					return false;
  				}
 				return true;
    	
			}
            // Function to check Whether both passwords 
            // is same or not. 
            function checkPassword(form) { 
                password1 = form.password.value; 
                password2 = form.confirm_password.value; 
  
                // If password not entered 
                if (password1 != password2) { 
                    alert ("\nPassword did not match: Please try again...") ;
                    return false; 
                } 
                else return true;
  
                
            }
            
            function isValidDate(form)
            {
                dateString = form.DOB.value
                var date_regex = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
                if (!(date_regex.test(dateString))) {
                	alert ("\nInvalid date of birth: Please try again...") ;
                    return false;
                }
            }
            
            
            function ValidateForm(form){
            	
            	return ValidateEmail(form) && checkPassword(form) && isValidDate(form);
            }
        </script> 
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>

       <form onSubmit = "return ValidateForm(this);" action="sign_up" method="post">
      
        <h1>WSU-DIY Sign Up</h1>
        
        <fieldset>     
          <label for="email">Email:</label><span style="color: red">${emailError}</span>
          <input type="text" name="email" placeholder="me@example.com" required>
          
          <label for="password">Password:</label>
          <input type="password" name="password" required>
          
          <label for="confirm_password">Confirm Password:</label><span style="color: red">${confirmPassError}</span>
          <input type="password" name="confirm_password" required>
          
          <label for="first_name">First Name:</label>
          <input input type="text" name="first_name" required>
          
          <label for="last_name">Last Name:</label>
          <input input type="text" name="last_name" required>
          
          <label>Gender:</label>
          <input type="radio" name="gender" value="Male" checked><label for="Male" class="light">Male</label><br>
          <input type="radio" name="gender" value="Female"><label for="Female" class="light">Female</label>
          
          <label for="age">Date of birth:</label>
          <input type="text" id="age" name="DOB" placeholder="yyyy-mm-dd" required>
          
        </fieldset>
        <button type="submit">Sign Up</button>
        
        <center>
            <a href="SignIn.jsp">Do you want to Sign In now?</a>
             
        </center>
      </form>
      
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
  max-width: 300px;
  margin: 10px auto;
  padding: 10px 20px;
  background: #f4f7f8;
  border-radius: 8px;
}

h1 {
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
  font-size: 18px;
  text-align: center;
  font-style: normal;
  border-radius: 5px;
  width: 100%;
  border: 1px solid #3ac162;
  border-width: 1px 1px 3px;
  box-shadow: 0 -1px 0 rgba(255,255,255,0.1) inset;
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
    max-width: 480px;
  }

}
 </style>
 
  
    