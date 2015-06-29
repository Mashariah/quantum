/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:31:57 PM  : May 30, 2015
 */
package services;

import domain.Booking;
import domain.BookingDetails;
import domain.RateModel;
import domain.TrackingDescription;
import domain.User;
import domain.Vehicle;
import domain.VehicleDescription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Process all database requests on request from specific servlets. Database
 * connection already setup by servlet: ConnectionInit
 *
 * @author kelli
 */
public class DbRequestService {

    private static ResultSet results = null;
    private static Statement statement;
    private static ArrayList<Vehicle> vehicleList;

    public static ArrayList<Vehicle> processQueryRequest(Connection connection, String sql, int startPage) {
        try {

            SimpleDateFormat df = new SimpleDateFormat("YYYY");

            vehicleList = new ArrayList<>();
            statement = connection.createStatement();
            results = statement.executeQuery(sql);
            while (results.next()) {
                int vehicleId = results.getInt("vehicle_id");
                String reg_num = results.getString("registration_num");
                String make = results.getString("make");
                String model = results.getString("model");
                String color = results.getString("color");
                String year = df.format(results.getDate("_year"));
                String teaserImgFile = results.getString("teaser_img");
                String detailImgFile = results.getString("detail_img");
                String thumbnail1File = results.getString("thumbnail1_img");
                String thumbnail2File = results.getString("thumbnail2_img");
                String thumbnail3File = results.getString("thumbnail3_img");
                String status = results.getString("status");

                //get  and create the rate model
                Double daily = results.getDouble("daily_charge");
                Double hourly = results.getDouble("hourly_charge");
                Double weekly = results.getDouble("weekly_charge");

                RateModel rm = new RateModel(hourly, daily, weekly);
                Vehicle current = new Vehicle(vehicleId, reg_num, make, model, color, year,
                        teaserImgFile, detailImgFile, thumbnail1File, thumbnail2File, thumbnail3File);
                //set the rate model on vehicle
                current.setRateModel(rm);
                current.setStatus(status);
                vehicleList.add(current);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.SEVERE, sqle.getLocalizedMessage());
        }
        Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.INFO, "List of vehicles for caatalog {0}", vehicleList.size());
        return vehicleList;
    }

    /**
     * Get details of the selected vehicle
     *
     * @param connection
     * @param sql
     * @return
     */
    public static VehicleDescription getVehicleDetails(Connection connection, String sql) {
        VehicleDescription description = null;
        try {
            statement = connection.createStatement();
            results = statement.executeQuery(sql);
            while (results.next()) {
                int vehicleId = results.getInt("vehicle_id");
                String fConsumption = results.getString("fuel_consumption");
                String fCapacity = results.getString("fuel_capacity");
                String transmission = results.getString("transmission");
                int seating = results.getInt("seating_cap");
                String convinience = results.getString("convinience");
                String sasfety = results.getString("safety_security");
                String entertainment = results.getString("entertainment");
                String telematics = results.getString("telematics");
                String tireWheels = results.getString("tire_wheels");

                description = new VehicleDescription(vehicleId, fConsumption, fCapacity, transmission, seating,
                        convinience, sasfety, entertainment, telematics, tireWheels);
                Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.INFO, "Fuel capacity:{0}", description.getFuelCapacity());
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.SEVERE, sqle.getLocalizedMessage());
        }
        return description;
    }

    public static int addRecord(Connection c, String sql) {

        int sucess = 0;
        try {
            statement = c.createStatement();
            sucess = statement.executeUpdate(sql);
            Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.INFO, "Added user and got: {0}", sucess);
        } catch (SQLException sqe) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in persisting user: {0}", sqe.getLocalizedMessage());
        }
        return sucess;
    }

    public static int getUserId(Connection connection, String sql) {
        int userId = 0;
        try {
            statement = connection.createStatement();
            results = statement.executeQuery(sql);
            while (results.next()) {
                userId = results.getInt("user_id");
                Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Got result: {0}", userId);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in retrieving user id", sqle.getMessage());
            sqle.printStackTrace();
        }
        return userId;
    }

    public static boolean updateVehicleStatus(Connection connection, String sql) {
        boolean done = false;
        Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "vehicle status update sql: {0}", sql);
        try {
            statement = connection.createStatement();
            if (statement.executeUpdate(sql) > 0) {
                Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Updated vehicle status");
                done = true;
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error updating vehicle status{0}", sqle.getMessage());
        }

        return done;
    }

    /**
     * New Vehicle insert transaction Get the id of the vehicle added last; use
     * the retrieved id to attach the description details in the related table
     *
     * @param c the connection object
     * @return the id of the last inserted vehicle in the vehicles table.
     */
    public static int getLastAddedVehicle(Connection c) {
        int last = 0;
        String sql = "select vehicle_id from cars order by vehicle_id desc limit 1;";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs != null && rs.next()) {
                last = rs.getInt("vehicle_id");
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in getting last added: {0}", sqle.getMessage());
            sqle.printStackTrace();
        }
        Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Id of the last inserted vehicle: {0}", last);
        return last;
    }

    public static boolean addVehicle(Connection conn, Vehicle v, VehicleDescription vd) {
        boolean isAdded = false;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        String vehicleSql = "insert into cars(registration_num,make,model,color,_year,"
                + "teaser_img,detail_img,thumbnail1_img,thumbnail2_img,thumbnail3_img,rates) "
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(vehicleSql);
            ps.setString(1, v.getRegistrationNumber());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Model: {0}", v.getRegistrationNumber());
            ps.setString(2, v.getMake());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Model: {0}", v.getMake());
            ps.setString(3, v.getModel());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Model: {0}", v.getModel());
            ps.setString(4, v.getColor());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Color: {0}", v.getColor());

            ps.setString(5, v.getYear());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Date:  {0}", v.getYear());
            ps.setString(6, v.getTeaserImg());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "TeaserImg: {0}", v.getTeaserImg());
            ps.setString(7, v.getDetailImg());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "DetailImg: {0}", v.getDetailImg());
            ps.setString(8, v.getThumbnail1Img());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Thumbnail1: {0}", v.getThumbnail1Img());
            ps.setString(9, v.getThumbnail2Img());
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Thumbnail2: {0}", v.getThumbnail2Img());
            ps.setString(10, v.getThumbnail2Img());
            ps.setInt(11, 3);
            ps.executeUpdate(); //insert vehicle record ...use its generated id below for description

            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Success adding bare vehicle{0}");
            //add description 
