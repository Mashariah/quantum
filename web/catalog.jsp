<%-- 
    Document   : catalog
    Created on : May 30, 2015, 12:44:21 PM
    Author     : kelli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Allexi | Catalog</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
        <div class="header"><h1></h1></div>
                <%@include file="templates/navigation.jsp" %>
        <div class="filter-panel">
            <h3>Search Filter</h3>
            <table class="booking">
                <tr>
                    <td style="background:#666; color:#ffffff" colspan="2">Model</td>
                </tr>
                <tr>
                    <td><input type="checkbox" id ="chk_lexus" value="lexus" onchange="getModel(this.id);"/><label for="chk_lexus">Lexus</label></td>
                    <td><input type="checkbox" id ="chk_ford" value="ford" onchange="getModel(this.id);"/><label for="chk_lexus">Ford</label></td>
                <tr>
                    <td><input type="checkbox" id ="chk_mercedes" value="mercedes" onchange="getModel(this.id);"/><label for="chk_mercedes">Mercedes</label></td>
                    <td><input type="checkbox" id ="chk_bmw" value="bmw" onchange="getModel(this.id);"/><label for="chk_bmw">BMW</label></td>
                </tr>
                <tr>
                    <td><input type="checkbox" id ="chk_audi" value="audi" onchange="getModel(this.id);"/><label for="chk_audi">Audi</label></td>
                    <td><input type="checkbox" id ="chk_geele" value="geele" onchange="getModel(this.id);"/><label for="chk_geele">Geele</label><</td>
                </tr>
                <tr>
                    <td style="background:#666; color:#ffffff" colspan="2">Features</td>
                </tr>
                <tr><td><input type="checkbox" id ="chk_satnav" onchange="getModel(this.id);"/><label for="chk_satnav">Telematics</label> </td></tr>
                <td><input type="checkbox" id ="chk_satnav" onchange="getModel(this.id);"/><label for="chk_satnav">Entertainment</label> </td>
                <tr><td><input type="checkbox" id ="chk_satnav" onchange="getModel(this.id);"/><label for="chk_satnav">Transmission</label> </td></tr>
                <tr><td><input type="checkbox" id ="chk_satnav"/><label for="chk_satnav">Capacity</label> </td></tr>
            </table>

            <!--Display panel for all vehicle details-->
        </div>
        <div class="catalog-panel">
            <!--call servlet to retrieve image files-->
            <c:forEach items ="${vehicles}"  var="current">
                <div class="catalog-item">
                    <!--call servlet to get the vehicle details-->
                    <a href="details?selected_vehicle=${current.vehicleId}">
                        <img src="fileserver?param1=${current.teaserImg}"/>
                        <div class="item-info-box">${current.make}, ${current.model}<br>${current.year}<br>
                            <c:set var="booked" value="booked"/>
                            <c:choose>
                                <c:when test= "${current.status == 'booked'}">
                                    <strong ><c:out value="Booked"/></strong>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${current.status}"/>
                                </c:otherwise>
                            </c:choose> 
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>

        <%@include file="templates/footer.html" %>
    </body>
    <script>
        
            function getModel(x){
        var  selected = document.getElementById(x).value;
        console.log(selected);
                
            }
    </script>
</html>
