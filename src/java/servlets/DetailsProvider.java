/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 6:49:03 PM  : Jun 3, 2015
 */

package servlets;

import domain.VehicleDescription;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DbRequestService;

/**
 *
 * @author kelli
 * Get details on selected vehicle and forward results to details page 
 */
public class DetailsProvider extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Connection dbConn = (Connection) request.getServletContext().getAttribute("connector");
        response.setContentType("text/html;charset=UTF-8");
        
        String selectedVehicle = request.getParameter("selected_vehicle");
        
        String descriptionSql = "select vehicle_id,fuel_consumption,fuel_capacity,transmission,seating_cap,convinience,"
                + "safety_security,entertainment,telematics,tire_wheels from car_features where vehicle_id="+selectedVehicle;
        VehicleDescription desc = DbRequestService.getVehicleDetails(dbConn, descriptionSql);
        
//        Logger.getLogger(DetailsProvider.class.getName()).log(Level.INFO, desc.getEntertainment());
        request.getSession().setAttribute("description", desc); //make object available accross this user session
        request.setAttribute("vehicleId", desc.getVehicleId());// for forwarding to the details.jsp image server...
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/details.jsp");
       dispatcher.forward(request, response);
        

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
