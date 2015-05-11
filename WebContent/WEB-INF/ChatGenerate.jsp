<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="panel panel-default" style="max-height:600px;overflow:auto;">
<div class="panel-heading">
<h3 class="panel-title">
        <!--  Chat Session Active -->
         You and <s:property value="friendToChat"/>
      </h3>
      </div>
   <div class="panel-body">
<div id="chatbox">
	<div class="row">
		<div class="col-lg-12">
			<div id="chatMessagesContent">
			<s:iterator value="msgs" var="messages">
				<p   style='background-color:#C7E7F2;' sender="#messages.sender">
					<s:property value="#messages.sender"/> : <s:property value="#messages.Messages"/>
				</p>
			</s:iterator>
				<!-- chat contents shown here -->			
			</div>
		</div>
	</div>
	<form class="chatForm"> 
	<div class="row">
		<div class="col-lg-12">
			<input type="hidden" name="chatID" id="chatID" value="<s:property value="chatID"/>"/>
			
			<input type="hidden" name="currentTimestamp" id="currentTimestamp" value="<s:property value="currentTimestamp" />" />
		
			<div class="form-group">
				<div style="float:left; min-width:90%">
					<input name="textchat" class="form-control" required type="text" id="textchat"  />
				</div>	
				<div style="float:right">
					<input type="button" value="Send" class="btn btn-default" id="sendChatMessage"/>
				</div>
				
			</div>
		</div>	
		<!-- 	<input type="button" value="End Session" id="btnEndSession"/> -->
			<input type="hidden" name="friendToChat" id="friendToChat" value="<s:property value="friendToChat"/>"/>
	</div>
	</form>
	<script>		
		//for message load function
		
		function GetMessages() {
			$.ajax({
				url : "fetchnewmessages",
				data : $('.chatForm').serialize(),
				success : function(data) {
					console.log(data);
					var jsonObj = JSON.parse(data);

					console.log(jsonObj);

					$.each(jsonObj, function(i, v) {
						console.log(v.msg);
						$('#chatMessagesContent').append(
								"<p style='background-color:#C7E7F2;'>" + v.senderid +" : " +v.msg
										+ "</p>");
						$('#currentTimestamp').val(v.ts);
					});
					setTimeout(GetMessages, 800);
				}
			});
		}
		setTimeout(GetMessages, 800);
		$('#sendChatMessage').click(function() {
			$.ajax({
				url : "sendmessage",
				type : "POST",
				data : $('.chatForm').serialize(),
				success : function(result) {
					$('#textchat').val('');
					//$('#chatMessagesContent').append("<p style='background-color:#ececec;'>" + result + "</p>" );
				}
			});
		});
		$('.chatForm').on('keypress', function(e){
			if ( e.keyCode == 13){
				e.preventDefault();
				$('#sendChatMessage').click();
				return false;			
			}
	 
		});
	</script>
</div>
</div>
</div>
