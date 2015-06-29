/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 3:52:08 PM  : Jun 28, 2015
 */
package servlets;

import domain.TrackingDescription;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kelli
 */
public class BookingDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int itemRequest = Integer.parseInt(request.getParameter("item"));
        Logger.getLogger(BookingDetails.class.getName()).log(Level.INFO,"ItemRequest {0}",itemRequest);

        //compare the selected item with items in the list 
        ArrayList<TrackingDescription> td = (ArrayList<TrackingDescription>) request.getSession().getAttribute("trackingDetails");
        Logger.getLogger(BookingDetails.class.getName()).log(Level.INFO,"Items in trackdescription {0}",td.size());
        TrackingDescription target = new TrackingDescription();
        for (TrackingDescription current : td) {
                if (current.getVehicle().getVehicleId() == itemRequest) {
                        target = current;
                        Logger.getLogger(BookingDetails.class.getName()).log(Level.INFO,"Current: {0}",target.getVehicle().getRegistrationNumber());
                        Logger.getLogger(BookingDetails.class.getName()).log(Level.INFO,"Vehicle Id from match:  {0}",target.getVehicle().getVehicleId());
                }
        }
        request.setAttribute("target",target);
        getServletContext().getRequestDispatcher("/booking_details.jsp").forward(request, response);
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
