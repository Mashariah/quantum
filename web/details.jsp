<%-- 
    Document   : details
    Created on : Jun 1, 2015, 12:30:29 PM
    Author     : kelli
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="domain.Vehicle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Allexis | Vehicle Details</title>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
        <link rel="stylesheet" type="text/css" href="style/jquery-ui.css"/>
         <link rel="stylesheet" type="text/css" href="style/jquery.timepicker.css"/>
  
  <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
  <script src="js/jquery-ui.js"  type="text/javascript"/></script>
    <script src="js/jquery.timepicker.js"  type="text/javascript"/></script>
<script src="js/simplegallery.js"  type="text/javascript"/></script>
<script>
            
        $(document).ready(function(){
	$('#img-gallery').simplegallery({
	  galltime : 400, // transition delay
	  gallcontent: '.content',
	  gallthumbnail: '.thumbnail',
	  gallthumb: '.thumb'
	});
	});
</script>
    </head>
    <body>
        <style>
        .ui-datepicker {font-size: 9pt !important}
        .ui-timepicker-wrapper{font-size: 9pt;}
        </style>
        <div class="header"></div>
        <%@include file="templates/navigation.jsp" %>
            <%
            //get the vehicle id selected and compare to list in session.
                int vId =(Integer)request.getAttribute("vehicleId");
//                int  vId = Integer.parseInt(id);
                ArrayList <Vehicle> vehicles = (ArrayList)request.getSession().getAttribute("vehicles");
                for(Vehicle v:vehicles){
                    if(v.getVehicleId()==vId){
                        //create the currently selected vehicle as an attribute ...
                        getServletContext().setAttribute("target_vehicle", v);
                    }
                }
            %>
        <section class="simplegallery" id="img-gallery">
            <div class="content">
                <img src="fileserver?param1=${target_vehicle.detailImg}" class="image_0"/>
            <img src="fileserver?param1=${target_vehicle.detailImg}" class="image_1" style="display: none;" alt=""/>
            <img src="fileserver?param1=${target_vehicle.thumbnail1Img}" class="image_2" style="display: none;" alt=""/>
            <img src="fileserver?param1=${target_vehicle.thumbnail2Img}" class="image_3" style="display: none;" alt=""/>
            </div>
            <div class="clear"></div>
            <div class="thumbnail">
                <div class="thumb">
                    <a href="#" rel="0">
                        <img src="fileserver?param1=${target_vehicle.detailImg}" id="thumb_1"></a>
                </div>
                <div class="thumb">
                    <a href="#" rel="1">
                        <img src="fileserver?param1=${target_vehicle.thumbnail1Img}" id="thumb_2"></a>
                </div>
                <div class="thumb last">
                    <a href="#" rel="2"><img src="fileserver?param1=${target_vehicle.thumbnail2Img}" id="thumb_3"></a>
                </div>
            </div>
        </section>
                <!--<div class="clear"></div>-->
        <div class="details-panel-description">
            <strong>${target_vehicle.make} ${target_vehicle.model} Details</strong>
            <hr>
            <!--Get the id of the selected vehicle and retrieve its details from the vehicle list-->
            <table>
                <tbody>
                    <tr><td><h4>Fuel Consumption</h4></td><td>${description.fuelConsumption} Litres per Kilometer</td></tr>
                    <tr><td><h4>Fuel Capacity</h4></td><td>${description.fuelCapacity} Litres</td></tr>
                    <tr><td><h4>Transmission</h4></td><td>${description.transmission}</td></tr>
                    <tr><td><h4>Seating Capacity</h4></td><td>${description.seatingCapacity}</td></tr>
                    <tr><td><h4>Convinience</h4></td><td>${description.convinience}</td></tr>
                    <tr><td><h4>Safety & Security</h4></td><td>${description.safetyAndSecurity}</td></tr>
                    <tr><td><h4>Entertainment</h4></td><td>${description.entertainment}</td></tr>
                    <tr><td><h4>Telematics</h4></td><td>${description.telematics}</td></tr>
                    <tr><td><h4>Tire & Wheels</h4></td><td>${description.tireWheels}</td></tr>
                </tbody>
            </table>
           </div>
        <div class="fee-calculator-panel">
            <div class="fee-calculator-panel-bar"><h4>Charges Calculator: ${target_vehicle.make} ${target_vehicle.model}</h4></div>
            <!--<form >-->
                
                <table class="booking">
                <tbody>
