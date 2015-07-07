<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<form:form method="post" action="updateIncident.shtml" modelAttribute="formBean">
	<div id="content" class="site-full">
		<div id="content-main" class="site">
			<div id="content-border-box">
				<tags:incidentHeader/>

				<jsp:include page="/WEB-INF/jsp/forms/victim_inc_ro.jsp" />
			
				<jsp:include page="/WEB-INF/jsp/forms/banktrans_inc_ro.jsp" />
			
				<tags:complaintItemFormButtons/>
			</div>
		</div>
	</div>
</form:form>

