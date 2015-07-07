<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Email Identity Theft (Phishing)</h2>
	<hr>
	<h3>Email Information</h3>
	<div class="field-row clearfix">
		<label for="sendersEmail">Sender's Email</label>
		<b>${formBean.email.sendersEmail}</b>
	</div>
	<div class="field-row clearfix">
		<label for="recipientsEmail">Recipient's Email</label>
		<b>${formBean.email.recipientsEmail}</b>
	</div>
	<div class="field-row clearfix">
		<label for="dateReceived">When did you receive this email?</label>
		<b>${formBean.email.dateReceived}</b>
	</div>
	<div class="field-row clearfix">
		<label for="dateProvided">When did you provide your personal identifying information?</label>
		<b>${formBean.email.dateProvided}</b>
	</div>
	<div class="field-row clearfix">
		<label for="providedInfo">What personal identifying information did you provide?</label>
		<b>${formBean.email.providedInfo}</b>
	</div>
	<div class="field-row clearfix">
		<label for="description">Description of Situation</label>
		<b>${formBean.email.description}</b>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div id="emailSuspectDialog"></div>
   	<div id="emailSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
