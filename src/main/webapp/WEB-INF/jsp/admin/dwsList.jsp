<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>DWS Login Data</h2>
			<div class="border-box clearfix table-style">
			    <table style="width:100%; border:0; cellspacing:3; cellpadding: 2">
					<tr class="tableheading">
						<th class="tableheading">UMD ID </th>
						<th class="tableheading">Created Date</th>
						<th class="tableheading">DWS Number</th>
			        </tr>
					<c:choose>
						<c:when test="${fn:length(dwsList) > 0}">
							<c:forEach var="dws" items="${dwsList}" varStatus="status">
								<c:choose>
									<c:when test="${status.count % 2 == 0}"><tr></c:when>
									<c:otherwise><tr class="blue-bar"></c:otherwise>
								</c:choose>
								<td>${dws.umdId}</td>
								<td>${dws.createdDate}</td>
								<td>${dws.dwsNumber}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
			              <tr>
			                <td colspan="5" nowrap>There are currently no existing DWS login data.</td>
			              </tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<div class="clearfix">
		   		<button type="button" name="exit" onclick="MM_goToURL('parent','admin');return document.MM_returnValue" class="button float-right">
					Exit        		
		   		</button>
			</div>
		</div>
	</div>
</div>
