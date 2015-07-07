<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-style">
<table id="issueTable" style="width:100%; border:0;cellspacing:0;cellpadding:3">
	<thead>
	    <tr class="tableheading">
	        <th>Issuer Name</th>
	        <th>Address</th>
	        <th style="text-align:center">Delete</th>
	    </tr>
	</thead>
	<tbody id="issueBody">
	    <c:forEach var="issue" items="${sessionScope.cache.issue}" varStatus="status">
	    	<c:choose>
				<c:when test="${status.count % 2 == 0}"><tr id="issue-${status.index}"></c:when>
				<c:otherwise><tr class="blue-bar" id="issue-${status.index}"></c:otherwise>
	    	</c:choose>
	        <td><a href="javascript:editSubItem(${status.index}, 'issue', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${issue.issuerName}</a></td>
	        <td><a href="javascript:editSubItem(${status.index}, 'issue', '${formBean.fraudTypeCd}', 'Edit Unauthorized Transaction', '${formBean.trackingNumber}','${formBean.fraudTypeSeq}','${formBean.wsAction}')">${issue.issuerAddress}</a></td>
	        <td style="text-align:center"><a href="javascript:deleteSubItem('delete Unauthorized Transaction', 'Are you sure you want to delete this Unauthorized Transaction record?', 'issue', '${formBean.fraudTypeCd}', ${status.index}, '${formBean.fraudTypeSeq}', '${issue.dataElementSeq}', '${formBean.trackingNumber}', '${formBean.wsAction}', '')"><img style="border:0" src="${pageContext.request.contextPath}/img/delete-icon_2x.png"/></a></td>
	    </tr>
    	</c:forEach>
    </tbody>
</table>
</div>
