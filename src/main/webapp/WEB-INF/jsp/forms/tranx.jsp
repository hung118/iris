<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<form:form commandName="claimSubItemForm" modelAttribute="claimSubItemForm" id="tranxForm">
	<div class="error" style="display:none;">
      	<span></span>.<br style="clear:all"/>
    </div>
    <div class="border-box clearfix">
		<hr>
		<h3>Unauthorized Transaction Information</h3>
		<div class="field-row clearfix">
			<label for="tranxAmount">Transaction Amount $</label>
			<form:input path="tranx['tranxAmount']" id="tranxAmount" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
		</div>
		<div class="field-row clearfix">
			<label for="tranxDate">Transaction Date</label>
			<form:input path="tranx['tranxDate']" id="tranxDate" cssClass="datepicker" />
		</div>
		<div class="field-row clearfix">
			<label>Charges made to</label>
		</div>    
    	<div class="field-row clearfix">
			<div class="clearfix">
				<form:radiobutton path="tranx['tranxRecipientType']" value="COMPANY" id="tranxRecipientType1"/>
				<label for="tranxRecipientType1">Company</label>
			</div>
			<div class="clearfix">
				<form:radiobutton path="tranx['tranxRecipientType']" value="PERSON" id="tranxRecipientType2"/>
				<label for="tranxRecipientType2">Person</label>
			</div>
		</div>
		<div class="field-row clearfix">
			<label for="tranxFirstName">First Name</label>
			<form:input path="tranx['tranxFirstName']" id="tranxFirstName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="tranxMiddleName">Middle Initial</label>
			<form:input path="tranx['tranxMiddleName']" id="tranxMiddleName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="tranxLastName">Last Name</label>
			<form:input path="tranx['tranxLastName']" id="tranxLastName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="tranxBusinessName">Business Name</label>
			<form:input path="tranx['tranxBusinessName']" id="tranxBusinessName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="tranxAddress">Address</label>
			<form:input path="tranx['tranxAddress']" id="tranxAddress" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="tranxCity">City</label>
			<form:input path="tranx['tranxCity']" id="tranxCity" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="tranxState">State</label>
			<tags:stateOptions propertyName="tranx['tranxState']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="tranxZipCode">Zip Code</label>
			<form:input path="tranx['tranxZipCode']" id="tranxZipCode" class="x-small"/>
			<form:hidden path="tranx['dataElementSeq']"/>	
		</div>
	</div>

	<tags:complaintSubItem />

</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$("#tranxZipCode").mask("99999");	
	});
</script>	
