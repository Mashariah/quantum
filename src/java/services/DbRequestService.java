/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:31:57 PM  : May 30, 2015
 */
package services;

import domain.RateModel;
import domain.Vehicle;
import domain.VehicleDescription;
import java.sql.Connection;
import java.sql.Date;
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
                String features = results.getString("features");
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
                        features, teaserImgFile, detailImgFile, thumbnail1File, thumbnail2File, thumbnail3File);
                //set the rate model on vehicle
                current.setRateModel(rm);
                current.setStatus(status);
                vehicleList.add(current);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.SEVERE, sqle.getLocalizedMessage());
        }
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
                Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.INFO, description.getEntertainment());
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
    
    public static boolean updateVehicleStatus(Connection connection, String sql){
        boolean done = false;
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "vehicle status update sql: {0}",sql);
        try{
        statement = connection.createStatement();
        if(statement.executeUpdate(sql)>0){
            Logger.getLogger(DbRequestService.class.getName()).log(Level.INFO, "Updated vehicle status");
            done = true;
        }
        }catch(SQLException sqle){
            Logger.getLogger(DbRequestService.class.getName()).log(Level.SEVERE, "Error updating vehicle status{0}", sqle.getMessage());
        }
       
        return done;
    }
}
