<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="jquery-1.3.2.js"></script>
<title>konnect</title>
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
		<form action="retrieveforum" method="POST">





			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<%-- hasActionErrors() method is defined in ActionSupport --%>
						<s:if test="hasActionErrors()">
							<div class="errorDiv">
								<p class="lead">
									<s:actionerror />
								</p>
							</div>
						</s:if>

					</div>
				</div>
			</div>



			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<label for="Comment.description">Comment:</label>
						<textarea name="Comment.description" id="description"
							class="form-control" rows="2" required></textarea>
					</div>
				</div>
			</div>

			<div class="row" style="margin-left: 5px; margin-bottom: 1em;">
				<input type="submit" value="Comment" class="btn btn-primary"
					tabindex="9">

			</div>
			</form>

			<div class="row ">

				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">

						<s:iterator value="comments">


							<div id = "<s:property value="%{commentID}"/>" class="panel panel-default">

								<header class="panel-header lead panel-heading text-capitalize">
								<strong><s:label value="%{userID}" />
								
								</strong>
								<s:form action="blockThisCommentAJAX">
								<s:hidden name="comID" value="%{commentID}" />
								<button type="submit" class="btn btn-warning pull-right">
									<span class="glyphicon glyphicon-remove"></span> Report
								</button>
								
								</s:form>
								</header>


								<div class="panel-body">
									<s:label value="%{description}" />
									<p><s:label value="%{time}" class="pull-right" /></p>
								</div>
							</div>
						</s:iterator>`
					</div>
				</div>
			</div>
		
	</div>


	<script>
	function blockThisComment(commentID) {
   
	//var postID = $('#postIDDIV').html();
	var commentID = commentID;
    alert(commentID); 
    var cID = $('#commentIDDIV').html();
    alert(cID);
    alert('Comment blocking');     
     
      $.ajax( {
        type: "POST",      
        url: "blockThisCommentAJAX",  
        data: "commentID=" +commentID ,
        success: function(response) {
            alert("success");  
            $('#'+commentID).remove();
        },
        error: function(e){
            alert('Error: ' + e);
        }
      });
	} 

	</script>
</body>
</html>