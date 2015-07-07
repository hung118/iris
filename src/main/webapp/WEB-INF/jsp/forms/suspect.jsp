<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<form:form commandName="claimSubItemForm" modelAttribute="claimSubItemForm" id="${claimSubItemForm.suspectFormId}">
	<div class="error" style="display:none;">
      	<span></span>.<br style="clear:all"/>
    </div>
    <div class="border-box clearfix">
		<hr>
		<h3>Suspect Information</h3>
		<div class="field-row clearfix">
			<label for="suspectFirstName"><span style="color:red">*</span>First Name</label>
			<form:input path="suspect['firstName']" id="suspectFirstName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectMiddleName">Middle Initial</label>
			<form:input path="suspect['middleName']" id="suspectMiddleName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectLasttName"><span style="color:red">*</span>Last Name</label>
			<form:input path="suspect['lastName']" id="suspectLastName" class="medium"/>
		</div>
		<hr>
		<h3>Home Address</h3>
		<div class="field-row clearfix">
			<label for="suspectAddr1Street">Address</label>
			<form:input path="suspect['addr1Street']" id="suspectAddr1Street" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectAddr1City">City</label>
			<form:input path="suspect['addr1City']" id="suspectAddr1City" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="addr1State">State</label>
			<tags:stateOptions propertyName="suspect['addr1State']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="suspectAddr1Zip">Zip Code</label>
			<form:input path="suspect['addr1Zip']" id="suspectAddr1Zip" class="x-small"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectEmailAddress">Email Address</label>
			<form:input path="suspect['emailAddress']" id="suspectEmailAddress" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectEveningPhone">Phone Number</label>
			<form:input path="suspect['eveningPhone']" id="suspectEveningPhone" class="small"/>
		</div>
		<hr>
		<h3>Work Address</h3>
		<div class="field-row clearfix">
			<label for="suspectAddr2Street">Address</label>
			<form:input path="suspect['addr2Street']" id="suspectAddr2Street" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectAddr2City">City</label>
			<form:input path="suspect['addr2City']" id="suspectAddr2City" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="addr2State">State</label>
			<tags:stateOptions propertyName="suspect['addr2State']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="suspectAddr2Zip">Zip Code</label>
			<form:input path="suspect['addr2Zip']" id="suspectAddr2Zip" class="x-small"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectEmailAddress2">Email Address</label>
			<form:input path="suspect['emailAddress2']" id="suspectEmailAddress2" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectDayPhone">Phone Number</label>
			<form:input path="suspect['dayPhone']" id="suspectDayPhone" class="small"/>
		</div>
		<hr>
		<h3>Miscellaneous Information</h3>
		<div class="field-row clearfix">
			<label for="suspectDob">Date of Birth</label>
			<form:input path="suspect['dob']" id="suspectDob" cssClass="datepicker" />
		</div>
		<div class="field-row clearfix">
			<label for="suspectSsn">Social Security Number</label>
			<form:input path="suspect['ssn']" id="suspectSsn" class="small"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectDlNumber">Driver's License Number</label>
			<form:input path="suspect['dlNumber']" id="suspectDlNumber" class="small"/>
		</div>
		<div class="field-row clearfix">
			<label for="dlState">State Issued</label>
			<tags:stateOptions propertyName="suspect['dlState']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="race">Race</label>
			<tags:raceOptions propertyName="suspect['race']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="sex">Sex</label>
			<tags:sexOptions propertyName="suspect['sex']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="suspectPhysicalDescription">Physical Description</label>
			<form:textarea path="suspect['physicalDescription']" id="suspectPhysicalDescription" class="xlarge" rows="8" cols="80" />
		</div>
		<div class="field-row clearfix">
			<label for="suspectGangAssoc">Gang Association</label>
			<form:input path="suspect['gangAssoc']" id="suspectGangAssoc" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="suspectGangMoniker">Gang Moniker</label>
			<form:input path="suspect['gangMoniker']" id="suspectGangMoniker" class="medium"/>
			<form:hidden path="suspect['personIndex']"/>
		</div>
    </div>

	<tags:complaintSubItem />

</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$("#suspectAddr1Zip").mask("99999");	
		$("#suspectEveningPhone").mask("999-999-9999");
		$("#suspectAddr2Zip").mask("99999");	
		$("#suspectDayPhone").mask("999-999-9999");
		$("#suspectSsn").mask("999-99-9999");
	});
</script>	
