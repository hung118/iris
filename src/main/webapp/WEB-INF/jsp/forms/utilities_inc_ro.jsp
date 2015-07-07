<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Utilities Fraud</h2>
	<hr>
	<h3>Utility Information</h3>
	<div class="field-row clearfix">
		<label for="utilAccountNumber">Account Number</label>
		<b>${formBean.utilities.accountNumber}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilitiesType">What type of utilities have been received?</label>
		<b>${formBean.utilities.utilitiesType}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilCompanyName">Utility Name</label>
		<b>${formBean.utilities.companyName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilAddress">Your Address</label>
		<b>${formBean.utilities.address}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilCity">City</label>
		<b>${formBean.utilities.city}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilState">State</label>
		<b>${formBean.utilities.state}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilZipCode">Zip Code</label>
		<b>${formBean.utilities.zipCode}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilPhoneNumber">Phone Number</label>
		<b>${formBean.utilities.phoneNumber}</b>
	</div>
	<hr>
	<h3>Contact Person at Utility Company</h3>
	<div class="field-row clearfix">
		<label for="utilFirstName">First Name</label>
		<b>${formBean.utilities.firstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilLastName">Last Name</label>
		<b>${formBean.utilities.lastName}</b>
	</div>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="utilEstablished">When was the account established?</label>
		<b>${formBean.utilities.dateEstablished}</b>
	</div>
	<div class="field-row clearfix">
		<label>Is there an amount owed on the account?</label>
		<b>${formBean.utilities.amountOwed}</b>
	</div>  
	<div class="field-row clearfix">
		<label for="utilDateLearned">When did you learn that someone had opened an utility account using your personal identifying information?</label>
		<b>${formBean.utilities.dateLearned}</b>
	</div>
	<div class="field-row clearfix">
		<label for="utilHowLearned">How did you learn that someone had opened an utility account using your personal identifying information?</label>
		<b>${formBean.utilities.howLearned}</b>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div id="utilitiesSuspectDialog"></div>
   	<div id="utilitiesSuspect">
   		<tags:suspectsTable_ro />
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
