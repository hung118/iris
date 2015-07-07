<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="tranxTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Date</th>
	        <th>Amount</th>
	        <th>Transaction Type</th>
	        <th style="text-align:center">Delete</th>
	    </tr>
	</thead>
	<tbody id="tranxBody">
	    <c:forEach var="tranx" items="${sessionScope.cache.tranx}" varStatus="status">
	    	<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="tranx-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="tranx-${status.index}"></c:otherwise>
	    	</c:choose>
	        <td><a href="javascript:editSubItem(${status.index}, 'tranx', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${tranx.tranxDate}</a></td>
	        <td>$<a href="javascript:editSubItem(${status.index}, 'tranx', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${tranx.tranxAmount}</a></td>
	        <td><a href="javascript:editSubItem(${status.index}, 'tranx', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}')">${tranx.tranxRecipientType}</a></td>
	        <td style="text-align:center"><a href="javascript:deleteSubItem('delete Unauthorized Transaction', 'Are you sure you want to delete this Unauthorized Transaction record?', 'tranx', '${formBean.fraudTypeCd}', ${status.index}, '${formBean.fraudTypeSeq}', '${tranx.dataElementSeq}', '${formBean.trackingNumber}', '${formBean.wsAction}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a></td>
	    </tr>
    	</c:forEach>
    </tbody>
</table>
</div>
