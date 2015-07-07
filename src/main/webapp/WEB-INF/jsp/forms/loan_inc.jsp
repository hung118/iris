<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized Loan</h2>
	<hr>
	<h3>Loan Officer</h3>
	<div class="field-row clearfix">
		<label for="loanOfficerFirstName">First Name</label>
		<form:input path="loan['loanOfficerFirstName']" id="loanOfficerFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="loanOfficerLastName">Last Name</label>
		<form:input path="loan['loanOfficerLastName']" id="loanOfficerLastName" class="medium"/>
	</div>
	<hr>
	<h3>Loan Institution</h3>
	<div class="field-row clearfix">
		<label for="loanBusinessName">Business Name</label>
		<form:input path="loan['businessName']" id="loanBusinessName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="loanBusinessAddress">Address</label>
		<form:input path="loan['businessAddress']" id="loanBusinessAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="loanBusinessCity">City</label>
		<form:input path="loan['businessCity']" id="loanBusinessCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="businessState">State</label>
		<tags:stateOptions propertyName="loan['businessState']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="LoanBusinessZipCode">Zip Code</label>
		<form:input path="loan['businessZipCode']" id="LoanBusinessZipCode" class="x-small"/>
	</div>
	<div class="field-row clearfix">
		<label for="loanBusinessPhoneNumber">Phone</label>
		<form:input path="loan['businessPhoneNumber']" id="loanBusinessPhoneNumber" class="small"/>
	</div>
	<hr>
	<h3>Loan Information</h3>
	<div class="field-row clearfix">
		<label for="loanNumber">Loan Number</label>
		<form:input path="loan['loanNumber']" id="loanNumber" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="loanAmount">Loan Amount $</label>
		<form:input path="loan['loanAmount']" onkeypress="return(currencyFormat(this,',','.',event))" id="loanAmount" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="loanDateIssued">Date Issued</label>
		<form:input path="loan['dateIssued']" id="loanDateIssued" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="loanDateLearned">When did you learn that someone had taken out a loan using your personal identifying information?</label>
		<form:input path="loan['dateLearned']" id="loanDateLearned" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="howLearned">How did you learn that someone had taken out a loan using your personal identifying information?</label>
		<form:textarea path="loan['howLearned']" id="howLearned" class="xlarge" rows="8" cols="80" />
		<div id="loanLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "LOAN", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="loanSuspectDialog"></div>
   	<div id="loanSuspect">
   		<tags:suspectsTable tableId="loanSuspectTable" bodyId="loanSuspectBody"/>
   	</div>
</div>

<script id="loanSuspectRowTemplate" type="x-handlebars-template">
	<tr id="loanSuspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'LOAN', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'LOAN', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'LOAN', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#LoanBusinessZipCode").mask("99999");
		$("#loanBusinessPhoneNumber").mask("999-999-9999");
		$("#howLearned").charCounter(4096, {
			container : "#loanLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
