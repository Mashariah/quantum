/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 8:09:03 PM  : Jun 29, 2015
 */
package servlets;

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
 */
public class BookingReset extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Connection conn = (Connection)getServletContext().getAttribute("connector");
        int vehicle = Integer.parseInt(request.getParameter("booking"));
        Logger.getLogger(BookingReset.class.getName()).log(Level.INFO,"Booking id: {0}",vehicle);
        
        int done = DbRequestService.clearBooking(vehicle,conn);
        Logger.getLogger(BookingReset.class.getName()).log(Level.INFO,"Status: {0}",done);
        getServletContext().getRequestDispatcher("/booking_list.jsp").forward(request, response);
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
