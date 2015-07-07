<script type="text/javascript">
function submitForm() {
	f = document.adminForm;
	if (validateAdminForm()) {
		MM_goToURL('parent','userProxy.admin?umdId=' + f.umdId.value);
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
			<h2>User Proxy</h2>
			<p>Enter or copy/paste user's UMD ID (e.g. cn=100043675,ou=5,ou=Pub,o=UT) to the below box and click Proxy button.</p>
			<p>User's UMD ID can be searched by going to <a href="https://login.utah.gov/apadmin" TARGET="_blank">UMD administration URL</a>.</p>
			
			<div class="error" style="display:none;">
			     	<span></span>.<br style="clear:all"/><br />
			</div>
			<div class="border-box clearfix">
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
						<td style="text-align: right; width: 15%"><span class="redtext">*</span>UMD ID:</td>
						<td style="width: 85%">
							<input type="text" name="umdId" class="large" id="umdId" />
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="clearfix">
				<button type="button" onclick="return submitForm();" class="button float-left">
					 Proxy
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
