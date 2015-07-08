<%-- 
    Document   : mybookings
    Created on : Jun 19, 2015, 4:57:03 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Allexis | My Bookings</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    
    <body>
         <div class="header"></div>
        <%@include file="templates/navigation.jsp" %>
        <div id="confirmation-bg" style="padding-top: 10px;">
            <div class="booking-img-pane">
                <img src="fileserver?param1=${user_booking.vehicle.teaserImg}" width="300px" height="200px"/>
            </div>
        <table class="booking">
            <tbody>
                <tr>
                    <td><h3>Your Booking History</h3></td>
                </tr>
                <tr>
                    <td>
                        Vehicle Type: <strong>${user_booking.vehicle.make},  ${user_booking.vehicle.model}</strong><br>
                        Registration:   ${user_booking.vehicle.registrationNumber}<br>
                        Pickup Date: ${user_booking.booking.dtPickup}<br>
                        Return Date: ${user_booking.booking.dtDropoff}<br>
                    </td>
                </tr>
            </tbody>
        </table>
                    <div class="spacer"></div>
                        <a href="cancel_booking" id="bk_cancel_btn">cancel booking</a>
        </div>
                <%@include file="templates/footer.html" %>
    </body>
    
</html>
