<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Benefits Fraud</h2>
	<hr>
	<h3>Benefits Information</h3>
	<div class="field-row clearfix">
		<label for="benefitsReceived">What benefits were received?</label>
		<form:input path="benefits['benefitsReceived']" id="benefitsReceived" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsProvider">From which company or government entity were the benefits received?</label>
		<form:input path="benefits['benefitsProvider']" id="benefitsProvider" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsAmount">Amount of benefits received</label>
		<form:input path="benefits['amount']" id="benefitsAmount" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
	</div>
	What was the time period when the benefits were received:
	<div class="field-row clearfix">
		<label for="benefitsStartDate">From</label>
		<form:input path="benefits['startDate']" id="benefitsStartDate" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsEndDate">To</label>
		<form:input path="benefits['endDate']" id="benefitsEndDate" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsDateDiscovered">When did you discover that someone was receiving or had received benefits using your personal identifying information?</label>
		<form:input path="benefits['dateDiscovered']" id="benefitsDateDiscovered" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="benefitsIncidentDesc">Describe the Incident</label>
		<form:textarea path="benefits['incidentDesc']" id="benefitsIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="benefitsLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "BENEFITS", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="benefitsSuspectDialog"></div>
   	<div id="benefitsSuspect">
   		<tags:suspectsTable tableId="benefitsSuspectTable" bodyId="benefitsSuspectBody"/>
   	</div>
</div>

<script id="benefitsSuspectRowTemplate" type="x-handlebars-template">
	<tr id="benefitsSuspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'BENEFITS', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'BENEFITS', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'BENEFITS', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#benefitsIncidentDesc").charCounter(4096, {
			container : "#benefitsLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
