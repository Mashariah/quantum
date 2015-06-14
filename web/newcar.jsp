<%-- 
    Document   : newcar
    Created on : Jun 11, 2015, 6:19:43 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Allexis Admin | New Vehicle</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
        <div class="header"></div>
         <%@include file="templates/navigation.jsp" %>
         <%@include  file="templates/dashboard_menu.jsp" %>
         <form method="POST" action="../newcar" class="form-layout" style="margin-left: 10px; width: 72%;" enctype="multipart/form-data">
            <table class="booking">
                <tbody>
                    <tr ><td colspan="3"><h3>New Car Details</h3></td></tr>
                    <tr>
                        <!--<td colspan="4"> <input type="checkbox"  id="txt_return_location" name="tx_return_location" placeholder="Return Location"/>-->
                            <!--<strong>${vehicle_add_error}</strong>-->
                        <!--</td>-->
                    </tr>
                    <tr>
                <td><label>Reg.  Num</label></td>
                <td><input type="text"  id="txt_regnum" name="tx_regnum" placeholder="Enter registration number"/></td></td>
                <td><label>Model</label></td>
                <td><input type="text"  id="txtmake" name="tx_model" placeholder="Enter car make"/></td></td>
                    </tr>
                    <tr>
                <td><label>Make</label></td>
                <td>
                    <select name="drp_carmake">
                                <option>Audi</option>
                                <option>Ford</option>
                                <option>Mercedes Benz</option>
                                <option>Jaguar</option>
                                <option>Range Rover</option>
                            </select>
                </td></td>
                <td><label>Year</label></td>
                <td>
                    <select name="drp_caryear">
                                <option>2012</option>
                                <option>2013</option>
                                <option>2014</option>
                                <option>2015</option>
                                <option>2016</option>
                            </select>
                </td>
                    </tr>
                    <tr><td><br><h3>Images</h3></td></tr>
                    <tr>
                        <td><label>Catalog/Teaser</label></td>
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_teaserimg" placeholder="browse for images" style="width: 100%;"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><label>Details</label></td>
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_detailsimg" placeholder="browse for images" style="width: 100%;"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><label>Thumbnail 1</label></td>
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_thumb1" placeholder="browse for images" style="width: 100%;"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><label>Thumbnail 2</label></td>
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_thumb2" placeholder="browse for images" style="width: 100%;"/></td>
                        <td></td>
                    </tr>
                     <tr><td><br><h3>Features</h3></td></tr>
                    <tr>
                        <td><label>Fuel Economy</label></td>
                        <td><input type="text" id="tx_fueleconomy" name="tx_fueleconomy" placeholder="Kms per litre"/></td>
                        <td><label>Fuel Capacity</label></td>
                        <td><input type="text" id="tx_fuelcapacity" name="tx_fuelcapacity" placeholder="tank capacity"/></td>
                    </tr>
                    <tr>
                        <td><label>Transmission</label></td>
                        <td>
                            <select name="drp_transmission">
                <option>4-speed Automatic</option>
                <option>6-speed Automatic</option>
                <option>7-Speed Automatic</option>
                <option>4-Speed Manual</option>
                            </select>
                        </td>
                        <td><label>Seating Capacity</label></td>
                        <td>
                            <select name="drp_scapacity">
                                <option value="4">Four</option>
                                <option value="5">Five</option>
                                <option value="7">Seven</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Convenience</label></td>
                        <td colspan="3">
                            <input type="text" name = "txb_convenience"  style="width: 100%; height: 60px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Safety & Security</label></td>
                        <td colspan="3">
                            <input type="text" name = "txb_safetysec" style="width: 100%; height: 60px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Entertainment</label></td>
                        <td colspan="3">
                            <input type="text" name = "txb_entertainment" style="width: 100%; height: 60px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Telematics</label></td>
                        <td colspan="3">
                            <input type="text" name = "txb_telematics" style="width: 100%; height: 60px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Tires & Wheels </label></td>
                        <td colspan="3">
                            <input type="text" name = "txb_tireswheels" style="width: 100%; height: 60px;"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="3"><button id="upload" type="submit" name='btn_reset'>Reset</button></td>
                        <td ><button id="upload" type="submit" name='btn_addcar'>Add Noow!</button></td>
                    </tr>
                </tbody>
            </table>
       </form>
         <%@include file="templates/footer.html" %>
    </body>
</html>
