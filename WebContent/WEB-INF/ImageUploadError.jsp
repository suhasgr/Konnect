<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>konnect</title>
</head>
<body>
<div><%@include file="dashboard.jsp"%></div>
<div style="position: absolute; z-index: 0;  top: 80px; left: 270px">
<h1>Welcome <s:property value="userInformation.getUserID()" /></h1>  
	

</div>

 <div class="container" style="position: absolute; z-index: 0;  top: 140px; left: 270px">
      <h4>Update Status</h4>
      <form action="postaction" method="post">
        <div class="form-group">
          <input type="text" class="form-control input-lg" id="PostText" placeholder = "What's on your mind!" name="pText">
        </div>
        <div class="btn-toolbar">
        <button type="submit" class="btn pull-right">Post</button> 
        <button type="submit" class="btn pull-right">Privacy</button> 
       <button type="submit" class="btn pull-right">Tag</button>
      </div>
	</form>
  
  </div>	
  	   
   <div class="container" style="position: absolute; z-index: 0;  top: 280px; left: 270px">
   <h3>News Feed</h3>
    <form action="NewsFeedActionAJAX" method="post">
   		<button type="submit" class="btn">NewsFeed</button>
   			<h4>Upload failed. Please check the size of the uploaded file</h4>
   	</form>
   </div> 
   
	   
  <!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
   	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" >
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    -->
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
 
</body>
</html>


