<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>verified page</title>
</head>
<body>
  <span>we already sent a verification code to your mail id </span>
  
  <form action="VerifyCode" method="post">
  <input type="text" name="authcode">
  <input type="submit" name="verify" >
  <a href="">change the password</a>
  </form>
</body>
</html>