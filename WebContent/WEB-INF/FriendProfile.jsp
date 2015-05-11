<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>konnect</title>

	<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="assets/css/bootstrap.min.css"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>

</head>
<body>
<div><%@include file="dashboard.jsp"%></div>
<div style="position: absolute; z-index: 0;  top: 80px; left: 270px">
</div>
<div class="container" style="position: absolute; z-index: 0;  top: 140px; left: 270px">
	<h3 class="alert alert-info"><s:property value="personInformation.getUserID()"/></h3>
	
<div class="container">
 
<!-------->
<div class="container target">
	<div class="row">
        <div class="col-sm-3">
            <!--left col-->
            <ul class="list-group">
                <li class="list-group-item text-muted" contenteditable="false">Profile</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class=""> Email</strong></span> <h3><s:property  value="personInformation.getEmail()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class=""> Gender</strong></span> <h3><s:property  value="personInformation.getGender()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Location</strong></span> <h3 ><s:property value="personInformation.getLocation()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">First Name</strong></span> <h3><s:property value="personInformation.getName()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Last Name </strong></span> <h3><s:property value="personInformation.getLastname()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Occupation </strong></span> <h3><s:property value="personInformation.getOccupation()" /></h3></li>
            </ul>
           <div class="panel panel-default">
             <div class="panel-heading">Hobbies</div>
                <div class="panel-body"><i style="color:green" class="fa fa-check-square"></i> <s:property value="personInformation.getHobbies()" /></div>
            </div>       
        </div>

 <div class="col-sm-9" style="" contenteditable="false">        
	<div id="content">
    	<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
        	<li class="active"><a href="#friends" data-toggle="tab">Friends</a></li>
        	<li><a href="#Mutualfriends" data-toggle="tab">Mutual Friends</a></li>
    	</ul>
    <div id="my-tab-content" class="tab-content">
        <div class="tab-pane active" id="friends">
        	<s:iterator value="allFriends" >
        			<s:url id="friendlink" action="profileview">
   						<s:param name="userID" value="%{userID}" />
					</s:url>
					<div class="panel panel-default">
  						<div class="panel-body">
    						<s:a href="%{friendlink}"><s:property value="%{userID}"/></s:a>
  						</div>
  						<div class="panel-footer">
  								<s:form action="friendstatus">
								<s:hidden name="userID" value="%{userID}"/>
						
        						</s:form>
  						</div>
  					</div>
			</s:iterator>
        </div>
        
        <div class="tab-pane" id="Mutualfriends">
        	<s:iterator value="mutualFriends" >
        			<s:url id="friendlink" action="profileview">
   						<s:param name="userID" value="%{userID}" />
					</s:url>
					<div class="panel panel-default">
  						<div class="panel-body">
    						<s:a href="%{friendlink}"><s:property value="%{userID}"/></s:a>
  						</div>
  						<div class="panel-footer">
  								<s:form action="friendstatus">
								<s:hidden name="userID" value="%{userID}"/>
						
        						</s:form>
  						</div>
  					</div>
			</s:iterator>
        </div>        
        </div>
    </div>        
    </div>	
    </div>
    </div>
    </div>
    
    <script type="text/javascript">
    jQuery(document).ready(function ($) {
        $("#tabs").tab();
    });
</script>    
</div> <!-- container -->
 
 
<script type="text/javascript" src="assets/js/bootstrap.js"></script>

</body>
</html>