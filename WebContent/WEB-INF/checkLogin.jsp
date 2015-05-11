<html>
<head>
    <title>Disable Browser Back Button Using JavaScript</title>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js">
        </script>
  <script type="text/javascript">

	function init(){

		document.getElementById('auto').click();
		document.getElementById('one').click();
		document.getElementById('bbb').click();
	}

	onload=init;

</script>
<script src="jquery-1.11.2.min.js"></script>
</head>


<body id = "bbb" style="font-family:Arial;font-size:15px;">
<div onload="init()" id="auto">
    <a  id = "one" href="index.jsp">Click Here To Login</a>
    </div>
</body>

<script>

    $(document).ready(function() {
        function disableBack() { window.history.forward() }

        window.onload = disableBack();
        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
    });
    
    $(function() {
    $('#auto').find('#one').trigger('click'); 
    });
    
    window.onload=function(){
    	document.getElementById('auto').click();
    }
</script>
</html>

