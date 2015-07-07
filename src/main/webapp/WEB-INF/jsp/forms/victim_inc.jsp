<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="border-box clearfix">
	<h2>Victim/Witness Information</h2>
	<c:if test="${status == 'n'}">
		<span class="redtext">*</span>indicates a required field.
	</c:if>	
	<div class="field-row clearfix">
		<label for="victimFirstName"><span class="redtext">*</span>First Name</label>
		<form:input path="victim['firstName']" id="victimFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="victimMiddleName">Middle Name</label>
		<form:input path="victim['middleName']" id="victimMiddleName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="victimLastName"><span class="redtext">*</span>Last Name</label>
		<form:input path="victim['lastName']" id="victimLastName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="addr1Street"><span class="redtext">*</span>Address</label>
		<form:input path="victim['addr1Street']" id="addr1Street" class="medium"/> &nbsp; Address only, no PO Box, apartment, or suite.
	</div>
	<div class="field-row clearfix">
		<label for="addr1City"><span class="redtext">*</span>City</label>
		<form:input path="victim['addr1City']" id="addr1City" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="addr1State">State</label>
		<tags:stateOptions propertyName="victim['addr1State']" styleClassName="single-select2 small"/>
	</div>
	<div class="field-row clearfix">
		<label for="addr1Zip"><span class="redtext">*</span>Zip Code</label>
		<form:input path="victim['addr1Zip']" id="addr1Zip" class="x-small"/>
	</div>
	<div class="field-row clearfix">
		<label for="victimEmailAddress"><span class="redtext">*</span>Email Address</label>
		<form:input path="victim['emailAddress']" id="victimEmailAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="dayPhone">Phone</label>
		<form:input path="victim['dayPhone']" id="dayPhone" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="dob">Date of Birth</label>
		<form:input path="victim['dob']" id="dob" cssClass="datepicker" />
	</div>
	<div class="field-row clearfix">
		<label for="victimSsn"><span class="redtext">*</span>Social Security Number</label>
		<form:input path="victim['ssn']" id="victimSsn" class="small"/>
		
		<form:hidden path="victim['umdId']" />
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#addr1Zip").mask("99999");
		$("#dayPhone").mask("999-999-9999");
		$("#victimSsn").mask("999-99-9999");
	});
</script>
