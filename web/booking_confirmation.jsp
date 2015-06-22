<%-- 
    Document   : booking_confirmation
    Created on : Jun 18, 2015, 8:09:24 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Allexis | Booking Confirmation</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    
    <body>
         <div class="header"></div>
        <%@include file="templates/navigation.jsp" %>
        <div id="confirmation-bg">
        <div id="confirmation-box">
            Your booking request has been sent. Please check your email to complete the booking. Thank you for 
            choosing Allexus.
        </div>
        </div>
                <%@include file="templates/footer.html" %>
    </body>
    
</html>
