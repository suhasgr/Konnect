<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">

<!--theme -->
<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="assets/js/bootstrap.min.js"></script>
</head>


  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid" style="background-color:#265A88">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="<s:url action='homepage' />" class="navbar-brand "  style="color: #FFFFFF;">Konnect</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav navbar-right">
            <li><a href= "<s:url action='homepage' />" style="color: #FFFFFF;">Home</a></li>
            <li><a href="<s:url action='profileview' />" style="color: #FFFFFF;">My Profile</a></li>
            <li><a href="<s:url action='friendaction' />" style="color: #FFFFFF;">Friends</a></li>
            <li><a href= "<s:url action='logout' />" style="color: #FFFFFF;">Logout</a></li>
          </ul>
         </div>
      </div>
    </nav>

    <div class="container-fluid">
    	<div class="row">
    	<div style="position: relative; z-index: 0; width: 40px; height: 40px;  top: 00px; left: 250px">
    		
   		</div>
    	</div>
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" style="top: 15px;  ">
          <ul class="nav nav-sidebar">
            <li style="bottom: 15px; top: 15px;"><img class="first-slide" src="assets/images/connect1.jpg" alt="First slide" height="200" width="200"> </li>
            <li><a href="<s:url action='profileview' />">Profile</a></li>
            <li><a href="<s:url action='friendaction' />">Friends</a></li>
            <li><a href="<s:url action='notification' />">Notifications</a></li>
            <li><a href="<s:url action='forums' />">Forums</a></li>
            <li><a id="createforums" href="<s:url action='createforum' />">Create Forums</a></li>
            <li><a href="<s:url action='myforums' />">My Forums</a></li>
            <li><a href="chatopen">Chat</a></li>
          </ul>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>