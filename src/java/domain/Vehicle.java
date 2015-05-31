/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:25:01 PM  : May 30, 2015
 */

package domain;

import java.io.File;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author kelli
 */
public class Vehicle {
    
    private int vehicleId;
    private String  registrationNumber;
    private String make;
    private String model;
    private String color;
    private Date year;
    private boolean satNav;
    private boolean advEnt;
    private boolean chauffered;
    
    //list of picture files for this vehicle 
    private String imageFile;

    public Vehicle(int vId,String regNum,String make,String model,String color,Date year,int satNav,int advEnt,int chauffered,String images){
        this.vehicleId = vId;
        this.registrationNumber = regNum;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.satNav = convertIntToBoolean(satNav);
        this.advEnt = convertIntToBoolean(advEnt);
        this.chauffered = convertIntToBoolean(chauffered);
        this.imageFile = images;
    }
 

    /**
     * @return the vehicleId
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * @param vehicleId the vehicleId to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * @return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * @param registrationNumber the registrationNumber to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the year
     */
    public Date getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Date year) {
        this.year = year;
    }

    /**
     * @return the satNav
     */
    public boolean isSatNav() {
        return satNav;
    }

    /**
     * @param satNav the satNav to set
     */
    public void setSatNav(boolean satNav) {
        this.satNav = satNav;
    }

    /**
     * @return the advEnt
     */
    public boolean isAdvEnt() {
        return advEnt;
    }

    /**
     * @param advEnt the advEnt to set
     */
    public void setAdvEnt(boolean advEnt) {
        this.advEnt = advEnt;
    }

    /**
     * @return the chauffered
     */
    public boolean isChauffered() {
        return chauffered;
    }

    /**
     * @param chauffered the chauffered to set
     */
    public void setChauffered(boolean chauffered) {
        this.chauffered = chauffered;
    }

    /**
     * @return the imageFile
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFiles the imageFile to set
     */
    public void setImageFile(String imageFiles) {
        this.imageFile = imageFiles;
    }
    
    /** utility methods**/
        private boolean convertIntToBoolean(int num){
        boolean state = false;
        if(num>0){
            state =  true;
        }
        return state;
    }

}
