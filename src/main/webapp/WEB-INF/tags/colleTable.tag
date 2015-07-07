<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="colleTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Collection Agency</th>
	        <th>Address</th>
	        <th style="text-align:center">Delete</th>
	    </tr>
    </thead>
	<tbody id="colleBody">
	    <c:forEach var="colle" items="${sessionScope.cache.colle}" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="colle-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="colle-${status.index}"></c:otherwise>
			</c:choose>
	        <td><a href="javascript:editSubItem(${status.index}, 'colle', '${formBean.fraudTypeCd}', 'Edit Collection Agency', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${colle.collectorName}</a></td>
	        <td><a href="javascript:editSubItem(${status.index}, 'colle', '${formBean.fraudTypeCd}', 'Edit Collection Agency', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${colle.collectorAddress}, ${colle.collectorCity}, ${colle.collectorState} ${colle.collectorZipCode}</a></td>
	        <td style="text-align:center"><a href="javascript:deleteSubItem('delete Collection Agency', 'Are you sure you want to delete this collection agency record?', 'colle', '${formBean.fraudTypeCd}', ${status.index}, '${formBean.fraudTypeSeq}', '${colle.dataElementSeq}', '${formBean.trackingNumber}', '${formBean.wsAction}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a></td>
	    </tr>
	    </c:forEach>
	</tbody>
</table>
</div>