<!--                    <tr><td><h4>Hourly: </h4>${target_vehicle.rateModel.hourlyCharge}</td>
                        <td><h4>Daily  (8 hours)</h4>${target_vehicle.rateModel.dailyCharge}</td>
                        <td><h4>Weekly (7 days) </h4>${target_vehicle.rateModel.weeklyCharge}</td></tr>
                    --><tr>
                        <td >Select rate type</td>
                        <td colspan="4">
                            <select name="calcOption" id="calcOption">
                                <option value="hourly">Hourly ${target_vehicle.rateModel.hourlyCharge}</option>
                                <option  value="daily">Daily ${target_vehicle.rateModel.dailyCharge}</option>
                                <option  value="weekly">Weekly:  ${target_vehicle.rateModel.weeklyCharge}</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Pickup Date</td>
                        <td><input type="text" id="datepickerFrom" name="tx_dateFrom"/></td>
                        <td>Time</td>
                        <td><input type="text" id="timerFrom" name="tx_timeFrom"/></td>
                    </tr>
                    <tr>
                        <td>Return Date</td>
                        <td><input type="text" id="datepickerTo" name="tx_dateTo"/></td>
                        <td>Time</td>
                        <td><input type="text" id="timerTo" name="tx_timeTo"/></td>
                    </tr>
                    <tr><td colspan="4"><button id="calcCharges" onclick="calculateCharges()">Get Charges</button></td></tr>
                    <tr>
                        <td id="td_duration" style="text-align: left;" colspan="2"><h5>Duration: </h5></td>
                        <td id="td_amount" colspan="2"><h5>Amount:&nbsp;&nbsp;</h5></td></td>
                    </tr>
                    <tr>
                    </tr>
                    <tr><td colspan="4">
                            <a href="booking.jsp"><button id="back" >Book Now</button></a>
                            <a href="catalog"><button id="back" type="submit" value="catalog" >Back</button></a></td></tr>
                </tbody>
            </table>
            <!--</form>-->
        </div>
        <%@include file="templates/footer.html" %>
    </body>
     <script>
        $(document).ready(function() {
            $("#datepickerFrom").datepicker();
            $("#datepickerTo").datepicker();
            $("#timerFrom").timepicker();
            $("#timerTo").timepicker();
            console.log("done good");
            
            //check for booked vehicles and disable booking functionality
            
        });

//
        function calculateCharges(){
            console.log("entered method...")
            //encode any special characters in the date values..
            var dateFrom = encodeURIComponent(document.getElementById("datepickerFrom").value);
            var dateTo= encodeURIComponent(document.getElementById("datepickerTo").value);
            var timerFrom = encodeURIComponent(document.getElementById("timerFrom").value);
            var timerTo = encodeURIComponent(document.getElementById("timerTo").value);
            var calcOption = encodeURIComponent(document.getElementById("calcOption").value);
                        console.log("dateForm =:"+dateFrom);
                        console.log("dateTo =:"+dateTo);
                        console.log("timerFrom =:"+timerFrom);
                        console.log("timerTo =:"+timerTo);
            var url = "charges?dateFrom="+dateFrom+"&dateTo="+dateTo+"&timerFrom="+timerFrom+"&timerTo="+timerTo
            +"&calcOption="+calcOption+"&hCharge="+${target_vehicle.rateModel.hourlyCharge}+"&dCharge="+${target_vehicle.rateModel.dailyCharge}
    +"&wCharge="+${target_vehicle.rateModel.weeklyCharge};
                        console.log("url =:"+url);
                //create XMLHTTP request object... for IE7+, Chrome, Firefox, Opera 
                if (window.XMLHttpRequest) {
                    xmlHttpRequest = new XMLHttpRequest();
                        console.log("created xmlhttprequest obj");
                }
                //.... for IE5 and IE6
                else {
                    xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                }
               
               //at every state change of xhr...
                xmlHttpRequest.onreadystatechange = function() {
                //get the current state of the xhr object i.e. 0,1,2,3,4
                    if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
                        var response = xmlHttpRequest.responseText; //server results as palin text
                        console.log("response="+response);
                        var jsResponseObj = JSON.parse(response);
                        document.getElementById("td_duration").innerHTML=
                                "<h5>Duration:&nbsp;&nbsp;&nbsp;&nbsp;"+jsResponseObj.duration+" "+jsResponseObj.indicator+"</h5>";
                        document.getElementById("td_amount").innerHTML=
                                "<h5>Amount: Ksh&nbsp;&nbsp;&nbsp;&nbsp;"+jsResponseObj.amount+"</h5>";

                    }
                };
//                xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlHttpRequest.open("GET", url, true); //send using post to servlet charges
                xmlHttpRequest.send(null);                        
                        
            }

    </script>
</html>
