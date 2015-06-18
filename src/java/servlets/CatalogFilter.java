/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:05:18 PM  : Jun 16, 2015
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
 * @author kelli
 */
public class CatalogFilter extends HttpServlet {

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
        Connection connection = (Connection) getServletContext().getAttribute("connector");
        //  response.setContentType("text/html;charset=UTF-8");
        String parameters = request.getParameter("target");

        String sqlStatement = "select cars.vehicle_id,registration_num,make,model,color,_year,teaser_img,detail_img,thumbnail1_img,"
                + "thumbnail2_img,thumbnail3_img,daily_charge,hourly_charge,weekly_charge,status from cars"
                + " join rate_model right join vehicle_status on cars.make = '" + parameters + "' and cars.rates = rate_model.rate_id and cars.vehicle_id = vehicle_status.vehicle_id ;";

        Logger.getLogger(CatalogFilter.class.getName()).log(Level.INFO, parameters);
        Logger.getLogger(CatalogFilter.class.getName()).log(Level.INFO, sqlStatement);

        //get the matches for this target 
        ArrayList filtered = DbRequestService.getFilterResults(connection, sqlStatement);
        JSONArray array = new JSONArray();
        //format arraylist as json object 
        for (Object object : filtered) {
            JSONObject obj = new JSONObject(object);
            array.put(obj);
        }
        try {
            Logger.getLogger(CatalogFilter.class.getName()).log(Level.INFO, array.toString(3));
        } catch (JSONException ex) {
            Logger.getLogger(CatalogFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

         //write the response
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(array.toString());
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
            throws ServletException, IOException{
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
