<%-- 
    Document   : bookings
    Created on : Jun 13, 2015, 6:24:57 PM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Allexis Admin | Booking List</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
        <div class="header"></div>
         <%@include file="templates/navigation.jsp" %>
         <%@include  file="templates/dashboard_menu.jsp" %>

         <table class="booking" id="booking-list">
                 <thead>
                 <td id="tbl-heading">Vehicle</td>
                 <td id="tbl-heading">Booked By</td>
                 <td id="tbl-heading">From Date</td>
                 <td id="tbl-heading">To Date</td>
                 </thead>
                 <tbody>
                                         <c:forEach items="${trackingDetails}" var="current">
                                             <tr>
                                                 <td>${current.vehicle.registrationNumber} ${current.vehicle.make}, ${current.vehicle.model}</td>
                                                 <td>${current.user.fName} ${current.user.lName}</td>
                                                 <!--<td>${current.user.email}</td>-->
                                                 <!--<td>${current.user.phone}</td>-->
                                                 <td>${current.booking.dtPickup}</td>
                                                 <td>${current.booking.dtDropoff}</td>
                                                 <td>${current.booking.bookingId}</td>
                                                 <td><a href="booking_details?item=${current.vehicle.vehicleId}">details</a></td>
                                             </tr>
                                         </c:forEach>
                 </tbody>
             </table>
                  <%@include file="templates/footer.html" %>
    </body>
</html>

