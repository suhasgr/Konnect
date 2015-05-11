<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Konnect</title>
</head>
<body>
	<div><%@include file="dashboard.jsp"%></div>
	<div
		style="position: absolute; z-index: 0; top: 80px; left: 270px; width: 80%">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h1>
					Forum Name:
					<s:property value="#session.forumName" />
				</h1>
			</div>
			<div class="panel-body">
				<h2>Description:</h2>
				<h3>
					<s:property value="#session.forumDescription" />
				</h3>
				<s:form action="deleteforum" method="post">
				<button type="submit" class="btn btn-danger pull-right" name="delete" value="delete">Delete Forum</button>
				</s:form>
			</div>
		</div>





		<div id="content">
			<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
				<li class="active"><a href="#PendingRequests" data-toggle="tab">Pending
						Requests</a></li>
				<li ><a href="#Comments" data-toggle="tab">Comments</a></li>
			</ul>

			<div id="my-tab-content" class="tab-content">
				<div class="tab-pane active" id="PendingRequests">

					
					
						<s:iterator value="requestList">
							<s:url id="pendinglink" action="profileview">
								<s:param name="userID" value="%{requestUserID}" />
							</s:url>
							<div class="panel panel-default">
								<div class="panel-body">
									<s:a href="%{pendinglink}">
										<s:property value="%{requestUserID}" />
									</s:a>
								</div>
								<div class="panel-footer">
									<s:form action="acceptforumrequest">
										<s:hidden name="ForumRequests.requestUserID" value="%{requestUserID}" />
										<s:hidden name="ForumRequests.approveFlag" value="false" />
										<s:hidden name="forumID" value="%{forumID}" />
										<s:set name="ForumRequests.requestUserID" var="forumRequestorID" value="%{requestUserID}" scope="session" /> 
										
										
										
										<button class="btn btn-success" type="submit" name="status"
											value="accept">Accept</button>
										<button class="btn btn-danger" type="submit" name="status"
											value="reject">Reject</button>
									 </s:form>
								</div>
							</div>

						</s:iterator>
					
				</div>





				<div class="tab-pane" id="Comments">
					<form action="retrieveforumOwner" method="POST">





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


										<div class="panel panel-default">

											<header class="panel-header lead panel-heading text-capitalize">
											<strong><s:label value="%{userID}" /> </strong> 
											<s:form	action="blockThisCommentAJAX">
												<s:hidden name="comID" value="%{commentID}" />
												<button type="submit" class="btn btn-warning pull-right">
													<span class="glyphicon glyphicon-remove"></span> Report
												</button>

											</s:form> 
											</header>


											<div class="panel-body">
												<s:label value="%{description}" />
												<p>
													<s:label value="%{time}" class="pull-right" />
												</p>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div>
					

				</div>


			</div>


			<script type="text/javascript">
				$(document).ready(
						function() {
							if (location.hash) {
								$('a[href=' + location.hash + ']').tab('show');
							}
							$(document.body).on(
									"click",
									"a[data-toggle]",
									function(event) {
										location.hash = this
												.getAttribute("href");
									});
						});
				$(window).on(
						'popstate',
						function() {
							var anchor = location.hash
									|| $("a[data-toggle=tab]").first().attr(
											"href");
							$('a[href=' + anchor + ']').tab('show');
						});
			</script>

			<script type="text/javascript">
				
			</script>
		</div>
		<!-- container -->


		<script type="text/javascript" src="assets/js/bootstrap.js"></script>

	</div>

</body>
</html>