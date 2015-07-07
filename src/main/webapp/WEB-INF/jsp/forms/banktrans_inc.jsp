<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized Withdrawal from Your Checking or Savings Account</h2>
	<hr>
	<h3>Bank or Credit Union Information</h3>
	<div class="field-row clearfix">
		<label for="bankName">Name</label>
		<form:input path="banktrans['bankName']" id="bankName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="bankAddress">Address</label>
		<form:input path="banktrans['bankAddress']" id="bankAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="bankCity">City</label>
		<form:input path="banktrans['bankCity']" id="bankCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="bankState">State</label>
		<tags:stateOptions propertyName="banktrans['bankState']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="bankZipCode">Zip Code</label>
		<form:input path="banktrans['bankZipCode']" id="bankZipCode" class="x-small"/>
	</div>
	<div class="field-row clearfix">
		<label for="bankPhoneNumber">Phone</label>
		<form:input path="banktrans['bankPhoneNumber']" id="bankPhoneNumber" class="small"/>
	</div>
	<hr>
	<h3>Bank or Credit Union Contact Information</h3>
	<div class="field-row clearfix">
		<label for="contactFirstName">First Name</label>
		<form:input path="banktrans['contactFirstName']" id="contactFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="contactLastName">Last Name</label>
		<form:input path="banktrans['contactLastName']" id="contactLastName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="contactPhoneNumber">Phone</label>
		<form:input path="banktrans['contactPhoneNumber']" id="contactPhoneNumber" class="small"/>
	</div>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="dateLearned">When did you learn about the unauthorized withdrawal(s) from your account? </label>
		<form:input path="banktrans['dateLearned']" id="dateLearned" cssClass="datepicker" />
	</div>
	<div class="field-row clearfix">
		<label for="banktransIncidentDesc">Describe the Incident</label>
		<form:textarea path="banktrans['incidentDesc']" id="banktransIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="banktransLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>
	
	<hr>
	<h3>Account Holder</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("accou", "BANKTRANS", "Add Account Holder", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="accouDialog"></div>
    <div id="accou">
    	<tags:accouTable />
    </div>
	<hr>
	<h3>Unauthorized Transaction on Account</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("trans", "BANKTRANS", "Add Unauthorized Transaction", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="transDialog"></div>
    <div id="trans">
    	<tags:transTable />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "BANKTRANS", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="banktransSuspectDialog"></div>
   	<div id="banktransSuspect">
   		<tags:suspectsTable tableId="banktransSuspectTable" bodyId="banktransSuspectBody"/>
   	</div>
</div>

<!--  Handlebars templates -->
<script id="accouRowTemplate" type="x-handlebars-template">
	<tr id="accou-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'accou', 'BANKTRANS', 'Edit Account Holder', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{accountHolderFirstName}} {{accountHolderMiddleName}} {{accountHolderLastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'accou', 'BANKTRANS', 'Edit Account Holder', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{accountHolderType}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Account Holder', 'Are you sure you want to delete this Account Holder record?', 'accou', 'BANKTRANS', {{index}}, '{{fraudTypeSeq}}', '{{dataElementSeq}}', '{{trackingNumber}}', '{{wsAction}}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<script id="transRowTemplate" type="x-handlebars-template">
	<tr id="trans-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'trans', 'BANKTRANS', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{transDate}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'trans', 'BANKTRANS', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{transAmount}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'trans', 'BANKTRANS', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{transAccountType}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Unauthorized Transaction', 'Are you sure you want to delete this Unauthorized Transaction record?', 'trans', 'BANKTRANS', {{index}}, '{{fraudTypeSeq}}', '{{dataElementSeq}}', '{{trackingNumber}}', '{{wsAction}}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<script id="banktransSuspectRowTemplate" type="x-handlebars-template">
	<tr id="suspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'BANKTRANS', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'BANKTRANS', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'BANKTRANS', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#bankZipCode").mask("99999");
		$("#bankPhoneNumber").mask("999-999-9999");
		$("#contactPhoneNumber").mask("999-999-9999");
		$("#banktransIncidentDesc").charCounter(4096, {
			container : "#banktransLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
