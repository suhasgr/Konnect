<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
@media (max-width: 767px) I added .nav-tabs > li { display:block; width: 100%;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friends</title>
<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="assets/css/bootstrap.min.css"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
		
</head>

<body>
<div><%@include file="dashboard.jsp"%></div>
<div style="position: absolute; z-index: 0;  top: 80px; left: 270px">

<h1 class="alert alert-info">Notifications</h1>
<div class="container">
 <s:if test="hasActionErrors()">
    	<div class="errorDiv">
        		<p class="lead"><s:actionerror/></p>
    	</div>
</s:if>
	<div class="row">
  	<div class="col-sm-6">
  		<form action="clearNotification" >           
     	<s:iterator value="notifications">   
        <div class="alert alert-info">
       	 	<a href="#" class="close" data-dismiss="alert">&times;</a>
        	<strong>Note!</strong> <s:property />
    	</div>
    </s:iterator>
    
    <button class="btn btn-warning" type="submit" name="status" value="clear">Clear</button>
    </form>
   </div>
   </div>

</div>
</div>
</body>
</html>