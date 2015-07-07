<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Email Identity Theft (Phishing)</h2>
	<hr>
	<h3>Email Information</h3>
	<div class="field-row clearfix">
		<label for="sendersEmail">Sender's Email</label>
		<form:input path="email['sendersEmail']" id="sendersEmail" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="recipientsEmail">Recipient's Email</label>
		<form:input path="email['recipientsEmail']" id="recipientsEmail" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="dateReceived">When did you receive this email?</label>
		<form:input path="email['dateReceived']" id="dateReceived" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="dateProvided">When did you provide your personal identifying information?</label>
		<form:input path="email['dateProvided']" id="dateProvided" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="providedInfo">What personal identifying information did you provide?</label>
		<form:textarea path="email['providedInfo']" id="providedInfo" class="xlarge" rows="8" cols="80" />
		<div id="providedInfoLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>
	<div class="field-row clearfix">
		<label for="description">Description of Situation</label>
		<form:textarea path="email['description']" id="description" class="xlarge" rows="8" cols="80" />
		<div id="descriptionLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "EMAIL", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="emailSuspectDialog"></div>
   	<div id="emailSuspect">
   		<tags:suspectsTable tableId="emailSuspectTable" bodyId="emailSuspectBody"/>
   	</div>
</div>

<script id="emailSuspectRowTemplate" type="x-handlebars-template">
	<tr id="emailSuspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'EMAIL', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'EMAIL', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'EMAIL', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#providedInfo").charCounter(4096, {
			container : "#providedInfoLccCharCount"
		});
		$("#description").charCounter(4096, {
			container : "#descriptionLccCharCount"
		});

	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
