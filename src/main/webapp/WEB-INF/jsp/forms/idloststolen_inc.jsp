<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Driver's License / ID Lost, Stolen or Misused</h2>
	<hr>
	<h3>Victim Information</h3>
	<div class="field-row clearfix">
		<label for="idloststolenDate">Date Missing</label>
		<form:input path="idloststolen['date']" id="idloststolenDate" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label>ID Type</label>
	</div>  
	<div class="field-row clearfix">
		<div class="clearfix">
			<form:radiobutton path="idloststolen['idType']" value="DRIVER LICENSE" id="idType1"/> 
			<label for="idType1">Driver License</label>
		</div>
		<div class="clearfix">
			<form:radiobutton path="idloststolen['idType']" value="ID CARD" id="idType2"/> 
			<label for="idType2">ID Card</label>
		</div>
	</div>
	<div class="field-row clearfix">
		<label for="idNumber">Driver's License or ID Number</label>
		<form:input path="idloststolen['idNumber']" id="idNumber" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="idStateIssued">State Issued</label>
		<tags:stateOptions propertyName="idloststolen['idState']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="idFirstName">First Name</label>
		<form:input path="idloststolen['firstName']" id="idFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="idMiddleName">Middle Initial</label>
		<form:input path="idloststolen['middleName']" id="idMiddleName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="idLastName">Last Name</label>
		<form:input path="idloststolen['lastName']" id="idLastName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="idAddress">Address</label>
		<form:input path="idloststolen['address']" id="idAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="idCity">City</label>
		<form:input path="idloststolen['city']" id="idCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="idState">State</label>
		<tags:stateOptions propertyName="idloststolen['state']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="idZipCode">Zip Code</label>
		<form:input path="idloststolen['zipCode']" id="idZipCode" class="x-small"/>
	</div>
	<hr>
	<h3>Location of where lost or stolen, if known</h3>
	<div class="field-row clearfix">
		<label for="lostAddress">Address</label>
		<form:input path="idloststolen['lostAddress']" id="lostAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="lostCity">City</label>
		<form:input path="idloststolen['lostCity']" id="lostCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="lostState">State</label>
		<tags:stateOptions propertyName="idloststolen['lostState']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="lostZipCode">Zip Code</label>
		<form:input path="idloststolen['lostZipCode']" id="lostZipCode" class="x-small"/>
	</div>
	<div class="field-row clearfix">
		<label for="idloststolenIncidentDesc">Describe the Incident</label>
		<form:textarea path="idloststolen['incidentDesc']" id="idloststolenIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="idloststolenLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "IDLOSTSTOLEN", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="idloststolenSuspectDialog"></div>
   	<div id="idloststolenSuspect">
   		<tags:suspectsTable tableId="idloststolenSuspectTable" bodyId="idloststolenSuspectBody"/>
   	</div>
</div>

<script id="idloststolenSuspectRowTemplate" type="x-handlebars-template">
	<tr id="idloststolenSuspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'IDLOSTSTOLEN', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'IDLOSTSTOLEN', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'IDLOSTSTOLEN', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#idZipCode").mask("99999");
		$("#lostZipCode").mask("99999");
		$("#idloststolenIncidentDesc").charCounter(4096, {
			container : "#idloststolenLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
