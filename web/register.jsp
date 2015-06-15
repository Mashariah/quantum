<%-- 
    Document   : register
    Created on : May 28, 2015, 11:56:41 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Allexi | User Registration</title>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
    </head>
    <body>
        <div class="header"><h1></h1></div>
                <%@include file="templates/navigation.jsp" %>
        <form method="POST" action="register" class="form-layout">
            <table class="booking">
                <tbody>
                    <tr ><td colspan="3"><h3>User Registration</h3></td></tr>
                    <tr>
                        ${vehicle_add_error}
                    </tr>
                    <tr>
                        <td><label>First Name</label></td>
                        <td><input type="text"  id="txt_regnum" name="tx_fname" placeholder="First Name"/></td></td>
                        <td><label>Last Name</label></td>
                        <td><input type="text"  id="txtmake" name="tx_lname" placeholder="Last Name"/></td>
                    </tr>
                    <tr>
                        <td><label>Email</label></td>
                        <td><input type="text"  id="txtmake" name="tx_email" placeholder="Email"/></td>
                        
                        <td><label>Phone</label></td>
                        <td><input type="text"  id="txtmake" name="tx_phone" placeholder="Phone"/></td>
                    </tr>
                    <tr>
                    <tr>
                        <td><label>User Name </label></td>
                        <td><input type="text"  id="txtmake" name="tx_username" placeholder="User Name"/></td>
                        
                        <td><label>Password</label></td>
                        <td><input type="text"  id="txtmake" name="tx_password" placeholder="Password"/></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                        <button id="upload" type="submit" name="register">Sign Up</button>
                            <button id="upload" type="submit" name="">Reset</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%@include file="templates/footer.html" %>
    </body>
</html>
