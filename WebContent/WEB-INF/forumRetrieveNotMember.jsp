<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Konnect</title>
</head>
<body>
	<div><%@include file="dashboard.jsp"%></div>
	<div style="position: absolute; z-index: 0; top: 80px; left: 270px; width: 80%">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h1>
					Forum Name:
					<s:property value="#session.forumName" />
				</h1>
			</div>
			<div class="panel-body">
				<h2>Description:</h2>
				<h3><s:property value="#session.forumDescription" /></h3>
			</div>
		</div>
		<form action="joinforum" method="POST">

			<button class="btn btn-primary btn-block" type="submit" value="save">Join forum</button>

		</form>

	</div>
	

	
	
</body>
</html>