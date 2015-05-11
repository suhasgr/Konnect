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
		style="position: absolute; z-index: 0; top: 80px; left: 290px; width: 75%">
		

		<div id="content">
			<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
				<li class="active"><a href="#MyOwnedForums" data-toggle="tab">My Owned Forums</a></li>
				<li ><a href="#MemberForums" data-toggle="tab">Member Forums</a></li>
			</ul>

			<div id="my-tab-content" class="tab-content">
				<div class="tab-pane active" id="MyOwnedForums">
					<s:property value="category" />

					<s:iterator value="myForumList">
						<div class="row bg-warning " style="margin-top: 15px;">
							<s:form action="selectforum">
								<s:submit value="%{forumname}" class="btn btn-link" />
								<s:hidden name="Forum.forumid" value="%{forumid}" />
								<s:hidden name="Forum.forumname" value="%{forumname}" />
								<s:hidden name="Forum.description" value="%{description}" />
							</s:form>
						</div>
					</s:iterator>

				</div>





				<div class="tab-pane" id="MemberForums">
					My member forums
					<s:property value="category" />

					<s:iterator value="myMemberList">
						<div class="row bg-warning " style="margin-top: 15px;">
							<s:form action="selectforum">
								<s:submit value="%{forumname}" class="btn btn-link" />
								<s:hidden name="Forum.forumid" value="%{forumid}" />
								<s:hidden name="Forum.forumname" value="%{forumname}" />
								<s:hidden name="Forum.description" value="%{description}" />
							</s:form>
						</div>
					</s:iterator>
					
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