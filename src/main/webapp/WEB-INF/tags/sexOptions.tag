<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="propertyName" required="true" %>
<%@ attribute name="styleClassName" required="false" %>

<form:select path="${propertyName}" cssClass="${styleClassName}">
    <form:option value="">Select Sex</form:option>
    <form:option value="m">Male</form:option>
    <form:option value="f">Female</form:option>
</form:select>