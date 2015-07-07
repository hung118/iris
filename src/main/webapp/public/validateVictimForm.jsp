<script type="text/javascript">
function submitForm() {
	f = document.publicForm;
	if (validatePublicForm()) {
		MM_goToURL('parent','validateVictim.shtml?trackingNumber=' + f.trackingNumber.value);
		return true;
	} else {
		return false;
	}
}	
</script>

<form action="#" name="publicForm" id="publicForm">
<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Track My Case</h2>
			<p>Track your case by entering your ID Theft Central Incident Number.</p>
			
			<div class="error" style="display:none;">
			     	<span></span>.<br style="clear:all"/><br />
			</div>
			<div class="border-box clearfix">
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
						<td><span class="redtext">*</span>Incident Number:</td>
						<td>
							<input name="trackingNumber" type="text" id="incidentNo" class="large"/>
						</td>
					</tr>
				</table>			
			</div>
			<div class="clearfix">
				<button type="button" onclick="return submitForm();" class="button float-left">
					 Search
				</button>
		   		<button type="button" name="gotoIris" onclick="MM_goToURL('parent','/iris');return document.MM_returnValue" class="button float-left">
					My Report and Affidavit      		
		   		</button>
		   		<button type="button" name="exit" onclick="MM_goToURL('parent','http://idtheft.utah.gov/iris.html');return document.MM_returnValue" class="button float-right">
					Cancel       		
		   		</button>
			</div>
		</div>
	</div>
</div>
</form>

<script type="text/javascript" src="/iris/js/publicForm.js"></script>