<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="accouTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Name</th>
	        <th>Type</th>
	    </tr>
    </thead>
	<tbody id="accouBody">
	    <c:forEach var="accou" items="${sessionScope.cache.accou}" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="accou-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="accou-${status.index}"></c:otherwise>
			</c:choose>
	        <td><a href="javascript:viewSubItem(${status.index}, 'accou', '${formBean.fraudTypeCd}', 'View Account Holder')">${accou.accountHolderFirstName} ${accou.accountHolderMiddleName} ${accou.accountHolderLastName}</a></td>
	        <td><a href="javascript:viewSubItem(${status.index}, 'accou', '${formBean.fraudTypeCd}', 'View Account Holder')">${accou.accountHolderType}</a></td>
	    </tr>
	    </c:forEach>
	</tbody>
</table>
</div>