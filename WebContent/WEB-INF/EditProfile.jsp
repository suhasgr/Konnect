<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>

	<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript"	 src="assets/css/bootstrap.min.css"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>

</head>
<body>
<div><%@include file="dashboard.jsp"%></div>
<div style="position: absolute; z-index: 0;  top: 80px; left: 270px">
</div>
<div class="container" style="position: absolute; z-index: 0;  top: 140px; left: 270px">
<hr class="">
<div class="container target">

<s:form action="profilestatus">
<div class="row">   
        <div class="col-sm-3">
            <!--left col-->
            <ul class="list-group">
                <li class="list-group-item text-muted" contenteditable="false">Profile</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class=""> Gender</strong></span> 
    			
		  								<select class="form-control dropdown" name="userInformation.gender" id="gender">
		    									<option value="Male">Male</option><option value="Female">Female</option>
		  								</select>
		  			            
	                
                </li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Location</strong></span> <input type="text" name="userInformation.location" class="form-control" placeholder="Location" id="location" aria-describedby="basic-addon1" /> </li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">First Name</strong></span> <input type="text" name="userInformation.name" class="form-control" placeholder="First Name" id="name" aria-describedby="basic-addon1" /> </li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Last Name</strong></span> <input type="text" name="userInformation.lastname" class="form-control" placeholder="Last Name" id="lastname" aria-describedby="basic-addon1" /> </li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Occupation </strong></span> <input type="text" name="userInformation.occupation" class="form-control" placeholder="occupation" id="occupation" aria-describedby="basic-addon1" /></li>
            	<li class="list-group-item text-right"><span class="pull-left"><strong class="">Hobbies </strong></span> <input type="text" name="userInformation.hobbies" class="form-control" placeholder="hobbies" id="hobbies" aria-describedby="basic-addon1" /></li>
            </ul>
           
         <s:if test="hasActionErrors()">
    			<div class="errorDiv">
        				<p class="lead"><s:actionerror/></p>
    			</div>
		</s:if>
            <div class="panel panel-default">
                <div class="panel-heading">PassWord Change<i class="fa fa-link fa-1x"></i>
                </div>
                <div class="panel-body" ><input name="userInformation.pass" type="password" class="form-control" id="pass" aria-describedby="basic-addon1" /></div>
            </div>
						<button class="btn btn-primary" type="submit" name="status" value="save">Save</button>
						<button class="btn btn-warning" type="submit" name="status" value="cancel">Cancel</button>
        </div>
	</div>
 </s:form>  
   </div>
</div>
<script>
		window.jQuery
				|| document
						.write('<script src="includes/js/jquery-1.8.2.min.js"><\/script>')
	</script>
	
	<script type="text/JavaScript">
	
			
		document.getElementById("name").value='<s:property value="userInformation.getName()"/>';
		document.getElementById("lastname").value='<s:property value="userInformation.getLastname()"/>';
		document.getElementById("location").value='<s:property value="userInformation.getLocation()"/>';
		document.getElementById("occupation").value='<s:property value="userInformation.getOccupation()"/>';
		document.getElementById("gender").value='<s:property value="userInformation.getGender()"/>';
		document.getElementById("pass").value='<s:property value="userInformation.getPass()"/>';
		document.getElementById("hobbies").value='<s:property value="userInformation.getHobbies()"/>';
  </script>

   <script type="text/javascript">
   $(function () {
       $('#dob').datetimepicker();
   });
   </script>
   
	<!-- Bootstrap JS -->
	<script src="bootstrap/js/bootstrap.min.js"></script>

	<!-- Custom JS -->
	<script src="includes/js/script.js"></script>
</body>
</html>