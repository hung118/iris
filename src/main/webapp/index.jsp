<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>IRIS</title>

<script type="text/javascript">
function goStart() {
	document.getElementById("waitNext").style.display = "block";
		
	if (navigator.appName == "Microsoft Internet Explorer") {	// fix for IE by reset the animated image.
		waitImg= document.getElementById("waitId");
		window.setTimeout("waitImg.src = waitImg.src", 100);
	}
	
	document.forms[0].submit(); 
}
</script>
</head>
<body onload="goStart()">
	<form:form method="GET" action="/iris/secure/login.shtml" />

	<div id="waitNext" style="display:none; text-align:center">
		<br /><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<B>Loading IRIS ... ... Please wait!</B>
		<br/><br/><br/>
		<img src="/iris/img/wait.gif" id="waitId" width="50" height="50" alt="Wait" />
		<br /><br /><br /> 
	</div>
</body>
</html>
