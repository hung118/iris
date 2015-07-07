<script type="text/javascript">
function submitForm() {
	f = document.dwsloginForm;
	MM_goToURL('parent','start?dwsNumber=' + f.dwsNumber.value);
	return true;
}
</script>

<form action="#" name="dwsloginForm" id="dwslogin">
<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Department of Workforce Services Notice</h2>
			<p>You recently received a letter from the Department of Workforce
		Services informing you that a person in your public assistance case has
		had wages reported for his or her Social Security Number (SSN), which
		may indicate his or her SSN may have been misused. Please select the
		number you were provided in the form below and click on the
		&quot;Submit&quot; button to continue. If you are reporting the misuse
		of a Social Security Number on behalf of a child or another person, be
		sure to enter their information when activating the IRIS account.</p>
			<div class="border-box clearfix">
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
						<td style="text-align: right; width: 15%"><span class="redtext">*</span>DWS #:</td>
						<td style="width: 85%">
							<select class="single-select2 small" name="dwsNumber">
								<option value="3773">3773</option>
								<option value="5224">5224</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="clearfix">
				<button type="button" onclick="return submitForm();" class="button float-left">
					 Submit
				</button>
		   		<button type="button" name="exit" onclick="MM_goToURL('parent','http://idtheft.utah.gov/iris.html');return document.MM_returnValue" class="button float-right">
					Exit        		
		   		</button>
			</div>
		</div>
	</div>
</div>
</form>

<script type="text/javascript" src="/iris/js/adminForm.js"></script>
