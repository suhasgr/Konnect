<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chat-Friends</title>
<link rel="stylesheet" href="assets/css/window.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">

<!--theme -->
<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="assets/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

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
          <a href="WEB-INF/homepage.jsp" class="navbar-brand "  style="color: #FFFFFF;">Konnect</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse ">
          <ul class="nav navbar-nav navbar-right">
            <li><a href= "<s:url action='homepage' />" style="color: #FFFFFF;">Home</a></li>
            <li><a href="<s:url action='profileview' />" style="color: #FFFFFF;">My Profile</a></li>
            <li><a href=""<s:url action='friendaction' />"" style="color: #FFFFFF;">Settings</a></li>
            <li><a href="#" style="color: #FFFFFF;">Help</a></li>
            <li><a href= "<s:url action='logout' />" style="color: #FFFFFF;">Logout</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>
<%-- <h1> Welcome
<s:property value="userInformation.getUserID()" />
</h1> --%>

<!-- <p> Friends are :</p> -->
<br/>
<div class="row" style="position:relative;top:70px;">
<div class="col-sm-3 col-md-2 col-lg-2 sidebar" style="border: 2px solid #a1a1a1;">
<ul class="nav nav-sidebar list-group">
<li style="text-align:center"><img class="first-slide" src="assets/images/connect1.jpg" alt="First slide" height="200" width="150"> <br/></li>
            


<!--  <ul>  -->
<li><br/></li>

	<li class="list-group-item">Your Friends <br/></li>
	
	
		<s:iterator value="userInformation.getFriends()">
		<div class="list-group-item">
		<a class="chatfriend" href='#' frndid="<s:property/>"> <s:property/></a>
		</div>
		</s:iterator>
</ul>
</div>

<!-- style="position:relative; top: 10px; left: 300px" -->
<div class="col-sm-7 col-md-6 col-lg-6" >
  
  <form action="">
		
		<div id="chatwindow">
		</div>
	<br />
</form>
</div>
</div>

	
<script>
				var activeLink = null; 
				function onClick(){
					        
					        
				}
				
				$('.chatfriend').on('click', function(){
					var frndId = $(this).attr('frndid');
					$.ajax({url: "chataction", 
						data:"friendToChat=" + frndId,
						success: function(result){
			            console.log(result);
			           	activeLink = $(this);
			       
			        	$("#chatwindow").html(result);
			        }});
				});
				
				$(document).ready(function(){
					$('#btnEndSession').click(function(){
						$('#chatwindow').children().remove();
					});
					
				})
</script>

</body>
</html>
