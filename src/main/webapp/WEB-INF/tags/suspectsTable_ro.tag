<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${formBean.fraudTypeCd == 'BANKTRANS'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.banktransSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'BENEFITS'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.benefitsSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'UNAUTHCREDIT'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.unauthcreditSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'CREDITREPORT'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.creditreportSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'LOAN'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.loanSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'COLLECTOR'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.collectorSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'IDLOSTSTOLEN'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.idloststolenSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'TELEPHONE'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.telephoneSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'UTILITIES'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.utilitiesSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'SUIT'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.suitSuspect}"/>
	</c:when>
	<c:when test="${formBean.fraudTypeCd == 'EMAIL'}">
		<c:set var="sessionSuspect" value="${sessionScope.cache.emailSuspect}"/>
	</c:when>
	<c:otherwise>
		<!-- not possible -->
	</c:otherwise>
</c:choose>

<div class="table-style">
<table id="suspectTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Name</th>
	        <th>Address</th>
	    </tr>	
	</thead>
	<tbody>
	    <c:forEach var="suspect" items="${sessionSuspect}" varStatus="status">
	       	<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="suspect-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="suspect-${status.index}"></c:otherwise>
	    	</c:choose>
	    	<td><a href="javascript:viewSubItem(${status.index}, 'suspect', '${formBean.fraudTypeCd}', 'View Suspect')">${suspect.firstName} ${suspect.middleName} ${suspect.lastName}</a></td>
	    	<td><a href="javascript:viewSubItem(${status.index}, 'suspect', '${formBean.fraudTypeCd}', 'View Suspect')">${suspect.addr1Street}, ${suspect.addr1City}, ${suspect.addr1State} ${suspect.addr1Zip}</a></td>
	    </tr>
	    </c:forEach>	
	</tbody>
</table>
</div>