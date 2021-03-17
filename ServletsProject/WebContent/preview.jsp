<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/main.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form  p-l-55 p-r-55 p-t-178" action="Loginservlet" method="post" >
				<%

response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
response.setHeader("Progma", "no-cache");
response.setHeader("Expires", "0");


%>
					<span class="login100-form-title">
						Preview
					</span>

					<div class="wrap-input100 m-b-16  text-center">
					<img src="<%=session.getAttribute("path") %>" width="150px" height="150px">
                    </div>
                    <table style="font-weight: bold;">
                        <tr>
                            <td>Name  :<%=session.getAttribute("name") %></td>
                            <td style="text-decoration: underline;"></td>
                        </tr>
                        <tr>
                            <td>Email Id  :<%=session.getAttribute("email") %></td>
                            <td style="text-decoration: underline;"></td>
                        </tr>
                        
                    </table>
<!-- Button trigger modal -->
<!-- Button trigger modal -->
<div class="text-right p-t-13 p-b-23">
    <button type="button" class="btn btn-success txt2" data-toggle="modal" data-target="#exampleModalCenter">
     <a href="savepassword.jsp" style="color: white;"> Change Password</a>      
    </button>
</div>
                     <center>
                        <div class="container-login100-form-btn p-b-40" style="width: 40%;">
                            <div class="row">
                                <div class="col-lg-6">
                                    <button class="login100-form-btn">
                                    <a href="edit.jsp" style="color: white;"> Edit</a>   
                                    </button>
                                </div>
                                <div class="col-lg-6">
                                    <button class="login100-form-btn">
                                        <a href="login.jsp" style="color: white;">Logout</a>   
                                    </button>
                                </div>
                            </div>
                        </div>
                    </center>
				</form>
			</div>
		</div>
	</div>
	


</body>
</html>