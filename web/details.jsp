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
            <div class="fee-calculator-panel-bar"><h3>Charges Calculator: ${target_vehicle.make} ${target_vehicle.model}</h3></div>
            <form name="" action="rers">
                
                <table class="booking">
                <tbody>
                    <tr>
                        <td >Select rate type</td>
                        <td colspan="2"><select>
                                <option>Hourly (ksh. 700)</option>
                                <option>Daily(ksh. 4500)</option>
                                <option>Weekly (ksh. 12,700)</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Pickup Date</td>
                        <td><input type="text" id="datepickerFrom" name="txFrom"/></td>
                        <td>Time</td>
                        <td><input type="text" id="timerFrom" name="txFrom"/></td>
                    </tr>
                    <tr>
                        <td>Return Date</td>
                        <td><input type="text" id="datepickerTo" name="txTo"/></td>
                        <td>Time</td>
                        <td><input type="text" id="timerTo" name="txFrom"/></td>
                    </tr>
                    <tr><td colspan="4"><button id="calcCharges">Get Charges</button></td></tr>
                    <tr>
                        <td colspan="4" style="text-align: right;"><h4>Duration: </h4></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align: right;"><h4>Amount:</h4></td></td>
                    </tr>
                    <tr><td colspan="4"><button id="back" >Book Now</button>
                    <button id="back" >Back</button></td></tr>
                </tbody>
            </table>
                </form>
        </div>
        <%@include file="templates/footer.html" %>
    </body>
     <script>
        $(document).ready(function() {
            $("#datepickerFrom").datepicker();
            $("#datepickerTo").datepicker();
            $("#timerFrom").timepicker();
            $("#timerTo").timepicker();
            
        });
    </script>
</html>
