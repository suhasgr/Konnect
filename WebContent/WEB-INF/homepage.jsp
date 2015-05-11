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
<div style="position: absolute; z-index: 0;  top: 80px; left: 270px">
<h1>Welcome <s:property value="userInformation.getUserID()" /></h1>  
			<div id="userInformation"  style="display:none"><s:property value="userInformation.getFriends()" /></div>
</div>

 <div class="container" style="position: absolute; z-index: 0;  top: 140px; left: 270px">
      <h4>Update Status</h4>
      <form action="postaction" method="post">
       <textarea class="form-control input-lg" rows="3" id="PostText" placeholder = "Shout Out!!!" name="pText"></textarea>
         <!--  <input type="text" class="form-control input-lg" id="PostText" placeholder = "What's on your mind!" name="pText">  -->
      
        <button type="submit" class="btn pull-right">Post</button> 
        <!-- <button type="button" id="tagbutton" class="btn pull-right" data-toggle="modal" data-target="#createModal">Tag</button>
		<button type="button" id="privacybutton" class="btn pull-right" data-toggle="modal" data-target="#createModal">Privacy</button>  -->        		
       	<button type="button" id="tagbutton" class="btn pull-right" onclick="printFriendList()">Tag</button>
		<button type="button" id="privacybutton" class="btn pull-right" data-toggle="modal" data-target="#createModal">Privacy</button>
       	
       	<!-- <input type="file" style="visibility:hidden;"/>  -->  
        <!--  <div style="position:relative;">
		<a class='btn btn-primary' href='javascript:;'>
			Upload Image...
			<input type="file" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40"  onchange="getval(this)">
		</a>
		&nbsp;
		<span class='label label-info' id="upload-file-info"></span>
		</div>
         -->
         
    
	</form>
	<s:form action="uploadImage"  method="POST" enctype="multipart/form-data">
	<s:file name="image" label="Select an Image to upload" size="40"/>
	<s:submit value="Share Image!!"/>
 
			
 
	</s:form>
  
 </div>
  
  
  <!-- 	<sx:div updateFreq="2000">   
  	<s:form action="newsfeedaction">
   -->	
 <BR /> <!-- It seems dumb to need this!  Is this really how -->
<BR /> <!-- to get a newline? -->
<BR /> <!-- Well, it's what -->
<BR /> <!-- I'm doing now, I suppose... -->
<BR />
   <div class="container" style="position: absolute; z-index: 0;  top: 310px; left: 270px">
   <h3>News Feed</h3>
   <!--  <form action="newsfeedaction" method="post"> -->
   		<!-- <button type="submit" class="btn">NewsFeed</button> -->
   		<div id = "NewsFeed">
		   	<s:iterator var="post" value="listOfArticles">
		   <!--  <s:bean name="com.konnectcore.bean.Post" var="temp"></s:bean> -->	
				<div id = "<s:property value="postID"/>" class="panel panel-default">
						<header class="panel-header lead panel-heading text-capitalize"><strong><s:property value="userID"/></strong> <button type="button" class="btn btn-danger pull-right" onclick="blockThisPost(<s:property value="postID"/>)"><span class="glyphicon glyphicon-remove"></span> Block Post</button></header>
						<div class="panel-body">
								<p>
								<h4><s:property value="postText"/></h4>
								<s:hidden value="postID"/>
								<s:if test="%{imageEndode != null && imageEndode != ''}">
   								
  									<img src="data:image/jpeg;charset=utf-8;base64,<s:property value="imageEndode"/>"  id="img" class="img-rounded" height="350" width="250"/>
								</s:if>
								
			    				<p class="text-right"><s:property value="postTime"/></p>
								</p>
						</div>
						
						<footer class="panel-footer text-right"><button type="button" class="btn btn-primary" onClick="addLikesToPost(<s:property value="postID"/>)"><span class="glyphicon glyphicon-heart"></span><p id="NewLikes"><s:property value="likes"/></p></button></footer>
						<!-- <footer class="panel-footer text-right"><input type="text" class="form-control input-lg"  id="CommentText" placeholder = "Comment!" name="cText"></footer> -->
						<footer class="panel-footer text-right">
							
							<s:iterator var="comment" value="#post.comments">
								<ul class="list-group">
	  
									<li class="list-group-item text-left"><s:property value="#comment.commentText"/></li>
								</ul>
							</s:iterator>
						</footer>
						<footer class="panel-footer text-right"><input type="text" class="form-control input-lg"  id="CommentText" placeholder = "Comment!" name="ctext" onKeydown="if (event.keyCode==13) addCommentToPost(<s:property value="postID"/>, this)"></footer>
				</div>
			</s:iterator>
			</div>	
  <!--  </form> -->
  </div>

