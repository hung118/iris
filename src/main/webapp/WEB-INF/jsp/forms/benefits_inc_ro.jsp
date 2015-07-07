<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Benefits Fraud</h2>
	<hr>
	<h3>Benefits Information</h3>
	<div class="field-row clearfix">
		<label for="benefitsReceived">What benefits were received?</label>
		<b>${formBean.benefits.benefitsReceived}</b>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsProvider">From which company or government entity were the benefits received?</label>
		<b>${formBean.benefits.benefitsProvider}</b>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsAmount">Amount of benefits received $</label>
		<b>${formBean.benefits.amount}</b>
	</div>
	What was the time period when the benefits were received:
	<div class="field-row clearfix">
		<label for="benefitsStartDate">From</label>
		<b>${formBean.benefits.startDate}</b>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsEndDate">To</label>
		<b>${formBean.benefits.endDate}</b>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsDateDiscovered">When did you discover that someone was receiving or had received benefits using your personal identifying information?</label>
		<b>${formBean.benefits.dateDiscovered}</b>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsIncidentDesc">Describe the Incident</label>
		<b>${formBean.benefits.incidentDesc}</b>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div id="benefitsSuspectDialog"></div>
   	<div id="benefitsSuspect">
   		<tags:suspectsTable_ro />
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
