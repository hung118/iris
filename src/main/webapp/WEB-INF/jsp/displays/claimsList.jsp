<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" uri="/WEB-INF/tld/format_functions.tld"%>

<fmt:setBundle basename="MessageResources" var="lang" />

<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Instructions</h2>
			<p><strong>Edit My Account:</strong> Click on Edit My Account button if you wish to change any of
			your personal information. <br />
			<strong>View Status Of My Complaint:</strong> Click on the Status link to obtain your law enforcement Case Number or to view case updates. <br />
			<strong>View Complaint:</strong> Click on an incident number in the table below to view its details. <br />
			<div class="border-box clearfix">
				<h2 class="float-left">${userInfo.firstName} ${userInfo.middleName} ${userInfo.lastName}</h2>
				<div class="float-right clearfix">
					<a href="#" onclick="MM_goToURL('parent','gotoUMDAccount.shtml');return document.MM_returnValue" class="mini-gold-button medium">Edit My Account</a>
				</div>
			</div>
			<div class="clearfix"></div>
			<h2>Complaints</h2>
			<div class="table-style">
				<table style="width:100%; border:0; cellspacing:0; cellpadding:3">
					<tr class="tableheading">
						<th>Date</th>
						<th>Incident Number</th>
						<th>Status</th>
						<th style="text-align:center">Affidavit</th>
						<th style="text-align:center">Report</th>
						<th style="text-align:center">Delete</th>
					</tr>
					<c:choose>
						<c:when test="${fn:length(requestScope.claims) > 0}">
							<c:forEach var="complaint" items="${requestScope.claims}" varStatus="status">
								<c:choose>
									<c:when test="${status.count % 2 == 0}">
										<tr>
									</c:when>
									<c:otherwise>
										<tr class="blue-bar">
									</c:otherwise>
								</c:choose>
								<td>${format:date(complaint.insertDatetime, "long")}</td>
								<td><a
									href="selectComplaint.shtml?trackingNumber=${complaint.trackingNumber}">${complaint.trackingNumber}</a></td>
								<td><c:if
										test="${complaint['status'] != null and complaint['status'] != ''}">
										<a
											href='caseStatus.shtml?trackingNumber=${complaint.trackingNumber}&dateFiled=${format:date(complaint.insertDatetime, "long")}'>
											<fmt:message key="${complaint.status}" bundle="${lang}" />
										</a>
									</c:if></td>
								<td style="text-align:center">
									<c:if test="${complaint['status'] != 'n'}">
										<a href="javascript:;" onclick="return popupWindow('/iris/PDFReport?trackingNumber=${complaint.trackingNumber}&report=af&affidavit=yes', 'PDFRewport', 820, 950);">
											<img src="${pageContext.request.contextPath}/img/download-pdf-file-icon_2x.png" alt="Download PDF" width="20" height="24"/>
										</a>
									</c:if>
								</td>
								<td style="text-align:center">										
									<c:if test="${complaint['status'] != 'n'}">	
				                    	<a href="javascript:;" onclick="return popupWindow('/iris/PDFReportBatch?trackingNumber=${complaint.trackingNumber}', 'PDFReport', 820, 950);">
											<img src="${pageContext.request.contextPath}/img/download-pdf-file-icon_2x.png" alt="Download PDF" width="20" height="24"/>
										</a>
									</c:if></td>
								<td style="text-align:center">
									<c:if test="${complaint['status'] == 'n'}">
										<a href="javascript:confirmDelete('Are you sure you want to delete this incident number?', 'deleteComplaint.shtml?trackingNumber=${complaint.trackingNumber}')">
											<img src="${pageContext.request.contextPath}/img/delete-icon_2x.png" alt="Delete" width="18" height="24"/>
										</a>
									</c:if>
								</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4" nowrap>There are currently no complaints in
									this list.<br /><br /> To add one, click on the <strong>New
										Complaint</strong> button.
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				<h2 class="float-right">
					<%
						if ("iris.utah.gov".equalsIgnoreCase(request.getHeader("x-forwarded-host"))) {
					%>
						<a href="http://idtheft.utah.gov/report-identity-theft-instructions" class="mini-gold-button">New Complaint</a>
					<% } else { %>
						<a href="http://idtheft.at.utah.gov/report-identity-theft-instructions" class="mini-gold-button">New Complaint</a>
					<% } %>
				</h2>
			</div>
		</div>
	</div>
</div>

<div id="dialog-confirm"></div>



