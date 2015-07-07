function toggleSavingIndicator(show) {
	if (show) {
		$("#savingIndicatorSub").show().dialog({
			modal: true,
	        title: "In Progress",
	        height: 120,
	        width: 260,
	        dialogClass: 'IRIS'
		});
	} else {
		$("#savingIndicatorSub").dialog("close");			
	}
}

function addSubItem(type, fraudTypeCd, tname, trackingNumber, fraudTypeSeq, wsAction) {
	var action = "addSubItem.json";
	var dname = "#" + type + "Dialog";
	var formId = type + "Form";
	var h = 465;
	var w = 570;
	if (type == "accou") {
		h = 435;
		w = 570;
	} else if (type == "trans") {
		h = 620;
		w = 600;
	} else if (type == "tranx") {
		h = 690;
		w = 600;
	} else if (type == "suspect") {
		h = 620;
		w = 600;
		dname = "#" + fraudTypeCd.toLowerCase() + "SuspectDialog";
		formId = fraudTypeCd.toLowerCase() + "SuspectForm";
	}
	var selectUrl = "selectComplaintSubItem.shtml?type=" + type + "&fraudTypeCd=" + fraudTypeCd + "&trackingNumber=" + trackingNumber + "&fraudTypeSeq=" + fraudTypeSeq + "&wsAction=" + wsAction;
	$(dname).html("Loading ...").load(selectUrl).dialog({	
		height: h,
		width: w,
		modal: true,
		title: tname,
		dialogClass: 'IRIS',
		buttons: {
			"Save": function() {
				if (!validateSubForm(type, fraudTypeCd)) {
					return false;
				}
				toggleSavingIndicator(true);
				$.ajax({
					url: action,
					type: "POST",
					data: $("#" + formId).serialize(),
					cache: false,
					success: function(data) {
						if (data.errors !== "" && data.errors !== undefined && data.errors.length > 0) {
							$("div.error span").html("The server received errors: \n\n" + data.errors);
							$("div.error").show();
							return false;
						}
						if (type == "suspect") {
							$("#" + fraudTypeCd.toLowerCase() + "SuspectBody").empty();
						} else {
							$("#" + type + "Body").empty();
						}
						
						for (var i = 0; i < data.subItemList.length; i++) {	// populate all rows
							var subItem = data.subItemList[i];
							replaceRow(subItem, null, type, fraudTypeCd);
						}
						toggleSavingIndicator(false);
					}
				});
				$(this).dialog("close");
			},
			"Cancel": function() {
				$(this).dialog("close");
			},
		}
	});
}

function editSubItem(index, type, fraudTypeCd, tname, trackingNumber, fraudTypeSeq, wsAction) {
	var action = "updateSubItem.json";
	var dname = "#" + type + "Dialog";
	var formId = type + "Form";
	var h = 465;
	var w = 570;
	if (type == "accou") {
		h = 435;
		w = 570;
	} else if (type == "trans") {
		h = 620;
		w = 600;
	} else if (type == "tranx") {
		h = 690;
		w = 600;
	} else if (type == "suspect") {
		h = 620;
		w = 600;
		dname = "#" + fraudTypeCd.toLowerCase() + "SuspectDialog";
		formId = fraudTypeCd.toLowerCase() + "SuspectForm";
	}
	var selectUrl = "selectComplaintSubItem.shtml?type=" + type + "&fraudTypeCd=" + fraudTypeCd + "&index=" + index + "&trackingNumber=" + trackingNumber + "&fraudTypeSeq=" + fraudTypeSeq + "&wsAction=" + wsAction;
	$(dname).html("Loading ...").load(selectUrl).dialog({	
		height: h,
		width: w,
		modal: true,
		title: tname,
		dialogClass: 'IRIS',
		buttons: {
			"Save": function() {
				if (!validateSubForm(type, fraudTypeCd)) {
					return false;
				}
				toggleSavingIndicator(true);
				$.ajax({
					url: action,
					type: "POST",
					data: $("#" + formId).serialize(),
					cache: false,
					success: function(data) {
						if (data.errors !== "" && data.errors !== undefined && data.errors.length > 0) {
							$("div.error span").html("The server received errors: \n\n" + data.errors);
							$("div.error").show();
							return false;
						}
						if (type == "suspect") {
							$("#" + fraudTypeCd.toLowerCase() + "SuspectBody").empty();
						} else {
							$("#" + type + "Body").empty();
						}
						for (var i = 0; i < data.subItemList.length; i++) {	// populate all rows
							var subItem = data.subItemList[i];
							replaceRow(subItem, null, type, fraudTypeCd);
						}
						toggleSavingIndicator(false);
					}
				});
				$(this).dialog("close");
			},
			"Cancel": function() {
				$(this).dialog("close");
			},
		}
	});
}

