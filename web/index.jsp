<%-- 
    Document   : index
    Created on : May 28, 2015, 1:37:38 PM
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
        <script src="js/jquery.bxslider.min.js"></script>
        <link href="style/jquery.bxslider.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>

        <script>
            $(document).ready(function() {
                $('.bxslider').bxSlider({
                    auto: true,
                    captions:true,
                    autoControls: true
            });
        });
        </script>
    </head>
    <body>
        <div class="header"></div>
        <%@include file="templates/navigation.jsp" %>
        <div id="slider-content">
            <ul class="bxslider">
                <li><img src="resc/images/audi1.png" title="Go to town on the town
With its nimble performance and versatile cargo carrying capability, the Audi Q3 can take you on new adventures all over town. Available quattro® all-wheel drive means its great for getting out of it too."/></li>
                <li><img src="resc/images/xf2015_coupe.png" title="The XJ redefines what a luxury car should be. A dramatic combination of beauty, luxury and power, the XJ delivers a refreshingly dynamic driving experience. For the passenger, the cabin is built for stretching out and relaxing. No car looks, or feels, like the XJ."/></li>
                <li><img src="resc/images/audi_Q7.png" title="A luxurious performance for seven
The Q7 elevates luxury and capability to a new level. With three rows of seating, a refined interior and impressive power options, the Q7 delivers a stunning performance for a party of seven."/></li>
                <li><img src="resc/images/d_jaguar_fpace.jpg" title="This, the second-generation Jaguar XF, is the latest in a storm of new models 
                         from Jaguar Land Rover, joining the recently launched smaller XE saloon 
                         and beating the upcoming F-Pace SUV to market. "/></li>
                <li><img src="resc/images/audi_A3.png" title="This, the second-generation Jaguar XF, is the latest in a storm of new models 
                         from Jaguar Land Rover, joining the recently launched smaller XE saloon 
                         and beating the upcoming F-Pace SUV to market. "/></li>
            </ul>
        </div>
        <div id="slider-description">
            <div id="landing-search">
                <form method="get" action="search">
                    <input type="text" name="tx_search" placeholder="Search car model"/>
                    <!--<input type="submit" value="Search"/>-->
                    <button>Search</button>
                </form>
            </div>
            <p>
                Choose from our collection of luxury vehicles, we'get something to go with your style from the Audi 
                RS, BMW V8 to Ford X5. We assure you that whether its a business meeting or a date, you will 
                arrive in style and make an impression.<br>As a member you get to enjoy our great discount rates and 
                deals.</p>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        </div>
        
        <%@include file="templates/footer.html" %>

    </body>
</html>