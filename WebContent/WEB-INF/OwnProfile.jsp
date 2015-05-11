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
	<h3 class="alert alert-info"><s:property value="userInformation.getUserID()"/></h3>

	
	
<hr class="">
<div class="container target">
    <div class="row">
        <div class="col-sm-10">
             <h1 class=""><s:property value="userInformation.getUserID()" /></h1><br/>
             <s:form action="editprofile"><button type="submit" class="btn btn-success">Edit Profile</button></s:form>
        </div>
    </div>
  <br>
    <div class="row">
        <div class="col-sm-3">
            <!--left col-->
            <ul class="list-group">
                <li class="list-group-item text-muted" contenteditable="false">Profile</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class=""> Email</strong></span> <h3><s:property  value="userInformation.getEmail()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class=""> Gender</strong></span> <h3><s:property  value="userInformation.getGender()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Location</strong></span> <h3 ><s:property value="userInformation.getLocation()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">First Name</strong></span> <h3><s:property value="userInformation.getName()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Last Name </strong></span> <h3><s:property value="userInformation.getLastname()" /></h3></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Occupation </strong></span> <h3><s:property value="userInformation.getOccupation()" /></h3></li>
            </ul>
           <div class="panel panel-default">
             <div class="panel-heading">Hobbies</div>
                <div class="panel-body"><i style="color:green" class="fa fa-check-square"></i> <s:property value="userInformation.getHobbies()" /></div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">PassWord<i class="fa fa-link fa-1x"></i>
                </div>
                <div class="panel-body">****************</div>
            </div>          
        </div>
        
        
        <div class="col-sm-9" style="" contenteditable="false">
            <div class="panel panel-default target">
                <div class="panel-heading" contenteditable="false">Posts</div>
                <div id = "NewsFeed">
		   	<s:iterator var="post" value="listOfArticles">
		   <!--  <s:bean name="com.konnectcore.bean.Post" var="temp"></s:bean> -->	
				<div id = "<s:property value="postID"/>" class="panel panel-default">
						<header class="panel-header lead panel-heading text-capitalize"><strong><s:property value="userID"/></strong> </header>
						<div class="panel-body">
								<p>
								<h4><s:property value="postText"/></h4>
								<s:hidden value="postID"/>
								<s:if test="%{imageEndode != null && imageEndode != ''}">
   								
  									<img src="data:image/jpeg;charset=utf-8;base64,<s:property value="imageEndode"/>"  id="img" class="img-rounded" height="350" width="250"/>
								</s:if>
								
			    				<p class="text-right"><s:property value="postTime"/></p>
						</div>
						
						
							
							<s:iterator var="comment" value="#post.comments">
								<ul class="list-group">
	  
									<li class="list-group-item text-left"><s:property value="#comment.commentText"/></li>
								</ul>
							</s:iterator>
				</div>
			</s:iterator>
			</div>
					</div>
				</div>                 
            </div>
                     
            </div>   
        </div>
              
    </div>
</div>
</div> 
        <script src="/plugins/bootstrap-select.min.js"></script>
        <script src="/codemirror/jquery.codemirror.js"></script>
        <script src="/beautifier.js"></script>
                   	
		<script src="/plugins/bootstrap-pager.js"></script>
</div>

</body>
</html>