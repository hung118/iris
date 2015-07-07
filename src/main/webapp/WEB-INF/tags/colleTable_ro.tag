<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="colleTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Collection Agency</th>
	        <th>Address</th>
	    </tr>
    </thead>
	<tbody id="colleBody">
	    <c:forEach var="colle" items="${sessionScope.cache.colle}" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="colle-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="colle-${status.index}"></c:otherwise>
			</c:choose>
	        <td><a href="javascript:viewSubItem(${status.index}, 'colle', '${formBean.fraudTypeCd}', 'View Collection Agency')">${colle.collectorName}</a></td>
	        <td><a href="javascript:viewSubItem(${status.index}, 'colle', '${formBean.fraudTypeCd}', 'View Collection Agency')">${colle.collectorAddress}, ${colle.collectorCity}, ${colle.collectorState} ${colle.collectorZipCode}</a></td>
	    </tr>
	    </c:forEach>
	</tbody>
</table>
</div>