
var globalPath = 'http://www.idtheft.utah.gov/'; //this is the path to root that we can use for building common elements

var uiiOverride = false; //false allows the uii banner to load. true turns it off. used with the loadUII() function below


/* ------------------------------------------------   Menu Arrays   ------------------------------------------------ */
//in the arrays below, the first set of quotes is the url, the second is the graphical button, the third is the alt attribute and title value.
var mainNavArray = new Array();
	mainNavArray[0] = new Array('https://iris.utah.gov', 'imageHere', 'Report ID Theft');
	mainNavArray[1] = new Array('education/index.html', 'imageHere', 'Education');
	mainNavArray[2] = new Array('legislation/index.html', 'imageHere', 'Legislation');
	mainNavArray[3] = new Array('scams_alerts/index.html', 'imageHere', 'Scams and Alerts');
	mainNavArray[4] = new Array('index.html', 'images/homeicon.gif', 'Home');

var leftNavArray = new Array();
	leftNavArray[0] = new Array('http://idtheft.utah.gov/', 'imageHere', 'Home');
	leftNavArray[1] = new Array('/iris', 'imageHere', 'Report ID Theft');
	leftNavArray[2] = new Array('http://idtheft.utah.gov/education/index.html', 'imageHere', 'ID Theft Education');
	leftNavArray[3] = new Array('http://idtheft.utah.gov/legislation/index.html', 'imageHere', 'ID Theft Legislation');
	leftNavArray[4] = new Array('http://idtheft.utah.gov/scams_alerts/index.html', 'imageHere', 'Scams & Alerts');
	leftNavArray[5] = new Array('http://idtheft.utah.gov/education/media/index.html', 'imageHere', 'Media Center');
	
/*--------------------------------------------Status Bars----------------------------------------------*/

function selectFraud() {
	var htmlCode = '';
	htmlCode += '<img src="/iris/images/selectfraudbar.gif" alt="Select Fraud(s)" width="942" height="25" />';
	
	document.write(htmlCode);
}

function disclaimer() {
	var htmlCode = '';
	htmlCode += '<img src="/iris/images/disclaimerbar.gif" alt="Disclaimer" width="942" height="25" />';
	
	document.write(htmlCode);
}

function login() {
	var htmlCode = '';
	htmlCode += '<img src="/iris/images/loginbar.gif" alt="Login" width="942" height="25" />';
	
	document.write(htmlCode);
}

function createIncidents() {
	var htmlCode = '';
	htmlCode += '<img src="/iris/images/createincidentsbar.gif" alt="Create Incident(s)" width="942" height="25" />';
	
	document.write(htmlCode);
}

function submitComplaint() {
	var htmlCode = '';
	htmlCode += '<img src="/iris/images/submitcomplaintbar.gif" alt="Submit Complaint" width="942" height="25" />';
	
	document.write(htmlCode);
}


	
/* ------------------------------------------------   Menu Functions   ------------------------------------------------ */
// !! this uses an array to build menu items. it relies on the global variable called globalPath (defined at top of page) to point to the current domain.
// Provide the array you want to build.
// Answer 'yes' to hardReturn if you want the menu organized as paragraphs
// Answer 'yes' to listItem if you want the menu organized as a list

function buildTextMenu(theArray, hardReturn, listItem, sectionHighlight) { 
 var htmlCode = '';
 
 var listOpen = '';
 var listClose = '';
 var listItemBegin = '';
 var listItemEnd = '';
 var hardReturnBegin = '';
 var hardReturnEnd = '';
 var addClass = '';
 
 if (hardReturn == 'yes') {
  hardReturnBegin = '<p>';
  hardReturnEnd = '</p>';
 }
 
 if (listItem == 'yes') {
  listOpen = '<ul>';
  listClose = '</ul>';
  listItemBegin = '<li>';
  listItemEnd = '</li>';
 }
 
 htmlCode += listOpen;
 
 for(var i=0; i<theArray.length; i++) {
  if (sectionHighlight == i) {
   addClass = 'class="selected"';
  } else {
   addClass = '';
  }
  
  /*if( theArray[i][0].indexOf('http://') != -1 || theArray[i][0].indexOf('https://') != -1){ //check for absolute url
   htmlCode += hardReturnBegin + listItemBegin + '<a href="' + theArray[i][0] + '" alt="' + theArray[i][2] + '" title="' + theArray[i][2] + '" ' + addClass + '>' + theArray[i][2] + '</a>' + listItemEnd + hardReturnEnd;
  } else { //if url is not absolute, then add the global path
   htmlCode += hardReturnBegin + listItemBegin + '<a href="' + globalPath + theArray[i][0] + '" alt="' + theArray[i][2] + '" title="' + theArray[i][2] + '" ' + addClass + '>' + theArray[i][2] + '</a>' + listItemEnd + hardReturnEnd;
  }*/
  
  htmlCode += hardReturnBegin + listItemBegin + '<a href="' + theArray[i][0] + '" alt="' + theArray[i][2] + '" title="' + theArray[i][2] + '" ' + addClass + '>' + theArray[i][2] + '</a>' + listItemEnd + hardReturnEnd;
  
  //alert('addClass is: ' + addClass);
 }
 
 htmlCode += listClose;
 //alert(htmlCode);
 
 document.write(htmlCode);
 //alert(htmlCode);
}


