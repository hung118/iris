function validateAdminForm() {	
	// validate using id instead of name.
	$("#adminForm").validate({
		invalidHandler: function(form, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				var message = errors == 1
					? 'There is 1 missing field. Please correct it'
					: 'There are ' + errors + ' missing fields.  Please correct them';
				$("div.error span").html(message);
				$("div.error").show();
			} else {
				$("div.error").hide();
			}
		},
		onkeyup: false,
		submitHandler: function(form) {
			$("div.error").hide();
			form.submit();
		}
	});
	
	if ($("#alertName").length) $("#alertName").rules("add", {required: true, messages: {required: "Alert Name is required."}});
	if ($("#alertCode").length) $("#alertCode").rules("add", {required: true, messages: {required: "Alert Code is required."}});
	if ($("#alertEmail").length) $("#alertEmail").rules("add", {required: true, messages: {required: "Alert Email is required."}});
	if ($("#alertExitUrl").length) $("#alertExitUrl").rules("add", {required: true, messages: {required: "Exit URL is required."}});
	
	if ($("#umdId").length) $("#umdId").rules("add", {required: true, messages: {required: "UMD ID is required."}});
	
	if ($("#oriStreetAddress").length) $("#oriStreetAddress").rules("add", {required: true, messages: {required: "Street Address is required."}});
	if ($("#oriZipCode").length) $("#oriZipCode").rules("add", {required: true, messages: {required: "Zip Code is required."}});
	
	if ($("#adminForm").valid()) {
		return true; 
	} else {
		$('html,body').scrollTop(0);
		return false;
	}			
}

