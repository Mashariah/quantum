/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:07:36 AM  : Jul 4, 2015
 */
package servlets;

import domain.BookingDetails;
import domain.User;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DbRequestService;

/**
 *
 * @author kelli
 * 
 * ==========Member user cancels vehicle booking========
 * 1. Get logged in user id
 * 2. Get the vehicle details 
 * 3. Create sql statement 
 */
public class CancelBooking extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookingDetails booking;
        User user;
        //get database connection
        Connection connection = (Connection)getServletContext().getAttribute("connector");
        booking = (BookingDetails)request.getSession().getAttribute("user_booking");
        user = (User)request.getSession().getAttribute("user");
        int userId = user.getUserId();
        int cancelled = DbRequestService.cancelBooking(userId,booking,connection);
        if(cancelled>0){
            request.setAttribute("message","Booking has been successfully cleared");
            getServletContext().getRequestDispatcher("/empty_result.jsp").forward(request, response);
        }
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
