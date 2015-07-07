<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>Unauthorized Use of Your Credit Card</h2>
	<hr>
	<h3>Cardholder Information</h3>
	<div class="field-row clearfix">
		<label for="unauthcreditFirstName"><span class="redtext">*</span>First Name</label>
		<form:input path="unauthcredit['firstName']" id="unauthcreditFirstName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditMiddleName">Middle Initial</label>
		<form:input path="unauthcredit['middleName']" id="unauthcreditMiddleName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditLastName"><span class="redtext">*</span>Last Name</label>
		<form:input path="unauthcredit['lastName']" id="unauthcreditLastName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="cardNumber"><span class="redtext">*</span>Card Number</label>
		<form:input path="unauthcredit['cardNumber']" id="cardNumber" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="cardType">Card Type:<br>(Visa, MasterCard, etc.)</label>
		<form:input path="unauthcredit['cardType']" id="cardType" class="small"/>
	</div>
	<hr>
	<h3>Card Issuer Information</h3>
	<div class="field-row clearfix">
		<label for="issuerName">Name of Issuer</label>
		<form:input path="unauthcredit['issuerName']" id="issuerName" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="billingAddress">Billing Address</label>
		<form:input path="unauthcredit['billingAddress']" id="billingAddress" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditCity">City</label>
		<form:input path="unauthcredit['city']" id="unauthcreditCity" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditState">State</label>
		<tags:stateOptions propertyName="unauthcredit['state']" styleClassName="single-select2 small" />
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditZipCode">Zip Code</label>
		<form:input path="unauthcredit['zipCode']" id="unauthcreditZipCode" class="x-small"/>
	</div>
	<div class="field-row clearfix">
		<label for="issuerPhoneNumber">Phone</label>
		<form:input path="unauthcredit['issuerPhoneNumber']" id="issuerPhoneNumber" class="small"/>
	</div>
	<hr>
	<h3>Account Information</h3>
	<div class="field-row clearfix">
		<label for="dateAccountOpened">Approximate date account was created</label>
		<form:input path="unauthcredit['dateAccountOpened']" id="dateAccountOpened" cssClass="datepicker" />
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditBalanceDue">Balance Due $</label>
		<form:input path="unauthcredit['balanceDue']" id="unauthcreditBalanceDue" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="unauthcreditIncidentDesc">Describe the Incident</label>
		<form:textarea path="unauthcredit['incidentDesc']" id="unauthcreditIncidentDesc" class="xlarge" rows="8" cols="80" />
		<div id="unauthcreditLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>
	
	<hr>
	<h3>Unauthorized Transactions Information</h3>
	<div style="text-align:right"><br />
		<button type="button" onclick='addSubItem("tranx", "UNAUTHCREDIT", "Add Unauthorized Transaction", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="tranxDialog"></div>
    <div id="tranx">
    	<tags:tranxTable />
    </div>
	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "UNAUTHCREDIT", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="unauthcreditSuspectDialog"></div>
   	<div id="unauthcreditSuspect">
   		<tags:suspectsTable tableId="unauthcreditSuspectTable" bodyId="unauthcreditSuspectBody"/>
   	</div>
</div>

<!--  Handlebars templates -->
<script id="tranxRowTemplate" type="x-handlebars-template">
	<tr id="tranx-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'tranx', 'UNAUTHCREDIT', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{tranxDate}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'tranx', 'UNAUTHCREDIT', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{tranxAmount}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'tranx', 'UNAUTHCREDIT', 'Edit Unauthorized Transaction', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{tranxRecipientType}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Unauthorized Transaction', 'Are you sure you want to delete this Unauthorized Transaction record?', 'tranx', 'UNAUTHCREDIT', {{index}}, '{{fraudTypeSeq}}', '{{dataElementSeq}}', '{{trackingNumber}}', '{{wsAction}}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<script id="unauthcreditSuspectRowTemplate" type="x-handlebars-template">
	<tr id="suspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'UNAUTHCREDIT', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'UNAUTHCREDIT', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'UNAUTHCREDIT', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#unauthcreditZipCode").mask("99999");
		$("#issuerPhoneNumber").mask("999-999-9999");
		$("#unauthcreditIncidentDesc").charCounter(4096, {
			container : "#unauthcreditLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
