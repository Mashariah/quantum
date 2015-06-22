<%-- 
    Document   : newcar
    Created on : Jun 11, 2015, 6:19:43 PM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form method="POST" action="newcar" class="form-layout" style="margin-left: 10px; width: 72%;" enctype="multipart/form-data">
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
                        <td><input type="text"  id="txt_regnum" name="tx_regnum" placeholder="Enter registration number" 
                                   value="${target_vehicle.registrationNumber}"/></td></td>
                        <td><label>Model</label></td>
                        <td><input type="text"  id="txtmake" name="tx_model" placeholder="Enter car make"
                                   value="${target_vehicle.model}"/></td></td>
                    </tr>
                    <tr>
                        <td><label>Make</label></td>
                        <td>
                            <select name="drp_carmake">
                                <option selected="${target_vehicle.make}">${target_vehicle.make}</option>
                                <option>Audi</option>
                                <option>Ford</option>
                                <option>Mercedes Benz</option>
                                <option>Jaguar</option>
                                <option>Range Rover</option>
                                <option>BMW</option>
                            </select>
                        </td></td>
                        <td><label>Year</label></td>
                        <td>
                            <select name="drp_caryear">
                                <option >${target_vehicle.year}</option>
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
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_teaserimg" placeholder="browse for images" 
                                               style="width: 100%;"/> </td><td><img src="fileserver?param1=${target_vehicle.teaserImg}" width="100px" height="40px"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><label>Details</label></td>
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_detailsimg" placeholder="browse for images" style="width: 100%;"/></td>
                        <td><img src="fileserver?param1=${target_vehicle.detailImg}" width="100px" height="40px"/></td>
                    </tr>
                    <tr>
                        <td><label>Thumbnail 1</label></td>
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_thumb1" placeholder="browse for images" style="width: 100%;"/></td>
                        <td><img src="fileserver?param1=${target_vehicle.thumbnail1Img}" width="100px" height="40px"/></td>
                    </tr>
                    <tr>
                        <td><label>Thumbnail 2</label></td>
                        <td colspan="2"><input type="file"  id="txt_pick_location" name="tx_thumb2" placeholder="browse for images" style="width: 100%;"/></td>
                        <td><img src="fileserver?param1=${target_vehicle.thumbnail2Img}" width="100px" height="40px"/></td>
                    </tr>
                    <tr><td><br><h3>Features</h3></td></tr>
                    <tr>
                        <td><label>Fuel Economy</label></td>
                        <td><input type="text" id="tx_fueleconomy" name="tx_fueleconomy" placeholder="Kms per litre"
                                   value="${target_vehicle.description.fuelConsumption}"/></td>
                        <td><label>Fuel Capacity</label></td>
                        <td><input type="text" id="tx_fuelcapacity" name="tx_fuelcapacity" placeholder="tank capacity"
                                   value="${target_vehicle.description.fuelCapacity}"/></td>
                    </tr>
                    <tr>
                        <td><label>Transmission</label></td>
                        <td>
                            <select name="drp_transmission">
                                <option selected="true">${target_vehicle.description.transmission}</option>
                                <option>4-speed Automatic</option>
                                <option>6-speed Automatic</option>
                                <option>7-Speed Automatic</option>
                                <option>4-Speed Manual</option>
                            </select>
                        </td>
                        <td><label>Seating Capacity</label></td>
                        <td>
                            <select name="drp_scapacity">
                                <option value=${target_vehicle.description.seatingCapacity}>${target_vehicle.description.seatingCapacity}</option>
                                <option value="4">Four</option>
                                <option value="5">Five</option>
                                <option value="7">Seven</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Convenience</label></td>
                        <td colspan="3">
                            <textarea name = "txb_convenience"  cols="50" rows="5" class="description-text-area">
                                <c:out value="${target_vehicle.description.convinience}"></c:out>
                              </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Safety & Security</label></td>
                        <td colspan="3">
                            <textarea name = "txb_safetysec" cols="50" rows="5" class="description-text-area">
                                      <c:out value="${target_vehicle.description.safetyAndSecurity}"></c:out>
                        </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Entertainment</label></td>
                        <td colspan="3">
                            <textarea name = "txb_entertainment" cols="50" rows="5" class="description-text-area">
                            <c:out value="${target_vehicle.description.entertainment}"></c:out>
                        </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Telematics</label></td>
                        <td colspan="3">
                            <textarea name = "txb_telematics" cols="50" rows="5" class="description-text-area">
                                <c:out  value="${target_vehicle.description.telematics}"></c:out></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Tires & Wheels </label></td>
                        <td colspan="3">
                            <textarea name = "txb_tireswheels" rows="4" cols="50" class="description-text-area">
                            <c:out value="${target_vehicle.description.tireWheels}">${target_vehicle.description.tireWheels}</c:out>
                        </textarea>
                        </td>
                    </tr>

                    <tr>
                            <%--<c:set property="btn1" value="Addr"></c:set>--%>
                        <c:choose>
                            <c:when test="${edit=true}">
                                                        <input type="hidden" name="mode" value="edit_mode"/>
                        <td colspan="3">
                            <button id="upload" type="submit" name='btn_reset'>Clear</button>
                        </td>
                        <td >
                            <button id="upload" type="submit" name='btn_addcar'>Update</button>
                        </td>
                            </c:when>
                        <c:when  test="${edit=false}">
                                                        <input type="hidden" name="mode" value="create_mode"/>
                        <td colspan="3">
                            <button id="upload" type="submit" name='btn_reset'>Reset</button>
                        </td>
                        <td >
                            <button id="upload" type="submit" name='btn_addcar'>Add</button>
                        </td>
                                
                            </c:when>
                        </c:choose>
                    </tr>
                </tbody>
            </table>
        </form>
        <%@include file="templates/footer.html" %>
    </body>
</html>
