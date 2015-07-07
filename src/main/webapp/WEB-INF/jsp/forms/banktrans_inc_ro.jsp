<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized Withdrawal from Your Checking or Savings Account</h2>
	<hr>
	<h3>Bank or Credit Union Information</h3>
	<div class="field-row clearfix">
		<label for="bankName">Name</label>
		<b>${formBean.banktrans.bankName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="bankAddress">Address</label>
		<b>${formBean.banktrans.bankAddress}</b>
	</div>
	<div class="field-row clearfix">
		<label for="bankCity">City</label>
		<b>${formBean.banktrans.bankCity}</b>
	</div>
	<div class="field-row clearfix">
		<label for="bankState">State</label>
		<b>${formBean.banktrans.bankState}</b>
	</div>
	<div class="field-row clearfix">
		<label for="bankZipCode">Zip Code</label>
		<b>${formBean.banktrans.bankZipCode}</b>
	</div>
	<div class="field-row clearfix">
		<label for="bankPhoneNumber">Phone</label>
		<b>${formBean.banktrans.bankPhoneNumber}</b>
	</div>
	<hr>
	<h3>Bank or Credit Union Contact Information</h3>
	<div class="field-row clearfix">
		<label for="contactFirstName">First Name</label>
		<b>${formBean.banktrans.contactFirstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="contactLastName">Last Name</label>
		<b>${formBean.banktrans.contactLastName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="contactPhoneNumber">Phone</label>
		<b>${formBean.banktrans.contactPhoneNumber}</b>
	</div>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="dateLearned">When did you learn about the unauthorized withdrawal(s) from your account? </label>
		<b>${formBean.banktrans.dateLearned}</b>
	</div>
	<div class="field-row clearfix">
		<label for="ssnIncidentDesc">Describe the Incident</label>
		<b>${formBean.banktrans.incidentDesc}</b>
	</div>
	<hr>
	<h3>Account Holder</h3>
	<div id="accouDialog"></div>
    <div id="accou">
    	<tags:accouTable_ro />
    </div>
	<hr>
	<h3>Unauthorized Transaction on Account</h3>
	<div id="transDialog"></div>
    <div id="trans">
    	<tags:transTable_ro />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div id="banktransSuspectDialog"></div>
   	<div id="banktransSuspect">
   		<tags:suspectsTable_ro />
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>





















