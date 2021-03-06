<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="transTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Transaction Date</th>
	        <th>Amount</th>
	        <th>Account Type</th>
	        <th style="text-align:center">Delete</th>
	    </tr>
	</thead>
	<tbody id="transBody">
	    <c:forEach var="trans" items="${sessionScope.cache.trans}" varStatus="status">
	    	<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="trans-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="trans-${status.index}"></c:otherwise>
	    	</c:choose>
	        <td><a href="javascript:editSubItem(${status.index}, 'trans', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${trans.transDate}</a></td>
	        <td>$<a href="javascript:editSubItem(${status.index}, 'trans', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${trans.transAmount}</a></td>
	        <td><a href="javascript:editSubItem(${status.index}, 'trans', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${trans.transAccountType}</a></td>
	        <td style="text-align:center"><a href="javascript:deleteSubItem('delete Unauthorized Transaction', 'Are you sure you want to delete this Unauthorized Transaction record?', 'trans', '${formBean.fraudTypeCd}', ${status.index}, '${formBean.fraudTypeSeq}', '${trans.dataElementSeq}', '${formBean.trackingNumber}', '${formBean.wsAction}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a></td>
	    </tr>
    	</c:forEach>
    </tbody>
</table>
</div>