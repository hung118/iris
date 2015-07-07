<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="tranxTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Date</th>
	        <th>Amount</th>
	        <th>Transaction Type</th>
	    </tr>
	</thead>
	<tbody id="tranxBody">
	    <c:forEach var="tranx" items="${sessionScope.cache.tranx}" varStatus="status">
	    	<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="tranx-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="tranx-${status.index}"></c:otherwise>
	    	</c:choose>
	        <td><a href="javascript:viewSubItem(${status.index}, 'tranx', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${tranx.tranxDate}</a></td>
	        <td>$<a href="javascript:viewSubItem(${status.index}, 'tranx', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${tranx.tranxAmount}</a></td>
	        <td><a href="javascript:viewSubItem(${status.index}, 'tranx', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${tranx.tranxRecipientType}</a></td>
	    </tr>
    	</c:forEach>
    </tbody>
</table>
</div>
