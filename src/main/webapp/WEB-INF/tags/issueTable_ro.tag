<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="issueTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Issuer Name</th>
	        <th>Address</th>
	    </tr>
	</thead>
	<tbody id="issueBody">
	    <c:forEach var="issue" items="${sessionScope.cache.issue}" varStatus="status">
	    	<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="issue-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="issue-${status.index}"></c:otherwise>
	    	</c:choose>
	        <td><a href="javascript:viewSubItem(${status.index}, 'issue', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${issue.issuerName}</a></td>
	        <td><a href="javascript:viewSubItem(${status.index}, 'issue', '${formBean.fraudTypeCd}', 'View Unauthorized Transaction')">${issue.issuerAddress}</a></td>
	    </tr>
    	</c:forEach>
    </tbody>
</table>
</div>
