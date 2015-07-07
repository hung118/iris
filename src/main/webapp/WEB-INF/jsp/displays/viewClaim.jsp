<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="/WEB-INF/tld/format_functions.tld"%>

<fmt:setBundle basename="MessageResources" var="lang"/>

<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<b>Incident Number</b>: ${trackingNumber} <br /><br />
			From this screen you can view the details on each fraud complaint that you entered.
			<h2>Instructions</h2>
			<strong>View Fraud Type Details:</strong> Click on a specific Fraud Type in the table below to view the information you added. <br />
			<c:if test="${status == 'n'}">
				<strong>Submit to Law Enforcement:</strong> Click on Submit button to submit your complaint to law enforcement.<br />
			</c:if>
			<br />
			<div class="clearfix"></div>
			<div class="table-style">
				<table style="width:100%; border:0; cellspacing:0; cellpadding:3">
			        <tr class="tableheading">
			          <th>Date Filed</th>
			          <th>Fraud Type</th>
			          <th style="text-align:center">Delete</th>
			        </tr>
			        <!-- complaint details from Web service -->
			        <c:if test="${fn:length(requestScope.complaintDetails) > 0}">
			          <c:forEach var="detail" items="${requestScope.complaintDetails}" varStatus="status">
			            <c:choose>
			              <c:when test="${status.count % 2 == 0}"><tr></c:when>
			              <c:otherwise><tr class="blue-bar"></c:otherwise>
			            </c:choose>
			            	<td>${format:date(detail["insertDateTime"], "long")}</td>
			              <td><a href="selectComplaintItem.shtml?trackingNumber=${detail['trackingNumber']}&fraudTypeCd=${detail['fraudTypeCd']}&fraudTypeSeq=${detail['fraudTypeSeq']}&status=${requestScope['status']}"><fmt:message key="${detail['fraudTypeCd']}" bundle="${lang}"/></a></td>
			              <c:choose>
			              	<c:when test="${requestScope['status'] == 'n'}">
			               	<td style="text-align:center">
			               		<a href="deleteComplaintItem.shtml?trackingNumber=${detail['trackingNumber']}&fraudTypeCd=${detail['fraudTypeCd']}&fraudTypeSeq=${detail['fraudTypeSeq']}&dataElementSeq=${detail['dataElementSeq']}" onclick="if (confirm('Are you sure you want to delete <fmt:message key="${detail['fraudTypeCd']}" bundle="${lang}"/> ?')) {return true;} else {return false;}">
			               			<img src="${pageContext.request.contextPath}/img/delete-icon_2x.png" alt="Delete" width="18" height="24"/></a>
			               	</td>                	
			              	</c:when>
			              	<c:otherwise>
							<td>&nbsp;</td>                	
			              	</c:otherwise>
			              </c:choose>                
			            </tr>
			          </c:forEach>
			        </c:if>
			        <c:if test="${fn:length(requestScope.complaintDetails) == 0 && fn:length(requestScope.complaintLocalDetails) == 0 }">
			            <tr>
			              <td colspan="3">
			              	There are currently no incident in this list. To add one, click <a href="http://idtheft.at.utah.gov/report-identity-theft-instructions">Report New ID Theft</a>.
			              </td>
			            </tr>
			        </c:if>
			
				</table>
				<c:choose>
					<c:when test="${status == 'n'}">
						<h2 class="float-right">
							<a href="/iris" class="mini-gold-button large">Cancel</a>
							<a href="javascript:submitComplaint('${trackingNumber}')" class="mini-gold-button large">Submit</a>
						</h2>
					</c:when>
					<c:otherwise>
					<h2 class="float-right">
						<a href="/iris" class="mini-gold-button large">Cancel</a>
					</h2>
					</c:otherwise>
				</c:choose>	
			</div>
		</div>
	</div>
</div>

<div id="dialog-confirm"></div>
