/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:31:57 PM  : May 30, 2015
 */
package services;

import domain.Booking;
import domain.RateModel;
import domain.TrackingDescription;
import domain.User;
import domain.Vehicle;
import domain.VehicleDescription;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public static ArrayList<Vehicle> processQueryRequest(Connection connection, String sql) {
        try {
            vehicleList = new ArrayList<>();
            statement = connection.createStatement();
            results = statement.executeQuery(sql);
            while (results.next()) {
                int vehicleId = results.getInt("vehicle_id");
                String reg_num = results.getString("registration_num");
                String make = results.getString("make");
                String model = results.getString("model");
                String color = results.getString("color");
                Date year = results.getDate("_year");
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
                vehicleList.add(current);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.SEVERE, sqle.getLocalizedMessage());
        }
        Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.INFO, "List of vehicles = {0}", vehicleList.size());
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
                Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.INFO, "Fuel capacity:{0}",description.getFuelCapacity());
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

    public static int getLastAddedVehicle(Connection c) {
        int last = 0;
        String sql = "select vehicle_id from cars order by vehicle_id desc limit 1;";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs != null && rs.next()) {
                last = rs.getInt("vehicle_id");
                Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "last vehicle id = {0}", last);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in getting last added: {0}", sqle.getMessage());
            sqle.printStackTrace();
        }
        Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "last vehicle id returning= {0}", last);
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

            ps.setString(5, "2016"); //convert date from java.util.Date to java.sql.Date
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Date:== {0}", v.getYear());
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
            ps.executeUpdate();

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

            ps2.executeUpdate();
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error in addVehicle: {0}", sqle.getMessage());
            sqle.printStackTrace();
            System.out.println("MySQL error code: " + sqle.getErrorCode());
            System.out.println("SQL state: " + sqle.getSQLState());
        }
        return isAdded;
    }

    /**
     * Get vehicle, status and booking details for tracking purposes
     */
    public static ArrayList getTrackingDetails(Connection conn, String sql) {

        ArrayList list = new ArrayList();

        Booking booking = new Booking();
        Vehicle vehicle = new Vehicle();
        User user = new User();

        try {
            statement = conn.createStatement();
            results = statement.executeQuery(sql);
            while (results.next()) {
            //create the objects based on the results...
                //vehicle
                vehicle.setMake(results.getString("make"));
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
                    Date year = results.getDate("_year");
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
}
