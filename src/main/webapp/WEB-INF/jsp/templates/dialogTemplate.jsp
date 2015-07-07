<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title><tiles:insertAttribute name="title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/irisForms.css" rel="stylesheet" type="text/css" />

</head>
<body>

	<tiles:insertAttribute name="content" />

</body>
</html>