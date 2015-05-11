<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
<!-- CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script language="JavaScript">
  javascript:window.history.forward(1);
</script>
<!--theme -->
<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="assets/js/bootstrap.min.js"></script>
</head>


<body>
    <div class="container">
	  	<div class="col-sm-6" style="position: relative; z-index: 0; width: 600px;  top: 0px; left: 0px; ">
		<h1>
			<a href="#">Konnect</a>
		</h1>
		<p class="lead">Connect with people!</p>
		</div>
	 <div class="col-sm-6" style="position: relative; z-index: 0; width: 300px;  top: 200px; left: 50px">
	  
	  	<%-- hasActionErrors() method is defined in ActionSupport --%>
		<s:if test="hasActionErrors()">
    			<div class="errorDiv">
        				<p class="lead"><s:actionerror/></p>
    			</div>
		</s:if>
		
      <form class="form-signin" action="loginaction" method="post">
        <h2 class="form-signin-heading">Login</h2>
		
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" name="userInformation.email" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" name="userInformation.pass" placeholder="Password" required>
 		
     	 <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignIn">Sign in</button>
      </form>
	<form class="signup" action="signupaction" method="post">
        <div class="checkbox">
          <label>
            New User?
            <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignUp">Sign up</button>
          </label>
        </div>
        </form>
      </div>

    </div> <!-- /container -->
    
    
    <div style="position: relative; z-index: 0; width: 500px;  top: 00px; left: 250px">
    	<img class="first-slide" src="assets/images/connect1.jpg" alt="First slide">
    </div>
    
    

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>

