<%-- 
    Document   : booking_details
    Created on : Jun 28, 2015, 3:43:01 PM
    Author     : kelli
--%>

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
        <div class="booking-panel-details">
            <img src="fileserver?param1=${target.vehicle.teaserImg}" width="200" height="150"/>
        <table id="booking_details" >
            <tr><td>Car Id: ${target.vehicle.registrationNumber}  ${target.vehicle.make}&nbsp&nbsp&nbsp&nbsp${target.vehicle.model}</td></tr>
            <tr><td>Booked By: &nbsp;&nbsp;&nbsp;${target.user.fName} ${target.user.lName} </td></tr>
            <tr><td>PickUp:  &nbsp;&nbsp;&nbsp;${target.booking.dtPickup}</td></tr>
            <tr><td>Dropoff: &nbsp;&nbsp;&nbsp;${target.booking.dtDropoff}</td></tr>
            <tr><td>Pickup Location:&nbsp;&nbsp;&nbsp;${target.booking.pLocation}</td></tr>
            <tr><td>Return Location:&nbsp;&nbsp;&nbsp;${target.booking.dLocation}</td></tr>
            <tr><td><a href="tracklist?id=bookings">back</a>&nbsp;&nbsp;&nbsp;
            <a href="booking_cancel">clear booking</a></td></tr>
        </table>
        </div>
        </div>
        <%@include file="templates/footer.html" %>
    </body>
