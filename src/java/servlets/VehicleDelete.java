/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:24:49 AM  : Jun 22, 2015
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
public class VehicleDelete extends HttpServlet {

  /**
   *  
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException 
   */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection connection = (Connection)getServletContext().getAttribute("connector");
       /**
        *1.  Get the selected vehicle id 
        * 2. Issue sql statement...
        */
        String vehicleToDelete = request.getParameter("target");
        Logger.getLogger(VehicleDelete.class.getName()).log(Level.INFO, "Target to delete is:{0} ",vehicleToDelete);
        String message ="";
        String deleteSql = "delete from cars where vehicle_id="+vehicleToDelete;
        
        int done = DbRequestService.deleteVehicle(connection, deleteSql);
        if(done>0){
            request.setAttribute(message, "Vehicle successfully deleted");
            Logger.getLogger(VehicleDelete.class.getName()).log(Level.INFO, "Deleted vehicle...");
        }else{
            request.setAttribute(message, "Error deleting vehicle");
            Logger.getLogger(VehicleDelete.class.getName()).log(Level.INFO, "Error deleting vehicle...");
        }
            getServletContext().getRequestDispatcher("/vlisting").forward(request, response);
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
