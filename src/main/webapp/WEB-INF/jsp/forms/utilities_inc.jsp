<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Utilities Fraud</h2>
	<hr>
	<h3>Utility Information</h3>
	<div class="field-row clearfix">
		<label for="utilAccountNumber">Account Number</label>
		<form:input path="utilities['accountNumber']" id="utilAccountNumber" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="utilitiesType">What type of utilities have been received?</label>
		<form:select path="utilities['utilitiesType']" id="utilitiesType" class="single-select2 small">
			<form:option value="GAS" label="GAS" />
			<form:option value="ELECTRICITY" label="ELECTRICITY" />
			<form:option value="WATER" label="WATER" />
		</form:select>
	</div>
	<div class="field-row clearfix">
		<label for="utilCompanyName">Utility Name</label>
		<form:input path="utilities['companyName']" id="utilCompanyName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="utilAddress">Your Address</label>
		<form:input path="utilities['address']" id="utilAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="utilCity">City</label>
		<form:input path="utilities['city']" id="utilCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="utilState">State</label>
		<tags:stateOptions propertyName="utilities['state']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="utilZipCode">Zip Code</label>
		<form:input path="utilities['zipCode']" id="utilZipCode" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="utilPhoneNumber">Phone Number</label>
		<form:input path="utilities['phoneNumber']" id="utilPhoneNumber" class="small"/>
	</div>
	<hr>
	<h3>Contact Person at Utility Company</h3>
	<div class="field-row clearfix">
		<label for="utilFirstName">First Name</label>
		<form:input path="utilities['firstName']" id="utilFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="utilLastName">Last Name</label>
		<form:input path="utilities['lastName']" id="utilLastName" class="medium"/>
	</div>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="utilEstablished">When was the account established?</label>
		<form:input path="utilities['dateEstablished']" id="utilEstablished" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label>Is there an amount owed on the account?</label>
	</div>  
	<div class="field-row clearfix">
		<div class="clearfix">
			<form:radiobutton path="utilities['amountOwed']" value="Y" id="utilAmountOwed1"/> 
			<label for="utilAmountOwed1">Yes</label>
		</div>
		<div class="clearfix">
			<form:radiobutton path="utilities['amountOwed']" value="N" id="utilAmountOwed2"/> 
			<label for="utilAmountOwed2">No</label>
		</div>
	</div>
	<div class="field-row clearfix">
		<label for="utilDateLearned">When did you learn that someone had opened an utility account using your personal identifying information?</label>
		<form:input path="utilities['dateLearned']" id="utilDateLearned" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="utilHowLearned">How did you learn that someone had opened an utility account using your personal identifying information?</label>
		<form:textarea path="utilities['howLearned']" id="utilHowLearned" class="xlarge" rows="8" cols="80" />
		<div id="utilLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "UTILITIES", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="utilitiesSuspectDialog"></div>
   	<div id="utilitiesSuspect">
   		<tags:suspectsTable tableId="utilitiesSuspectTable" bodyId="utilitiesSuspectBody"/>
   	</div>
</div>

<script id="utilitiesSuspectRowTemplate" type="x-handlebars-template">
	<tr id="utilitiesSuspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'UTILITIES', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'UTILITIES', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'UTILITIES', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#utilZipCode").mask("99999");
		$("#utilPhoneNumber").mask("999-999-9999");
		$("#utilHowLearned").charCounter(4096, {
			container : "#utilLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