<!-- 	</s:form>
</sx:div>-->
   <!-- <div class="centered"  style="position: absolute; z-index: 0;  top: 890px; left: 780px">
    <form action="moreaction" method="post">
   		<button type="submit" class="btn btn-large btn-primary">More</button>
   	<s:iterator value="listOfArticles">
		<tr>
	    	<td><s:property value="postText"/></td>
	    	<td><s:property value="postTime"/></td>
	    	<td><s:property value="postID"/></td>
	    	<td><s:property value="userID"/></td>
		</tr>
	</s:iterator>	
   	</form>
   </div> 
	 -->   
   <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script> 
   	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" >
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
   

<div class="modal fade" id="createModal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="text-center">Select buddies that you wanna tag!</h3>
            </div>
            <div class="modal-body">
	            <div  class="col-xs-6">
		            <div class="well" style="max-height: 300px; width: 400px; overflow: auto;">
		        		<ul id="friendList" class="list-group checked-list-box">
		                  <li class="list-group-item">try</li>
		                </ul>
		            </div>
	        	</div>
        </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Save</button><br> <br> <br>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

function printFriendList(){
	
 	friends = document.getElementById("userInformation").innerHTML;
	//var arrayP = ["Suprith","Bipra","Suhas"];
	   //for(var i = 0; i < friendList.length; i++){
	       
	      friendList = friends.split(",");
	      
	      var ul = document.getElementById("friendList");
	      $("ul").html('');
	      ul.className = "list-group checked-list-box";
	      
	      for(var i = 0; i < friendList.length; i++){
	    	  var li = document.createElement("li");
	    	  li.className = "list-group-item";
	    	  li.innerHTML = friendList[i];
	    	 
		      
		      ul.appendChild(li);
	      }
	      friendList = [];
	      friends = [];
	      debugger;
	      $('#createModal').modal('show');
	}
</script>








<script>
function addLikesToPost(postid) {
   
	var postID = postid;
    alert(postID); 
    alert('adding likes ajax way');     
     
      $.ajax( {
        type: "POST",      
        url: "addLikesToPostAJAX",  
        data: "postID=" +postID ,
        success: function(response) {
            alert("success");  
            $('#NewLikes').html(response);
            //$('#NewLikes').hide().html(data).fadeIn('response');
           
        },
        error: function(e){
            alert('Error: ' + e);
        }
      });
} 
</script>
<%-- <script type="text/javascript">
function myFunction() {
    var x = document.getElementById("myDIV").getElementsByTagName("P");
    document.getElementById("demo").innerHTML = x.length;
}
</script>
 --%>
 <script>
function blockThisPost(postid) {
   
	//var postID = $('#postIDDIV').html();
	var postID = postid;
    alert(postID); 
    alert('blocking posts ajax way');     
     
      $.ajax( {
        type: "POST",      
        url: "blockThisPostAJAX",  
        data: "postID=" +postID ,
        success: function(response) {
            alert("success");  
            $('#'+postid).remove();
        },
        error: function(e){
            alert('Error: ' + e);
        }
      });
} 

</script>



 
<!--<script>
 (function worker() {
  $.ajax({
	type: "POST",
    url: "newsfeedaction", 
    success: function(data) {
      $('.NewsFeed').html(data);
    	//$("#NewsFeed").hide().html(data).fadeIn('fast');
    },
    complete: function() {
      // Schedule the next request when the current one's complete
      setTimeout(worker, 12000);
    }
  });
})(); 
</script>-->

