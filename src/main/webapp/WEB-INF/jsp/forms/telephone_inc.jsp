<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Telephone Service Fraud</h2>
	<hr>
	<h3>Telephone Services Information</h3>
	<div class="field-row clearfix">
		<label for="serviceType">What type of telephone services have been received?</label>
		<form:select path="telephone['serviceType']" id="serviceType" class="single-select2 medium">
			<form:option value="BUSINESS PHONE" label="BUSINESS PHONE" />
			<form:option value="RESIDENT PHONE" label="RESIDENT PHONE" />
			<form:option value="OTHER" label="OTHER" />
		</form:select>
	</div>
	<div class="field-row clearfix">
		<label for="providerName">Business Name</label>
		<form:input path="telephone['providerName']" id="providerName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="providerAddress">Address</label>
		<form:input path="telephone['providerAddress']" id="providerAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="providerCity">City</label>
		<form:input path="telephone['providerCity']" id="providerCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="providerState">State</label>
		<tags:stateOptions propertyName="telephone['providerState']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="providerZipCode">Zip Code</label>
		<form:input path="telephone['providerZipCode']" id="providerZipCode" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="providerPhoneNumber">Phone Number</label>
		<form:input path="telephone['providerPhoneNumber']" id="providerPhoneNumber" class="small"/>
	</div>
	<hr>
	<h3>Account Information</h3>
	<div class="field-row clearfix">
		<label for="telFirstName">First Name</label>
		<form:input path="telephone['firstName']" id="telFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="telLastName">Last Name</label>
		<form:input path="telephone['lastName']" id="telLastName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="telAddress">Address</label>
		<form:input path="telephone['address']" id="telAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="telCity">City</label>
		<form:input path="telephone['city']" id="telCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="telState">State</label>
		<tags:stateOptions propertyName="telephone['state']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="telZipCode">Zip Code</label>
		<form:input path="telephone['zipCode']" id="telZipCode" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="telPhoneNumber">Phone Number</label>
		<form:input path="telephone['phoneNumber']" id="telPhoneNumber" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="telEstablished">When was the account established?</label>
		<form:input path="telephone['dateEstablished']" id="telEstablished" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="telAmountOwed">Amount Due $</label>
		<form:input path="telephone['amountOwed']" id="telAmountOwed" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="telDateLearned">When did you learn that someone had opened a telephone service using your personal identifying information?</label>
		<form:input path="telephone['dateLearned']" id="telDateLearned" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="telHowLearned">How did you learn that someone had opened a telephone service using your personal identifying information?</label>
		<form:textarea path="telephone['howLearned']" id="telHowLearned" class="xlarge" rows="8" cols="80" />
		<div id="telLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "TELEPHONE", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="telephoneSuspectDialog"></div>
   	<div id="telephoneSuspect">
   		<tags:suspectsTable tableId="telephoneSuspectTable" bodyId="telephoneSuspectBody"/>
   	</div>
</div>

<script id="telephoneSuspectRowTemplate" type="x-handlebars-template">
	<tr id="telephoneSuspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'TELEPHONE', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'TELEPHONE', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'TELEPHONE', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#providerZipCode").mask("99999");
		$("#providerPhoneNumber").mask("999-999-9999");
		$("#telZipCode").mask("99999");
		$("#telPhoneNumber").mask("999-999-9999");
		$("#telHowLearned").charCounter(4096, {
			container : "#telLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
