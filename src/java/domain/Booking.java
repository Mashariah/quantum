/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:43:34 AM  : Jun 10, 2015
 */

package domain;

/**
 *
 * @author kelli
 */
public class Booking {
    
    private int bookingId;
    private int  userId;
    private int vehicleId;
    private String dtPickup;
    private String dtDropoff;
    private String pLocation;
    private String dLocation; 

    public Booking(int userId,int vehicleId,String dtPickup, String dtDropoff,String pLocation,String dLocation){
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.dtPickup = dtPickup;
        this.dtDropoff = dtDropoff;
        this.pLocation = pLocation;
        this.dLocation = dLocation;
    }
    /**
     * @return the bookingId
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * @param bookingId the bookingId to set
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * @return the dtPickup
     */
    public String getDtPickup() {
        return dtPickup;
    }

    /**
     * @param dtPickup the dtPickup to set
     */
    public void setDtPickup(String dtPickup) {
        this.dtPickup = dtPickup;
    }

    /**
     * @return the dtDropoff
     */
    public String getDtDropoff() {
        return dtDropoff;
    }

    /**
     * @param dtDropoff the dtDropoff to set
     */
    public void setDtDropoff(String dtDropoff) {
        this.dtDropoff = dtDropoff;
    }

    /**
     * @return the pLocation
     */
    public String getpLocation() {
        return pLocation;
    }

    /**
     * @param pLocation the pLocation to set
     */
    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
    }

    /**
     * @return the dLocation
     */
    public String getdLocation() {
        return dLocation;
    }

    /**
     * @param dLocation the dLocation to set
     */
    public void setdLocation(String dLocation) {
        this.dLocation = dLocation;
    }
    
    
    
    
    
}