function viewSubItem(index, type, fraudTypeCd, tname) {
	var dname = "#" + type + "Dialog";
	var h = 465;
	var w = 570;
	if (type == "accou") {
		h = 435;
		w = 570;
	} else if (type == "trans") {
		h = 620;
		w = 600;
	} else if (type == "tranx") {
		h = 690;
		w = 600;
	} else if (type == "suspect") {
		h = 620;
		w = 600;	
		dname = "#" + fraudTypeCd.toLowerCase() + "SuspectDialog";
	}
	var selectUrl = "selectComplaintSubItem.shtml?type=" + type + "&fraudTypeCd=" + fraudTypeCd + "&index=" + index;
	$(dname).html("Loading ...").load(selectUrl).dialog({	
		height: h,
		width: w,
		modal: true,
		title: tname,
		dialogClass: 'IRIS',
		buttons: {
			"Close": function() {
				$(this).dialog("close");
			},
		}
	});
}

function deleteSubItem(titleName, msg, type, fraudTypeCd, index, fraudTypeSeq, dataElementSeq, trackingNumber, wsAction, personIndex) {
	var action = "deleteSubItem.json";
	var dname = "#" + type + "Dialog";
	if (type == "suspect") {
		dname = "#" + fraudTypeCd.toLowerCase() + "SuspectDialog";
	}
	$(dname).html(msg).dialog({	
		height: 165,
		width: 502,
		modal: true,
		title: titleName,
		dialogClass: 'IRIS',
		buttons: {
			"Delete": function() {
				toggleSavingIndicator(true);
				$.ajax({
					url: action,
					type: "GET",
					data: {
						"type" : type,
						"fraudTypeCd" : fraudTypeCd,
						"index" : index,
						"fraudTypeSeq" : fraudTypeSeq,
						"dataElementSeq" : dataElementSeq,
						"trackingNumber" : trackingNumber,
						"wsAction" : wsAction,
						"personIndex" : personIndex
					},
					cache: false,
					success: function(data) {
						if (data.errors !== "" && data.errors !== undefined && data.errors.length > 0) {
							alert("ERROR: " + data.errors);
							return false;
						}
						if (type == "suspect") {
							$("#" + fraudTypeCd.toLowerCase() + "SuspectBody").empty();
						} else {
							$("#" + type + "Body").empty();
						}
						for (var i = 0; i < data.subItemList.length; i++) {	// populate all rows
							var subItem = data.subItemList[i];
							replaceRow(subItem, null, type, fraudTypeCd);
						}
						toggleSavingIndicator(false);
					}
				});
				$(this).dialog("close");
			},
			"Cancel": function() {
				$(this).dialog("close");
			},
		}
	});
}

function validateSubForm(type, fraudTypeCd) {	// validate using id instead of name.
	var validatedFormId = type + "Form";
	if (type == "suspect") {
		validatedFormId = fraudTypeCd.toLowerCase() + "SuspectForm";
	}
	$("#" + validatedFormId).validate({
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
		submitHandler: function() {
			$("div.error").hide();
		}
	});
	
	if (type == "accou") {
		$("#accountHolderFirstName").rules("add", {required: true, messages: {required: "First Name is required."}});
		$("#accountHolderLastName").rules("add", {required: true, messages: {required: "Last Name is required."}});
	} else if (type == "trans") {
		$("#transCompanyOrPerson").rules("add", {required: true, messages: {required: "Company or Person name is required."}});
		$("#transDate").rules("add", {required: true, messages: {required: "Date of Withdrawal is required."}});
	} else if (type == "suspect") {
		$("#suspectFirstName").rules("add", {required: true, messages: {required: "First Name is required."}});
		$("#suspectLastName").rules("add", {required: true, messages: {required: "Last Name is required."}});
	} else if (type == "issue") {
		$("#issuerName").rules("add", {required: true, messages: {required: "Issuer Name is required."}});
	}
	
	if ($("#" + validatedFormId).valid()) {
		return true;
	} else {
		return false;
	}			
}

function replaceRow(dataItem, rowId, type, fraudTypeCd) {
	var tname = "#" + type + "RowTemplate";
	if (type == "suspect") {
		tname = "#" + fraudTypeCd.toLowerCase() + "SuspectRowTemplate";
		type = fraudTypeCd.toLowerCase() + "Suspect";
	}
	
	var t = $(tname).html();
	var template = Handlebars.compile(t);
	var newRow = template(dataItem);
	if (rowId == null) { //new row
		$("#" + type + "Table  > tbody").append(newRow);
	} else {
		$("#" + type + "-" + rowId).replaceWith(newRow); //swap out the row
	}
}

