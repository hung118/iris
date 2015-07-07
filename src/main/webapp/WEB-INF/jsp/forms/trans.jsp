<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript">
	function toggle(type) {
		if (type == "CHECK") {
			$("#transTypeDiv").text("Check Number");
		} else if (type == "WIRE") {
			$("#transTypeDiv").text("Wire Transfer Number");
		} else if (type == "ATM") {
			$("#transTypeDiv").text("ATM Location");
		} else if (type == "OTHER") {
			$("#transTypeDiv").text("Other");
		}
	}
</script>

<form:form commandName="claimSubItemForm" modelAttribute="claimSubItemForm" id="transForm">
	<div class="error" style="display:none;">
      	<span></span>.<br style="clear:all"/>
    </div>
    <div class="border-box clearfix">
		<hr>
		<h3>Transaction Information</h3>
		<div class="field-row clearfix">
			<label for="transDate"><span class="redtext">*</span>Date of Withdrawal</label>
			<form:input path="trans['transDate']" id="transDate" cssClass="datepicker" />
		</div>
		<div class="field-row clearfix">
			<label>Account Type</label>
		</div>    
    	<div class="field-row clearfix">
			<div class="clearfix">
				<form:radiobutton path="trans['transAccountType']" value="CHECKING" id="transAccountType1"/>
				<label for="transAccountType1">Checking</label>
			</div>
			<div class="clearfix">
				<form:radiobutton path="trans['transAccountType']" value="SAVINGS" id="transAccountType2"/>
				<label for="transAccountType2">Savings</label>
			</div>
		</div>
		<div class="field-row clearfix">
			<label for="transAmount">Amount of Withdrawal $</label>
			<form:input path="trans['transAmount']" id="transAmount" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
		</div>
    	<div class="field-row clearfix">
			<label>How was the withdrawal done?</label>
		</div>    
    	<div class="field-row clearfix">
			<div class="clearfix">
				<form:radiobutton path="trans['transType']" value="CHECK" id="transType1" onclick="toggle(this.value)"/>
				<label for="transType1">Check</label>
			</div>
			<div class="clearfix">
				<form:radiobutton path="trans['transType']" value="WIRE" id="transType2" onclick="toggle(this.value)"/>
				<label for="transType2">Wire Transfer</label>
			</div>
			<div class="clearfix">
				<form:radiobutton path="trans['transType']" value="ATM" id="transType3" onclick="toggle(this.value)"/>
				<label for="transType3">ATM</label>
			</div>
			<div class="clearfix">
				<form:radiobutton path="trans['transType']" value="OTHER" id="transType4" onclick="toggle(this.value)"/>
				<label for="transType4">Other</label>
			</div>
		</div>
    	<div class="field-row clearfix">
			<label for="transTypeParam"><span id="transTypeDiv">Check Number</span></label>
			<form:input path="trans['transTypeParam']" id="transTypeParam" class="small"/>
		</div>
		<hr>
		<h3>Company or Person That Withdrew Money from Your Account</h3>
		<div class="field-row clearfix">
			<label for="transCompanyOrPerson"><span style="color:red">*</span>Company or Person</label>
			<form:input path="trans['transCompanyOrPerson']" id="transCompanyOrPerson" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="transAddress">Address</label>
			<form:input path="trans['transAddress']" id="transAddress" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="transCity">City</label>
			<form:input path="trans['transCity']" id="transCity" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="transState">State</label>
			<tags:stateOptions propertyName="trans['transState']" styleClassName="single-select2 small" />
		</div>
		<div class="field-row clearfix">
			<label for="transZipCode">Zip Code</label>
			<form:input path="trans['transZipCode']" id="transZipCode" class="x-small"/>
		</div>
		<div class="field-row clearfix">
			<label for="transPhoneNumber">Phone</label>
			<form:input path="trans['transPhoneNumber']" id="transPhoneNumber" class="small"/>
			<form:hidden path="trans['dataElementSeq']"/>	
		</div>  
    </div>
    
	<tags:complaintSubItem />

</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$("#transZipCode").mask("99999");	
		$("#transPhoneNumber").mask("999-999-9999");
		if ($("#transType1").attr("checked") == "checked") $("#transTypeDiv").text("Check Number");
		if ($("#transType2").attr("checked") == "checked") $("#transTypeDiv").text("Wire Transfer Number");
		if ($("#transType3").attr("checked") == "checked") $("#transTypeDiv").text("ATM Location");
		if ($("#transType4").attr("checked") == "checked") $("#transTypeDiv").text("Other");		
	});
</script>	
