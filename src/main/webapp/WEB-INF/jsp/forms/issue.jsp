<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<form:form commandName="claimSubItemForm" modelAttribute="claimSubItemForm" id="issueForm">
	<div class="error" style="display:none;">
      	<span></span>.<br style="clear:all"/>
    </div>
    <div class="border-box clearfix">
		<hr>
		<h3>Creditor Information</h3>
		<div class="field-row clearfix">
			<label for="issuerName"><span class="redtext">*</span>Creditor Name</label>
			<form:input path="issue['issuerName']" id="issuerName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="issuerAddress">Address</label>
			<form:input path="issue['issuerAddress']" id="issuerAddress" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="issuerCity">City</label>
			<form:input path="issue['issuerCity']" id="issuerCity" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="issuerState">State</label>
			<tags:stateOptions propertyName="issue['issuerState']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="issuerZipCode">Zip Code</label>
			<form:input path="issue['issuerZipCode']" id="issuerZipCode" class="x-small"/>
		</div>
		<div class="field-row clearfix">
			<label for="issuerPhoneNumber">Phone Number</label>
			<form:input path="issue['issuerPhoneNumber']" id="issuerPhoneNumber" class="small"/>
			<form:hidden path="issue['dataElementSeq']"/>	
		</div>
	</div>

	<tags:complaintSubItem />

</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$("#issuerZipCode").mask("99999");
		$("#issuerPhoneNumber").mask("999-999-9999");
	});
</script>	
