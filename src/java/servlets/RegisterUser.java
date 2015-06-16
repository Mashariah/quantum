/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 3:48:21 PM  : Jun 15, 2015
 */

package servlets;

import domain.User;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DbRequestService;
import services.LoginControl;

/**
 *
 * @author kelli
 */
public class RegisterUser extends HttpServlet {

    private static final String  DEFAULT_USER_TYPE = "member";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pageAfter="";
        
        Connection connection = (Connection)getServletContext().getAttribute("connector");
       String fName = request.getParameter("tx_fname");
       String lName = request.getParameter("tx_lname");
       String email = request.getParameter("tx_email");
       String phone = request.getParameter("tx_phone");
       String userName = request.getParameter("tx_username");
       String password = request.getParameter("tx_password");
       
       //generate salt value 
       String salt = LoginControl.getSalt();
       //generate password hash using salt value and password text
       String passwordHash = LoginControl.generateHashFromPassword(password, salt);
       
       //create and persist user details to database
       User user = new User(fName, lName, email, phone, userName, passwordHash, salt, DEFAULT_USER_TYPE);
        if(DbRequestService.createUser(connection,user)>0){
            //redirect to login page
            request.setAttribute("new_user", fName+" "+lName);
            String activation = "http://localhost:8080/allexi/login.jsp";
            request.setAttribute("activation_url", activation);
            pageAfter = "/confirmation.jsp";
        }else{
            //set some error message and redirect to registration page.
            request.setAttribute("reg_error", "some error occured in your registration");
            pageAfter = "/registration.jsp";
        }
            getServletContext().getRequestDispatcher(pageAfter).forward(request, response);
        
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
