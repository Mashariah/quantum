<%-- 
    Document   : editcar
    Created on : Jun 21, 2015, 12:16:07 AM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Allexis Admin | Edit  Vehicle</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
        <div class="header"></div>
        <%@include file="templates/navigation.jsp" %>
        <%@include  file="templates/dashboard_menu.jsp" %>
        <form method="POST" action="updatecar" class="form-layout" style="margin-left: 10px;">
            <table class="booking">
                <tbody>
                    <tr ><td colspan="3"><h3>Editing Vehicle Details</h3></td></tr>

                    <tr>
                        <td>Vehicle Id</td>
                        <td><input type="text" value="${target_vehicle.vehicleId}" readonly="true" name="tx_vehicleid"/></td>
                    </tr>
                    <tr>
                        <td><label>Reg.  Num</label></td>
                        <td>
                            <input type="text"  id="txt_regnum" name="tx_regnum" placeholder="Enter registration number" 
                                   value="${target_vehicle.registrationNumber}"/></td>
                        <td><label>Model</label></td>
                        <td><input type="text"  id="txtmake" name="tx_model" placeholder="Enter car make"
                                   value="${target_vehicle.model}"/></td>
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
                                <option>Lexus</option>
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
                            <textarea name = "txb_convenience"  cols="40" rows="5" class="description-text-area">
                                <c:out value="${target_vehicle.description.convinience}"></c:out>
                                </textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Safety & Security</label></td>
                            <td colspan="3">
                                <textarea name = "txb_safetysec" cols="40" rows="5" class="description-text-area">
                                <c:out value="${target_vehicle.description.safetyAndSecurity}"></c:out>
                                </textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Entertainment</label></td>
                            <td colspan="3">
                                <textarea name = "txb_entertainment" cols="40" rows="5" class="description-text-area">
                                <c:out value="${target_vehicle.description.entertainment}"></c:out>
                                </textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Telematics</label></td>
                            <td colspan="3">
                                <textarea name = "txb_telematics" cols="40" rows="5" class="description-text-area">
                                <c:out  value="${target_vehicle.description.telematics}"></c:out></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Tires & Wheels </label></td>
                            <td colspan="3">
                                <textarea name = "txb_tireswheels" rows="4" cols="40" class="description-text-area">
                                <c:out value="${target_vehicle.description.tireWheels}">${target_vehicle.description.tireWheels}</c:out>
                                </textarea>
                            </td>
                    <!--<input type="hidden" name="tx_vehicleid"  value="${target_vehicle.vehicleId}" id="tx_vehicleid"/>-->
                        </tr>

                        <tr>
                        <%--<c:set property="btn1" value="Addr"></c:set>--%>
                        <td colspan="3">
                            <button id="upload" type="submit" name='btn_reset'>Clear</button>
                        </td>
                        <td >
                            <button id="upload" type="submit" name='btn_addcar'>Update</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%@include file="templates/footer.html" %>
    </body>
</html>
