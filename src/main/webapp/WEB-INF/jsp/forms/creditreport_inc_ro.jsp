<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized Use of Your Credit Card</h2>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="creditreportDateLearned">When did you learn the credit card(s) or credit account(s) were on your credit report?</label>
		<b>${formBean.creditreport.dateLearned}</b>
	</div>
	<div class="field-row clearfix">
		<label for="unrecognizableInquires">Are there any inquiries on your account you do not recognize?</label>
		<b>${formBean.creditreport.unrecognizableInquires}</b>
	</div>
	<div class="field-row clearfix">
		<label for="accountNumber">Account Number</label>
		<b>${formBean.creditreport.accountNumber}</b>
	</div>
	<div class="field-row clearfix">
		<label for="creditLimit">Credit Limit $</label>
		<b>${formBean.creditreport.creditLimit}</b>
	</div>
	<div class="field-row clearfix">
		<label for="dateCreated">Date Account Created</label>
		<b>${formBean.creditreport.dateCreated}</b>
	</div>
	<div class="field-row clearfix">
		<label for="isBalanceDue">Is Balance Due?</label>
		<b>${formBean.creditreport.isBalanceDue}</b>
	</div>
	<div class="field-row clearfix">
		<label for="balanceDue">Amount Due $</label>
		<b>${formBean.creditreport.balanceDue}</b>
	</div>
	<div class="field-row clearfix">
		<label for="creditreportIncidentDesc">Describe the Incident</label>
		<b>${formBean.creditreport.incidentDesc}</b>
	</div>
	
	<hr>
	<h3>Unauthorized Account on Credit Report Information</h3>
	<div id="issueDialog"></div>
    <div id="issue">
    	<tags:issueTable_ro />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div id="creditreportSuspectDialog"></div>
   	<div id="creditreportSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
