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
                            
                            //get  and create the rate model
                            Double daily = results.getDouble("daily_charge");
                            Double hourly = results.getDouble("hourly_charge");
                            Double weekly = results.getDouble("weekly_charge");
                            
                            RateModel rm = new RateModel(daily,hourly,weekly);
                            Vehicle current = new Vehicle(vehicleId, reg_num, make, model, color, year, 
                                    features,teaserImgFile,detailImgFile,thumbnail1File,thumbnail2File,thumbnail3File);
                            //set the rate model on vehicle
                            current.setRateModel(rm);
                            vehicleList.add(current);
                        }
                    } 
             catch (SQLException sqle) {
                Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.SEVERE, sqle.getLocalizedMessage());
            }
        return vehicleList;
    }
    
    /**
     * Get details of the selected  vehicle 
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
                    } 
             catch (SQLException sqle) {
                Logger.getLogger(DbRequestService.class.getCanonicalName()).log(Level.SEVERE, sqle.getLocalizedMessage());
            }
        return description;
    }
}
