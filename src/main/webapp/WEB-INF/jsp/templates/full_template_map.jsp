<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title">
	<tiles:insertAttribute name="pageTitle" />
</c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>

<title><c:out value="${title}"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<link href="/iris/css/irisMasterMap.css" rel="stylesheet" type="text/css" />
<link href="/iris/css/irisForms.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/iris/js/irisIncludes.js"></script>
<script type="text/javascript">
	ieStyle();
</script>

</head>

<body>

<div id="skipToContent"><a href="#contentSkip">Skip to Content</a></div>

<div id="wrapper">
	<div id="branding">
		<script type="text/javascript">brandingContent();</script>
	</div>
	
	<!-- Menu and content -->
	<tiles:insertAttribute name="content"/>
		
	<div class="clear"></div>
</div>

<div id="ag-info">
	<div style="text-align:center; color: white"><script type="text/javascript">footer();</script></div>
	<div style="text-align:center; color:#606060"><jsp:include page="/version.jsp" /></div>
</div>

</body>
</html>
