<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Collection Agency</h2>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="firstContactDate">Date first contacted by collection agency</label>
		<b>${formBean.collector.firstContactDate}</b>
	</div>
	<div class="field-row clearfix">
		<label>Are you continuing to receive calls after informing them you have been a victim?</label>
		<b>${formBean.collector.stillReceivingCalls}</b>
	</div>
		<div class="field-row clearfix">
		<label for="collectorIncidentDesc">Describe the Incident</label>
		<b>${formBean.collector.incidentDesc}</b>
	</div>
	
	<hr>
	<h3>Collection Agency/Call Information</h3>
	<div id="colleDialog"></div>
    <div id="colle">
    	<tags:colleTable_ro />
    </div>
	<hr>
	<h3>Creditor Information</h3>
	<div id="crediDialog"></div>
    <div id="credi">
    	<tags:crediTable_ro />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div id="collectorSuspectDialog"></div>
   	<div id="collectorSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
