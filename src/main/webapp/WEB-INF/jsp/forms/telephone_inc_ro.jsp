<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Telephone Service Fraud</h2>
	<hr>
	<h3>Telephone Services Information</h3>
	<div class="field-row clearfix">
		<label for="serviceType">What type of telephone services have been received?</label>
		<b>${formBean.telephone.serviceType}</b>
	</div>
	<div class="field-row clearfix">
		<label for="providerName">Business Name</label>
		<b>${formBean.telephone.providerName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="providerAddress">Address</label>
		<b>${formBean.telephone.providerAddress}</b>
	</div>
	<div class="field-row clearfix">
		<label for="providerCity">City</label>
		<b>${formBean.telephone.providerCity}</b>
	</div>
	<div class="field-row clearfix">
		<label for="providerState">State</label>
		<b>${formBean.telephone.providerState}</b>
	</div>
	<div class="field-row clearfix">
		<label for="providerZipCode">Zip Code</label>
		<b>${formBean.telephone.providerZipCode}</b>
	</div>
	<div class="field-row clearfix">
		<label for="providerPhoneNumber">Phone Number</label>
		<b>${formBean.telephone.providerPhoneNumber}</b>
	</div>
	<hr>
	<h3>Account Information</h3>
	<div class="field-row clearfix">
		<label for="telFirstName">First Name</label>
		<b>${formBean.telephone.firstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telLastName">Last Name</label>
		<b>${formBean.telephone.lastName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telAddress">Address</label>
		<b>${formBean.telephone.address}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telCity">City</label>
		<b>${formBean.telephone.city}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telState">State</label>
		<b>${formBean.telephone.state}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telZipCode">Zip Code</label>
		<b>${formBean.telephone.zipCode}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telPhoneNumber">Phone Number</label>
		<b>${formBean.telephone.phoneNumber}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telEstablished">When was the account established?</label>
		<b>${formBean.telephone.dateEstablished}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telAmountOwed">Amount Due $</label>
		<b>${formBean.telephone.amountOwed}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telDateLearned">When did you learn that someone had opened a telephone service using your personal identifying information?</label>
		<b>${formBean.telephone.dateLearned}</b>
	</div>
	<div class="field-row clearfix">
		<label for="telHowLearned">How did you learn that someone had opened a telephone service using your personal identifying information?</label>
		<b>${formBean.telephone.howLearned}</b>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div id="telephoneSuspectDialog"></div>
   	<div id="telephoneSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
