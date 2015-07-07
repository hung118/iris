<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" uri="/WEB-INF/tld/format_functions.tld"%>

<fmt:setBundle basename="MessageResources" var="lang" />

<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">		
			<table style="width:560; border:0; cellpadding:2; cellspacing:0">
			  <tr>
			    <td class="formcopy">&nbsp;</td>
			  </tr>
			  <tr>
			    <td style="color: #FFFFFF; background-color: #990000;">Complaint Status for Complaint Number ${param.trackingNumber} </td>
			  </tr>
			  <tr>
			    <td class="formcopy">&nbsp;</td>
			  </tr>
			  <tr>
			    <td>Date Filed: <b>${param.dateFiled}</b> </td>
			  </tr>
			  <tr>
			    <td class="formcopy">&nbsp;</td>
			  </tr>
			  <c:choose>
			    <c:when test="${fn:length(requestScope.cases) > 0}">
			      <c:forEach items="${requestScope.cases}" var="row" varStatus="status">
			      <tr>
			        <td class="formcopy"><table style="width:100%; cellpadding:2; cellspacing:0; border-style: solid; border-width: small; border-color:#CCCCCC">
			          <tr>
			            <td style="background-color:#CCCCCC"><table style="width:100%; border:0; cellpadding:2; cellspacing:0">
			              <tr>
			                <td nowrap class="boldformcopy">Case ${status.index}</td>
			                <td><div style="text-align: right"></div></td>
			              </tr>
			            </table></td>
			          </tr>
			          <tr>
			            <td><table style="width:100%; border:0; cellpadding:2; cellspacing:0">
			              <tr>
			                <td><table style="width:100%; border:0; cellpadding:2; cellspacing:0">
			                  <tr>
			                    <td class="col1">Case Number:</td>
			                    <td><b>${row.caseNumber}</b></td>
			                  </tr>
			                  <tr>
			                    <td>Case Status:</td>
			                    <td><b><fmt:message key="${row.caseStatus}" bundle="${lang}" /></b></td>
			                  </tr>
			                  <tr>
			                    <td>Agency Name:</td>
			                    <td><b>${row.agencyName}</b></td>
			                  </tr>
			                  <tr>
			                    <td>Agency Address:</td>
			                    <td>
			                    	<b>${row.addrStreet}</b><br>
			                    	<b>${row.addrCity}, ${row.addrState} ${row.addrZip}</b>
			                    </td>
			                  </tr>
			                  <tr>
			                    <td>Investigator Name:</td>
			                    <td><b>${row.investigatorName}</b></td>
			                  </tr>
			                  <tr>
			                    <td>Investigator Phone:</td>
			                    <td><b>${row.investigatorPhone}</b></td>
			                  </tr>
			                  <tr>
			                    <td>Comments:</td>
			                    <td><b>${row.comments}</b></td>
			                  </tr>
			                </table></td>
			              </tr>
			            </table></td>
			          </tr>
			        </table></td>
			      </tr>
			      <tr>
			        <td class="formcopy">&nbsp;</td>
			      </tr>
			      </c:forEach>
			      <tr>
			      	<td>
			      		<button name="cancel" type="button" onClick="window.print();" class="button float-left">Print Page</button>
			      		<button name="cancel" type="button" onClick="history.back();" class="button float-left">Finished</button>
			      	</td>
			      </tr>
			    </c:when>
			    <c:otherwise>
			      <tr>
			        <td><b>New Complaint: </b>This complaint has not been submitted to Law Enforcement.</td>
			      </tr>
			    </c:otherwise>
			  </c:choose>
			</table>
		</div>
	</div>
</div>

