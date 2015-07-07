<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Collection Agency</h2>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="firstContactDate">Date first contacted by collection agency</label>
		<form:input path="collector['firstContactDate']" id="firstContactDate" cssClass="datepicker" />
	</div>
	<div class="field-row clearfix">
		<label>Are you continuing to receive calls after informing them you have been a victim?</label>
	</div>  
	<div class="field-row clearfix">
		<div class="clearfix">
			<form:radiobutton path="collector['stillReceivingCalls']" value="Y" id="stillReceivingCalls1"/> 
			<label for="stillReceivingCalls1">Yes</label>
		</div>
		<div class="clearfix">
			<form:radiobutton path="collector['stillReceivingCalls']" value="N" id="stillReceivingCalls2"/> 
			<label for="stillReceivingCalls2">No</label>
		</div>
	</div>
	<div class="field-row clearfix">
		<label for="collectorIncidentDesc">Describe the Incident</label>
		<form:textarea path="collector['incidentDesc']" id="collectorIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="collectorLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>
	
	<hr>
	<h3>Collection Agency/Call Information</h3>
	<div style="text-align:right"><br />
		<button type="button" onclick='addSubItem("colle", "COLLECTOR", "Add Collection Agency", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="colleDialog"></div>
    <div id="colle">
    	<tags:colleTable />
    </div>
	<hr>
	<h3>Creditor Information</h3>
	<div style="text-align:right"><br />
		<button type="button" onclick='addSubItem("credi", "COLLECTOR", "Add Creditor", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="crediDialog"></div>
    <div id="credi">
    	<tags:crediTable />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "COLLECTOR", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="collectorSuspectDialog"></div>
   	<div id="collectorSuspect">
   		<tags:suspectsTable tableId="collectorSuspectTable" bodyId="collectorSuspectBody"/>
   	</div>
</div>

<!--  Handlebars templates -->
<script id="colleRowTemplate" type="x-handlebars-template">
	<tr id="colle-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'colle', 'COLLECTOR', 'Edit Collection Agency', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{collectorName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'colle', 'COLLECTOR', 'Edit Collection Agency', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{collectorAddress}}, {{collectorCity}}, {{collectorState}} {{collectorZipCode}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Collection Agency', 'Are you sure you want to delete this Collection Agency record?', 'colle', 'COLLECTOR', {{index}}, '{{fraudTypeSeq}}', '{{dataElementSeq}}', '{{trackingNumber}}', '{{wsAction}}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<script id="crediRowTemplate" type="x-handlebars-template">
	<tr id="credi-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'credi', 'COLLECTOR', 'Edit Creditor', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{creditorName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'credi', 'COLLECTOR', 'Edit Creditor', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{creditorContactInfo}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Creditor', 'Are you sure you want to delete this Creditor record?', 'credi', 'COLLECTOR', {{index}}, '{{fraudTypeSeq}}', '{{dataElementSeq}}', '{{trackingNumber}}', '{{wsAction}}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<script id="collectorSuspectRowTemplate" type="x-handlebars-template">
	<tr id="suspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'COLLECTOR', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'COLLECTOR', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'COLLECTOR', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#collectorIncidentDesc").charCounter(4096, {
			container : "#collectorLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