//buildGraphicMenu(mainNavArray);
function buildGraphicMenu(theArray) { // !! this uses an array to build menu items. it relies on the global variable called globalPath (defined at top of page) to point to the current domain. 
	var htmlCode = '';

	for(var i=0; i<theArray.length; i++) {
		if( theArray[i][0].indexOf('http://') != -1 ){ //check for absolute url
			htmlCode += '<a href="' + theArray[i][0] + '"><img src="' + globalPath + theArray[i][1] + '" alt="' + theArray[i][2] + '" title="' + theArray[i][2] + '" /></a>';
		} else { //if url is not absolute, then add the global path
			htmlCode += '<a href="' + globalPath + theArray[i][0] + '"><img src="' + globalPath + theArray[i][1] + '" alt="' + theArray[i][2] + '" title="' + theArray[i][2] + '" /></a>';
		}
	}
	
	document.write(htmlCode);
}



/* ------------------------------------------------   Content   ------------------------------------------------ */
function uiiCustom() {
	var htmlCode = '';
	htmlCode += '<a href="http://utah.gov"><img src="' + globalPath + 'common/images/uii/00Logo.gif" alt="Utah.gov" title="Utah.gov" width="70" height="31" /></a>';
	//htmlCode += '<a href="http://www.utah.gov/services/index.html"><img src="' + globalPath + 'common/images/uii/01OnlineServices.gif" alt="Online Services" title="Online Services" width="140" height="31" /></a>';
	//htmlCode += '<a href="http://www.utah.gov/government/agencylist.html"><img src="' + globalPath + 'common/images/uii/02AgencyList.gif" alt="Agency List" title="Agency List" width="122" height="31" /></a>';
	//htmlCode += '<a href="http://business.utah.gov/business/"><img src="' + globalPath + 'common/images/uii/03Business.gif" alt="Business" title="Business" width="92" height="31" /></a>';
	
	document.write(htmlCode);
}





function brandingContent() {
	var htmlCode = '';
	
	htmlCode += '<div id="brandingLeft"><a href="http://idtheft.utah.gov/"><img src="/iris/img/irislogo.gif" alt="IRIS - Identity Theft Reporting Information System" width="453" height="78" border="0" /></a></div>';
	htmlCode += '<div id="brandingRight"><a href="http://attorneygeneral.utah.gov/"><img src="/iris/img/ag-office-logo.png" alt="State of Utah Attorney General" width="78" height="78" /></a></div>';
	htmlCode += '<div id="clear"></div>';
	
	document.write(htmlCode);
}



var theDate = new Date();
var currentYear = theDate.getYear();
if (currentYear < 2000) {
	currentYear += 1900;
}
//alert('currentYear = ' + currentYear);

function lastmod() {
	var months = new Array(13);
	months[1] = "January";
	months[2] = "February";
	months[3] = "March";
	months[4] = "April";
	months[5] = "May";
	months[6] = "June";
	months[7] = "July";
	months[8] = "August";
	months[9] = "September";
	months[10] = "October";
	months[11] = "November";
	months[12] = "December";

	var dateObj = new Date(document.lastModified);
	var lmonth = months[dateObj.getMonth() + 1];
	var fyear = dateObj.getYear();
	var date = dateObj.getDate();
	
	htmlCode = '<p>&nbsp</p>';
	htmlCode += '<p align="right">';
	htmlCode += ' This page last modified: ';
	htmlCode += lmonth;
	htmlCode += ' ';
	htmlCode += date;
	htmlCode += ', ';
	htmlCode += fyear;
	htmlCode += '</p>';
	
	document.write(htmlCode);
}

function footer() {
	var htmlCode = currentYear + " &copy; Utah State Attorney General\'s Office";
	document.write(htmlCode);
}

/* ------------------------------------------------   IE Style   ------------------------------------------------ */

function ieStyle() {
	//alert('ieStyle() fired');
	var htmlCode = '';
	htmlCode += '<!--[if IE 5]><link href="' + globalPath + 'common/css/ieStyle.css" rel="stylesheet" type="text/css"><![endif]-->';
	htmlCode += '<!--[if IE 6]><link href="' + globalPath + 'common/css/ieStyle.css" rel="stylesheet" type="text/css"><![endif]-->';
	
	document.write(htmlCode);
}

