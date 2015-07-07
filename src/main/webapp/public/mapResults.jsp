 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <style type="text/css">
        @import "https://serverapi.arcgisonline.com/jsapi/arcgis/2.0/js/dojo/dijit/themes/tundra/tundra.css";
        #progressbar_container
        {
            background: #393c3d url(topgradient-gray.jpg) repeat-x scroll left top;
            border: 1px solid #999999;
            display: none;
            height: 80px;
            padding: 5px 10px;
            position: absolute;
            right: 40%;
            top: 300px;
            width: 20%;
            z-index: 400;
        
        }
        
        .dijitTooltipContainer{
        	background-color:#FFFFCD;
        	font-size:15px;
        	border : 1px solid #AAAAAA;
        	padding:4px 4px 4px 4px;
        	font-family:arial;
        }
        
        #progresstext
        {
            color: #000000;
        }
        #progressbar
        {
            height: 15px;
            width: 100%;
        }
        #map
        {
            background-color: #ffffff;
            border-top: 1px solid #000000;
            height: 600px;
            width: 99%;
        }
        
    </style>
    
    <script type='text/javascript' src='/iris/public/dwr/interface/MapDataService.js'></script>
  	<script type='text/javascript' src='/iris/public/dwr/engine.js'></script>
    <script type="text/javascript">        djConfig = { parseOnLoad: true };</script>
    <script type="text/javascript" src="https://serverapi.arcgisonline.com/jsapi/arcgis/?v=2.0"></script>
    <script type="text/javascript">

    	

	    var icons = new Array();
	    var desc = new Array();
	    desc [1] = "Claims of unauthorized withdrawals from checking or savings account, or the establishment of unauthorized checking or savings account.";
    	desc [2] = "Claims of being contacted by collection agencies for debt created as a result of identity theft.";
   		desc [3] = "Reported incidents of false or mistaken accounts on Consumer Credit Reports that are a result of identity theft.";
   		desc [4] = "Claims of individuals providing personal identifying information and credit card or bank account numbers to an email or phishing scam.  ";
   		desc [5] = "Claims of identity thieves receiving Medicaid, Medicare, insurance, or other state benefits using another's personal identifying information. ";
   		desc [6] = "Reports of lost, stolen, or misused Driver's Licenses, or other type of personal Identification document. ";
   		desc [7] = "Claims of unauthorized loans created as a result of identity theft. ";
   		desc [8] = "Claims of individuals entering into a fraudulent contract for fungible, negotiable instruments representing financial value (such as banknotes, bonds, equity, common stock, derivatives or debentures), or contracts were established with the unauthorized use of another's personal identifying information. ";
   		desc [9] = "Claims of false or mistaken judgments found on Consumer Credit Reports.  False or mistaken judgment entries on Consumer Credit Reports are not uncommon and are something consumers should look for when examining their report. ";
   		desc [10] = "Claims of unauthorized use of Social Security Numbers.  Identity Thieves use stolen Social Security Numbers to obtain employment, items on credit, medical benefits, utility services, home mortgages, and to start businesses.    ";
   		desc [11] = "Reports of unauthorized use or transactions on credit card account.";
   		desc [12] = "Claims of utility service accounts (gas, electricity, etc.) established with the unauthorized use of another's personal identifying information. ";
   		desc [13] = "Claims of telephone or cellular phone service accounts created with the unauthorized use of another's personal identifying information. ";
   		desc [14] = "Medicaid/Medicare, Insurance, or State Benefits.";
   		desc [14] = "Other ID theft.";
   		
	    icons[1] = "/iris/img/icons/aqua_icon.png";
	    icons[3] = "/iris/img/icons/black_icon.png";
	    icons[4] = "/iris/img/icons/blue_icon.png";
	    icons[6] = "/iris/img/icons/brown_icon.png";
	    icons[8] = "/iris/img/icons/gray_icon.png";
	    icons[9] =  "/iris/img/icons/green_icon.png";
	    icons[11] =   "/iris/img/icons/orange_icon.png";
	    icons[12] =   "/iris/img/icons/pattern_icon.png";
	    icons[13] =   "/iris/img/icons/pink_icon.png";
	    icons[14] =   "/iris/img/icons/purple_icon.png";
	    icons[15] =   "/iris/img/icons/red_icon.png";
	    icons[16] =   "/iris/img/icons/violet_icon.png";
	    icons[18] =   "/iris/img/icons/yellow_icon.png";
	    icons[2] =   "/iris/img/icons/benefit_icon.png";
	    icons[19] =   "/iris/img/icons/other_icon.png";
	
	    var map;

        dojo.require("dojo.parser"); //for digits
        dojo.require("esri.map");
        dojo.require("esri.tasks.geometry");        
        dojo.require("esri.geometry");
        dojo.require("dijit.form.FilteringSelect");
        dojo.require("dojo.data.ItemFileReadStore");
        dojo.require("dijit.ProgressBar");
		dojo.require("dijit.Tooltip");
        


        var IMAGERY = new esri.layers.ArcGISTiledMapServiceLayer("https://mapserv.utah.gov/ArcGIS/rest/services/UtahBaseMap-Vector/MapServer", { id: "Imagery", visible: true });
        
        
		/**
		* Init function used to create the map on startup
		*
		*/
        function startup() {
            map = new esri.Map("map", { slider: false, nav: false });
            map.addLayer(IMAGERY);

            // setup tooltips
            //bankFraudType
            new dijit.Tooltip({label:desc[1], connectId:["fraudTypeDesc1"]});
            new dijit.Tooltip({label:desc[2], connectId:["fraudTypeDesc2"]});
            new dijit.Tooltip({label:desc[3], connectId:["fraudTypeDesc3"]});
            new dijit.Tooltip({label:desc[4], connectId:["fraudTypeDesc4"]});
            new dijit.Tooltip({label:desc[5], connectId:["fraudTypeDesc5"]});
            new dijit.Tooltip({label:desc[6], connectId:["fraudTypeDesc6"]});
            new dijit.Tooltip({label:desc[7], connectId:["fraudTypeDesc7"]});
            new dijit.Tooltip({label:desc[8], connectId:["fraudTypeDesc8"]});
            new dijit.Tooltip({label:desc[9], connectId:["fraudTypeDesc9"]});
            new dijit.Tooltip({label:desc[10], connectId:["fraudTypeDesc10"]});
            new dijit.Tooltip({label:desc[11], connectId:["fraudTypeDesc11"]});
            new dijit.Tooltip({label:desc[12], connectId:["fraudTypeDesc12"]});
            new dijit.Tooltip({label:desc[13], connectId:["fraudTypeDesc13"]});
            new dijit.Tooltip({label:desc[14], connectId:["fraudTypeDesc14"]});
            new dijit.Tooltip({label:desc[15], connectId:["fraudTypeDesc15"]});

            new dijit.Tooltip({label:desc[1], connectId:["fraudTypeImg1"]});
            new dijit.Tooltip({label:desc[2], connectId:["fraudTypeImg2"]});
            new dijit.Tooltip({label:desc[3], connectId:["fraudTypeImg3"]});
            new dijit.Tooltip({label:desc[4], connectId:["fraudTypeImg4"]});
            new dijit.Tooltip({label:desc[5], connectId:["fraudTypeImg5"]});
            new dijit.Tooltip({label:desc[6], connectId:["fraudTypeImg6"]});
            new dijit.Tooltip({label:desc[7], connectId:["fraudTypeImg7"]});
            new dijit.Tooltip({label:desc[8], connectId:["fraudTypeImg8"]});
            new dijit.Tooltip({label:desc[9], connectId:["fraudTypeImg9"]});
            new dijit.Tooltip({label:desc[10], connectId:["fraudTypeImg10"]});
            new dijit.Tooltip({label:desc[11], connectId:["fraudTypeImg11"]});
            new dijit.Tooltip({label:desc[12], connectId:["fraudTypeImg12"]});
            new dijit.Tooltip({label:desc[13], connectId:["fraudTypeImg13"]});
            new dijit.Tooltip({label:desc[14], connectId:["fraudTypeImg14"]});
            new dijit.Tooltip({label:desc[15], connectId:["fraudTypeImg15"]});
            

        }
       

		/** 
		* clears the ghost text from a text field
		*/
        function clearGhost(field){
			if (field != null){
				field.style.color = '#000000';
				if(field.value == 'zip' || field.value == 'city' ){				
					field.value = "";
				}
			}
        }

        /**
		* shows the ghost text from a text field
		*/
        function showGhost(field){
			if (field != null){				
				if(field.value == ''){
					
					field.style.color = '#AAAAAA';
					if (field.id =='zip')				
						field.value = "zip";
					
				}
			}
        }

        /**
        * Shows
        *
        **/
        function showProgressBar(text) {
            dojo.byId("progresstext").innerHTML = text;
            var progressbar_container = dojo.byId("progressbar_container");
            progressbar_container.style.display = "block";
        }

        function hideProgressBar(){
        	
            var progressbar_container = dojo.byId("progressbar_container");
            progressbar_container.style.display = "none";
        }

        
		


		/**
		* callback function for showing the data theft points
		*
		*/
        function showDataPoints(points){

        	var alreadyShown = false;
        	var pointsArray = new Array();        	
        	
        	map.graphics.clear();        
			if (points != null && points.length > 0){
				var span = document.getElementById('searchInformation');				
				span.style.color + "#000000";
				span.innerHTML = "Found " + points.length + " results.";
				var mPoints = new esri.geometry.Multipoint(map.spatialReference);
				

				
				// count instances...just in case there are multiples
				for (var i = 0; i < points.length; ++i){
					if (!pointsArray[points[i].utmEasting + " " +points[i].utmNorthing]){
						pointsArray[points[i].utmEasting + " " +points[i].utmNorthing] = 0;
					}

					pointsArray[points[i].utmEasting + " " +points[i].utmNorthing] ++;

				}

				
				
				for (var i = 0; i < points.length; ++i){
					var pointer = new esri.geometry.Point(points[i].utmEasting,points[i].utmNorthing,map.spatialReference);
					if (pointer == null) continue;
					if (points[i].county != null && !alreadyShown){
						//showCounty(points[i].county);
						if (document.getElementById("city").value != "")
							map.centerAndZoom(pointer, 6);
						else
							map.centerAndZoom(pointer, 5);
						alreadyShown = true;
					}
					var iconLoc = icons[points[i].type.id]; 
					var sms = new esri.symbol.PictureMarkerSymbol(iconLoc, 35, 35);
					var title = "Information";

					if (pointsArray[points[i].utmEasting + " " +points[i].utmNorthing] > 1){
						title += " ("+pointsArray[points[i].utmEasting + " " +points[i].utmNorthing]+" complaints.)";
					}
					
					var tbl ="<table><tr><td>Type:</td><td>"+points[i].typeCode+"</td></tr><tr>" 
						+ "<td>Zip:</td><td>" + points[i].zip + "</td></tr><tr><td>Date</td><td>" + points[i].complaintDate + "</td></tr></table>";
					var infoTemplate = new esri.InfoTemplate(title,tbl);   
					var graphic = new esri.Graphic(pointer,sms, null, infoTemplate);
					map.graphics.add(graphic);
					mPoints.addPoint(pointer);
					
				}


				if (!alreadyShown){
					map.setLevel(2);				
				}

				hideProgressBar();
				
			}else{
				var span = document.getElementById('searchInformation');				
				span.style.color + "#FF0000";
				span.innerHTML = "<span style='color:#FF0000'>No Results Found.</span>";
				map.setLevel(2);
				hideProgressBar();
				
			}

			map.disableDoubleClickZoom();
            map.disableRubberBandZoom();
            map.disableScrollWheelZoom();
            map.disableShiftDoubleClickZoom(); 
            map.disableDoubleClickZoom();
			
        }

		/**
		* finds the data points
		*
		*/
        function findDataPoints(){

        	
        	showProgressBar("Loading Results....");
        	var zip = null, city = null, county = null;

        	county = document.getElementById("county").value;
        	city = document.getElementById("city").value;
        	zip = document.getElementById("zip").value;

        	if (city == 'city')
            	city = "";

        	if (zip == 'zip')
				zip = "";	        	
        	
            
            // type, date, city, zip
        	MapDataService.findDataPoints(document.getElementById("fraudType").value, document.getElementById("datePeriod").value, county, city, zip, showDataPoints);

        }

		/**
		* resets the filters
		*
		*/
        function resetFilters(){

        	document.getElementById("county").value = "";
        	document.getElementById("city").value = "";
        	document.getElementById("fraudType").value = "-1";
        	document.getElementById("datePeriod").value = "5";
        	showGhost(document.getElementById("zip"));        	
        	
            
        	map.graphics.clear();
        	map.setLevel(2);
        }

		/**
		* Custom Zoom button so we can limit the zoom in/out
		*
		*/
        function zoomIn(){
			if (map.getLevel() < 6){
				map.setLevel(map.getLevel() + 1);
			}
        }

        /**
		* Custom Zoom button so we can limit the zoom in/out
		*
		*/
        function zoomOut(){
        	if (map.getLevel() > 1){
				map.setLevel(map.getLevel() - 1);
			}
        }


        dojo.addOnLoad(startup);
 
    </script>
    
			
    <table style="border-bottom:1px solid #0B3570" width="100%">
    	<tr>
			<td>
				
				<ul>
					<li><b>Search Instructions</b> - Use Drop-Down Filters to search by Identity Theft Type, Date Range, County, City, or Zip Code.  To view all reports, leave Filters at default settings. Press the "Go" button to search results.</li>
				<li><b>Legend</b> - Located below the IRIS Map and describes balloon markers.</li>
				<li><b>Information Window</b> - Click a Balloon Marker for additional information.</li>
				</ul>  
			</td>
		</tr>
    </table>
    
    <table width="100%">
    	<tr>    		
    		<td>
    		<b>Filters:</b>
   			  <select id="fraudType" style="font-size:11px">
			    	<option value="-1">All Types</option>
			    	<c:forEach items="${fraudList}" var="ft">
			    		<option value="<c:out value="${ft.code}"/>"><c:out value="${ft.description}"/></option>
			    	</c:forEach>
			    </select> 
    		</td>
	   		<td>
			    <select id="datePeriod" style="font-size:11px">
			    	<option value="9">All Time</option>
			    	<option value="8">Last 36 Months</option>
			    	<option value="7">Last 24 Months</option>
			    	<option value="6">Last 18 Months</option>
			    	<option value="5">Last 12 Months</option>
			    	<option value="4">Last 6 Months</option>
			    	<option value="3">Last 30 Days</option>
			    	<option value="2">Last 14 Days</option> 
			    	<option value="1">Last 7 Days</option>
			    	<option value="0">Last 3 Days</option>
			    </select>
			</td>
			<td>
				<select id="county" style="font-size:11px">
					<option value="">All Counties</option>
					<option value="Beaver">Beaver</option>
					<option value="Box Elder">Box Elder</option>
					<option value="Cache">Cache</option>
					<option value="Carbon">Carbon</option>
					<option value="Daggett">Daggett</option>
					<option value="Davis">Davis</option>
					<option value="Duchesne">Duchesne</option>
					<option value="Emery">Emery</option>
					<option value="Garfield">Garfield</option>
					<option value="Grand">Grand</option>
					<option value="Iron">Iron</option>
					<option value="Juab">Juab</option>
					<option value="Kane">Kane</option>
					<option value="Millard">Millard</option>
					<option value="Morgan">Morgan</option>
					<option value="Piute">Piute</option>
					<option value="Rich">Rich</option>
					<option value="Salt Lake">Salt Lake</option>
					<option value="San Juan">San Juan</option>
					<option value="Sanpete">Sanpete</option>
					<option value="Sevier">Sevier</option>
					<option value="Summit">Summit</option>
					<option value="Tooele">Tooele</option>
					<option value="Uintah">Uintah</option>
					<option value="Utah">Utah</option>
					<option value="Wasatch">Wasatch</option>
					<option value="Washington">Washington</option>
					<option value="Wayne">Wayne</option>
					<option value="Weber">Weber</option>
				</select>
			</td>
			<td>
				<select id="city" style="font-size:11px">
				<option value="">All Cities</option>
				<option value="Alpine">Alpine</option>
				<option value="Alta">Alta</option>
				<option value="Altamont">Altamont</option>
				<option value="Alton">Alton</option>
				<option value="Amalga">Amalga</option>
				<option value="American Fork">American Fork</option>
				<option value="Annabella">Annabella</option>
				<option value="Antimony">Antimony</option>
				<option value="Aurora">Aurora</option>
				<option value="Axtell">Axtell</option>
				<option value="Ballard">Ballard</option>
				<option value="Bear River City">Bear River City</option>
				<option value="Beaver">Beaver</option>
				<option value="Beryl">Beryl</option>
				<option value="Bicknell">Bicknell</option>
				<option value="Big Water">Big Water</option>
				<option value="Blanding">Blanding</option>
				<option value="Bloomington">Bloomington</option>
				<option value="Bluffdale">Bluffdale</option>
				<option value="Boulder">Boulder</option>
				<option value="Bountiful">Bountiful</option>
				<option value="Brian Head">Brian Head</option>
				<option value="Brigham City">Brigham City</option>
				<option value="Cannonville">Cannonville</option>
				<option value="Canyon Rim">Canyon Rim</option>
				<option value="Castle Dale">Castle Dale</option>
				<option value="Castle Valley">Castle Valley</option>
				<option value="Cedar City">Cedar City</option>
				<option value="Cedar Fort">Cedar Fort</option>
				<option value="Cedar Hills">Cedar Hills</option>
				<option value="Centerfield">Centerfield</option>
				<option value="Centerville">Centerville</option>
				<option value="Charleston">Charleston</option>
				<option value="Circleville">Circleville</option>
				<option value="Clarkston">Clarkston</option>
				<option value="Clawson">Clawson</option>
				<option value="Clearfield">Clearfield</option>
				<option value="Cleveland">Cleveland</option>
				<option value="Clinton">Clinton</option>
				<option value="Coalville">Coalville</option>
				<option value="Corinne">Corinne</option>
				<option value="Cornish">Cornish</option>
				<option value="Cottonwood Heights">Cottonwood Heights</option>
				<option value="Cove">Cove</option>
				<option value="Delta">Delta</option>
				<option value="Deweyville">Deweyville</option>
				<option value="Draper">Draper</option>
				<option value="Duchesne">Duchesne</option>
				<option value="Dugway">Dugway</option>
				<option value="East Carbon">East Carbon</option>
				<option value="East Millcreek">East Millcreek</option>
				<option value="Elk Ridge">Elk Ridge</option>
				<option value="Elmo">Elmo</option>
				<option value="Elsinore">Elsinore</option>
				<option value="Elwood">Elwood</option>
				<option value="Emery">Emery</option>
				<option value="Enoch">Enoch</option>
				<option value="Enterprise">Enterprise</option>
				<option value="Ephraim">Ephraim</option>
				<option value="Erda">Erda</option>
				<option value="Escalante">Escalante</option>
				<option value="Eureka">Eureka</option>
				<option value="Fairview">Fairview</option>
				<option value="Farmington">Farmington</option>
				<option value="Farr West">Farr West</option>
				<option value="Fayette">Fayette</option>
				<option value="Ferron">Ferron</option>
				<option value="Fielding">Fielding</option>
				<option value="Fillmore">Fillmore</option>
				<option value="Fort Duchesne">Fort Duchesne</option>
				<option value="Fountain Green">Fountain Green</option>
				<option value="Francis">Francis</option>
				<option value="Fruit Heights">Fruit Heights</option>
				<option value="Garden City">Garden City</option>
				<option value="Garland">Garland</option>
				<option value="Genola">Genola</option>
				<option value="Glendale">Glendale</option>
				<option value="Glenwood">Glenwood</option>
				<option value="Goshen">Goshen</option>
				<option value="Granite">Granite</option>
				<option value="Grantsville">Grantsville</option>
				<option value="Green River">Green River</option>
				<option value="Gunnison">Gunnison</option>
				<option value="Hanksville">Hanksville</option>
				<option value="Harrisville">Harrisville</option>
				<option value="Hatch">Hatch</option>
				<option value="Heber City">Heber City</option>
				<option value="Helper">Helper</option>
				<option value="Henefer">Henefer</option>
				<option value="Henrieville">Henrieville</option>
				<option value="Herriman">Herriman</option>
				<option value="Hiawatha">Hiawatha</option>
				<option value="Highland">Highland</option>
				<option value="Hildale">Hildale</option>
				<option value="Hinckley">Hinckley</option>
				<option value="Holden">Holden</option>
				<option value="Holladay">Holladay</option>
				<option value="Honeyville">Honeyville</option>
				<option value="Hooper">Hooper</option>
				<option value="Howell">Howell</option>
				<option value="Huntington">Huntington</option>
				<option value="Huntsville">Huntsville</option>
				<option value="Hurricane">Hurricane</option>
				<option value="Hyde Park">Hyde Park</option>
				<option value="Hyrum">Hyrum</option>
				<option value="Ivins">Ivins</option>
				<option value="Jensen">Jensen</option>
				<option value="Joseph">Joseph</option>
				<option value="Junction">Junction</option>
				<option value="Kamas">Kamas</option>
				<option value="Kanab">Kanab</option>
				<option value="Kanarraville">Kanarraville</option>
				<option value="Kanosh">Kanosh</option>
				<option value="Kaysville">Kaysville</option>
				<option value="Kearns">Kearns</option>
				<option value="Keetley">Keetley</option>
				<option value="Kingston">Kingston</option>
				<option value="Koosharem">Koosharem</option>
				<option value="La Verkin">La Verkin</option>
				<option value="Laketown">Laketown</option>
				<option value="Layton">Layton</option>
				<option value="Leamington">Leamington</option>
				<option value="Leeds">Leeds</option>
				<option value="Lehi">Lehi</option>
				<option value="Levan">Levan</option>
				<option value="Lewiston">Lewiston</option>
				<option value="Lindon">Lindon</option>
				<option value="Loa">Loa</option>
				<option value="Logan">Logan</option>
				<option value="Lund">Lund</option>
				<option value="Lyman">Lyman</option>
				<option value="Lynndyl">Lynndyl</option>
				<option value="Maeser">Maeser</option>
				<option value="Magna">Magna</option>
				<option value="Mammoth">Mammoth</option>
				<option value="Manila">Manila</option>
				<option value="Manti">Manti</option>
				<option value="Mantua">Mantua</option>
				<option value="Mapleton">Mapleton</option>
				<option value="Marysvale">Marysvale</option>
				<option value="Mayfield">Mayfield</option>
				<option value="Meadow">Meadow</option>
				<option value="Mendon">Mendon</option>
				<option value="Mexican Hat">Mexican Hat</option>
				<option value="Midvale">Midvale</option>
				<option value="Midway">Midway</option>
				<option value="Milford">Milford</option>
				<option value="Millcreek">Millcreek</option>
				<option value="Millville">Millville</option>
				<option value="Minersville">Minersville</option>
				<option value="Moab">Moab</option>
				<option value="Modena">Modena</option>
				<option value="Mona">Mona</option>
				<option value="Monroe">Monroe</option>
				<option value="Montezuma Creek">Montezuma Creek</option>
				<option value="Monticello">Monticello</option>
				<option value="Morgan">Morgan</option>
				<option value="Moroni">Moroni</option>
				<option value="Mount Carmel">Mount Carmel</option>
				<option value="Mount Carmel Junction">Mount Carmel Junction</option>
				<option value="Mount Olympus">Mount Olympus</option>
				<option value="Mount Pleasant">Mount Pleasant</option>
				<option value="Murray">Murray</option>
				<option value="Myton">Myton</option>
				<option value="Naples">Naples</option>
				<option value="Neola">Neola</option>
				<option value="Nephi">Nephi</option>
				<option value="New Castle">New Castle</option>
				<option value="New Harmony">New Harmony</option>
				<option value="Newton">Newton</option>
				<option value="Nibley">Nibley</option>
				<option value="North Logan">North Logan</option>
				<option value="North Ogden">North Ogden</option>
				<option value="North Salt Lake">North Salt Lake</option>
				<option value="Oak City">Oak City</option>
				<option value="Oakley">Oakley</option>
				<option value="Ogden">Ogden</option>
				<option value="Ophir">Ophir</option>
				<option value="Oquirrh">Oquirrh</option>
				<option value="Orangeville">Orangeville</option>
				<option value="Orderville">Orderville</option>
				<option value="Orem">Orem</option>
				<option value="Panguitch">Panguitch</option>
				<option value="Paradise">Paradise</option>
				<option value="Paragonah">Paragonah</option>
				<option value="Park City">Park City</option>
				<option value="Parowan">Parowan</option>
				<option value="Payson">Payson</option>
				<option value="Perry">Perry</option>
				<option value="Plain City">Plain City</option>
				<option value="Pleasant Grove">Pleasant Grove</option>
				<option value="Pleasant View">Pleasant View</option>
				<option value="Plymouth">Plymouth</option>
				<option value="Portage">Portage</option>
				<option value="Price">Price</option>
				<option value="Providence">Providence</option>
				<option value="Provo">Provo</option>
				<option value="Randlett">Randlett</option>
				<option value="Randolph">Randolph</option>
				<option value="Redmond">Redmond</option>
				<option value="Richfield">Richfield</option>
				<option value="Richmond">Richmond</option>
				<option value="River Heights">River Heights</option>
				<option value="Riverdale">Riverdale</option>
				<option value="Riverton">Riverton</option>
				<option value="Rockville">Rockville</option>
				<option value="Roosevelt">Roosevelt</option>
				<option value="Roy">Roy</option>
				<option value="Rush Valley">Rush Valley</option>
				<option value="Salem">Salem</option>
				<option value="Salina">Salina</option>
				<option value="Salt Lake City">Salt Lake City</option>
				<option value="Sandy">Sandy</option>
				<option value="Santa Clara">Santa Clara</option>
				<option value="Santaquin">Santaquin</option>
				<option value="Saratoga Springs">Saratoga Springs</option>
				<option value="Scipio">Scipio</option>
				<option value="Scofield">Scofield</option>
				<option value="Sigurd">Sigurd</option>
				<option value="Smithfield">Smithfield</option>
				<option value="Snowville">Snowville</option>
				<option value="Soldier Summit">Soldier Summit</option>
				<option value="South Jordan">South Jordan</option>
				<option value="South Ogden">South Ogden</option>
				<option value="South Salt Lake">South Salt Lake</option>
				<option value="South Weber">South Weber</option>
				<option value="Spanish Fork">Spanish Fork</option>
				<option value="Spring City">Spring City</option>
				<option value="Springdale">Springdale</option>
				<option value="Springville">Springville</option>
				<option value="St. George">St. George</option>
				<option value="Stansbury Park">Stansbury Park</option>
				<option value="Sterling">Sterling</option>
				<option value="Stockton">Stockton</option>
				<option value="Summit">Summit</option>
				<option value="Sunnyside">Sunnyside</option>
				<option value="Sunset">Sunset</option>
				<option value="Syracuse">Syracuse</option>
				<option value="Tabiona">Tabiona</option>
				<option value="Taylorsville">Taylorsville</option>
				<option value="Thistle">Thistle</option>
				<option value="Tintic">Tintic</option>
				<option value="Tooele">Tooele</option>
				<option value="Topaz">Topaz</option>
				<option value="Toquerville">Toquerville</option>
				<option value="Torrey">Torrey</option>
				<option value="Tremonton">Tremonton</option>
				<option value="Trenton">Trenton</option>
				<option value="Tridell">Tridell</option>
				<option value="Tropic">Tropic</option>
				<option value="Uintah">Uintah</option>
				<option value="Union">Union</option>
				<option value="Val Verda">Val Verda</option>
				<option value="Vernal">Vernal</option>
				<option value="Vernon">Vernon</option>
				<option value="Veyo">Veyo</option>
				<option value="Vineyard">Vineyard</option>
				<option value="Virgin">Virgin</option>
				<option value="Wales">Wales</option>
				<option value="Wallsburg">Wallsburg</option>
				<option value="Washington">Washington</option>
				<option value="Washington Terrace">Washington Terrace</option>
				<option value="Wellington">Wellington</option>
				<option value="Wellsville">Wellsville</option>
				<option value="Wendover">Wendover</option>
				<option value="West Bountiful">West Bountiful</option>
				<option value="West Jordan">West Jordan</option>
				<option value="West Point">West Point</option>
				<option value="West Valley City">West Valley City</option>
				<option value="White City">White City</option>
				<option value="Whiterocks">Whiterocks</option>
				<option value="Willard">Willard</option>
				<option value="Woodland Hills">Woodland Hills</option>
				<option value="Woodruff">Woodruff</option>
				<option value="Woods Cross">Woods Cross</option>
				</select>
			</td>
			
			
    		    	    	
    		<td><input type="text" size="8" id="zip"  style="font-size:11px;color:#AAAAAA" value="zip" onblur="showGhost(this);" onfocus="clearGhost(this);"/></td>
    		<td><input type="button" value="Go!" onclick="findDataPoints()" style="font-size:11px;"/></td>
    		<td><input type="button" value="Reset" onclick="resetFilters();" style="font-size:11px;"/></td>
    		<td align="right" width="20%"><span id="searchInformation"></span></td>
    	</tr>
    </table>
       
   
 	<div id="map" class="tundra"></div>
 	

	<table width="100%">
		<tr>
			<td align="center" colspan="8" style="border-bottom: 1px solid #000000">
				<input type="button" value="Zoom In" onclick="zoomIn()"/>
				<input type="button" value="Zoom Out" onclick="zoomOut()"/>
			</td>					
			
			
		</tr>
		<tr>
			<td id="fraudTypeImg1"><img src="/iris/img/icons/aqua_icon.png" /></td>					
			<td id="fraudTypeDesc1"><span >Bank Withdrawal</span></td>
			
			<td id="fraudTypeImg2"><img src="/iris/img/icons/black_icon.png" /></td>
			<td><span id="fraudTypeDesc2">Collection Agencies</span></td>
			
			<td id="fraudTypeImg3"><img src="/iris/img/icons/blue_icon.png" /></td>
			<td><span id="fraudTypeDesc3">Consumer Credit Report</span></td>
			
			<td id="fraudTypeImg4"><img src="/iris/img/icons/brown_icon.png" /></td>
			<td><span id="fraudTypeDesc4">Email/Phishing</span></td>
			
		</tr>
		<tr>
			
			<td id="fraudTypeImg5"><img src="/iris/img/icons/gray_icon.png" /></td>
			<td><span id="fraudTypeDesc5">Government Benefits</span></td>
			
			<td id="fraudTypeImg6"><img src="/iris/img/icons/green_icon.png" /></td>
			<td><span id="fraudTypeDesc6">ID Lost or Stolen</span></td>
			
			<td id="fraudTypeImg7"><img src="/iris/img/icons/orange_icon.png" /></td>
			<td><span id="fraudTypeDesc7">Unauthorized Loan</span></td>
			
			<td id="fraudTypeImg8"><img src="/iris/img/icons/pattern_icon.png" /></td>
			<td><span id="fraudTypeDesc8">Securities Fraud</span></td>
		</tr>
		<tr>
			
			<td id="fraudTypeImg9"><img src="/iris/img/icons/pink_icon.png" /></td>
			<td><span id="fraudTypeDesc9">Suit or Judgment</span></td>				
			
			<td id="fraudTypeImg10"><img src="/iris/img/icons/purple_icon.png" /></td>
			<td><span id="fraudTypeDesc10">Social Security Number</span></td>
			
			<td id="fraudTypeImg11"><img src="/iris/img/icons/red_icon.png" /></td>
			<td><span id="fraudTypeDesc11">Unauthorized Credit Card</span></td>
			
			<td id="fraudTypeImg12"><img src="/iris/img/icons/violet_icon.png" /></td>
			<td><span id="fraudTypeDesc12">Utilities Theft</span></td>
		</tr>
		<tr>
			
			<td id="fraudTypeImg13"><img src="/iris/img/icons/yellow_icon.png" /></td>
			<td><span id="fraudTypeDesc13">Telephone Service Theft</span></td>
			
			<td id="fraudTypeImg14"><img src="/iris/img/icons/benefit_icon.png" /></td>
			<td><span id="fraudTypeDesc14">Benefit</span></td>
			
			<td id="fraudTypeImg15"><img src="/iris/img/icons/other_icon.png" /></td>
			<td><span id="fraudTypeDesc15">Other</span></td>
			
			<td></td>
			<td></td>
		</tr>
	
	
	</table>

<!-- legend>Zoom to County:</legend>
    <table>
               <tr>
            <td>

                <input dojotype="dijit.form.FilteringSelect" searchattr="Value"
                    id="ddl_county" name="ddl_county" autocomplete="false" hasdownarrow="false" />
            </td>
        </tr>
        <tr>
            <td>
                <button id="btn_county" dojotype="dijit.form.Button" onclick="Zoom()">
                    Zoom To County</button>
            </td>

        </tr>
    </table-->
    <div id="progressbar_container" style="display: none;">
        <h5>
            <b id="progresstext">Running Analysis...</b></h5>
        <div dojotype="dijit.ProgressBar" jsid="jsProgress" id="progressbar" indeterminate="true">
        </div>
    </div>
    