<script>
function retrieveFriendlist(buttonid){
	alert(buttonid);
	$.ajax( {
        type: "POST",      
        url: "retrieveFriendsAJAX",  
        success: function(response) {
            alert("success");  
            $('#NewLikes').html(response);
            //$('#NewComment').hide().html(data).fadeIn('response');
            window.alert('Done');
            
        },
        error: function(xhr, status, error) {
        	  alert(xhr.responseText);
        	}
      });
}
</script>


<script>
function addCommentToPost(postid, ele){
	var postID = postid
    alert(postID); 
    
    var ctext = ele.value;
    alert('commenting ajax way');
    alert(ctext);
    var myObj = {};
    myObj["postID"] = postID;
    myObj["ctext"] = ctext;
    ele.value='';
    var data =  JSON.stringify({ data: myObj });
    alert(data);
    $.ajax( {
        type: "POST",      
        url: "addCommentToPostAJAX",  
        data: data,
        dataType: "json",
        contentType:"application/json;charset=utf-8",
        success: function(response) {
        },
        error: function(xhr, status, error) {
        	debugger;  
        	//alert(xhr.responseText);
        	}
      });
}
</script>

    <%-- <script  type="text/javascript" charset="utf-8">
    $(function ajaxNewsFeedCall() {
    	alert('inside ajaxcall');     
          $("#createLabel").click(function() {
            $.ajax( {
              type: "POST",      
              url: "newsfeedaction.action",  //will this declaration of action work? 
              //dataType: "text/html",
              contentType: "text/html; charset=utf-8",
              data: "",
              success: function() {
                  alert("success");  
              }
            });
          });
    }); 
 
</script>--%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<%-- .state-icon {
    left: -5px;
}
.list-group-item-primary {
    color: rgb(255, 255, 255);
    background-color: rgb(66, 139, 202);
}


.well .list-group {
    margin-bottom: 0px;
} 

<script type="text/javascript">
$(function () {
    $('.list-group.checked-list-box .list-group-item').each(function () {
        
        // Settings
        var $widget = $(this),
            $checkbox = $('<input type="checkbox" class="hidden" />'),
            color = ($widget.data('color') ? $widget.data('color') : "primary"),
            style = ($widget.data('style') == "button" ? "btn-" : "list-group-item-"),
            settings = {
                on: {
                    icon: 'glyphicon glyphicon-check'
                },
                off: {
                    icon: 'glyphicon glyphicon-unchecked'
                }
            };
            
        $widget.css('cursor', 'pointer')
        $widget.append($checkbox);

        // Event Handlers
        $widget.on('click', function () {
            $checkbox.prop('checked', !$checkbox.is(':checked'));
            $checkbox.triggerHandler('change');
            updateDisplay();
        });
        $checkbox.on('change', function () {
            updateDisplay();
        });
          

        // Actions
        function updateDisplay() {
            var isChecked = $checkbox.is(':checked');

            // Set the button's state
            $widget.data('state', (isChecked) ? "on" : "off");

            // Set the button's icon
            $widget.find('.state-icon')
                .removeClass()
                .addClass('state-icon ' + settings[$widget.data('state')].icon);

            // Update the button's color
            if (isChecked) {
                $widget.addClass(style + color + ' active');
            } else {
                $widget.removeClass(style + color + ' active');
            }
        }

        // Initialization
        function init() {
            
            if ($widget.data('checked') == true) {
                $checkbox.prop('checked', !$checkbox.is(':checked'));
            }
            
            updateDisplay();

            // Inject the icon if applicable
            if ($widget.find('.state-icon').length == 0) {
                $widget.prepend('<span class="state-icon ' + settings[$widget.data('state')].icon + '"></span>');
            }
        }
        init();
    });
    
    $('#get-checked-data').on('click', function(event) {
        event.preventDefault(); 
        var checkedItems = {}, counter = 0;
        $("#check-list-box li.active").each(function(idx, li) {
            checkedItems[counter] = $(li).text();
            counter++;
        });
        $('#display-json').html(JSON.stringify(checkedItems, null, '\t'));
    });
});
</script> --%>
</body>
</html>


