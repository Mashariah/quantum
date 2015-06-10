<%-- 
    Document   : booking
    Created on : Jun 3, 2015, 11:00:01 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Allexis | Vehicle Booking</title>
  <link rel="stylesheet" type="text/css" href="style/core.css"/>
  <link rel="stylesheet" type="text/css" href="style/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="style/jquery.timepicker.css"/>
  
  <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
  <script src="js/jquery-ui.js"  type="text/javascript"/></script>
    <script src="js/jquery.timepicker.js"  type="text/javascript"/></script>
    </head>
    <body>
        
        <style>
            .ui-datepicker {font-size: 9pt !important}
        </style>
        <div class="header"></div>
        <%@include file="templates/navigation.jsp" %>
        <form method="POST" action="booking_processor" class="form-layout">
            <h2>${target_vehicle.make} ${target_vehicle.model} Booking</h2><hr><br>
            


            <table class="booking">
                <tbody>
                    
                    <tr><td><h3>Personal Info</h3></td></tr>
                    <tr>
                        <td colspan="4"> <input type="checkbox"  id="txt_return_location" name="tx_return_location" placeholder="Return Location"/>
                            <strong>I am a registered user on this site</strong>
                        </td>
                    </tr>
                    <tr>
                <td><label>First name</label></td>
                <td><input type="text"  id="txt_fname" name="tx_fname" placeholder="Enter first name"/></td></td>
                <td><label>Last Name</label></td>
                <td><input type="text"  id="txt_lname" name="tx_lname" placeholder="Enter last name"/></td></td>
                    </tr>
                    <tr>
                <td><label>Email Address</label></td>
                <td><input type="text"  id="txt_email" name="tx_email" placeholder="Enter email address"/></td></td>
                <td><label>Phone Number</label></td>
                <td><input type="text"  id="txt_phone" name="tx_phone" placeholder="Phone e.g (+254 7702 111111)"/></td></td>
                    </tr>
                    <tr><td><br><h3>Pick-up Details</h3></td></tr>
                    <tr>
                        <td><label>Pick up Location</label></td>
                        <td><input type="text"  id="txt_pick_location" name="tx_pick_location" placeholder="Enter area or town"/></td>
                        <td><label>Town</label></td>
                        <td>
                            <select>
                                <option>Nairobi</option>
                                <option>Thika</option>
                                <option>Karen</option>
                                <option>Kiambu</option>
                                <option>Nakuru</option>
                                <option>Naivasha</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Pick up Date</label></td>
                        <td><input type="text" id="datepicker"/></td>
                        <td><label>Time</label></td>
                        <td><input type="text" id="timer1"/></td>
                    </tr>
                    <tr>
                        <td><label>Return Date</label></td>
                        <td><input type="text" id="datepicker2"/></td>
                        <td><label>Time</label></td>
                        <td><input type="text" id="timer2"/></td>
                    </tr>
                    <tr>
                        <td><label>Renter's Age</label></td>
                        <td>
                            <select>
                <option>Below 25</option>
                <option>Between 25 - 65</option>
                <option>Over 75</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><br><h3>Return Point Details</h3></td>
                    </tr>
                    <tr >
                        <td colspan="2"> 
                    <input type="checkbox"  id="txt_return_location" name="tx_return_location" placeholder="Return Location"/>
                    <label>From Pick Up Location</label>
                </td>
                <td><label>Other (Specify) </label></td>
                <td><input type="text"  id="txt_return_location" name="tx_return_location" placeholder="Return Location"/></td>
                    </tr>
                    <tr>
                        <td colspan="4"><button type="submit" name='book'>Book Now</button></td>
                    </tr>
                </tbody>
            </table>
       </form>
        <%@include file="templates/footer.html" %>
    </body>
    <script>
        $(document).ready(function() {
            $("#datepicker").datepicker();
            $("#datepicker2").datepicker();
            $("#timer1").timepicker();
            $("#timer2").timepicker();
            
        });
    </script>
</html>
