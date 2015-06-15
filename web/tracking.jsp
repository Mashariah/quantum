<%-- 
    Document   : tracking
    Created on : Jun 13, 2015, 6:13:06 PM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Allexis Admin | Tracking</title>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
        <script src="js/jquery-1.11.3.min.js"></script>
         <script src="https://maps.googleapis.com/maps/api/js"></script>
             <script>
                 function initialize(){
                     
                     var myLatlng = new google.maps.LatLng(-1.292066, 36.821946);
                     //create a new google maps object using the target div as an argument
                     var mapCanvas = document.getElementById('tracking-panel');
                     //set options for the map e.g zoom leve,type and center
                     var mapOptions = {
                         center:  new google.maps.LatLng(-1.292066, 36.821946),
                         zoom:17,
                         mapTypeId:google.maps.MapTypeId.ROADMAP
                     }
                     var map = new google.maps.Map(mapCanvas, mapOptions);
                     
                     var marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      title: 'KBX 345!'
  });
//                     var marker = new google.maps.Marker({
//                         postion: -1.292066, 36.821946,
//                         map: map,
//                         title'KBZ 433'
//                     });
                 }
                 google.maps.event.addDomListener(window,'load',initialize);
             </script>
             <script>
                 //get details on selected vehicle using ajax
                 function loadSelected(){
                     
                     //get details from the list
                     
                 }
             </script>
    </head>
    <body>
        <div class="header"></div>
         <%@include file="templates/navigation.jsp" %>
         <%@include  file="templates/dashboard_menu.jsp" %>
         <div id="tracking-map-canvas">
         </div>
         <div id="tracking-panel">
             <table class="booking">
                 <tbody>
                     <tr>
                                 <td>
                                     <select style="width:160px;">
                                         <c:forEach items="${trackingDetails}" var="current">
                                                
                                             <option value="${current.vehicle.registrationNumber}" onselect="loadselected();">${current.vehicle.registrationNumber}</option>
                                         </c:forEach>
                             </select>
                                 </td>
                                 <td>
                                     
                                 </td>
                     </tr>
                 </tbody>
             </table>
         </div>
                  <%@include file="templates/footer.html" %>
    </body>
</html>
