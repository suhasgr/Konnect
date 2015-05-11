<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div><%@include file="dashboard.jsp"%></div>
<div style="position: absolute; z-index: 0;  top: 80px; left: 270px">
<h1 class="text-center">Discussion Forums</h1>

<h2 class="text-center">Categories</h2>
<s:form action="sendCategory">
<div class="row padding-1">
  
  											
  <div class="col-md-3 col-md-offset-1">  <input type="image" name="Forum.category" value="sports" src="assets/images/sports.png" class="img-circle" width="256" height="256" /> </div>
  <div class="col-md-3 col-md-offset-1">  <input type="image" name="Forum.category" value="finance" src="assets/images/finance.jpg" class="img-circle" width="256" height="256"/></div>
  <div class="col-md-3 col-md-offset-1"> <input type="image" name="Forum.category" value="health" src="assets/images/health.jpg" class="img-circle" width="256" height="256"/></div>
 
 </div>
 <div class="row">
 	<div class="col-md-3 col-md-offset-2">Sports</div>
 	<div class="col-md-3 col-md-offset-1">Finance</div>
 	<div class="col-md-3 ">Health</div>
 </div>
 
 
 <div class="row">

  <div class="col-md-3 col-md-offset-1" > <input type="image" src="assets/images/science.jpg" name="Forum.category" value="science" alt="Submit" class="img-circle" width="256" height="256"></div>
  <div class="col-md-3 col-md-offset-1"> <input type="image" src="assets/images/tech.png" name="Forum.category" value="technology" alt="Submit" class="img-circle" width="256" height="256"></div>
  <div class="col-md-3 col-md-offset-1"><input type="image" src="assets/images/education.jpg" name="Forum.category" value="general" alt="Submit" class="img-circle" width="256" height="256"></div>

 </div>
  <div class="row">
 	<div class="col-md-3 col-md-offset-2">Science</div>
 	<div class="col-md-3 col-md-offset-1">Technology</div>
 	<div class="col-md-3 ">General</div>
 </div>
  </s:form>


</div>
</body>
</html>