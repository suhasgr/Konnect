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

<h1 class="alert alert-info">Friends</h1>
<div class="container">
 <s:if test="hasActionErrors()">
    	<div class="errorDiv">
        		<p class="lead"><s:actionerror/></p>
    	</div>
</s:if>
<!-------->

<div id="content">
    <ul id="tabs" class="nav nav-tabs " data-tabs="tabs">
        <li class="active"><a href="#friends" data-toggle="tab">Friends</a></li>
        <li><a href="#PendingRequests" data-toggle="tab">Pending Requests</a></li>
        <li><a href="#SentRequests" data-toggle="tab">Sent Requests</a></li>
        <li><a href="#SuggestedFriends" data-toggle="tab">Suggested Friends</a></li>
    </ul>
    <div id="my-tab-content" class="tab-content">
        <div class="tab-pane active" id="friends">
            
            <s:iterator value="friends" >
        			<s:url id="friendlink" action="profileview">
   						<s:param name="userID" value="%{userID}" />
					</s:url>
            <div class="panel panel-default">
  				<div class="panel-body">
    				<s:a href="%{friendlink}"><s:property value="%{userID}"/></s:a>
  				</div>
  				<div class="panel-footer"><s:form class="form-group" action="friendstatus">
							
							<s:hidden name="userID" value="%{userID}"/>
						
							<td><s:submit type="button" class="btn btn-primary" name="status" value="unfriend" align="left"></s:submit></td>				
        					
        		</s:form></div>
				</div>
			</s:iterator>
			
        </div>
        <div class="tab-pane" id="PendingRequests">
            	<s:iterator value="pending">
        			<s:url id="pendinglink" action="profileview">
   						<s:param name="userID" value="%{userID}" />
					</s:url>
					<div class="panel panel-default">
  					<div class="panel-body">
    					<s:a href="%{pendinglink}"><s:property value="%{userID}"/></s:a>
  					</div>
  					<div class="panel-footer">
						<s:form action="friendstatus">
						<s:hidden name="userID" value="%{userID}"/>
						
						<button class="btn btn-primary" type="submit" name="status" value="accept">Accept</button>
						<button class="btn btn-warning" type="submit" name="status" value="reject">Reject</button>
						<button class="btn btn-danger" type="submit" name="status" value="block">Block</button>
        				</s:form>
        			</div>	        					
        			</div>
						
				</s:iterator>
        </div>
        <div class="tab-pane" id="SentRequests">
			<s:iterator value="sentRequests">
        			<s:url id="sentrequestlink" action="profileview">
   						<s:param name="userID" value="%{userID}" />
					</s:url>
				<div class="panel panel-default">
  					<div class="panel-body">
    					<s:a href="%{sentrequestlink}"><s:property value="%{userID}"/></s:a>
  					</div>
  					<div class="panel-footer">
        				<s:form action="friendstatus">
						<s:hidden name="userID" value="%{userID}" />
						<button class="btn btn-warning" type="submit" name="status" value="withdraw">Withdraw</button>
						</s:form>
					</div>
				</div>
        	</s:iterator>
		
        </div>
        <div class="tab-pane" id="SuggestedFriends">
            <s:iterator value="suggestedFriends">
        			<s:url id="suggestedfreindslink" action="profileview">
   						<s:param name="userID" value="%{userID}" />
					</s:url>
			<div class="panel panel-default">
  					<div class="panel-body">
    					<s:a href="%{suggestedfreindslink}"><s:property value="%{userID}"/></s:a>
  					</div>
  					<div class="panel-footer">			
        				<s:form action="friendstatus">
						<s:hidden name="userID" value="%{userID}"/>
						<button class="btn btn-primary" type="submit" name="status" value="Send Request">Send Request</button>						
						</s:form>
        			</div>
        		</div>   			
			</s:iterator>
        </div>
    </div>
</div>
 
<script type="text/javascript">
$(document).ready(function() {
    if(location.hash) {
        $('a[href=' + location.hash + ']').tab('show');
    }
    $(document.body).on("click", "a[data-toggle]", function(event) {
        location.hash = this.getAttribute("href");
    });
});
$(window).on('popstate', function() {
    var anchor = location.hash || $("a[data-toggle=tab]").first().attr("href");
    $('a[href=' + anchor + ']').tab('show');
});
</script>

<script type="text/javascript">


</script>
</div> <!-- container -->
 
 
<script type="text/javascript" src="assets/js/bootstrap.js"></script>


</div>
</body>
</html>