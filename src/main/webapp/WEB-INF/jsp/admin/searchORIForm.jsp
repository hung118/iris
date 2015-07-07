<script type="text/javascript">
function submitForm() {
	f = document.adminForm;
	if (validateAdminForm()) {
		parent.location = 'searchORI.admin?streetAddress=' + f.streetAddress.value + '&zipCode=' + f.zipCode.value;
		return true;
	} else {
		return false;
	}
}
</script>

<form action="#" name="adminForm" id="adminForm">
<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Search ORI</h2>
			<div class="error" style="display:none;">
		     	<span></span>.<br style="clear:all"/><br />
			</div>
			<div class="border-box clearfix">
				<h3 style="text-align: center">Search Results</h3>
				<hr>
				<%= request.getAttribute("searchResult") == null ? " " : request.getAttribute("searchResult") %>
			</div>
			<div class="border-box clearfix">
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
						<td style="width: 15%; text-align: right"><span class="redtext">*</span>Street Address:</td>
						<td style="width: 85%">
							<input type="text" name="streetAddress" id="oriStreetAddress" class="medium" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><span class="redtext">*</span>Zip Code:</td>
						<td>
							<input type="text" name="zipCode" id="oriZipCode" class="small" />
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>			
			</div>	
			<div class="clearfix">
				<button type="button" onclick="return submitForm();" class="button float-left">
					 Search
				</button>
		   		<button type="button" name="exit" onclick="MM_goToURL('parent','admin');return document.MM_returnValue" class="button float-right">
					Exit        		
		   		</button>
			</div>
		</div>
	</div>
</div>
</form>

<script type="text/javascript" src="/iris/js/adminForm.js"></script>




