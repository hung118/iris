<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form method="POST" commandName="alert" action="saveAlert.admin" id="adminForm">
<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Security Alert Administration</h2>
			<p><strong>Add/Edit Alert:</strong> Fill in, or
			change information below and click Add Alert or
			Update Alert button.<br />
			<strong>Add ORI:</strong> Click Add ORI
			button on top of the ORI Jurisdiction table.<br />
			<strong>Delete ORI: </strong>Click
			Delete text link, in the ORI Jurisdiction table, to the
			right of the jurisdiction.<br />
			<strong>Exit:</strong> When you are finished, click
			Exit button to exit this administration screen.</p>
			
			<div class="error" style="display:none;">
			     	<span></span>.<br style="clear:all"/><br />
			</div>			
			<div class="border-box clearfix">
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
						<td style="text-align: right; width: 15%"><span class="redtext">*</span>Alert Name:</td>
						<td style="width: 85%">
							<form:input path="id.name" class="medium" id="alertName"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><span class="redtext">*</span>Alert Code:</td>
						<td>
							<form:input path="id.code" class="medium" id="alertCode"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><span class="redtext">*</span>Alert Email:</td>
						<td>
							<form:input path="email" class="large" id="alertEmail"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><span class="redtext">*</span> Exit URL:</td>
						<td>
							<form:input path="exitUrl" class="large" id="alertExitUrl"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td style="text-align: right">Alert Page URL:</td>
						<td><b><span id="aurl">&nbsp;</span></b></td>
					</tr>
					<tr>
						<td style="text-align: right">Exit Button URL:</td>
						<td><b><span id="eurl">&nbsp;</span></b></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2" class="table-style">
					<tr>
						<td colspan="2"><b>Select Fraud(s)</b></td>
					</tr>
					<tr>
						<td colspan="2" class="adminHead1">Checking Savings Account</td>
					</tr>
					<tr>
						<td style="width: 4%">
							<span class="checkbox"><form:checkbox path="fraudsArr" value="p1"/></span></td>
						<td style="width: 96%">Unauthorized withdrawal from your checking or savings account OR </td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> &nbsp; </span></td>
						<td>Unauthorized checking/savings account opened in your name</td>
					</tr>
					<tr>
						<td colspan="2" class="adminHead1">Benefits</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p3"/> </span></td>
						<td>Medicaid/Medicare, Insurance, or State Benefits</td>
					</tr>
					<tr>
						<td colspan="2" class="adminHead1">Credit</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p4"/></span></td>
						<td>Unauthorized use of your credit card</td>
					</tr>
					<tr class="blue-bar">
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p5"/> </span></td>
						<td>Unauthorized accounts on credit report</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p6"/> </span></td>
						<td>Unauthorized loan</td>
					</tr>	
					<tr class="blue-bar">
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p7"/> </span></td>
						<td>Collection agency</td>
					</tr>
					<tr>
						<td colspan="2" class="adminHead1">Government Document </td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p8"/> </span></td>
						<td>Driver's License/ID Lost, Stolen or Misused</td>
					</tr>
					<tr class="blue-bar">
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p9"/> </span></td>
						<td>Birth/Death Certificate</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p10"/> </span></td>
						<td>Passport</td>
					</tr>
					<tr class="blue-bar">
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p11"/> </span></td>
						<td>Military ID</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p12"/> </span></td>
						<td>Permanent Resident Card (Green Card)</td>
					</tr>	
					<tr class="blue-bar">
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p13"/> </span></td>
						<td>Social Security Number Theft</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p19"/> </span></td>
						<td>IRS notification</td>
					</tr>
					<tr>
						<td colspan="2" class="adminHead1">Utilities/Telephone</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p14"/> </span></td>
						<td>Telephone service (Land line or Cellular)</td>
					</tr>
					<tr class="blue-bar">
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p15"/> </span></td>
						<td>Utilities service (Gas, electricity, water, sewer, etc.)</td>
					</tr>
					<tr>
						<td colspan="2" class="adminHead1">Judicial</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p16"/> </span></td>
						<td>False criminal history</td>
					</tr>
					<tr class="blue-bar">
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p17"/> </span></td>
						<td>Warrant issued</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p18"/> </span></td>
						<td>False civil judgment</td>
					</tr>
					<tr>
						<td colspan="2" class="adminHead1">Miscellaneous</td>
					</tr>
					<tr>
						<td>
							<span class="checkbox"> <form:checkbox path="fraudsArr" value="p20"/> </span></td>
						<td>Email phisihing/pharming</td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
				</table>
			
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
						<td><strong>Select ORI Jurisdiction</strong></td>
					</tr>		
					<tr>
						<td><a name="ori" id="ori"></a>ORI Name - Code </td>
					</tr>
					<tr>
						<td>
							<form:select path="oriCode" class="single-select2 large">
								<form:option value="" label=""/>
								<form:options items="${oriList}" itemLabel="nameAndCode" itemValue="oriCode"/>
							</form:select>				
						</td>
					</tr>
				</table>
			</div>
			<div class="clearfix">
				<c:choose>
					<c:when test="${alert.method == 'updateAlert'}">
						<button type="submit" name="submitButton" onclick="return validateAdminForm();" class="button float-left">
							Update Alert
						</button>
					</c:when>
					<c:otherwise>
						<button type="submit" name="submitButton" onclick="return validateAdminForm();" class="button float-left">
							Add Alert
						</button>
					</c:otherwise>
				</c:choose>				
		   		<button type="button" name="exit" onclick="MM_goToURL('parent','viewAlertList.admin');return document.MM_returnValue" class="button float-right">
					Exit        		
		   		</button>
		   		<form:hidden path="method"/>	
			</div>
		</div>
	</div>
</div>
</form:form>

<script type="text/JavaScript">
	var alertPage = '<fmt:bundle basename="iris"><fmt:message key="alert.page"/></fmt:bundle>';
	
	if ("${alert.method}" == "updateAlert") {
		document.getElementById("aurl").firstChild.nodeValue=alertPage + "?breachName=" + escape("${alert.id.name}") + "&exitUrl=" + "${alert.exitUrl}";
		document.getElementById("eurl").firstChild.nodeValue="${alert.exitUrl}";
	}
</script>

<script type="text/javascript" src="/iris/js/adminForm.js"></script>
