<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/JavaScript">
	function confirmDelete(name, code) {
		if (confirm("Are you sure you want to delete this record?")) {
			MM_goToURL('parent','deleteAlert.admin?name=' + escape(name) + '&code=' + escape(code));
			return document.MM_returnValue;
		} else {
			return false;	
		}
	}
	
	function editAlert(name, code) {
		MM_goToURL('parent','viewAlert.admin?name=' + escape(name) + '&code=' + escape(code));
		return true;
	}
</script>


<div id="content" class="site-full">
	<div id="content-main" class="site">
		<div id="content-border-box">
			<h2>Security Alert Administration</h2>
			<p><strong>Add Alert:</strong> Click 
			Add Alert button.<br />
			<strong>Edit Alert:</strong> Click
			Edit text link to the right of the alert in the table below.<br />
			<strong>Delete Alert:</strong> Click
			Delete text link to the right of the alert in the table
			below.<br />
			<strong>Exit:</strong> When you are finished, click
			Exit button to exit this administration screen.</p>
		
			<div class="border-box clearfix table-style">
				<h3 style="text-align: center">Existing Alerts</h3>
				<hr>
			    <table style="width:100%; border:0; cellspacing:3; cellpadding: 2">
					<tr class="tableheading">
						<th class="tableheading">Name </th>
						<th class="tableheading">Code</th>
						<th class="tableheading">Email</th>
						<th class="tableheading">Action</th>
			        </tr>
					<c:choose>
						<c:when test="${not empty alerts}">
							<c:forEach var="alert" items="${alerts}" varStatus="status">
								<c:choose>
									<c:when test="${status.count % 2 == 0}"><tr></c:when>
									<c:otherwise><tr class="blue-bar"></c:otherwise>
								</c:choose>
								<td>${alert.id.name}</td>
								<td>${alert.id.code}</td>
								<td>${alert.email}</td>
								<td>
									<a href="#" onclick="return editAlert('${alert.id.name}', '${alert.id.code}');">Edit</a>
									<a href="#" onclick="return confirmDelete('${alert.id.name}', '${alert.id.code}');">Delete</a>
								</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
			              <tr>
			                <td colspan="5" nowrap>There are currently no existing alert.</td>
			              </tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<div class="clearfix">
				<button name="addAlert" type="button" id="addAlert" onclick="MM_goToURL('parent','viewAlert.admin');return document.MM_returnValue" class="button float-left">
					 Add Alert
				</button>
				<button name="exit" type="button" id="exit" onclick="MM_goToURL('parent','admin');return document.MM_returnValue" class="button float-right">
				 	Exit
				</button>							
			</div>
		</div>
	</div>
</div>
		
