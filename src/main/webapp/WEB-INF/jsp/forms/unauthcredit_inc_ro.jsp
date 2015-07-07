<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized Use of Your Credit Card</h2>
	<hr>
	<h3>Cardholder Information</h3>
	<div class="field-row clearfix">
		<label for="unauthcreditFirstName"><span class="redtext">*</span>First Name</label>
		<b>${formBean.unauthcredit.firstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditMiddleName">Middle Initial</label>
		<b>${formBean.unauthcredit.middleName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditLastName"><span class="redtext">*</span>Last Name</label>
		<b>${formBean.unauthcredit.lastName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="cardNumber"><span class="redtext">*</span>Card Number</label>
		<b>${formBean.unauthcredit.cardNumber}</b>
	</div>
	<div class="field-row clearfix">
		<label for="cardType">Card Type:<br>(Visa, MasterCard, etc.)</label>
		<b>${formBean.unauthcredit.cardType}</b>
	</div>
	<hr>
	<h3>Card Issuer Information</h3>
	<div class="field-row clearfix">
		<label for="issuerName">Name of Issuer</label>
		<b>${formBean.unauthcredit.issuerName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="billingAddress">Billing Address</label>
		<b>${formBean.unauthcredit.billingAddress}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditCity">City</label>
		<b>${formBean.unauthcredit.city}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditState">State</label>
		<b>${formBean.unauthcredit.state}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditZipCode">Zip Code</label>
		<b>${formBean.unauthcredit.zipCode}</b>
	</div>
	<div class="field-row clearfix">
		<label for="issuerPhoneNumber">Phone</label>
		<b>${formBean.unauthcredit.issuerPhoneNumber}</b>
	</div>
	<hr>
	<h3>Account Information</h3>
	<div class="field-row clearfix">
		<label for="dateAccountOpened">Approximate date account was created</label>
		<b>${formBean.unauthcredit.dateAccountOpened}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditBalanceDue">Balance Due $</label>
		<b>${formBean.unauthcredit.balanceDue}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditIncidentDesc">Describe the Incident</label>
		<b>${formBean.unauthcredit.incidentDesc}</b>
	</div>
	
	<hr>
	<h3>Unauthorized Transactions Information</h3>
	<div id="tranxDialog"></div>
    <div id="tranx">
    	<tags:tranxTable_ro />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div id="unauthcreditSuspectDialog"></div>
   	<div id="unauthcreditSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
