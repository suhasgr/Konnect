<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<!-- CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">

<!--theme -->
<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="assets/js/bootstrap.min.js"></script>
</head>


<body>
		<div class="col-sm-6" style="position: relative; z-index: 0; width: 900px;  top: 0px; left: 250px; ">
		<h1>
			<a href="#">Konnect</a>
		</h1>
		<p class="lead">Connect with people!</p>
	
	<div class="col-sm-6" style="position: relative; z-index: 0; width: 900px;  top: 0px; left: 0px; ">	
	<!-- Form for Registering the user into the system or Adding the person into the system -->
	
	<%-- hasActionErrors() method is defined in ActionSupport --%>
		<s:if test="hasActionErrors()">
    			<div class="errorDiv">
        				<p class="lead"><s:actionerror/></p>
    			</div>
		</s:if>
	
	<form role="form" action="signupform" method="POST">
		<h2>Sign Up:</h2>

		<div class="row">

			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">
					<label for="userid">UserID:</label>
					<input type="text" maxlength = 25 name="username" id="userid" class="form-control" placeholder="UserID" tabindex="1" required>
				</div>
			</div>
			
		</div>
		
		<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6">
		<div class="form-group">
			<label for="email">Email:</label>
			<input type="email" maxlength = 25 name="email" id="email" class="form-control" placeholder="Email" tabindex="4" required>
		</div>
		</div>
		</div>
		<div class="row">

			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">
					<label for="pwd">Password:</label>
					<input type="password" maxlength = 12 name="password" id="password" class="form-control " placeholder="Password" tabindex="6" required>
				</div>
			</div>
			</div>

		
		
		<div class="row" style="margin-left:5px">
				<input type="submit" value="Register" class="btn btn-primary" tabindex="9">
			</div>
	</form>
</div>
<!-- End of Form for Registering the user  and Adding a new user -->
		</div>

</body>
</html>