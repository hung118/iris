function validateAndConfirm(actionType) {
	if (validateMainForm()) {
		// confirm submit
		if (actionType == "submit") {	// save and submit to law enforcement
			$("#actionType").val("submitReport");
			confirmSubmitDialog("Are you sure you want to submit your complaint?", 170, 300);
		} else {	// save only
			$("#actionType").val("saveReport");
			confirmSubmitDialog("Your incident won't be submitted now, but the information entered so far will be saved. You can go to ID Theft Central to look up this incident #, and continue it later.", 240, 360);
		}
	} else {
		return false;
	}
}

function validateIndForm() {
	if (validateMainForm()) {
		showWait();
		return true;
	} else {
		return false;
	}
}

function validateMainForm() {
	// validate using id instead of name.
	$("#claimItemForm").validate({
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
		onkeyup: false
		//submitHandler: function(form) {	// will not submit form when click Yes on dialog. Remove completely!
			//$("div.error").hide();
			//form.submit();
		//}
	});
	
	$("#victimFirstName").rules("add", {required: true, messages: {required: "First Name is required."}});
	$("#victimLastName").rules("add", {required: true, messages: {required: "Last Name is required."}});
	$("#addr1Street").rules("add", {required: true, messages: {required: "Address is required."}});
	$("#addr1City").rules("add", {required: true, messages: {required: "City is required."}});
	$("#addr1Zip").rules("add", {required: true, messages: {required: "Zip Code is required."}});
	$("#victimEmailAddress").rules("add", {required: true, messages: {required: "Email Address is required."}});
	$("#victimSsn").rules("add", {required: true, messages: {required: "Social Security Number is required."}});
	
	if ($("#ssnFirstName").length) $("#ssnFirstName").rules("add", {required: true, messages: {required: "First Name is required."}});
	if ($("#ssnLastName").length) $("#ssnLastName").rules("add", {required: true, messages: {required: "Last Name is required."}});
	if ($("#ssnSsn").length) $("#ssnSsn").rules("add", {required: true, messages: {required: "Social Security Number is required."}});
	
	if ($("#unauthcreditFirstName").length) $("#unauthcreditFirstName").rules("add", {required: true, messages: {required: "First Name is required."}});
	if ($("#unauthcreditLastName").length) $("#unauthcreditLastName").rules("add", {required: true, messages: {required: "Last Name is required."}});
	if ($("#cardNumber").length) $("#cardNumber").rules("add", {required: true, messages: {required: "Card Number is required."}});
	
	if ($("#docketCaseNumber").length) $("#docketCaseNumber").rules("add", {required: true, messages: {required: "Case Number is required."}});
	
	if ($("#ottFirstName").length) $("#ottFirstName").rules("add", {required: true, messages: {required: "First Name is required."}});
	if ($("#ottLasttName").length) $("#ottLasttName").rules("add", {required: true, messages: {required: "Last Name is required."}});
	if ($("#ottSsn").length) $("#ottSsn").rules("add", {required: true, messages: {required: "Social Security Number is required."}});
	if ($("#incidentDate").length) $("#incidentDate").rules("add", {required: true, messages: {required: "Date of Incident is required."}});
	if ($("#ottIncidentDesc").length) $("#ottIncidentDesc").rules("add", {required: true, messages: {required: "Describe the Incident is required."}});
	
	if ($("#claimItemForm").valid()) {
		return true;
	} else {
		$('html,body').scrollTop(0);
		return false;
	}			
}

function confirmSubmitDialog(msg, h, w) {
    $("#dialog-confirm").html(msg).dialog({
        modal: true,
        title: "Confirm Box",
        height: h,
        width: w,
        dialogClass: 'IRIS',
        buttons: {
            "Yes": function () {
            	$(this).dialog('close');
            	$("#savingIndicatorSub").show().dialog({
            		modal: true,
                    title: "In Progress",
                    height: 120,
                    width: 260,
                    dialogClass: 'IRIS'
                });
            	$("#claimItemForm").submit();
    			return true;
            },
            "No": function () {
            	$(this).dialog('close');
            	return false;
            }
        }
    });
}

