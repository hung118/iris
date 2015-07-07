<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="clearfix">
	<button name="cancel" type="button" onClick="MM_goToURL('parent','selectComplaint.shtml?trackingNumber=${formBean.trackingNumber}');" class="button float-right">Cancel</button>
	<c:if test="${status == 'n'}">
		<button name="updateIncident" type="submit" onclick="return validateIndForm();" class="button float-right">Update Incident</button>
	</c:if>

	<form:hidden path="trackingNumber"/>
	<form:hidden path="fraudTypeCd"/>
	<form:hidden path="fraudTypeSeq"/>
	<form:hidden path="wsAction"/>
</div>	

<div id="dialog-confirm"></div>

<script type="text/javascript" src="/iris/js/mainForm.js"></script>
