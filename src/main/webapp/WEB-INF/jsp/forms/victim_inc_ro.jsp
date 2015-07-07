<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<div class="border-box clearfix">
	<h2>Victim/Witness Information</h2>
	<div class="field-row clearfix">
		<label for="victimFirstName"><span class="redtext">*</span>First Name</label>
		<b>${formBean.victim.firstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="victimMiddleName">Middle Name</label>
		<b>${formBean.victim.middleName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="victimLastName"><span class="redtext">*</span>Last Name</label>
		<b>${formBean.victim.lastName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="addr1Street"><span class="redtext">*</span>Address</label>
		<b>${formBean.victim.addr1Street}</b>
	</div>
	<div class="field-row clearfix">
		<label for="addr1City"><span class="redtext">*</span>City</label>
		<b>${formBean.victim.addr1City}</b>
	</div>
	<div class="field-row clearfix">
		<label for="addr1State">State</label>
		<b>${formBean.victim.addr1State}</b>
	</div>
	<div class="field-row clearfix">
		<label for="addr1Zip"><span class="redtext">*</span>Zip Code</label>
		<b>${formBean.victim.addr1Zip}</b>
	</div>
	<div class="field-row clearfix">
		<label for="victimEmailAddress"><span class="redtext">*</span>Email Address</label>
		<b>${formBean.victim.emailAddress}</b>
	</div>
	<div class="field-row clearfix">
		<label for="dayPhone">Phone</label>
		<b>${formBean.victim.dayPhone}</b>
	</div>
	<div class="field-row clearfix">
		<label for="dob">Date of Birth</label>
		<b>${formBean.victim.dob}</b>
	</div>
	<div class="field-row clearfix">
		<label for="victimSsn"><span class="redtext">*</span>Social Security Number</label>
		<b>${formBean.victim.ssn}</b>
	</div>
</div>
