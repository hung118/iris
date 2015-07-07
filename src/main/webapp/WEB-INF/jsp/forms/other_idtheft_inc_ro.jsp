<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Other Identity Theft</h2>
	<hr>
	<h3>Other Identity Theft Information</h3>
	<div class="field-row clearfix">
		<label for="ottHolder">Whose Incident Is This?</label>
		<b>${formBean.other_idtheft.holder}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ottFirstName"><span class="redtext">*</span>First Name</label>
		<b>${formBean.other_idtheft.firstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ottMiddleName">Middle Initial</label>
		<b>${formBean.other_idtheft.middleName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ottLastName"><span class="redtext">*</span>Last Name</label>
		<b>${formBean.other_idtheft.lastName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ottSsn"><span class="redtext">*</span>Social Security Number</label>
		<b>${formBean.other_idtheft.ssn}</b>
	</div>
	<div class="field-row clearfix">
		<label for="incidentDate"><span class="redtext">*</span>Date of Incident</label>
		<b>${formBean.other_idtheft.incidentDate}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ottIncidentDesc"><span class="redtext">*</span>Describe the incident</label>
		<b>${formBean.other_idtheft.incidentDesc}</b>
	</div>
</div>