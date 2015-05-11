<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forum Discussion</title>
</head>
<body>
<div><%@include file="dashboard.jsp"%></div>
	<div style="position: absolute; z-index: 0; top: 80px; left: 280px; width: 80%">
		<h1>Discussion Forums</h1>


		<s:property value="category" />

		<s:iterator value="forumList">
		<div class="row bg-warning " style="margin-top:15px;">
			<s:form action="selectforum">
				<s:submit value="%{forumname}" class="btn btn-link" />
				<s:hidden name="Forum.forumid" value="%{forumid}" />
				<s:hidden name="Forum.forumname" value="%{forumname}" />
				<s:hidden name="Forum.description" value="%{description}" />
			</s:form>
			</div>
		</s:iterator>

	</div>

</body>
</html>