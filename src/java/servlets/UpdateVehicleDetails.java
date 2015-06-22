/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 12:19:38 AM  : Jun 21, 2015
 */
package servlets;

import domain.Vehicle;
import domain.VehicleDescription;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DbRequestService;

/**
 *
 * @author kelli
 */
public class UpdateVehicleDetails extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Read connection object from context
        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("connector");
        
        String navPath = "";
        // get parameters
        String vehicleId = request.getParameter("tx_vehicleid");
        Logger.getLogger(UpdateVehicleDetails.class.getName()).log(Level.INFO, "vehicle is = {0}",vehicleId);
        String registrationNum = request.getParameter("tx_regnum");
        String model = request.getParameter("tx_model");
        String make = request.getParameter("drp_carmake");
        String year = request.getParameter("drp_caryear");
        Logger.getLogger(UpdateVehicleDetails.class.getName()).log(Level.INFO, "Year is: {0}",year);
        
        //features
        String fuelEconomy = request.getParameter("tx_fueleconomy");
        String fuelCapacity = request.getParameter("tx_fuelcapacity");
        String transmission = request.getParameter("drp_transmission");
        String seatingCapacity = request.getParameter("drp_scapacity");
        String convenience = request.getParameter("txb_convenience");
        String safetySec = request.getParameter("txb_safetysec");
        String entertainment = request.getParameter("txb_entertainment");
        String telematics = request.getParameter("txb_telematics");
        String tiresWheels = request.getParameter("txb_tireswheels");
        //create the representational vehicle obj and its description...
        VehicleDescription description = new VehicleDescription(Integer.parseInt(vehicleId), fuelEconomy, 
                fuelCapacity, transmission,Integer.parseInt(seatingCapacity), convenience, safetySec, entertainment,
                telematics, tiresWheels);
        Vehicle vehicle = new Vehicle(Integer.parseInt(vehicleId),registrationNum, make, model,year, description);
        //update database record for this vehicle...
        
        if (DbRequestService.updateVehicleDetails(connection, vehicle)) {
            Logger.getLogger(NewCar.class.getName()).log(Level.INFO, "Updating ....");
            navPath = "/vlisting";
        }else{
            //update failed.. revert to the same page.
            navPath = "/editcar.jsp";
        }

        getServletContext().getRequestDispatcher(navPath).forward(request, response);
        //check if the form contains upload files
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

private static Date getYear(String date){
    Date carYear = null;
    SimpleDateFormat df = new SimpleDateFormat("yy");
        try {
            carYear = df.parse(date);
            Logger.getLogger(UpdateVehicleDetails.class.getName()).log(Level.INFO, "date in getYear is= {0}",carYear);
            
        } catch (ParseException ex) {
            Logger.getLogger(UpdateVehicleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carYear;
}



}
