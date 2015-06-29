/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:46:53 PM  : May 30, 2015
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
public class ItemsCatalog extends HttpServlet {


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
        final int ROWS_PER_PAGE = 6;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int pageCounter = 1;
        int offsetValue =0;
        String page = request.getParameter("page");
        
        if(page!=null){
            pageCounter = Integer.parseInt(page);
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Page counter is: {0}",pageCounter);
            offsetValue = (pageCounter-1) * ROWS_PER_PAGE;
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Offset value is: {0}",offsetValue);
        }
        
        
     ArrayList<Vehicle> vehiclesList;
            vehiclesList = new ArrayList<>();
        //get the connection obj
        Connection dbConn = (Connection) request.getServletContext().getAttribute("connector");
        if(dbConn==null){
            Logger.getLogger(ItemsCatalog.class.getName()).log(Level.SEVERE, "The connection object is null");
        }
        String sqlStatement = "select cars.vehicle_id,registration_num,make,model,color,_year,teaser_img,detail_img,thumbnail1_img,"
                + "thumbnail2_img,thumbnail3_img,daily_charge,hourly_charge,weekly_charge,status from cars"
                + " join rate_model right join vehicle_status on cars.rates = rate_model.rate_id and "
                + "cars.vehicle_id = vehicle_status.vehicle_id order by cars.vehicle_id desc "
                + "limit "+ ROWS_PER_PAGE+" offset "+offsetValue+";";

        //fetch results...and create list
        vehiclesList = DbRequestService.processQueryRequest(dbConn, sqlStatement,(pageCounter-1)*ROWS_PER_PAGE);
        request.getSession().setAttribute("vehicles", vehiclesList); //make object available accross this user session
        request.setAttribute("page_count",pageCounter);
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

