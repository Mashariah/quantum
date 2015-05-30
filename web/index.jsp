<%-- 
    Document   : index
    Created on : May 28, 2015, 1:37:38 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Allexi | Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/jquery.bxslider.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
        <link href="style/jquery.bxslider.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="header"><h1>allexi.com</h1></div>
        <%@include file="templates/navigation.html" %>
        <div id="slider-content">
            <ul class="bxslider">
                <li><img src="resc/images/audi1.png"</li>
                <li><img src="resc/images/audi_A3.png"</li>
                <li><img src="resc/images/audi_A4.png"</li>
                <li><img src="resc/images/audi_C.png"</li>
                <li><img src="resc/images/audi_Q5.png"</li>
                <li><img src="resc/images/audi_Q7.png"</li>
                <li><img src="resc/images/audi_RS.png"</li>
                <li><img src="resc/images/audi_e-tron.png"</li>
            </ul>
        </div>
        <div id="slider-description">
            <p>Choose from our collection of luxury vehicles, we'get something to go with your style from the Audi 
                RS, BMW V8 to Ford X5. We assure you that whether its a business meeting or a date, you will 
                arrive in style and make an impression.<br>As a member you get to enjoy our great discount rates and 
            deals.</p>
            <button class="btn"><span><a href="">Login</a></span></button>
            <button class="btn"><span><a href="">Sign Up</a></span></button>
        </div>
        <%@include file="templates/footer.html" %>
    </body>
    <script>
        $(document).ready(function (){
            $('.bxslider').bxSlider();
        });
    </script>
</html>