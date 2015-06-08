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
    </head>
    <body>
        <style>
            .ui-datepicker {font-size: 9pt !important}
        </style>
        <div class="header"></div>
        <%@include file="templates/navigation.html" %>
            <%
                int vId =(Integer)request.getAttribute("vehicleId");
//                int  vId = Integer.parseInt(id);
                ArrayList <Vehicle> vehicles = (ArrayList)request.getSession().getAttribute("vehicles");
                for(Vehicle v:vehicles){
                    if(v.getVehicleId()==vId){
                        //create this vehicle as an attribute ...
                        getServletContext().setAttribute("target_vehicle", v);
                    }
                }
            %>
        <div class="details-panel">
            <img src="fileserver?param1=${target_vehicle.detailImg}">
            <img src="fileserver?param1=${target_vehicle.thumbnail1Img}">
        </div>
        <div class="details-panel-description">
            <strong>${target_vehicle.make} ${target_vehicle.model}</strong>
            <hr>
            <!--Get the id of the selected vehicle and retrieve its details from the vehicle list-->
            
            <!--<p></p>-->
            <table>
                <tbody>
                    <tr><td><h4>Fuel Consumption</h4></td><td>${description.fuelConsumption}</td></tr>
                    <tr><td><h4>Fuel Capacity</h4></td><td>${description.fuelCapacity}</td></tr>
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
                    <tr><td><h4>Hourly: </h4>${target_vehicle.rateModel.hourlyCharge}</td>
                        <td><h4>Daily: </h4>${target_vehicle.rateModel.dailyCharge}</td>
                        <td><h4>Weekly: </h4>${target_vehicle.rateModel.weeklyCharge}</td></tr>
                    <tr>
                        <td >Select rate type</td>
                        <td colspan="2"><select name="calcOption" id="calcOption">
                                <option value="hourly">Hourly ${target_vehicle.rateModel.hourlyCharge}</option>
                                <option  value="daily">Daily: ${target_vehicle.rateModel.dailyCharge}</option>
                                <option  value="weekly">Weekly: ${target_vehicle.rateModel.weeklyCharge}</option>
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
                        <td id="td_duration"colspan="4" style="text-align: right;"><h4>Duration: </h4></td>
                    </tr>
                    <tr>
                        <td id="td_amount" colspan="4" style="text-align: right;"><h4>Amount:</h4></td></td>
                    </tr>
                    <tr><td colspan="4"><a href="booking"><button id="back" >Book Now</button></a>
                            <button id="back" type="submit" value="catalog" >Back</button></td></tr>
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
                //when ze response arrives...
                xmlHttpRequest.onreadystatechange = function() {
                    if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
                        var response = xmlHttpRequest.responseText; //server results
                        console.log("response="+response);
                        var jsResponseObj = JSON.parse(response);
                        document.getElementById("td_duration").innerHTML=
                                "<h5>Duration: "+jsResponseObj.duration+" "+jsResponseObj.indicator+"</h5>";
                        document.getElementById("td_amount").innerHTML=
                                "<h5>Amount: Ksh "+jsResponseObj.amount+"</h5>";

                    }
                }
//                xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlHttpRequest.open("GET", url, true); //send using post to servlet charges
                xmlHttpRequest.send(null);                        
                        
            }
        
    </script>
</html>
