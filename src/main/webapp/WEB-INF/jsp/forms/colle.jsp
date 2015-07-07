<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<form:form commandName="claimSubItemForm" modelAttribute="claimSubItemForm" id="colleForm">
	<div class="error" style="display:none;">
      	<span></span>.<br style="clear:all"/>
    </div>
    <div class="border-box clearfix">
		<hr>
		<h3>Collection Agency Information</h3>
		<div class="field-row clearfix">
			<label for="collectorName">Name</label>
			<form:input path="colle['collectorName']" id="collectorName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="collectorAddress">Address</label>
			<form:input path="colle['collectorAddress']" id="collectorAddress" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="collectorCity">City</label>
			<form:input path="colle['collectorCity']" id="collectorCity" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="collectorState">State</label>
			<tags:stateOptions propertyName="colle['collectorState']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="collectorZipCode">Zip Code</label>
			<form:input path="colle['collectorZipCode']" id="collectorZipCode" class="small"/>
		</div>
		<div class="field-row clearfix">
			<label for="collectorPhoneNumber">Phone Number</label>
			<form:input path="colle['collectorPhoneNumber']" id="collectorPhoneNumber" class="small"/>
			<form:hidden path="colle['dataElementSeq']"/>	
		</div>
	</div>

	<tags:complaintSubItem />

</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$("#collectorZipCode").mask("99999");	
		$("#collectorPhoneNumber").mask("999-999-9999");
	});
</script>	