//        int vehicleToUpdate;
            String vehicleFeatures = "insert into car_features(vehicle_id,fuel_consumption,fuel_capacity,transmission,seating_cap,"
                    + "convinience,safety_security,entertainment,telematics,tire_wheels) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int vehicleToUpdate = getLastAddedVehicle(conn);
            ps2 = conn.prepareCall(vehicleFeatures);
            ps2.setInt(1, vehicleToUpdate);
            ps2.setString(2, vd.getFuelConsumption());
            ps2.setString(3, vd.getFuelCapacity());
            ps2.setString(4, vd.getTransmission());
            ps2.setInt(5, vd.getSeatingCapacity());
            ps2.setString(6, vd.getConvinience());
            ps2.setString(7, vd.getSafetyAndSecurity());
            ps2.setString(8, vd.getEntertainment());
            ps2.setString(9, vd.getTelematics());
            ps2.setString(10, vd.getTireWheels());

            ps2.executeUpdate(); //run insert statement for vehicle description.

            // !important: update the vehicle status table for new vehicle
            String statusSql = "insert into vehicle_status(vehicle_id,status) values(" + vehicleToUpdate + ",1);";
            int state = statement.executeUpdate(statusSql);
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in addVehicle: {0}", sqle.getMessage());
        }
        return isAdded;
    }

    /**
     * Get vehicle, status and booking details for tracking purposes
     *
     * @param conn
     * @param sql
     * @return
     */
    public static ArrayList getTrackingDetails(Connection conn, String sql) {

        ArrayList list = new ArrayList();


        try {
            statement = conn.createStatement();
            results = statement.executeQuery(sql);
            while (results.next()) {
                //create the objects based on the results...
        Booking booking = new Booking();
        Vehicle vehicle = new Vehicle();
        User user = new User();
                //vehicle
                vehicle.setTeaserImg(results.getString("teaser_img"));
                vehicle.setTeaserImg(results.getString("detail_img"));
                vehicle.setMake(results.getString("make"));
                vehicle.setVehicleId(results.getInt("vehicle_id"));
                vehicle.setModel(results.getString("model"));
                vehicle.setRegistrationNumber(results.getString("registration_num"));

                //booking
                booking.setDtPickup(results.getString("dt_pickup"));
                booking.setDtDropoff(results.getString("dt_dropoff"));
                booking.setdLocation(results.getString("d_location"));
                booking.setpLocation(results.getString("p_location"));

                //user/renter
                user.setfName(results.getString("first_name"));
                user.setlName(results.getString("last_name"));
                user.setEmail(results.getString("email_address"));
                user.setPhone(results.getString("phone"));
                //set the objects into the arraylist
                TrackingDescription td = new TrackingDescription(vehicle, booking, user);
                list.add(td);
            }

        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE,
                    "Error getting track details" + sqle.getLocalizedMessage());
            sqle.printStackTrace();
        }
        Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "items in list: " + list.size());
        return list;
    }

    /**
     * Add a new user default is member
     *
     * @param connection
     * @param sql
     * @return
     */
    public static int createUser(Connection connection, User u) {
        PreparedStatement psUser;
        int DEFAULT_GROUP = 1;
        int resultState = 0;
        String userAddSql = "insert into users"
                + " (first_name,last_name,username,email_address,phone,password,salt,type)"
                + " values(?,?,?,?,?,?,?,?);";
        try {
            psUser = connection.prepareCall(userAddSql);
            psUser.setString(1, u.getfName());
            psUser.setString(2, u.getlName());
            psUser.setString(3, u.getUserName());
            psUser.setString(4, u.getEmail());
            psUser.setString(5, u.getPhone());
            psUser.setString(6, u.getPassHash());
            psUser.setString(7, u.getSalt());
            psUser.setInt(8, DEFAULT_GROUP);
            resultState = psUser.executeUpdate();
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Result from userAdd: {0}", resultState);

        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "SQL Error: " + sqle.getLocalizedMessage());
        }
        return resultState;
    }

    /**
     * Get matching vehicles
     *
     * @param connection
     * @param sql
     * @return list of vehicles
     */
    public static ArrayList getFilterResults(Connection connection, String sql) {

        ArrayList filteredResults = new ArrayList();
        try {
            statement = connection.createStatement();
            results = statement.executeQuery(sql);
            while (results.next()) {
                int vehicleId = results.getInt("vehicle_id");
                Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "vehicle id={0}", vehicleId);
                if (vehicleId > 0) { //for any non null item
                    //create a vehicle description item 
                    String reg_num = results.getString("registration_num");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    String color = results.getString("color");
                    String year = String.valueOf(results.getDate("_year").getYear());
                    String teaserImgFile = results.getString("teaser_img");
                    String detailImgFile = results.getString("detail_img");
                    String thumbnail1File = results.getString("thumbnail1_img");
                    String thumbnail2File = results.getString("thumbnail2_img");
                    String thumbnail3File = results.getString("thumbnail3_img");
                    String status = results.getString("status");

                    //get  and create the rate model
                    Double daily = results.getDouble("daily_charge");
                    Double hourly = results.getDouble("hourly_charge");
                    Double weekly = results.getDouble("weekly_charge");

                    RateModel rm = new RateModel(daily, hourly, weekly);
                    Vehicle current = new Vehicle(vehicleId, reg_num, make, model, color, year,
                            teaserImgFile, detailImgFile, thumbnail1File, thumbnail2File, thumbnail3File);
                    //set the rate model on vehicle
                    current.setRateModel(rm);
                    current.setStatus(status);
                    filteredResults.add(current);
                }
            }
        } catch (SQLException sqe) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "SQL Error: {0}", sqe.getLocalizedMessage());
            sqe.printStackTrace();
        }

        return filteredResults;
    }

    //
    public static BookingDetails getUserBooking(Connection connection, String sql) {
        BookingDetails details = null;
        Booking b = new Booking();
        Vehicle v = new Vehicle();
        User u = new User();
        try {
            results = connection.createStatement().executeQuery(sql);
            while (results.next()) {
                b.setDtPickup(results.getString("dt_pickup"));
                b.setDtDropoff(results.getString("dt_dropoff"));
                b.setdLocation(results.getString("d_location"));
                b.setpLocation(results.getString("p_location"));
                Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Adding to booking detail...");

                //vehicle 
                v.setMake(results.getString("make"));
                v.setModel(results.getString("model"));
                v.setTeaserImg(results.getString("teaser_img"));
                v.setRegistrationNumber(results.getString("registration_num"));
                details = new BookingDetails(b, v);
            }
        } catch (SQLException sqe) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "SQL Error in getting booking details: {0}", sqe.getLocalizedMessage());
        }
        return details;
    }

    /* 
     Edit details of a selected vehicle
     */
    public static Vehicle editVehicle(Connection conn, String sql) {

//        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("YYYY");
        Vehicle targetVehicle = null;
        try {
            statement = conn.createStatement();
            results = statement.executeQuery(sql);
            //expecting 1 result in the set
            while (results.next()) {

                //work on the year formatting
                Date carYear = results.getDate("_year");
                String year = df.format(carYear);
                Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Year after format is:  {0}", year);
                //recreate the vehicle and its description
                targetVehicle = new Vehicle(results.getInt("vehicle_id"),
                        results.getString("registration_num"),
                        results.getString("make"),
                        results.getString("model"),
                        results.getString("color"),
                        year,
                        //                        String.valueOf(results.getDate("_year")),
                        results.getString("teaser_img"),
                        results.getString("detail_img"),
                        results.getString("thumbnail1_img"),
                        results.getString("thumbnail2_img"),
                        results.getString("thumbnail3_img"));
                //the description 
                VehicleDescription description = new VehicleDescription(results.getInt("vehicle_id"),
                        results.getString("fuel_consumption"),
                        results.getString("fuel_capacity"),
                        results.getString("transmission"),
                        results.getInt("seating_cap"),
                        results.getString("convinience"),
                        results.getString("safety_security"),
                        results.getString("entertainment"),
                        results.getString("telematics"),
                        results.getString("tire_wheels"));
                targetVehicle.setDescription(description);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in edit sql" + ex.getMessage());
        }
        return targetVehicle;
    }

    public static boolean updateVehicleDetails(Connection c, Vehicle v) {
        boolean success = false;
        //update vehicle
        String vehicleUpdateSql = "update  cars set registration_num= '" + v.getRegistrationNumber() + "',make='" + v.getMake()
                + "',model='" + v.getModel() + "',_year='" + v.getYear() + "'  where cars.vehicle_id =" + v.getVehicleId();
        Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "vehicles update sql ={0},", vehicleUpdateSql);
        //update vehicle description 
        VehicleDescription vd = v.getDescription();
        String descriptionUpdateSql = "update  car_features set fuel_consumption='" + vd.getFuelConsumption()
                + "',fuel_capacity='" + vd.getFuelCapacity() + "',transmission='" + vd.getTransmission() + "',seating_cap=" + vd.getSeatingCapacity()
                + ",convinience='" + vd.getConvinience() + "',safety_security='" + vd.getSafetyAndSecurity() + "',entertainment='" + vd.getEntertainment()
                + "',telematics='" + vd.getTelematics() + "',tire_wheels='" + vd.getTireWheels() + "' where car_features.vehicle_id =" + v.getVehicleId();;
        Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "desc update sql ={0}", descriptionUpdateSql);
        try {
            statement = c.createStatement();
            int vehiclesUpdated = statement.executeUpdate(vehicleUpdateSql);
            int descriptionsUpdates = statement.executeUpdate(descriptionUpdateSql);
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "vehiclesUpdate =" + vehiclesUpdated);
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "descriptionUpdate =" + descriptionsUpdates);
            success = true;
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in executing update sql" + sqle.getMessage());

        }
        return success;
    }

    public static int deleteVehicle(Connection c, String deleteSql) {
        int state = 0;
        try {
            statement = c.createStatement();
            state = statement.executeUpdate(deleteSql);
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Deleting state...{0}", state);
        } catch (SQLException ex) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in deleting vehicle...{0}", ex.getMessage());
        }
        return state;
    }

    public static ArrayList<Vehicle> searchVehicle(Connection connection, String searchTarget) {
        String sqlStatement = "select cars.vehicle_id,registration_num,make,model,color,_year,teaser_img,detail_img,thumbnail1_img,"
                + "thumbnail2_img,thumbnail3_img,daily_charge,hourly_charge,weekly_charge,status from cars"
                + " join rate_model  join vehicle_status on cars.rates = rate_model.rate_id and cars.vehicle_id ="
                + " vehicle_status.vehicle_id and cars.make='" + searchTarget + "'";
        Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Search sql is: {0}", sqlStatement);
        ArrayList<Vehicle> results = processQueryRequest(connection, sqlStatement, 0);
        return results;
    }

}
