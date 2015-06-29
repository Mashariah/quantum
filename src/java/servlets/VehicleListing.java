/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 8:43:47 PM  : Jun 13, 2015
 */

package servlets;

import domain.Vehicle;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
 */
public class VehicleListing extends HttpServlet {

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
        
ArrayList<Vehicle> vehiclesList;
        //get the connection obj
        Connection dbConn = (Connection) request.getServletContext().getAttribute("connector");
        if(dbConn==null){
            Logger.getLogger(ItemsCatalog.class.getName()).log(Level.SEVERE, "The connection object is null");
        }
        String sqlStatement = "select cars.vehicle_id,registration_num,make,model,color,_year,teaser_img,detail_img,thumbnail1_img,"
                + "thumbnail2_img,thumbnail3_img,daily_charge,hourly_charge,weekly_charge,status from cars"
                + " join rate_model right join vehicle_status on cars.rates = rate_model.rate_id and cars.vehicle_id = vehicle_status.vehicle_id"
                + " order by cars.vehicle_id desc;";

        //fetch results...and create list
        vehiclesList = DbRequestService.processQueryRequest(dbConn, sqlStatement,0);
        request.getSession().setAttribute("vehicles", vehiclesList); //make object available accross this user session
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicle_listing.jsp");
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
