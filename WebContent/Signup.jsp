<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="signup" action="Hello" method="doGet">
            <label>
              First Name
            </label>
            <div>${userMessage}</div>
            <input type="text" name="fname" required autocomplete="on"/>
            <br>
            <label>
              Last Name
            </label>
            <input type="text" name="lname" required autocomplete="on"/>
            <br>
            <label>E-Mail Id : </label>
            <input type="text" name = "email" />
            <br>
            <label>Username</label>
            <input type="text" name="uname" required autocomplete="on"/>
            <br>
            <label>Password</label>
            <input type="password" name="pass" required autocomplete="off"/>
            <br>
            <label>Gender</label>
            <input type="radio" name = "gender" value="M"/>
            Male
            <input type="radio" name = "gender" value="F"/>
            Female
            <br>
            <label> Date of Birth</label>
            <input type="Date" name="dob"/>
            <br>
          <button class="button button-block" name = "signup" >Sign Up</button>
          </form>
</body>
</html>

