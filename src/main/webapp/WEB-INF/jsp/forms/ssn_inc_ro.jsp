<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="border-box clearfix">
	<h2>Social Security Number Theft Report</h2>
	<div class="field-row clearfix">
		<label for="ssnHolder">Whose number is this?</label>
		<b>${formBean.ssn.ssnHolder}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ssnStatus">Status</label>
		<b>${formBean.ssn.ssnStatus}</b>
	</div>
	<div class="field-row clearfix">
		<label for="firstName">First Name<span class="redtext">*</span></label>
		<b>${formBean.ssn.firstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ssnMiddleName">Middle Initial</label>
		<b>${formBean.ssn.middleName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ssnLastName">Last Name<span class="redtext">*</span></label>
		<b>${formBean.ssn.lastName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ssnSsn"><span class="redtext">*</span>Social Security Number</label>
		<b>${formBean.ssn.ssn}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ssnDate">Date Lost or Stolen</label>
		<b>${formBean.ssn.date}</b>
	</div>
	<div class="field-row clearfix">
		<label for="location">Location Where Lost or Stolen</label>
		<b>${formBean.ssn.location}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ssnIncidentDesc">Describe the Incident</label>
		<b>${formBean.ssn.incidentDesc}</b>
	</div>
</div>
