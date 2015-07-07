<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Other Identity Theft</h2>
	<hr>
	<h3>Other Identity Theft Information</h3>
	<div class="field-row clearfix">
		<label for="ottHolder">Whose Incident Is This?</label>
		<form:select path="other_idtheft['holder']" id="ottHolder" class="single-select2 small">
			<form:option value="MINE" label="Mine" />
			<form:option value="CHILD" label="Child" />
			<form:option value="CLIENT" label="Client" />
			<form:option value="WARD" label="Ward" />
			<form:option value="PARENT" label="Parent" />
			<form:option value="SPOUSE" label="Spouse" />
		</form:select>
	</div>
	<div class="field-row clearfix">
		<label for="ottFirstName"><span class="redtext">*</span>First Name</label>
		<form:input path="other_idtheft['firstName']" id="ottFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="ottMiddleName">Middle Initial</label>
		<form:input path="other_idtheft['middleName']" id="ottMiddleName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="ottLastName"><span class="redtext">*</span>Last Name</label>
		<form:input path="other_idtheft['lastName']" id="ottLasttName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="ottSsn"><span class="redtext">*</span>Social Security Number</label>
		<form:input path="other_idtheft['ssn']" id="ottSsn" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="incidentDate"><span class="redtext">*</span>Date of Incident</label>
		<form:input path="other_idtheft['incidentDate']" id="incidentDate" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="ottIncidentDesc"><span class="redtext">*</span>Describe the Incident</label>
		<form:textarea path="other_idtheft['incidentDesc']" id="ottIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="ottLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#ottSsn").mask("999-99-9999");
		$("#ottIncidentDesc").charCounter(4096, {
			container : "#ottLccCharCount"
		});
	});
</script>
