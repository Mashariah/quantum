/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 9:26:41 PM  : Jun 7, 2015
 */
package servlets;

import domain.Booking;
import domain.User;
import domain.Vehicle;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DbRequestService;
import services.MailMan;

/**
 *
 * @author kelli
 */
public class BookingAgent extends HttpServlet {

    Connection dbConn = null;
    static final int BOOKED = 2;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbConn = (Connection) request.getServletContext().getAttribute("connector");

        /**
         * 1. Retrieve user if login present else create new user (visitor type)
         * and persist 2. Get other user form input 3. Retrieve car id from
         * request attribute 'vehicles' 4. create new booking record and persist
         * to bookings table 5. Update the vehicle status to indicate booked
         */
        Vehicle vehicle;
        User user;

// get pickup and dropoff date and times
        String dtPickup = request.getParameter("tx_dpickup")+" "+request.getParameter("tx_tpickup");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "pickup date+time: {0}",dtPickup);
        String dtDropoff = request.getParameter("tx_ddropoff")+" "+request.getParameter("tx_tdropoff");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "dropoff date+time: {0}",dtDropoff);
        
 //get the pickup and dropoff locations 
        String pLocation = request.getParameter("tx_pickup_loc") + request.getParameter("opt_pickup_town");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "pickup location: {0}",pLocation);
        String dLocation = "";
 //     the same-location dropoff checkbox check
        String [] dropoffLoc = request.getParameterValues("checkbx_dropoff");
        if(dropoffLoc!=null){
        dLocation = pLocation;
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, " dropoff checkbox state is : {0}",dropoffLoc[0]);
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "same dropoff  location is : {0}",dLocation);
        }else{
            dLocation = request.getParameter("tx_dropoff");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "different  dropoff entered:: {0}",dLocation);
        }
        
        int persistedUserId =0; //the user id returned after creating the record
        user = (User) request.getSession().getAttribute("user");
        vehicle = (Vehicle) getServletContext().getAttribute("target_vehicle");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "vehicle selected in session: {0}",vehicle.getRegistrationNumber());
        if (user == null) {
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "user is null... creating...");
            //create new user with supplied details
            String fName = request.getParameter("tx_fname");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "First Name: {0}",fName);
            String lName = request.getParameter("tx_lname");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Last Name: {0}",lName);
            String email = request.getParameter("tx_email");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Email: {0}",email);
            String phoneNumber = request.getParameter("tx_phone");
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Phone: {0}",phoneNumber);
            user = new User(fName, lName, email, phoneNumber);
            user.setType("visitor");
            persistedUserId =persistUser(user);
            if(persistedUserId==0){
                //the adding of visitor user failed so stop ...get user back to page and show error..
                request.setAttribute("user_add_error", "The Email address provided is already in use!");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/booking.jsp");
                dispatcher.forward(request, response);
            }
            }
//          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "user logged in session: {0}",user.getEmail());
        
        //make the booking
        int vehicleId = vehicle.getVehicleId();
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Vehicle Id=: {0}",vehicleId);
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "User Id=: {0}",persistedUserId);
            Booking booking = new Booking(persistedUserId, vehicleId, dtPickup,dtDropoff, pLocation, dLocation);
            persistBooking(booking);
            
//update the vehicle status table to set booked status
            
            String statusUpdateSql = "update  vehicle_status set status=2 where vehicle_id="+vehicleId+";";
            DbRequestService.updateVehicleStatus(dbConn, statusUpdateSql);
//send confirmation email and prompt for payment code 
            try{
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Starting mail...");
           if(new  MailMan().sendConfirmation(user.getEmail(),"ALLEXUS CAR HIRE: BOOKING CONFIRMATION",
                   "Thank you "+user.getfName()+" for choosing us."
                   + "\nWe have received your booking request as follows:\nVehicle: "+vehicle.getMake()+" "+vehicle.getModel()+" "
                   + "registration number: "+vehicle.getRegistrationNumber()+"\n Hiring from: "+dtPickup+" to "+dtDropoff+"\n"
           +"Please enter the code below or as received in SMS to complete your booking")){
               RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/comfirmation.jsp");
               dispatcher.forward(request, response);
           }
           else{
               RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
               dispatcher.forward(request, response);
               
           }
            }
              catch(AddressException adrexp){
                              Logger.getLogger(BookingAgent.class.getName()).log(Level.SEVERE, "Address Error: {0}",adrexp.getMessage());

              }catch( MessagingException msgexp){
                              Logger.getLogger(BookingAgent.class.getName()).log(Level.SEVERE, " Messaging Error: {0}",msgexp.getMessage());

              }
  // Show summary of booking on confirmation page...prompt for 
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

    /**
     * Persist user to database and get the user id back;
     */
    private int persistUser(User user) {
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Inside persist user... {0}");
        int userId =0;
        int userGroup=0;
        switch(user.getType()){
            case "visitor":
                userGroup = 3;
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "case visitor....");
                break;
            case "member":
                userGroup = 1;
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "case member....");
                break;
        }
        String userAddSql = "insert into users(first_name,last_name,email_address,phone,type) "
                + "values('"+user.getfName()+"','"+user.getlName()+"','"+user.getEmail()+"','"+user.getPhone()+"',"+userGroup+ ");";
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "userAddSql= {0} ",userAddSql);
        if(DbRequestService.addRecord(dbConn, userAddSql)>0){ 
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Sucess in adding user ...");
          //get the user_id
          String idSql = "select user_id from users where email_address ='"+user.getEmail()+"'";
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "idSql = {0}",idSql);
          userId = DbRequestService.getUserId(dbConn, idSql);
        }else{
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Error in adding user, userId is =",userId);
            //some error occured 
        }
            return userId;
    }
    
    private boolean persistBooking(Booking booking){
        boolean done = false;
        
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Inside persist booking...");
        String bookingSql = "insert into bookings(vehicle_id,user_id,dt_pickup,dt_dropoff,p_location,d_location) "
                + "values('"+booking.getVehicleId()+"','"+booking.getUserId()+"','"+booking.getDtPickup()+
                "','"+booking.getDtDropoff()+ "','"+booking.getpLocation()+"','"+booking.getdLocation()+"');";
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "bookingSql = {0}",bookingSql);
         if(DbRequestService.addRecord(dbConn, bookingSql)>0){
          Logger.getLogger(BookingAgent.class.getName()).log(Level.INFO, "Sucess in adding booking");
          done = true;
         }
        return done;
    }
}
