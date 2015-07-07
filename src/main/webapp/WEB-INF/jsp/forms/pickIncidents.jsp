<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="post" action="processFrauds.shtml" modelAttribute="formBean" id="claimItemForm">
	<div id="content" class="site-full">
		<div id="content-main" class="site">
			<div id="loadContent" style="display:block;">		
				<div id="content-border-box">
					<div class="hello-name" class="clearfix">
						Hello ${userInfo.firstName} ${userInfo.middleName} ${userInfo.lastName}
					</div>
					<h2>Reported Frauds</h2>
					<table style="width:100%; border:0; cellspacing:0; cellpadding:2">
						<tr><td><b>You have selected to report the following fraud(s):</b></td></tr>
						<tr>
							<td>			
								<form:checkboxes items="${reportedFraudList}" path="frauds" id="id" itemLabel="label" element="div class='checkbox'" />
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td>Incident Number: ${formBean.trackingNumber}</td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td><b>Fill out the information below. A red (<span class="redtext">*</span>) asterisk indicates a required field.</b></td></tr>
						<tr><td>&nbsp;</td></tr>
					</table>
					
					<div class="error" style="display:none;">
					     	<span></span>.<br style="clear:all"/><br />
					</div>
					
					<jsp:include page="/WEB-INF/jsp/forms/victim_inc.jsp" />
		
					<c:forEach items="${reportedFraudList}" var="fraudType">
						<c:if test="${fraudType.id == 1}">
							<jsp:include page="/WEB-INF/jsp/forms/banktrans_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 2}">
							<jsp:include page="/WEB-INF/jsp/forms/benefits_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 15}">
							<jsp:include page="/WEB-INF/jsp/forms/unauthcredit_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 4}">
							<jsp:include page="/WEB-INF/jsp/forms/creditreport_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 11}">
							<jsp:include page="/WEB-INF/jsp/forms/loan_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 3}">
							<jsp:include page="/WEB-INF/jsp/forms/collector_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 9}">
							<jsp:include page="/WEB-INF/jsp/forms/idloststolen_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 14}">
							<jsp:include page="/WEB-INF/jsp/forms/ssn_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 18}">
							<jsp:include page="/WEB-INF/jsp/forms/telephone_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 16}">
							<jsp:include page="/WEB-INF/jsp/forms/utilities_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 13}">
							<jsp:include page="/WEB-INF/jsp/forms/suit_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 6}">
							<jsp:include page="/WEB-INF/jsp/forms/email_inc.jsp" />
						</c:if>
						<c:if test="${fraudType.id == 19}">
							<jsp:include page="/WEB-INF/jsp/forms/other_idtheft_inc.jsp" />
						</c:if>
					</c:forEach>
					<div class="clearfix">
						<button type="button" name="submitReport" onclick="return validateAndConfirm('submit');" class="button float-left">Submit Completed Report</button>
						<button type="button" name="saveReport" onclick="return validateAndConfirm('save');" class="button float-left">Save Incomplete Report</button>
						<button type="button" name="cancel" onclick="confirmCancel('deleteComplaint.shtml?trackingNumber=${formBean.trackingNumber}');" class="button float-left">Cancel</button>
						<form:hidden path="trackingNumber"/>
						<form:hidden path="actionType" id="actionType"/>
						<form:hidden path="wsAction"/>
					</div>				
				</div>
			</div>
		</div>
	</div>
</form:form>

<div id="dialog-confirm"></div>

<script type="text/javascript" src="/iris/js/mainForm.js"></script>
