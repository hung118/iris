<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Track my Case</h2>
			<p>Below is the result of your search. Click finished when completed.</p>
			<p>
			The number you entered is an official Utah Attorney General Incident Number, and it was assigned to an Identity Theft Incident 
			Report entered at ID Theft Central. A Case Number and the investigator's contact information will be displayed below after 
			an investigating officer has reviewed and updated your case. 
			You may contact the agency(s) listed below to obtain the status of your case, and /or to provide additional information.<br/><br/>
			Incident Number: <b>${param.trackingNumber}</b><br />
			Victim's Last Name: <b>${requestScope.victimLastName}</b>
			</p>
			<div class="border-box clearfix">
				<table style="width:100%; border:0; cellspacing:3; cellpadding:2">
					<tr>
				    	<td class="formcopy">&nbsp;</td>
				  	</tr>
				  	<c:choose>
				    	<c:when test="${fn:length(requestScope.cases) > 0}">
				      		<c:forEach var="fraudCase" items="${requestScope.cases}" varStatus="status">
								<tr>
									<td class="formcopy"><table style="border: 1px solid #CCCCCC;">
										<tr>
											<td style="background-color: #CCCCCC"><table style="width:100%; border:0; cellspacing:3; cellpadding:2">
												<tr>
													<td>Case ${status.index}</td>
												</tr>
											</table></td>
										</tr>
									    <tr>
									      <td><table style="width:100%; border:0; cellspacing:3; cellpadding:2">
									        <tr>
									          <td><table style="width:100%; border:0; cellspacing:3; cellpadding:2">
									            <tr>
									              <td>Case Number:</td>
									              <td><b>
									              	<c:choose>
									              		<c:when test="${fraudCase.caseNumber eq 'Not validated by Law Enforcement'}">
									              			This identity theft incident is waiting for a response from law enforcement.
									              		</c:when>
									              		<c:when test="${fraudCase.caseNumber == 'Not yet submitted to law enforcement'}">
									              			Valid Utah Attorney General Incident number. The report however was not submitted to law enforcement.
									              		</c:when>
									              		<c:otherwise>
									              			${fraudCase.caseNumber}
									              		</c:otherwise>
									              	</c:choose>
									              </b></td>
									            </tr>
									            <tr>
									              <td>Agency Name:</td>
									              <td><b>${fraudCase.leName}</b></td>
									            </tr>
									            <tr>
									              <td>Agency Address:</td>
									              <td><b>
									              	<c:if test="${fraudCase.leAddress != 'null'}">
									              		${fraudCase.leAddress}<br>${fraudCase.leCity}
									              	</c:if>
									              </b></td>
									            </tr>
									            <tr>
									              <td>Phone:</td>
									              <td>
									              	<c:if test="${fraudCase.lePhone != 'null'}">
									               	<script>
									               		document.write("<b>" + formatPhone('${fraudCase.lePhone}') + "</b>");
									               	</script>
									              	</c:if>
									              </td>
									            </tr>
									            <tr>
									              <td>Investigator Name:</td>
									              <td><b>
									              	<c:if test="${fraudCase.investigatorName != 'null'}">
									              		${fraudCase.investigatorName}
									              	</c:if>
									              </b></td>
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
				    	</c:when>
				    	<c:otherwise>
					      	<tr>
					        	<td>
					        		<p class="redtext"><em><strong>Not a valid Utah Attorney General Incident Number. Check the number and try again.</strong></em>
								</td>
					      	</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<div class="clearfix">
				<button type="button" onclick="MM_goToURL('parent','/iris/public/validateVictim.shtml');return document.MM_returnValue;" class="button float-left">
					 Search Again
				</button>
		   		<button type="button" name="exit" onclick="MM_goToURL('parent','http://www.idtheft.utah.gov/');return document.MM_returnValue;" class="button float-left">
					Finished        		
		   		</button>
			</div>
		</div>
	</div>
</div>
