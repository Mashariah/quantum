/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 5:54:35 PM  : Jun 5, 2015
 */

package domain;

/**
 *
 * @author kelli
 */
public class RateModel {
    
    private double hourlyCharge;
    private double dailyCharge;
    private double weeklyCharge;

    
    public RateModel(Double hourlyCharge,Double dailyCharge,Double weeklyCharge){
        this.hourlyCharge = hourlyCharge;
        this.dailyCharge = dailyCharge;
        this.weeklyCharge = weeklyCharge;
    }
    /**
     * @return the hourlyCharge
     */
    public double getHourlyCharge() {
        return hourlyCharge;
    }

    /**
     * @param hourlyCharge the hourlyCharge to set
     */
    public void setHourlyCharge(double hourlyCharge) {
        this.hourlyCharge = hourlyCharge;
    }

    /**
     * @return the dailyCharge
     */
    public double getDailyCharge() {
        return dailyCharge;
    }

    /**
     * @param dailyCharge the dailyCharge to set
     */
    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    /**
     * @return the weeklyCharge
     */
    public double getWeeklyCharge() {
        return weeklyCharge;
    }

    /**
     * @param weeklyCharge the weeklyCharge to set
     */
    public void setWeeklyCharge(double weeklyCharge) {
        this.weeklyCharge = weeklyCharge;
    }
    
    
}
