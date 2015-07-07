<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized Loan</h2>
	<hr>
	<h3>Loan Officer</h3>
	<div class="field-row clearfix">
		<label for="loanOfficerFirstName">First Name</label>
		<b>${formBean.loan.loanOfficerFirstName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="loanOfficerLastName">Last Name</label>
		<b>${formBean.loan.loanOfficerLastName}</b>
	</div>
	<hr>
	<h3>Loan Institution</h3>
	<div class="field-row clearfix">
		<label for="loanBusinessName">Business Name</label>
		<b>${formBean.loan.businessName}</b>
	</div>
	<div class="field-row clearfix">
		<label for="loanBusinessAddress">Address</label>
		<b>${formBean.loan.businessAddress}</b>
	</div>
	<div class="field-row clearfix">
		<label for="loanBusinessCity">City</label>
		<b>${formBean.loan.businessCity}</b>
	</div>
	<div class="field-row clearfix">
		<label for="businessState">State</label>
		<b>${formBean.loan.businessState}</b>
	</div>
	<div class="field-row clearfix">
		<label for="LoanBusinessZipCode">Zip Code</label>
		<b>${formBean.loan.businessZipCode}</b>
	</div>
	<div class="field-row clearfix">
		<label for="loanBusinessPhoneNumber">Phone</label>
		<b>${formBean.loan.businessPhoneNumber}</b>
	</div>
	<hr>
	<h3>Loan Information</h3>
	<div class="field-row clearfix">
		<label for="loanNumber">Loan Number</label>
		<b>${formBean.loan.loanNumber}</b>
	</div>
	<div class="field-row clearfix">
		<label for="loanAmount">Loan Amount $</label>
		<b>${formBean.loan.loanAmount}</b>
	</div>
	<div class="field-row clearfix">
		<label for="loanDateIssued">Date Issued</label>
		<b>${formBean.loan.dateIssued}</b>
	</div>
	<div class="field-row clearfix">
		<label for="loanDateLearned">When did you learn that someone had taken out a loan using your personal identifying information?</label>
		<b>${formBean.loan.dateLearned}</b>
	</div>
	<div class="field-row clearfix">
		<label for="howLearned">How did you learn that someone had taken out a loan using your personal identifying information?</label>
		<b>${formBean.loan.howLearned}</b>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div id="loanSuspectDialog"></div>
   	<div id="loanSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
