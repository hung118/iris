<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="propertyName" required="true" %>
<%@ attribute name="styleClassName" required="false" %>

<form:select path="${propertyName}" cssClass="${styleClassName}">
    <form:option value="">Select a Race</form:option>
    <form:option value="c">Caucasion</form:option>
    <form:option value="b">Black</form:option>
    <form:option value="a">Asian</form:option>
    <form:option value="n">Native American</form:option>
    <form:option value="o">Other</form:option>
</form:select>
