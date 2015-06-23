<%-- 
    Document   : dashboard_menu
    Created on : Jun 13, 2015, 10:53:34 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title></title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/dashboard.css"/>
    </head>
    <body>
        <div class="dashboard-menu-panel">
            <ul>
                <li><a href="">Vehicles</a>
                    <ul>
                        <li><a href="vlisting">Listing</a></li>
                        <li><a href="newcar.jsp">Add New</a></li>
                    </ul>
                </li>
                <li><a href="tracklist?id=bookings">Booking</a></li>
                <li><a href="tracking.jsp">Tracking</a></li>
            </ul>
        </div>
    </body>
</html>