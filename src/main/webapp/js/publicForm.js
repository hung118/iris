function validatePublicForm() {	
	// validate using id instead of name.
	$("#publicForm").validate({
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
	
	if ($("#incidentNo").length) $("#incidentNo").rules("add", {required: true, messages: {required: "An Incident Number is required."}});
	
	if ($("#publicForm").valid()) {
		return true;
	} else {
		$('html,body').scrollTop(0);
		return false;
	}			
}

