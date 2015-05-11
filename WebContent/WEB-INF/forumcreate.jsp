<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Forum</title>
</head>
<body>
<div><%@include file="dashboard.jsp"%></div>
<div style="position: absolute; z-index: 0;  top: 80px; left: 300px; width: 80%">
<h1>Create Forum</h1>
 


 <form role="form" action="createforumpage" method="POST">
					<s:if test="hasActionErrors()">
					<div class="errorDiv">
						<p class="lead">
							<s:actionerror />
						</p>
					</div>
				</s:if>
				<div class="row">

					<div class="col-xs-12 col-sm-6 col-md-6">
						<div class="form-group">
							<label for="Forum.forumname">Forum Name:</label> <input type="text"
								name="Forum.forumname" id="forumname" class="form-control"
								placeholder="Forum Name" tabindex="1" required>
						</div>
					</div>
					</div>
	
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
						<div class="form-group">
							<label for="Forum.description">Description:</label> 
							<textarea maxlength="250" name="Forum.description" id="description" class="form-control" rows="10" required></textarea>
						</div>
					</div>
					</div>

			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<label for="category">Category:</label> 
						<select name="Forum.category">
							<option value="sports">Sports</option>
							<option value="finance">Finance</option>
							<option value="health">Health</option>
							<option value="science">Science</option>
							<option value="technology">Technology</option>
							<option value="general">General</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row" style="margin-left: 5px">
				<input type="submit" value="Create" class="btn btn-primary"
					tabindex="9">

			</div>

		</form>
		</div>
	

</body>
</html>