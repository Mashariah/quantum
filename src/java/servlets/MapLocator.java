/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:39:26 AM  : Jul 6, 2015
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import services.DbRequestService;

/**
 *
 * @author kelli Called via Ajax Get all track details (including geo coords) as
 * json array
 */
public class MapLocator extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connector");
        //  response.setContentType("text/html;charset=UTF-8");

        String selected = request.getParameter("selected");
        String sqlStatement = "select distinct coord_long,coord_lat,make,model,_year,registration_num,"
                + "cars.vehicle_id,first_name,last_name,email_address,phone,dt_pickup,dt_dropoff, bookings.user_id,"
                + "p_location,d_location from vehicle_coords join cars join users join bookings "
                + "on cars.vehicle_id=vehicle_coords.vehicle_id and bookings.user_id=users.user_id "
                + "and bookings.vehicle_id=vehicle_coords.vehicle_id where bookings.vehicle_id="+selected;

        Logger.getLogger(CatalogFilter.class.getName()).log(Level.INFO, sqlStatement);

        //get the matches for this target 
        ArrayList mapDetails = DbRequestService.getTrackMapDetails(connection, sqlStatement);
        JSONArray array = new JSONArray();
        JSONObject responseObj = null;
        //format arraylist as json object 
        for (Object object : mapDetails) {
            responseObj= new JSONObject(object);
//            array.put(obj);
        }
        try {
            Logger.getLogger(CatalogFilter.class.getName()).log(Level.INFO, array.toString(3));
        } catch (JSONException ex) {
            Logger.getLogger(CatalogFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        //write the response
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        out.write(array.toString());
            Logger.getLogger(CatalogFilter.class.getName()).log(Level.INFO, responseObj.toString());
        out.write(responseObj.toString());
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
