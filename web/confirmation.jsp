<%-- 
    Document   : confirmation
    Created on : Jun 10, 2015, 8:17:01 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Allexis | Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    
    <body>
         <div class="header"></div>
        <%@include file="templates/navigation.jsp" %>
        <p style="height: 200px; background: #ffffff;">
            ${new_user}, your account has successfully been created. Click on the link below to log in.<br>
            <a href="${activation_url}">${activation_url}</a>
    </p>
                <%@include file="templates/footer.html" %>
    </body>
    
</html>
