<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Driver's License / ID Lost, Stolen or Misused</h2>
	<hr>
	<h3>Victim Information</h3>
	<div class="field-row clearfix">
		<label for="idloststolenDate">Date Missing</label>
		<b>${formBean.idloststolen.date}</b>
	</div>
	<div class="field-row clearfix">
		<label>ID Type</label>
		<b>${formBean.idloststolen.idType}</b>
	</div>  
	<div class="field-row clearfix">
		<label for="idNumber">Driver's License or ID Number</label>
		<b>${formBean.idloststolen.idNumber}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idStateIssued">State Issued</label>
		<b>${formBean.idloststolen.idState}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idFirstName">First Name</label>
		<b>${formBean.idloststolen.firstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idMiddleName">Middle Initial</label>
		<b>${formBean.idloststolen.middleName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idLastName">Last Name</label>
		<b>${formBean.idloststolen.lastName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idAddress">Address</label>
		<b>${formBean.idloststolen.address}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idCity">City</label>
		<b>${formBean.idloststolen.city}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idState">State</label>
		<b>${formBean.idloststolen.state}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idZipCode">Zip Code</label>
		<b>${formBean.idloststolen.zipCode}</b>
	</div>
	<hr>
	<h3>Location of where lost or stolen, if known</h3>
	<div class="field-row clearfix">
		<label for="lostAddress">Address</label>
		<b>${formBean.idloststolen.lostAddress}</b>
	</div>
	<div class="field-row clearfix">
		<label for="lostCity">City</label>
		<b>${formBean.idloststolen.lostCity}</b>
	</div>
	<div class="field-row clearfix">
		<label for="lostState">State</label>
		<b>${formBean.idloststolen.lostState}</b>
	</div>
	<div class="field-row clearfix">
		<label for="lostZipCode">Zip Code</label>
		<b>${formBean.idloststolen.lostZipCode}</b>
	</div>
	<div class="field-row clearfix">
		<label for="idloststolenIncidentDesc">Describe the Incident</label>
		<b>${formBean.idloststolen.incidentDesc}</b>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div id="idloststolenSuspectDialog"></div>
   	<div id="idloststolenSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
