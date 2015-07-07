<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="crediTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Creditor Name</th>
	        <th>Contact Information</th>
	        <th style="text-align:center">Delete</th>
	    </tr>
    </thead>
	<tbody id="crediBody">
	    <c:forEach var="credi" items="${sessionScope.cache.credi}" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="credi-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="credi-${status.index}"></c:otherwise>
			</c:choose>
	        <td><a href="javascript:editSubItem(${status.index}, 'credi', '${formBean.fraudTypeCd}', 'Edit Creditor', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${credi.creditorName}</a></td>
	        <td><a href="javascript:editSubItem(${status.index}, 'credi', '${formBean.fraudTypeCd}', 'Edit Creditor', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${credi.creditorContactInfo}</a></td>
	        <td style="text-align:center"><a href="javascript:deleteSubItem('delete Creditor Information', 'Are you sure you want to delete this Creditor Information record?', 'credi', '${formBean.fraudTypeCd}', ${status.index}, '${formBean.fraudTypeSeq}', '${credi.dataElementSeq}', '${formBean.trackingNumber}', '${formBean.wsAction}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a></td>
	    </tr>
	    </c:forEach>
	</tbody>
</table>
</div>