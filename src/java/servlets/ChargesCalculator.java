/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 5:08:02 PM  : Jun 6, 2015
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import services.DateCalc;

/**
 *
 * @author kelli
 */
public class ChargesCalculator extends HttpServlet {

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
        
        JSONObject jsobj = new JSONObject();
        //get the date and time parameters from the url
        String dFrom = request.getParameter("dateFrom");
        String dTo = request.getParameter("dateTo");
        String tFrom = request.getParameter("timerFrom");
        String tTo = request.getParameter("timerTo");
        Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO,"param timeForm: "+tFrom);
        
        //selected calculation options
        String calcOption = request.getParameter("calcOption");
        String houly = request.getParameter("hCharge");
        String daily = request.getParameter("dCharge");
        String weekly = request.getParameter("wCharge");
        Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO,"param weekly: "+weekly);
        
        //create date and time objects from the parameter values
        String from = dFrom+" "+tFrom; //(03/07/15 16:49 PM)
        String to = dTo+" "+tTo;
        Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO,"date string from:: "+from);
        Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO,"date string to:: "+to);
        
        Date dateFrom = DateCalc.stringToDate(from);
        Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO,"Pickup: "+dateFrom.toString());
        Date dateTo = DateCalc.stringToDate(to);
        Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO,"Dropoff: "+dateTo.toString());
        
        long duration =0; double amount=0;
        String indicator = "";
        //get the selected option for calculation: days, hours or weeks?
            switch(calcOption){
                case "hourly":
                    indicator ="hours";
                    duration=DateCalc.getHours(DateCalc.compareDates(dateFrom, dateTo));
                    Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO,"millsecs: "+DateCalc.compareDates(dateFrom, dateTo));
                    amount = Integer.parseInt(houly)*duration;
                    break;
                case "daily":
                    indicator = "days";
                    duration=DateCalc.getDays(DateCalc.compareDates(dateFrom, dateTo));
                    amount = Integer.parseInt(daily)*duration;
                    break;
                case "weekly":
                    indicator = "weeks";
                    duration=DateCalc.getWeeks(DateCalc.compareDates(dateFrom, dateTo));
                    amount = Integer.parseInt(weekly)*duration;
                    break;
            }
                    
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
        jsobj.put("duration",duration);
        jsobj.put("amount",amount);
        jsobj.put("indicator",indicator);
        }catch(JSONException jse){
        out.print("json error occured");
        }
        out.print(jsobj.toString());
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