/* ------------------------------------------------   UII Loader   ------------------------------------------------ */
function checkUIIblock() {
	if (uiiOverride == true) {
		document.getElementById('uiiStage').innerHTML = '<img src="common/images/uii/uiiLeft.gif" alt="Utah.gov" title="Utah.gov" />';
	}
}

function loadUII(pleaseLoad) {
	if (pleaseLoad == true && uiiOverride == false) {
		//alert("pleaseLoad = true");
		document.getElementById('uiiBanner').innerHTML = document.getElementById('uiiStage').innerHTML;
		document.getElementById('uiiStage').innerHTML = '&nbsp;';
	}
}

/** Gets number of line feed asc(10) characters. */
function getLFCount(textval) {
	
	var lfCount = 0;
	for (var i = 1; i < textval.length; i++) {
		var a = textval.charCodeAt(i);
		if (a == 10) lfCount++;
	}
	
	return lfCount;
}

/** Limits max length of characters in text area box. Called from onkeyup and onkeydown events */
function textCounter(field, maxlimit) {
	
	var lfcount = getLFCount(field.value);
	var fieldval = field.value;
	if (navigator.appName != "Microsoft Internet Explorer") {	// Firefox returns asc(10) char for a return key, whereas IE returns asc(13) and asc(10).
		maxlimit = maxlimit - lfcount;
	}
	
	var fieldLen = fieldval.length;
	
	if (fieldLen > maxlimit) { // if too long...trim it!
		field.value = fieldval.substring(0, maxlimit);
	} else {	// otherwise, update 'characters left' counter
		var countDown = maxlimit - fieldLen;
		document.getElementById("counterId").firstChild.nodeValue = countDown;
	}
}

/** Displays initial count down number when loading page */
function showInitCount() {
	
	var field = document.getElementById("textId");
	var fieldLen = 0;
	if (navigator.appName == "Microsoft Internet Explorer") {	// IE
		fieldLen = field.value.length;	
	} else {	// firefox
		fieldLen = field.value.length + getLFCount(field.value);
	}
	
	var countDown = 4096 - fieldLen;
	document.getElementById("counterId").firstChild.nodeValue = countDown.toString();
}

/**
 * Redmine 9573 - waiting friendly message for submitting form to Web service.
 * 
 * @returns {Boolean}
 */
function confirmFormSubmit() {

	document.getElementById("waitNext").style.display = "block";
	document.getElementById("loadContent").style.display = "none";
		
	if (navigator.appName == "Microsoft Internet Explorer") {	// fix for IE by reset the animated image.
		waitImg= document.getElementById("waitId");
		window.setTimeout("waitImg.src = waitImg.src", 100);
	}
	
	return true; 
}

function MM_goToURL() { //v3.0
	  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
	  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

function logout() {
	confirmDialog("Are you sure you want to logout?", "/iris/secure/logout.shtml", 170, 300);
}

function confirmCancel(gotoUrl) {
	confirmDialog("Are you sure you want to cancel?", gotoUrl, 170, 300);
}

function confirmDelete(msg, gotoUrl) {
	confirmDialog(msg, gotoUrl, 170, 300);
}

function submitComplaint(trackingNumber) {
	confirmDialog("Are you sure you want to submit your complaint? Be sure to return to this website to view updates to your case.", "/iris/secure/submitComplaint.shtml?trackingNumber=" + trackingNumber, 205, 300);
}

function showWait() {
	$("#savingIndicatorSub").show().dialog({
		modal: true,
        title: "In Progress",
        height: 120,
        width: 260,
        dialogClass: 'IRIS'
	});
	return true;
}

function popupWindow(path, winName, width, height) {
	var popupWin;

    var opt = "width=" + width;
	opt += ",height=" + height;
	opt += ",menubar=0";
	opt += ",toolbar=0";
	opt += ",scrollbars=yes";
	opt += ",status=yes";
	opt += ",resizable=yes";
	opt += ",location=0";
    opt += ",left=0";
    opt += ",top=0";
	popupWin = window.open(path, winName, opt);
    popupWin.focus();
}

function confirmDialog(msg, gotoUrl, h, w) {
    $("#dialog-confirm").html(msg).dialog({
        modal: true,
        title: "Confirm Box",
        height: h,
        width: w,
        dialogClass: 'IRIS',
        buttons: {
            "Yes": function () {
                $(this).dialog('close');
                callbackConfirm(true, gotoUrl);
            },
            "No": function () {
                $(this).dialog('close');
                callbackConfirm(false, gotoUrl);
            }
        }
    });
}

function callbackConfirm(value, gotoUrl) {
    if (value) {
   		MM_goToURL('parent', gotoUrl);
    	return true;
    } else {
        return false;
    }
}
