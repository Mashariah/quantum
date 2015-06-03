<%-- 
    Document   : login
    Created on : May 28, 2015, 10:45:29 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
        <div class="header"><h1></h1></div>
        <%@include file="templates/navigation.html" %>
            <div class="login_panel">
            <form action="auth" method="POST">
                <div class="title_bar"> Login</div>
                <div style="color: #ff0000; font-size: small; font-family: sans-serif; margin:3px;">${error}</div>
                <input type="text" class="text_box" name="tx_user" placeholder="email address"/>
                <input type="password" class="text_box" name="tx_password" placeholder="password"/>
                <input  type="submit" class="btn"  id="btn_login" value="Login"/>
            </form>
        </div>
       <%@include file="templates/footer.html" %>
    </body>
</html>
