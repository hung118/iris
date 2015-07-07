<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="border-box clearfix">
	<h2>Social Security Number Theft Report</h2>
	<div class="field-row clearfix">
		<label for="ssnHolder">Whose number is this?</label>
		<form:select path="ssn['ssnHolder']" id="ssnHolder" class="single-select2 small">
			<form:option value="MINE" label="Mine" />
			<form:option value="CHILD" label="Child" />
			<form:option value="CLIENT" label="Client" />
			<form:option value="WARD" label="Ward" />
			<form:option value="PARENT" label="Parent" />
			<form:option value="SPOUSE" label="Spouse" />
		</form:select>
	</div>
	<div class="field-row clearfix">
		<form:radiobutton path="ssn['ssnStatus']" value="LOST" label="LOST" /> 
		<form:radiobutton path="ssn['ssnStatus']" value="STOLEN" label="STOLEN" />
	</div>
	<div class="field-row clearfix">
		<label for="firstName"><span class="redtext">*</span>First Name</label>
		<form:input path="ssn['firstName']" id="ssnFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="ssnMiddleName">Middle Initial</label>
		<form:input path="ssn['middleName']" id="ssnMiddleName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="ssnLastName"><span class="redtext">*</span>Last Name</label>
		<form:input path="ssn['lastName']" id="ssnLastName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="ssnSsn"><span class="redtext">*</span>Social Security Number</label>
		<form:input path="ssn['ssn']" id="ssnSsn" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="ssnDate">Date Lost or Stolen</label>
		<form:input path="ssn['date']" id="ssnDate" cssClass="datepicker" />
	</div>
	<div class="field-row clearfix">
		<label for="location">Location Where Lost or Stolen</label>
		<form:input path="ssn['location']" id="ssnLocation" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="ssnIncidentDesc">Describe the Incident</label>
		<form:textarea path="ssn['incidentDesc']" id="ssnIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="ssnLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#ssnSsn").mask("999-99-9999");	
		$("#ssnIncidentDesc").charCounter(4096, {container: "#ssnLccCharCount"});
	});
</script>
