<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>User Proxy Account</h2>
			<p>Below are the user account information retrieved from Web service. Click on Go To Claims List button to proxy (to be) the user.</p>
			<div class="border-box clearfix">
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
						<td style="width: 30%">First Name:</td>
						<td style="width: 70%"><b>${sessionScope.userInfo.firstName}</b>
						</td>
					</tr>
					<tr>
						<td>Middle Initial:</td>
						<td><b>${sessionScope.userInfo.middleName}</b></td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td><b>${sessionScope.userInfo.lastName}</b></td>
					</tr>
					<tr>
						<td>Address:</td>
						<td><b>${sessionScope.userInfo.addr1Street}</b></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><b>${sessionScope.userInfo.addr1City}</b></td>
					</tr>
					<tr>
						<td>State:</td>
						<td><b>${sessionScope.userInfo.addr1State}</b></td>
					</tr>
					<tr>
						<td>Zip Code:</td>
						<td><b>${sessionScope.userInfo.addr1Zip}</b></td>
					</tr>
					<tr>
						<td>Email Address:</td>
						<td><b>${sessionScope.userInfo.emailAddress}</b></td>
					</tr>
					<tr>
						<td>Phone:</td>
						<td><b>${sessionScope.userInfo.dayPhone}</b></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><b>${sessionScope.userInfo.dob}</b></td>
					</tr>
					<tr>
						<td>Social Security #:</td>
						<td><b>${sessionScope.userInfo.ssn}</b></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="clearfix">
				<button type="button" onclick="MM_goToURL('parent','/iris/secure/claimsList.shtml');return document.MM_returnValue" class="button float-left">
					 Go To Claims List
				</button>
		   		<button type="button" name="exit" onclick="MM_goToURL('parent','admin');return document.MM_returnValue" class="button float-right">
					Exit        		
		   		</button>
			</div>
		</div>
	</div>
</div>

