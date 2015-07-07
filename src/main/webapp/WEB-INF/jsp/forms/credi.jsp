<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<form:form commandName="claimSubItemForm" modelAttribute="claimSubItemForm" id="crediForm">
	<div class="error" style="display:none;">
      	<span></span>.<br style="clear:all"/>
    </div>
    <div class="border-box clearfix">
		<hr>
		<h3>Creditor Information</h3>
		<div class="field-row clearfix">
			<label for="creditorName">Creditor Name</label>
			<form:input path="credi['creditorName']" id="creditorName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="creditorContactInfo">Contact Info</label>
			<form:input path="credi['creditorContactInfo']" id="creditorContactInfo" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="creditorAmount">Amount $</label>
			<form:input path="credi['creditorAmount']" onkeypress="return(currencyFormat(this,',','.',event))" id="creditorAmount" class="small"/>
		</div>
		<div class="field-row clearfix">
			<label for="creditorAccountNumber">Account Number</label>
			<form:input path="credi['creditorAccountNumber']" id="creditorAccountNumber" class="small"/>
			<form:hidden path="credi['dataElementSeq']"/>	
		</div>
	</div>

	<tags:complaintSubItem />

</form:form>

