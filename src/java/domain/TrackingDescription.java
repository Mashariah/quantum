/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:11:55 PM  : Jun 14, 2015
 */

package domain;

/**
 *
 * @author kelli
 */
public class TrackingDescription {
    
    private Vehicle vehicle;
    private Booking booking;
    private User user;
    
    public TrackingDescription (){
        
    }
    
    public TrackingDescription(Vehicle vehicle, Booking booking, User user){
    this.vehicle = vehicle;
    this.booking = booking;
    this.user = user;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle the vehicle to set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * @param booking the booking to set
     */
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}
