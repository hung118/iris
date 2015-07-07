<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="transTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Transaction Date</th>
	        <th>Amount</th>
	        <th>Account Type</th>
	    </tr>
	</thead>
	<tbody id="transBody">
	    <c:forEach var="trans" items="${sessionScope.cache.trans}" varStatus="status">
	    	<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="trans-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="trans-${status.index}"></c:otherwise>
	    	</c:choose>
	        <td><a href="javascript:viewSubItem(${status.index}, 'trans', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${trans.transDate}</a></td>
	        <td>$<a href="javascript:viewSubItem(${status.index}, 'trans', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${trans.transAmount}</a></td>
	        <td><a href="javascript:viewSubItem(${status.index}, 'trans', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${trans.transAccountType}</a></td>
	    </tr>
    	</c:forEach>
    </tbody>
</table>
</div>