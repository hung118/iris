<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>False Civil Judgment</h2>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="docketCaseNumber"><span class="redtext">*</span>Docket Number or Case Number</label>
		<form:input path="suit['docketCaseNumber']" id="docketCaseNumber" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="suitDate">Date of Suit or Judgment</label>
		<form:input path="suit['date']" id="suitDate" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="court">In which court is this Suit or Judgment filed?</label>
		<form:input path="suit['court']" id="court" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="suitAmount">Amount of Suit or Judgment $</label>
		<form:input path="suit['amount']" id="suitAmount" onkeypress="return(currencyFormat(this,',','.',event))" class="small"/>
	</div>
	<div class="field-row clearfix">
		<label for="court">Person or Organization that filed the Suit or Judgment</label>
		<form:input path="suit['filer']" id="filer" class="medium"/>
	</div>
	<div class="field-row clearfix">
		<label for="courtDate">Next scheduled Court date</label>
		<form:input path="suit['courtDate']" id="courtDate" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="suitDateLearned">Date learned of the Suit or Judgment</label>
		<form:input path="suit['dateLearned']" id="suitDateLearned" class="datepicker"/>
	</div>
	<div class="field-row clearfix">
		<label for="reason">What is the reason for the Suit or Judgment</label>
		<form:textarea path="suit['reason']" id="reason" class="xlarge" rows="8" cols="80" />
		<div id="suitLccCharCount" style="margin-left:11em; text-align: center">(4096 characters remaining)</div>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div style="text-align:right">
		<button type="button" onclick='addSubItem("suspect", "SUIT", "Add Suspect", "${formBean.trackingNumber}", "${formBean.fraudTypeSeq}", "${formBean.wsAction}")'>
			 Add
		</button>
	</div>
	<div id="suitSuspectDialog"></div>
   	<div id="suitSuspect">
   		<tags:suspectsTable tableId="suitSuspectTable" bodyId="suitSuspectBody"/>
   	</div>
</div>

<script id="suitSuspectRowTemplate" type="x-handlebars-template">
	<tr id="suitSuspect-{{index}}">
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'SUIT', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{firstName}} {{middleName}} {{lastName}}</a></td>
		<td><a href="javascript:editSubItem({{index}}, 'suspect', 'SUIT', 'Edit Suspect', '{{trackingNumber}}', '{{fraudTypeSeq}}', '{{wsAction}}')">{{addr1Street}}, {{addr1City}}, {{addr1State}} {{addr1Zip}}</a></td>
		<td style="text-align:center">
			<a href="javascript:deleteSubItem('Delete Suspect', 'Are you sure you want to delete this suspect record?', 'suspect', 'SUIT', {{index}}, '{{fraudTypeSeq}}', '', '{{trackingNumber}}', '{{wsAction}}', '{{personIndex}}')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a>
		</td>
	</tr>
</script>
<!-- end templates -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#reason").charCounter(4096, {
			container : "#suitLccCharCount"
		});
	});
</script>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
