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
                <table class="panel">
                <tr>
                    <td id="header" colspan="2">Model</td>
                </tr>
                <tr>
                    <td><input type="checkbox" id ="chk_lexus" value="lexus" onchange="filterResults(this.id);"/><label for="chk_lexus">Lexus</label></td>
                    <td><input type="checkbox" id ="chk_ford" value="ford" onchange="filterResults(this.id);"/><label for="chk_lexus">Ford</label></td>
                <tr>
                    <td><input type="checkbox" id ="chk_mercedes" value="mercedes" onchange="filterResults(this.id);"/><label for="chk_mercedes">Mercedes</label></td>
                    <td><input type="checkbox" id ="chk_bmw" value="bmw" onchange="filterResults(this.id);"/><label for="chk_bmw">BMW</label></td>
                </tr>
                <tr>
                    <td><input type="checkbox" id ="chk_audi" value="audi" onchange="filterResults(this.id);"/><label for="chk_audi">Audi</label></td>
                    <td><input type="checkbox" id ="chk_geele" value="geele" onchange="filterResults(this.id);"/><label for="chk_geele">Jaguar</label></td>
                </tr>
                <tr>
                    <td style="background:#666; color:#ffffff" colspan="2">Features</td>
                </tr>
                <tr><td><input type="checkbox" id ="chk_satnav" onchange=""/><label for="chk_satnav">Telematics</label> </td></tr>
                <td><input type="checkbox" id ="chk_satnav" onchange=""/><label for="chk_satnav">Entertainment</label> </td>
                <tr><td><input type="checkbox" id ="chk_satnav" onchange=""/><label for="chk_satnav">Transmission</label> </td></tr>
                <tr><td><input type="checkbox" id ="chk_satnav"/><label for="chk_satnav">Capacity</label> </td></tr>
            </table>
            <!--Display panel for all vehicle details-->
        </div>
        <div class="catalog-panel" id="catalog-panel">
            <!--call servlet to retrieve image files-->
            <c:forEach items ="${vehicles}"  var="current">
                <div class="catalog-item" id="catalog-item">
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
        <div id="navigation-panel">
            <a id = "p_link" href="catalog?page=${page_count  - 1}">Previous</a>
            <a id = "p_link" href="catalog?page=${page_count  + 1}">Next</a>
        </div>

        <%@include file="templates/footer.html" %>
    </body>
    <script>

var jsResponseObj ;
        function filterResults(x) {
            var selected = document.getElementById(x).value;
            console.log(selected);

            //create the request url..to search servlet 
            var url = "cfilter?target=" + selected;
           
        
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
                xmlHttpRequest.onreadystatechange = function() {
                //get the current state of the xhr object i.e. 0,1,2,3,4
                    if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
                        var response = xmlHttpRequest.responseText; //server results as palin text
                        console.log("Json received array: "+response);
                       jsResponseObj = JSON.parse(response);
                       console.log(jsResponseObj[0].detailImg);
                                              console.log(jsResponseObj[0].model);
                        formatAndDisplay(jsResponseObj);
                    }
                };
//                xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlHttpRequest.open("GET", url, true); //send using post to servlet charges
                xmlHttpRequest.send(null); 
    }
    
        function formatAndDisplay(jsObj) {
            console.log("Entering the format method...");
//            //reset/clear the exiting 'category-panel' div for the new results
            //var targetDiv = document.getElementById('catalog-panel');
           // targetDiv.innerHTML = jsResponseObj[0].detailImg;
//            console.log("cleared the main parent div");


//get the list of of div with class names : 'category-item' and 'item-info-box'
                var items = document.getElementsByClassName("catalog-item");
                var infoBoxes = document.getElementsByClassName("item-info-box");
                console.log("Items of div catalog-item = "+items.length);
                console.log("Items in div info-boxes = "+infoBoxes.length);
                var catalogPanel = document.getElementById("catalog-panel");
                var newPanel = document.createElement('div');
                newPanel.setAttribute('class','catalog-panel');
           for(i=0;i<jsResponseObj.length;i++){
                infoBoxes[i].innerHTML = jsResponseObj[i].make+", "+jsResponseObj[i].model+"<br>"+
                        jsResponseObj[i].year+"<br>"+ jsResponseObj[i].status;
//                
                var imageId = jsResponseObj[i].teaserImg;
                items[i].innerHTML ="<a href=details?selected_vehicle="+jsResponseObj[i].vehicleId+"><img src=fileserver?param1="+imageId+"/></a>";
                items[i].appendChild(infoBoxes[i]);
                newPanel.appendChild(items[i]);
            }
            //replace the entire panel with results..
            document.getElementById("catalog-panel").parentNode.replaceChild(newPanel,catalogPanel);

            }        
        


    </script>
</html>
