<%-- 
    Document   : vehicle_listing
    Created on : Jun 13, 2015, 6:45:17 PM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Allexis Admin | Vehicles List</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
        <div class="header"></div>
         <%@include file="templates/navigation.jsp" %>
         <%@include  file="templates/dashboard_menu.jsp" %>
         <table class="booking" style="width: 76%; background:#ffffff; margin-left:10px; padding: 5px;">
                <tbody>
                    <tr ><td colspan="3"><h3>Vehicles Listing</h3></td></tr>
                <c:forEach items ="${vehicles}" var="vehicle">
                    <tr>
                        <td> <img src="fileserver?param1=${vehicle.thumbnail1Img}" height="70px" width="90px"/></td>
                        <td>Registration: ${vehicle.registrationNumber}<br>Make: ${vehicle.make}<br>Model: ${vehicle.model}<br>Color: ${vehicle.color}</td></td>
                        <td><strong>Rates</strong> <br>Hourly: ${vehicle.rateModel.hourlyCharge}<br>Daily: ${vehicle.rateModel.dailyCharge}<br>Weekly: ${vehicle.rateModel.weeklyCharge}</td></td>
                        <td>
                            <a href="">view details</a><br>
                            <a href="">edit</a><br>
                            <a href="">delete</a><br>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
         </table>
                  <%@include file="templates/footer.html" %>
    </body>
</html>
