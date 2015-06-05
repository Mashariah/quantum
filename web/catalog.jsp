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
        <%@include file="templates/navigation.html" %>
        <div class="filter-panel">
            <h3>Search Filter</h3>
            <h4>Model</h4>
            <input type="checkbox" id ="chk_lexus"/><label for="chk_lexus" style="margin-right: 22%; ">Lexus</label>
            <input type="checkbox" id ="chk_ford"/><label for="chk_ford">Ford</label><br>
            <input type="checkbox" id ="chk_mercedes"/><label for="chk_merc" style="margin-right: 10%;">Mercedes</label>
            <input type="checkbox" id ="chk_bmw"/><label for="chk_bmw">BMW</label><br>
            <input type="checkbox" id ="chk_audi"/><label for="chk_audi" style="margin-right: 26%; ">Audi</label>
            <input type="checkbox" id ="chk_chrysler"/><label for="chk_chrysler">Chrysler</label><br>
            <input type="checkbox" id ="chk_geele"/><label for="chk_geele">Geele</label>
            <h4>Rates</h4>
            <input type="range" min="4500" max="25000" name="rate_range" step="4000" value="rate"/>
            <h4>Features</h4>
            <input type="checkbox" id ="chk_satnav"/><label for="chk_satnav">Sattelite Navigation</label><br>
            <input type="checkbox" id ="chk_advent"/><label for="chk_advent">Advanced Entertainment</label><br>
            <input type="checkbox" id ="chk_chauf"/><label for="chk_chauf">Chauffered</label><br>
            
            <!--Display panel for all vehicle details-->
        </div>
        <div class="catalog-panel">
            <!--call servlet to retrieve image files-->
            <c:forEach items ="${vehicles}"  var="current">
                <div class="catalog-item">
                    <!--call servlet to get the vehicle details-->
                    <a href="details?selected_vehicle=${current.vehicleId}">
                        <img src="fileserver?param1=${current.teaserImg}"/>
                        <div class="item-info-box">${current.make}, ${current.model}<br>${current.year}</div>
                    </a>
                </div>
            </c:forEach>
<!--            <div class="catalog-item"><img src="resc/images/audi_A3.png"/></div>
            <div class="catalog-item"><img src="resc/images/audi_C.png" /></div>
            <div class="catalog-item"><img src="resc/images/audi_Q7.png"/></div>-->
        </div>

        <%@include file="templates/footer.html" %>
    </body>
    <script>
        $(document).ready(function (){
            
        });
    </script>
</html>
