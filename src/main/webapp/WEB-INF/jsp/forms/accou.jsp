<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<form:form commandName="claimSubItemForm" modelAttribute="claimSubItemForm" id="accouForm">
	<div class="error" style="display:none;">
      	<span></span>.<br style="clear:all"/>
    </div>
    <div class="border-box clearfix">
		<hr>
		<h3>Account Holder Information</h3>
		<div class="field-row clearfix">
			<label>Account Type</label>
		</div>
		<div class="field-row clearfix">
			<div class="clearfix">
				<form:radiobutton path="accou['accountHolderType']" value="PRIMARY" id="accountHolderType1"/>
				<label for="accountHolderType1">Primary</label>
			</div>
			<div class="clearfix">
				<form:radiobutton path="accou['accountHolderType']" value="JOINT" id="accountHolderType2"/>
				<label for="accountHolderType2">Joint</label>
			</div>
		</div>
		<div class="field-row clearfix">
			<label for="accountHolderFirstName"><span class="redtext">*</span>First Name</label>
			<form:input path="accou['accountHolderFirstName']" id="accountHolderFirstName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="accountHolderMiddleName">Middle Name</label>
			<form:input path="accou['accountHolderMiddleName']" id="accountHolderMiddleName" class="medium"/>
		</div>
		<div class="field-row clearfix">
			<label for="accountHolderLastName"><span class="redtext">*</span>Last Name</label>
			<form:input path="accou['accountHolderLastName']" id="accountHolderLastName" class="medium"/>
			<form:hidden path="accou['dataElementSeq']"/>	
		</div>
	</div>

	<tags:complaintSubItem />

</form:form>

