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
            counter = 0;
            var map;
            var marker;
            var infoWindow;
            var image = 'resc/icons/gmaps1ico.png';
            function initialize() {
                var myLatlng = new google.maps.LatLng(-1.292066, 36.821946);
                //create a new google maps object using the target div as an argument
                mapCanvas = document.getElementById('tracking-map-canvas');
                //set options for the map e.g zoom leve,type and center
                var mapOptions = {
                    center: new google.maps.LatLng(-1.292066, 36.821946),
                    zoom: 17,
                    mapTypeId: google.maps.MapTypeId.HYBRID
                }
                map = new google.maps.Map(mapCanvas, mapOptions);

                //default marker..    
                var deflatLng = new google.maps.LatLng(-1.256789, 36.8032929);
                marker = new google.maps.Marker({
                    position: deflatLng,
                    map: map,
                    icon: image,
                    title: 'REg893jnXj3'
                });
                map.setCenter(marker.getPosition());
                //pan to marker after 3 seconds of a center_changed event
                google.maps.event.addListener(map, 'center_changed', function () {
                    window.setTimeout(function () {
                        map.panTo(marker.getPosition());
                    }, 2000);
                });
            }
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
        <script>
            //get details on selected vehicle using ajax
            function loadSelected(x) {
                //ajax call to get listing...
                var jsResponseObj;
                var selected = x;
                console.log("selected vehicle is: " + selected);

                //create the request url..to search servlet 
                var url = "maplocate?selected=" + selected;


                //test
                if (window.XMLHttpRequest) {
                    xmlHttpRequest = new XMLHttpRequest();
                    console.log("created xmlhttprequest obj test");
                }
                //.... for IE5 and IE6
                else {
                    xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                }

                //at every state change of xhr...
                xmlHttpRequest.onreadystatechange = function () {
                    //get the current state of the xhr object i.e. 0,1,2,3,4
                    if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
                        var response = xmlHttpRequest.responseText; //server results as palin text
                        console.log("Response obj  from server: " + response);
                        jsResponseObj = JSON.parse(response);
                        populateUI(jsResponseObj);
                    }
                };
//                xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlHttpRequest.open("GET", url, true); //send using post to servlet charges
                xmlHttpRequest.send(null);
            }

            function populateUI(responseObj) {
                console.log("Value of Response: " + responseObj.vehicle.registrationNumber);
                var vcell = document.getElementById("v_details");
                vcell.innerHTML = "<strong>Vehicle Details</strong><br>" + responseObj.vehicle.registrationNumber + "<br>"
                        + responseObj.vehicle.make + ", " + responseObj.vehicle.model + "<br>" +
                        "<br>From:" + responseObj.booking.dtPickup + "<br>To: " + responseObj.booking.dtDropoff + "<br>";
                var ucell = document.getElementById("u_details");
                ucell.innerHTML = "<strong>Renter Details</strong><br>" +
                        responseObj.user.email + "<br>" + responseObj.user.phone + "<br>";

                //pan to retrieved co-oords
                var latt = responseObj.vmap.lattitude;
                var longt = responseObj.vmap.longitude;

                //create latlong coords from the retrived....
                var latLng = new google.maps.LatLng(-1.256789, 36.8032929);
                //create new marker with the given coords
                marker = new google.maps.Marker({
                    position: latLng,
                    map: map,
                    icon: image,
                    title: responseObj.vehicle.registrationNumber
                });
                map.setCenter(marker.getPosition());

//        map.setCenter(new google.maps.LatLng(latt,longt));


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
            <!--<td>-->
            <select style="width:160px;" onchange="loadSelected(this.value)">
                <!--get the attribute from session-->
                <c:forEach items="${trackingDetails}" var="current">
                    <option  value="${current.vehicle.vehicleId}" >${current.vehicle.registrationNumber}</option>
                </c:forEach>
            </select>
            <!--</td>-->
            <table class="booking track-panel">
                <tbody>
                    <tr>
                        <td id="v_details"> </td>
                        <td style="vertical-align: top" id="u_details"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <%@include file="templates/footer.html" %>
    </body>
</html>
