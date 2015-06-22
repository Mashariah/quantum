/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 7:51:38 AM  : Jun 19, 2015
 */

package servlets;

import domain.BookingDetails;
import domain.User;
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
public class UserBooking extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        /**
         *  Get the logged-in user details from db and the booking info.
         */
        Connection conn = (Connection) getServletContext().getAttribute("connector");
        
        User  user = (User)request.getSession().getAttribute("user");
        Logger.getLogger(UserBooking.class.getName()).log(Level.INFO, "");
        int  userId =  user.getUserId();
        Logger.getLogger(UserBooking.class.getName()).log(Level.INFO, "user id ="+userId);
        String bookingSql="select registration_num, teaser_img, make,model, dt_pickup,dt_dropoff,p_location,"
                + "d_location from bookings join cars where cars.vehicle_id = bookings.vehicle_id and user_id ="+userId;
        
        Logger.getLogger(UserBooking.class.getName()).log(Level.INFO, "Booking Details SQL:"+bookingSql);
        //Get the booking...
        BookingDetails bookingDetails = DbRequestService.getUserBooking(conn,bookingSql);
        if(bookingDetails!=null){
        Logger.getLogger(UserBooking.class.getName()).log(Level.INFO, "Booking Time:"+bookingDetails.getBooking().getDtPickup());
        }else{
        Logger.getLogger(UserBooking.class.getName()).log(Level.SEVERE,"Booking Details is empty");
        }
        request.setAttribute("user_booking",bookingDetails);
        getServletContext().getRequestDispatcher("/mybookings.jsp").forward(request, response);
        
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
