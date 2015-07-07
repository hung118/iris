<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<form:form method="post" action="updateIncident.shtml" modelAttribute="formBean" id="claimItemForm">
	<div id="content" class="site-full">
		<div id="content-main" class="site">
			<div id="content-border-box">
				<tags:incidentHeader/>
				
				<div class="error" style="display:none;">
				     	<span></span>.<br style="clear:all"/><br />
				</div>
				
				<jsp:include page="/WEB-INF/jsp/forms/victim_inc.jsp" />
			
				<jsp:include page="/WEB-INF/jsp/forms/utilities_inc.jsp" />
			
				<tags:complaintItemFormButtons/>			
			</div>
		</div>
	</div>
</form:form>

