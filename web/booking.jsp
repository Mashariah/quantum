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
        <script src="js/jquery.validate.js" type="text/javascript"></script>
        <script src="js/jquery-ui.js"  type="text/javascript"/></script>
    <script src="js/jquery.timepicker.js"  type="text/javascript"/></script>
</head>
<body>
    <script>
                $('#frm-booking').validate(
        {
        rules:  {
        tx_fname:  { required: true },
                tx_lname:  { required: true },
                tx_email:  { required: true },
                tx_phone:  { required: true },
                tx_pickup_loc:  { required: true },
                tx_dpickup:  { required: true },
                tx_tpickup: { required: true}
                tx_ddropoff: { required: true}
                tx_tdropoff: { required: true}
        },
                messages: {
                        tx_fname:  { required: "First Name Required" },
                        tx_lname:  { required: "Last Name Required" },
                        tx_email:  { required: "Email Address Required"},
                        tx_phone:  { required: "Phone Number Required" },
                        tx_pickup_loc:  { required: "Pick up Location  Required" },
                        tx_dpickup:  { required: "Date of pick up Required" },
                        tx_tpickup: { required: "Time of Pickup  Required"}
                        tx_ddropoff: { required: "Return Date  Required"}
                        tx_tdropoff: { required: "Retun time  Required"}
                }
            });
    </script>
    <style>
        .ui-datepicker {font-size: 9pt !important}
        .ui-timepicker-wrapper{font-size: 9pt;}
    </style>
    <div class="header"></div>
    <%@include file="templates/navigation.jsp" %>
    <div id ="frm-booking">
    <form method="POST" action="booking" class="form-layout" id="frm-booking">
        <h2>${target_vehicle.make} ${target_vehicle.model} Booking</h2><hr><br>
        <table class="booking">
            <tbody>

                <tr><td><h3>Personal Info</h3></td></tr>
                <tr>
                    <td colspan="4"> <input type="checkbox"  id="txt_return_location" name="tx_return_location" 
                                            placeholder="Return Location"/>
                        <strong>${user_add_error}</strong>
                    </td>
                </tr>
                <tr>
                    <td><label>First name</label></td>
                    <td><input type="text"  id="txt_fname" name="tx_fname" id ="tx_fname" placeholder="First Name" 
                               value="${user.fName}" required="true"/></td></td>
                    <td><label>Last Name</label></td>
                    <td><input type="text"  id="txt_lname" name="tx_lname" id ="tx_lname"placeholder="Last Name" 
                               value="${user.lName}" required="true"/></td></td>
                </tr>
                <tr>
                    <td><label>Email Address</label></td>
                    <td><input type="text"  id="txt_email" name="tx_email" id ="tx_email" placeholder="Email Address" 
                               value="${user.email}" required="true"/></td></td>
                    <td><label>Phone Number</label></td>
                    <td><input type="text"  id="txt_phone" name="tx_phone" id ="tx_phone" required="true"
                               placeholder="Phone e.g (+254 7702 111111)"  value="${user.phone}" /></td></td>
                </tr>
                <tr><td><br><h3>Pick-up Details</h3></td></tr>
                <tr>
                    <td><label>Pick up Location</label></td>
                    <td><input type="text"  id="txt_pick_location" name="tx_pickup_loc" id ="tx_pickup_loc" 
                               placeholder="Enter area or town" required="true"/></td>
                    <td><label>Town</label></td>
                    <td>
                        <select name="opt_pickup_town">
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
                    <td><input type="text" id="datepicker" name="tx_dpickup" id ="tx_dpickup" required="true"/></td>
                    <td><label>Time</label></td>
                    <td><input type="text" id="timer1" name="tx_tpickup" id ="tx_tpickup" required="true"/></td>
                </tr>
                <tr>
                    <td><label>Return Date</label></td>
                    <td><input type="text" id="datepicker2" name="tx_ddropoff" id ="tx_ddropoff" required="true"/></td>
                    <td><label>Time</label></td>
                    <td><input type="text" id="timer2" name="tx_tdropoff" id ="tx_tdropoff" required="true"/></td>
                </tr>
                <tr>
                    <td><label>Renter's Age</label></td>
                    <td>
                        <select>
                            <option>Below 25</option>
                            <option>Between 25 - 75</option>
                            <option>Over 75</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><br><h3>Return Point Details</h3></td>
                </tr>
                <tr >
                    <td colspan="2"> 
                        <input type="checkbox"  id="txt_return_location" name="checkbx_dropoff" 
                               placeholder="Return Location"/>
                        <label>From Pick Up Location</label>
                    </td>
                    <td><label>Other (Specify) </label></td>
                    <td><input type="text"  id="txt_return_location" name="tx_dropoff" id ="tx_dropoff" 
                               placeholder="Return Location" required="false"/></td>
                </tr>
                <tr>
                    <td colspan="4"><button type="submit" name='book'>Book Now</button></td>
                </tr>
            </tbody>
        </table>
    </form>
    </div>
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
