/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 5:22:52 PM  : Jun 14, 2015
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
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
public class TrackingList extends HttpServlet {

   /**
    *  Get list of all vehicles + status + booking details
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException 
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                Connection dbConn = (Connection) request.getServletContext().getAttribute("connector");

             Logger.getLogger(TrackingList.class.getName()).log(Level.INFO,"URL= "+request.getRequestURL().toString());
             Logger.getLogger(TrackingList.class.getName()).log(Level.INFO,"URI= "+request.getRequestURI());
        ArrayList trackingDetails;
        
        String trackSql = "select distinct cars.vehicle_id, make,model,registration_num,status,dt_pickup,dt_dropoff,"
                + "p_location,d_location,first_name,last_name,email_address,"
                + "phone from cars join vehicle_status join bookings join users on cars.vehicle_id=vehicle_status.vehicle_id"
                + " and cars.vehicle_id=bookings.vehicle_id and bookings.user_id=users.user_id order by dt_pickup asc;";
        
        trackingDetails = DbRequestService.getTrackingDetails(dbConn,trackSql);
             Logger.getLogger(TrackingList.class.getName()).log(Level.INFO,"items in list servlet= "+trackingDetails.size());
        request.setAttribute("trackingDetails", trackingDetails);
        getServletContext().getRequestDispatcher("/bookings_list.jsp").forward(request, response);
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
