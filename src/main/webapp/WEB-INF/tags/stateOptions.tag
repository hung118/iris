<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="propertyName" required="true" %>
<%@ attribute name="styleClassName" required="false" %>
<%@ attribute name="propertyId" required="false" %>

<form:select path="${propertyName}" cssClass="${styleClassName}" id="${propertyId}">
    <form:option value="">Select State</form:option>
    <form:option value="AL">AL - Alabama</form:option>
    <form:option value="AK">AK - Alaska</form:option>
    <form:option value="AZ">AZ - Arizona</form:option>
    <form:option value="AR">AR - Arkansas</form:option>
    <form:option value="CA">CA - California</form:option>
    <form:option value="CO">CO - Colorado</form:option>
    <form:option value="CT">CT - Connecticut</form:option>
    <form:option value="DE">DE - Delaware</form:option>
    <form:option value="DC">DC - District of Columbia</form:option>
    <form:option value="FL">FL - Florida</form:option>
    <form:option value="GA">GA - Georgia</form:option>
    <form:option value="HI">HI - Hawaii</form:option>
    <form:option value="ID">ID - Idaho</form:option>
    <form:option value="IL">IL - Illinois</form:option>
    <form:option value="IN">IN - Indiana</form:option>
    <form:option value="IA">IA - Iowa</form:option>
    <form:option value="KS">KS - Kansas</form:option>
    <form:option value="KY">KY - Kentucky</form:option>
    <form:option value="LA">LA - Louisiana</form:option>
    <form:option value="ME">ME - Maine</form:option>
    <form:option value="MD">MD - Maryland</form:option>
    <form:option value="MA">MA - Massachusetts</form:option>
    <form:option value="MI">MI - Michigan</form:option>
    <form:option value="MN">MN - Minnesota</form:option>
    <form:option value="MS">MS - Mississippi</form:option>
    <form:option value="MO">MO - Missouri</form:option>
    <form:option value="MT">MT - Montana</form:option>
    <form:option value="NE">NE - Nebraska</form:option>
    <form:option value="NV">NV - Nevada</form:option>
    <form:option value="NH">NH - New Hampshire</form:option>
    <form:option value="NJ">NJ - New Jersey</form:option>
    <form:option value="NM">NM - New Mexico</form:option>
    <form:option value="NY">NY - New York</form:option>
    <form:option value="NC">NC - North Carolina</form:option>
    <form:option value="ND">ND - North Dakota</form:option>
    <form:option value="OH">OH - Ohio</form:option>
    <form:option value="OK">OK - Oklahoma</form:option>
    <form:option value="OR">OR - Oregon</form:option>
    <form:option value="PA">PA - Pennsylvania</form:option>
    <form:option value="RI">RI - Rhode Island</form:option>
    <form:option value="SC">SC - South Carolina</form:option>
    <form:option value="SD">SD - South Dakota</form:option>
    <form:option value="TN">TN - Tennessee</form:option>
    <form:option value="TX">TX - Texas</form:option>
    <form:option value="UT">UT - Utah</form:option>
    <form:option value="VT">VT - Vermont</form:option>
    <form:option value="VA">VA - Virginia</form:option>
    <form:option value="WA">WA - Washington</form:option>
    <form:option value="WV">WV - West Virginia</form:option>
    <form:option value="WI">WI - Wisconsin</form:option>
    <form:option value="WY">WY - Wyoming</form:option>
</form:select>