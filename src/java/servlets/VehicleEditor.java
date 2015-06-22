/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:55:49 AM  : Jun 20, 2015
 */

package servlets;

import domain.Vehicle;
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
public class VehicleEditor extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        /** 
         * 1. Get the selected vehicle 
         * 2. Issue query for all details of the selected vehicle 
         * 3. Populate the editcar.jsp with details of the selected vehicle.
         */
        Connection  connection = (Connection)getServletContext().getAttribute("connector");
        int targetVehicle = Integer.parseInt(request.getParameter("target"));
        
        //gets all the details of the selected vehicle and create representative vehicle object
        String selectedVehicleSql = "select * from cars join car_features on cars.vehicle_id=car_features.vehicle_id and"
                + " cars.vehicle_id="+targetVehicle;
        Vehicle vehicle = DbRequestService.editVehicle(connection, selectedVehicleSql);
        
        if(vehicle!=null){
            request.setAttribute("target_vehicle",vehicle);
        }else{
            //unlikely...but no vehicle with that id..yet selected?
            Logger.getLogger(VehicleEditor.class.getName()).log(Level.INFO, "vehicle is somehow null");
            return;
        }
//        populate the editcar form with the vehicle object attributes
        getServletContext().getRequestDispatcher("/editcar.jsp").forward(request, response);
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
