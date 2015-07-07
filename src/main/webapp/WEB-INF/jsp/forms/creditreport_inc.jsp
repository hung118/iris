<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized accounts on your credit report</h2>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="creditreportDateLearned">When did you learn the credit card(s) or credit account(s) were on your credit report?</label>
		<form:input path="creditreport['dateLearned']" id="creditreportDateLearned" cssClass="datepicker" />
	</div>
	Are there any inquiries on your account you do not recognize?
	<div class="field-row clearfix">
		<form:radiobutton path="creditreport['unrecognizableInquires']" value="Y" label="Yes" /> 
		<form:radiobutton path="creditreport['unrecognizableInquires']" value="N" label="No" />
	</div>
	<div class="field-row clearfix">
		<label for="accountNumber">Account Number</label>
		<form:input path="creditreport['accountNumber']" id="accountNumber" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="creditLimit">Credit Limit $</label>
		<form:input path="creditreport['creditLimit']" id="creditLimit" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="dateCreated">Date Account Created</label>
		<form:input path="creditreport['dateCreated']" id="dateCreated" cssClass="datepicker" />
	</div>
	
	<div class="field-row clearfix">
		<label>Is Balance Due?</label>
	</div>  
	<div class="field-row clearfix">
		<div class="clearfix">
			<form:radiobutton path="creditreport['isBalanceDue']" value="Y" id="isBalanceDue1"/> 
			<label for="isBalanceDue1">Yes</label>
		</div>
		<div class="clearfix">
			<form:radiobutton path="creditreport['isBalanceDue']" value="N" id="isBalanceDue2"/> 
			<label for="isBalanceDue2">No</label>
		</div>
	</div>
	<div class="field-row clearfix">
		<label for="balanceDue">Amount Due $</label>
		<form:input path="creditreport['balanceDue']" id="balanceDue" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="creditreportIncidentDesc">Describe the Incident</label>
		<form:textarea path="creditreport['incidentDesc']" id="creditreportIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="creditreportLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>
	
	<hr>
	<h3>Unauthorized Account on Credit Report Information</h3>
	<div style="text-align:right"><br />
		<button type="button" onclick='addSubItem("issue", "CREDITREPORT", "Add Unauthorized Account on Credit Report", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="issueDialog"></div>
    <div id="issue">
    	<tags:issueTable />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "CREDITREPORT", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="creditreportSuspectDialog"></div>
   	<div id="creditreportSuspect">
   		<tags:suspectsTable tableId="creditreportSuspectTable" bodyId="creditreportSuspectBody"/>
   	</div>
</div>

<!--  Handlebars templates -->
<script id="issueRowTemplate" type="x-handlebars-template">
	<tr id="issue-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'issue', 'CREDITREPORT', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{issuerName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'issue', 'CREDITREPORT', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{issuerAddress}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Unauthorized Transaction', 'Are you sure you want to delete this Unauthorized Transaction record?', 'issue', 'CREDITREPORT', {{index}}, '{{fraudTypeSeq}}', '{{dataElementSeq}}', '{{trackingNumber}}', '{{wsAction}}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<script id="creditreportSuspectRowTemplate" type="x-handlebars-template">
	<tr id="suspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'CREDITREPORT', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'CREDITREPORT', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'CREDITREPORT', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript" src="/iris/js/subForms.js"></script>
