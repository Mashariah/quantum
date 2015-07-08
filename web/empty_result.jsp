<%-- 
    Document   : empty_result
    Created on : Jul 4, 2015, 11:36:27 AM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div id="empty-err-msg">
            <c:out value="${message}"></c:out>
            <a href="catalog" id="back_btn">Back</a>
            </div>
        </div>
        <%@include file="templates/footer.html" %>
    </body>

</html>

