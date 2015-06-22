/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 3:42:42 PM  : Jun 19, 2015
 */

package domain;

/**
 *
 * @author kelli
 */
public class BookingDetails {
    private Booking booking;
    private Vehicle vehicle;
    private User user;
    
    public BookingDetails(Booking b,Vehicle v){
        this.booking = b;
        this.vehicle = v;
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
