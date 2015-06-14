<%-- 
    Document   : dashboard
    Created on : Jun 11, 2015, 2:19:32 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Allexis | Admin Dashboard</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
                <div class="header"></div>
                <%@include file="templates/navigation.jsp" %>
                 <%@include  file="templates/dashboard_menu.jsp" %>
                <div class="dashboard-workspace">
                    current item here
                         some text
                </div>
         <%@include file="templates/footer.html" %>
    </body>
</html>
