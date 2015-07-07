<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<script type="text/javascript" src="/iris/js/base.js"></script>

<div class="border-box clearfix">
	<h2>False Civil Judgment</h2>
	<hr>
	<h3>Case Information</h3>
	<div class="field-row clearfix">
		<label for="docketCaseNumber"><span class="redtext">*</span>Docket Number or Case Number</label>
		<b>${formBean.suit.docketCaseNumber}</b>
	</div>
	<div class="field-row clearfix">
		<label for="suitDate">Date of Suit or Judgment</label>
		<b>${formBean.suit.date}</b>
	</div>
	<div class="field-row clearfix">
		<label for="court">In which court is this Suit or Judgment filed?</label>
		<b>${formBean.suit.court}</b>
	</div>
	<div class="field-row clearfix">
		<label for="suitAmount">Amount of Suit or Judgment $</label>
		<b>${formBean.suit.amount}</b>
	</div>
	<div class="field-row clearfix">
		<label for="court">Person or Organization that filed the Suit or Judgment</label>
		<b>${formBean.suit.filer}</b>
	</div>
	<div class="field-row clearfix">
		<label for="courtDate">Next scheduled Court date</label>
		<b>${formBean.suit.courtDate}</b>
	</div>
	<div class="field-row clearfix">
		<label for="suitDateLearned">Date learned of the Suit or Judgment</label>
		<b>${formBean.suit.dateLearned}</b>
	</div>
	<div class="field-row clearfix">
		<label for="reason">What is the reason for the Suit or Judgment</label>
		<b>${formBean.suit.reason}</b>
	</div>

	<hr>
	<h3>Suspects</h3>
	<div id="suitSuspectDialog"></div>
   	<div id="suitSuspect">
   		<tags:suspectsTable_ro/>
   	</div>
</div>

<script type="text/javascript" src="/iris/js/subForms.js"></script>
