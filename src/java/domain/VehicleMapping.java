/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 1:09:26 PM  : Jul 6, 2015
 */
package domain;

/**
 *
 * @author kelli
 */
public class VehicleMapping {
    
    private int vehicleId;
    private double lattitude;
    private double longitude;

    public VehicleMapping(){
        
    }
    
    public VehicleMapping(double _long,double lat){
        this.longitude = _long;
        this.lattitude = lat;
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
     * @return the lattitude
     */
    public double getLattitude() {
        return lattitude;
    }

    /**
     * @param lattitude the lattitude to set
     */
    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
}
