<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Administration Consoles</h2>
			<div class="border-box clearfix table-style">
				<h3 style="text-align: center">Dashboard</h3>
				<hr>
			    <table style="width:100%; border:0; cellspacing:3; cellpadding: 2">
					<tr class="tableheading">
						<th class="tableheading">&nbsp;</th>
						<th style="text-align: center">Today<br>${sessionScope.dashboard.Today.dateStr}</th>
						<th style="text-align: center">Yesterday<br>${sessionScope.dashboard.Yesterday.dateStr}</th>
						<th style="text-align: center">Week To Date<br>${sessionScope.dashboard.Week_To_Date.dateStr}</th>
						<th style="text-align: center">Month To Date<br>${sessionScope.dashboard.Month_To_Date.dateStr}</th>
						<th style="text-align: center">Year To Date<br>${sessionScope.dashboard.Year_To_Date.dateStr}</th>
						<th style="text-align: center">All Time</th>
			        </tr>
			        <tr class="blue-bar">
			        	<td>Unauthorized Checking/Saving</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.BANKTRANS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.BANKTRANS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.BANKTRANS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.BANKTRANS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.BANKTRANS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.BANKTRANS}</td>
			        </tr>
			        <tr>
			        	<td>Benefits Theft</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.BENEFITS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.BENEFITS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.BENEFITS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.BENEFITS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.BENEFITS}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.BENEFITS}</td>
			        </tr>
			        <tr class="blue-bar">
			        	<td>Credit Card Number Theft</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.UNAUTHCREDIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.UNAUTHCREDIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.UNAUTHCREDIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.UNAUTHCREDIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.UNAUTHCREDIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.UNAUTHCREDIT}</td>			        
			        </tr>
			        <tr>
			        	<td>Accounts on Credit Report</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.CREDITREPORT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.CREDITREPORT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.CREDITREPORT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.CREDITREPORT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.CREDITREPORT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.CREDITREPORT}</td>			
			        </tr>
			        <tr class="blue-bar">
			        	<td>Unauthorized Loan</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.LOAN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.LOAN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.LOAN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.LOAN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.LOAN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.LOAN}</td>		
			        </tr>
			        <tr>
			        	<td>Collections</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.COLLECTOR}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.COLLECTOR}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.COLLECTOR}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.COLLECTOR}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.COLLECTOR}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.COLLECTOR}</td>	
			        </tr>
			        <tr class="blue-bar">
			        	<td>ID Lost or Stolen</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.IDLOSTSTOLEN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.IDLOSTSTOLEN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.IDLOSTSTOLEN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.IDLOSTSTOLEN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.IDLOSTSTOLEN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.IDLOSTSTOLEN}</td>	
			        </tr>
			        <tr>
			        	<td>Social Security Number Theft</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.SSN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.SSN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.SSN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.SSN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.SSN}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.SSN}</td>	
			        </tr>
			        <tr class="blue-bar">
			        	<td>Telephone Service</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.TELEPHONE}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.TELEPHONE}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.TELEPHONE}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.TELEPHONE}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.TELEPHONE}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.TELEPHONE}</td>	
			        </tr>
			        <tr>
			        	<td>Utilities</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.UTILITIES}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.UTILITIES}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.UTILITIES}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.UTILITIES}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.UTILITIES}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.UTILITIES}</td>	
			        </tr>
			        <tr class="blue-bar">
			        	<td>False Civil Judgment</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.SUIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.SUIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.SUIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.SUIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.SUIT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.SUIT}</td>	
			        </tr>
			        <tr>
			        	<td>Email</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.EMAIL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.EMAIL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.EMAIL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.EMAIL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.EMAIL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.EMAIL}</td>	
			        </tr>
			        <tr class="blue-bar">
			        	<td>Other</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.OTHER_IDTHEFT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.OTHER_IDTHEFT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.OTHER_IDTHEFT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.OTHER_IDTHEFT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.OTHER_IDTHEFT}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.OTHER_IDTHEFT}</td>	
			        </tr>	
			    	<tr>
			        	<td>TOTAL</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Today.TOTAL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Yesterday.TOTAL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Week_To_Date.TOTAL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Month_To_Date.TOTAL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.Year_To_Date.TOTAL}</td>
			        	<td style="text-align: right">${sessionScope.dashboard.All_Time.TOTAL}</td>	
			        </tr>
				</table>
			</div>
			<div class="border-box clearfix">
				<h3 style="text-align: center">Administration Items</h3>
				<hr>
				<ul>
					<li><a href="viewAlertList.admin">Security Alert Administration</a></li>
					<li><a href="userProxyForm.admin">User Proxy</a></li>
					<li><a href="searchORIForm.admin">Search ORI</a></li>
					<li><a href="https://apadmin.utah.gov/apadmin/" TARGET="_blank">Production UMD Administration</a></li>
					<li><a href="viewDWSList.admin">View DWS Login Data</a></li>
				</ul>
			</div>
			<div class="clearfix">
				<button name="exit" type="button" onClick="MM_goToURL('parent','http://www.idtheft.utah.gov');return document.MM_returnValue" class="button float-right">Exit</button>			
			</div>
			
		</div>
	</div>
</div>

