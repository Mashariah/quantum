/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 2:24:51 PM  : Jun 3, 2015
 */

package domain;

/**
 *
 * @author kelli
 */
public class VehicleDescription {
    
    private int descId;
    private int vehicleId;
    private String fuelConsumption;
    private String fuelCapacity;
    private String transmission;
    private int seatingCapacity;
    private String convinience;
    private String safetyAndSecurity;
    private String entertainment;
    private String telematics;
    private String tireWheels;

    
    /*
    Vehicle description for use in creating new vehicle
    */
    public VehicleDescription (String fuelConsumption,String fuelCapacity,String transmission,
            int seatingCapacity,String convinience,String safetyAndSecurity,String entertainment,String telematics,
            String tireWheels){
        this.fuelConsumption = fuelConsumption;
        this.fuelCapacity = fuelCapacity;
        this.transmission = transmission;
        this.seatingCapacity = seatingCapacity;
        this.convinience = convinience;
        this.safetyAndSecurity = safetyAndSecurity;
        this.entertainment = entertainment;
        this.telematics = telematics;
        this.tireWheels = tireWheels;
    }
    
    //update vehicle description
    public VehicleDescription (int vehicleId,String fuelConsumption,String fuelCapacity,String transmission,
            int seatingCapacity,String convinience,String safetyAndSecurity,String entertainment,String telematics,
            String tireWheels){
        this.vehicleId = vehicleId;
        this.fuelConsumption = fuelConsumption;
        this.fuelCapacity = fuelCapacity;
        this.transmission = transmission;
        this.seatingCapacity = seatingCapacity;
        this.convinience = convinience;
        this.safetyAndSecurity = safetyAndSecurity;
        this.entertainment = entertainment;
        this.telematics = telematics;
        this.tireWheels = tireWheels;
    }
    
    /**
     * @return the descId
     */
    public int getDescId() {
        return descId;
    }

    /**
     * @param descId the descId to set
     */
    public void setDescId(int descId) {
        this.descId = descId;
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
     * @return the fuelConsumption
     */
    public String getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * @param fuelConsumption the fuelConsumption to set
     */
    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * @return the fuelCapacity
     */
    public String getFuelCapacity() {
        return fuelCapacity;
    }

    /**
     * @param fuelCapacity the fuelCapacity to set
     */
    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    /**
     * @return the transmission
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * @param transmission the transmission to set
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * @return the seatingCapacity
     */
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    /**
     * @param seatingCapacity the seatingCapacity to set
     */
    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    /**
     * @return the convinience
     */
    public String getConvinience() {
        return convinience;
    }

    /**
     * @param convinience the convinience to set
     */
    public void setConvinience(String convinience) {
        this.convinience = convinience;
    }

    /**
     * @return the safetyAndSecurity
     */
    public String getSafetyAndSecurity() {
        return safetyAndSecurity;
    }

    /**
     * @param safetyAndSecurity the safetyAndSecurity to set
     */
    public void setSafetyAndSecurity(String safetyAndSecurity) {
        this.safetyAndSecurity = safetyAndSecurity;
    }

    /**
     * @return the entertainment
     */
    public String getEntertainment() {
        return entertainment;
    }

    /**
     * @param entertainment the entertainment to set
     */
    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    /**
     * @return the telematics
     */
    public String getTelematics() {
        return telematics;
    }

    /**
     * @param telematics the telematics to set
     */
    public void setTelematics(String telematics) {
        this.telematics = telematics;
    }

    /**
     * @return the tireWheels
     */
    public String getTireWheels() {
        return tireWheels;
    }

    /**
     * @param tireWheels the tireWheels to set
     */
    public void setTireWheels(String tireWheels) {
        this.tireWheels = tireWheels;
    }
    
    
}
