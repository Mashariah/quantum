/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:46:53 PM  : May 30, 2015
 */
package servlets;

import domain.Vehicle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ItemsCatalog extends HttpServlet {

    private ArrayList<Vehicle> vehiclesList;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * Read the database connection params from context Retrieve all vehicles
     * from database (paginate later....) Create list using retrieved records
     * Create attribute using list and forward to catalog.jsp for display
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            vehiclesList = new ArrayList<>();
        //get the connection obj
        Connection dbConn = (Connection) request.getServletContext().getAttribute("connector");
        if(dbConn==null){
            Logger.getLogger(ItemsCatalog.class.getName()).log(Level.SEVERE, "The connection object is null");
        }
        String sqlStatement = "select * from vehicles,vehicle_images where vehicles.vehicle_id = vehicle_images.vehicle_id;";

        //fetch results...and create list
        vehiclesList = DbRequestService.processQueryRequest(dbConn, sqlStatement);
        request.setAttribute("vehicles", vehiclesList);
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
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

