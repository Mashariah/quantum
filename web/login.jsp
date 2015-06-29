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
        <title>Allexus | Login</title>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
        <script type="text/javascript">
            
            $('#loginform').validate(
      {
        rules:  {
          tx_user:  { required: true },
          tx_password: { required: true}
        },
        messages: {
          tx_user { required: "Enter username or email" },
          tx_password:  { required: "Your password is required."}
        }
      });   
//      $('#myemailform').validate( { rules:{ }, messages:{ } } );
        </script>
    </head>
    <body>
        <div class="header"><h1></h1></div>
        <%@include file="templates/navigation.jsp" %>
        <div id="confirmation-bg">
            <div class="login_panel">
            <form action="auth" method="POST" id="loginform">
                <div class="title_bar"> Login</div>
                <div style="color: #ff0000; font-size: small; font-family: sans-serif; margin:3px;">${error}</div>
                <input type="text" class="text_box" name="tx_user" id="tx_user" 
                       placeholder="Email Address or User Name" required="true"/>
                <input type="password" class="text_box" name="tx_password" id="tx_password" 
                       placeholder="Password" required="true"/>
                <input  type="submit" class="btn"  id="btn_login" value="Login"/>
            </form>
        </div>
        </div>
       <%@include file="templates/footer.html" %>
    </body>
</html>
