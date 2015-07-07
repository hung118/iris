<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="accouTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Name</th>
	        <th>Type</th>
	        <th style="text-align:center">Delete</th>
	    </tr>
    </thead>
	<tbody id="accouBody">
	    <c:forEach var="accou" items="${sessionScope.cache.accou}" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="accou-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="accou-${status.index}"></c:otherwise>
			</c:choose>
	        <td><a href="javascript:editSubItem(${status.index}, 'accou', '${formBean.fraudTypeCd}', 'Edit Account Holder', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${accou.accountHolderFirstName} ${accou.accountHolderMiddleName} ${accou.accountHolderLastName}</a></td>
	        <td><a href="javascript:editSubItem(${status.index}, 'accou', '${formBean.fraudTypeCd}', 'Edit Account Holder', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${accou.accountHolderType}</a></td>
	        <td style="text-align:center"><a href="javascript:deleteSubItem('delete Account Holder', 'Are you sure you want to delete this account holder record?', 'accou', '${formBean.fraudTypeCd}', ${status.index}, '${formBean.fraudTypeSeq}', '${accou.dataElementSeq}', '${formBean.trackingNumber}', '${formBean.wsAction}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a></td>
	    </tr>
	    </c:forEach>
	</tbody>
</table>
</div>