<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/irisForms.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/irisIncludes.js"></script>

</head>

<body>
	<!-- ========================== Header ========================== -->
	<div id="header-container" class="site-full">
		<a class="visuallyhidden" href="#content-main" tabindex="1" title="esc_attr_e( 'Skip to content', 'wpboilerplate' );"></a>
		<div id="header-wrapper" class="site-full">
			<div id="header" class="site">
				<div id="header-wrapper-left" class="id-theft-header-logo ir">
					ID Theft Central - Utah's Identity Theft Solution
				</div>
				<div id="header-wrapper-right" class="ag-office-logo ir">
					Office of the Attorney General - State of Utah
				</div>
			</div>
		</div>
		<!-- ========================== Top Menu ========================== -->
		<div id="mobilemenu">
			<a href="#" id="mobile-mainmenu-button" class="ir">Main Menu</a>
			<span class="mobile-mainmenu-title"><a href="/index.php">ID Theft Central</a></span>
		</div>
		<div id="mainmenu-wrapper" class="h-blue clearfix">
			<div class="site">
					<ul>
						<%
							if ("iris.utah.gov".equalsIgnoreCase(request.getHeader("x-forwarded-host"))) {
						%>
							<li><a href="http://idtheft.utah.gov/report-identity-theft-instructions">Report New ID Theft</a></li>
						<%	} else { %>
							<li><a href="http://idtheft.at.utah.gov/report-identity-theft-instructions">Report New ID Theft</a></li>
						<% 	} %>
						<li><a href="/iris/secure/login.shtml" class="highlight">Report History</a></li>
						<%
							if ("iris.utah.gov".equalsIgnoreCase(request.getHeader("x-forwarded-host"))) {
						%>
							<li><a href="http://idtheft.utah.gov/?p=253">Victim Prevention Tips</a></li>
						<%	} else { %>
							<li><a href="http://idtheft.at.utah.gov/?p=253">Victim Prevention Tips</a></li>
						<% 	} %>
						<li><a href="javascript:logout()">Logout</a></li>
					</ul>
			</div>
		</div>
	</div>	

	<!-- ========================== Content ========================== -->
	
	<tiles:insertAttribute name="content"/>
	
	<!-- ========================== Footer ========================== -->
	<div id="footer-wrapper" class="site-full h-blue clearfix">
		<div id="footer" class="site">
			<div id="ag-logo-column">
				<a href="http://attorneygeneral.utah.gov"><img src="/iris/img/footer-ag-logo_2x.png" width="114" height="114" alt="Office of the Attorney General - State of Utah" /></a>
			</div>
			<div id="ag-info">
				<div style="text-align:center"><script type="text/javascript">footer();</script></div>
				<div style="text-align:center; color:#606060"><jsp:include page="/version.jsp" /></div>
			</div>
			<div id="uii-column">
			<ul>
				<li><a href="http://www.utah.gov/main/index">Utah.gov&nbsp;Home</a></li>
				<li><a href="http://www.utah.gov/disclaimer.html">Utah.gov&nbsp;Terms&nbsp;of&nbsp;Use</a></li>
				<li><a href="http://www.utah.gov/privacypolicy.html">Utah.gov&nbsp;Privacy&nbsp;Policy</a></li>
				<li><a href="http://www.utah.gov/accessibility.html">Utah.gov&nbsp;Accessibility&nbsp;Policy</a></li>
				<li><a href="http://www.utah.gov/translate.html">Translate&nbsp;Utah.gov</a></li>
			</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

<!-- ==== Google analytics on production only ==== -->
<%
	if ("iris.utah.gov".equalsIgnoreCase(request.getHeader("x-forwarded-host"))) {
%>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-16653987-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

<% } %>

</body>
</html>
