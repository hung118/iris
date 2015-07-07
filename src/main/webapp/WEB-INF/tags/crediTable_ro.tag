<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="crediTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Creditor Name</th>
	        <th>Contact Information</th>
	    </tr>
    </thead>
	<tbody id="crediBody">
	    <c:forEach var="credi" items="${sessionScope.cache.credi}" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="credi-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="credi-${status.index}"></c:otherwise>
			</c:choose>
	        <td><a href="javascript:viewSubItem(${status.index}, 'credi', '${formBean.fraudTypeCd}', 'View Creditor')">${credi.creditorName}</a></td>
	        <td><a href="javascript:viewSubItem(${status.index}, 'credi', '${formBean.fraudTypeCd}', 'View Creditor')">${credi.creditorContactInfo}</a></td>
	    </tr>
	    </c:forEach>
	</tbody>
</table>
</div>