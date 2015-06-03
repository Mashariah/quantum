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
    </head>
    <body>
        <div class="header"></div>
        <%@include file="templates/navigation.html" %>
            <%
                int  vId = Integer.parseInt(request.getParameter("vehicle_id"));
                ArrayList <Vehicle> vehicles = (ArrayList)request.getSession().getAttribute("vehicles");
                for(Vehicle v:vehicles){
                    if(v.getVehicleId()==vId){
                        //create this vehicle as an attribute ...
                        getServletContext().setAttribute("target_vehicle", v);
                    }
                }
            %>
        <div class="details-panel">
            <img src="fileserver?param1=${target_vehicle.imageFiles[1]}">
        </div>
        <div class="details-panel-description">
            <button type="button">Features</button>
            <button type="button">Security</button>
            <button type="button">Power</button>
            <hr>
            <!--Get the id of the selected vehicle and retrieve its details from the vehicle list-->
            
            <p>${target_vehicle.features}</p>
            <p>lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum
            lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum
            lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsu</p>
        </div>
            <div class="buttons-panel">
                <button type="button" id="btn_book">Book Now</button>
                <button type="button" id="btn_rates">Calculate Rates</button>
            </div>
        <%@include file="templates/footer.html" %>
    </body>
    <script>
        $(document).ready(function (){
        });
    </script>
</html>
