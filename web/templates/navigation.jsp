<%-- 
    Document   : navigation
    Created on : Jun 9, 2015, 8:49:32 PM
    Author     : kelli
--%>

<%@page import="domain.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../style/navigation.css"/>
    </head>
    <body>
        <div class="menu">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="catalog">Vehicles</a></li>
                <!--<li><a href="booking.jsp">Booking</a></li>-->
                 <%
                        User user = (User)request.getSession().getAttribute("user");
                    if(user!=null && user.getType().equals("admin")){
                        out.print("<li><a href=\"vlisting\">Admin Dashboard</a></li>");
                    }else if(user!=null && user.getType().equals("member")){
                        out.print("<li><a href=\"mybooking\">My Bookings</a></li>");
                    }else{
                        out.print("<li><a href=\"login\">My Bookings</a></li>");
                    }
                    %>
                

                <li id="login_anchor">
                    <a id="a_login" href="login">
                <%
                        User user_ = (User)request.getSession().getAttribute("user");
                    if(user_!=null){
                        out.print(user_.getEmail());
                        out.print("     <a href=\"logout\">logout</a>");
                    }else{
                        out.print("Login"); //link text
                    }
                    %>
                    </a></li>
            </ul>
        </div>
    </body>
</html>
