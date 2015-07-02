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
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="style/core.css"/>
        <script type="text/javascript">
            
            $("#myemailform").validate(
      {
        rules: 
        {
          tx_fname: 
          {
            required: true
          },
          tx_email: 
          {
            required: true,
            email: true
          }
        },
        messages: 
        {
          tx_fname 
          {
            required: "Please enter your first name"
          },
          tx_email: 
          {
            required: "Please enter your email address."
          }
        }
      });   
//      $('#myemailform').validate( { rules:{ }, messages:{ } } );
        </script>
    </head>
    <body>
        <div class="header"><h1></h1></div>
                <%@include file="templates/navigation.jsp" %>
        <form method="POST" action="register" class="form-layout" id="myemailform">
            <table class="booking">
                <tbody>
                    <tr ><td colspan="3"><h3>User Registration</h3></td></tr>
                    <tr>
                <strong style="background: white; color:red; font-size: 0.8em;" > ${reg_error}</strong>
                    </tr>
                    <tr>
                        <td><label for="tx_fname">First Name</label></td>
                        <td><input type="text"  id="txt_firstname" name="tx_fname" placeholder="First Name"  required /></td></td>
                        <td><label for="tx_lname">Last Name</label></td>
                        <td><input type="text"  id="txt_lastname" name="tx_lname" placeholder="Last Name" required /></td>
                    </tr>
                    <tr>
                        <td><label for="tx_email">Email</label></td>
                        <td><input type="text"  id="tx_email" name="tx_email" placeholder="Email" required  /></td>
                        
                        <td><label for="phone">Phone</label></td>
                        <td><input type="text"  id="tx_phone" name="tx_phone" placeholder="Phone" required /></td>
                    </tr>
                    <tr>
                    <tr>
                        <td><label for="tx_username">User Name </label></td>
                        <td><input type="text"  id="tx_username" name="tx_username" placeholder="User Name" required /></td>
                        
                        <td><label for="tx_password">Password</label></td>
                        <td><input type="password"  id="tx_password" name="tx_password" placeholder="Password" required/></td>
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
