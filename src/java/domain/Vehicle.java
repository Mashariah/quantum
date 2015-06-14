/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:25:01 PM  : May 30, 2015
 */

package domain;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
//    private String features;
    private String teaserImg;
    private String detailImg;
    private String thumbnail1Img;
    private String thumbnail2Img;
    private String thumbnail3Img;
    private RateModel rateModel;
    private String status;

    public Vehicle(int vId,String regNum,String make,String model,String color,Date year,
            String teaserImg, String detailImg,String thumnail1Img,String thumnail2Img,String thumnail3Img){
        this.vehicleId = vId;
        this.registrationNumber = regNum;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
//        this.features = features;
        this.teaserImg = teaserImg;
        this.detailImg = detailImg;
        this.thumbnail1Img = thumnail1Img;
        this.thumbnail2Img = thumnail2Img;
        this.thumbnail3Img = thumnail3Img;
       }
    
    //create new vehicles from form...attach images using getset
    public Vehicle(String regNum,String make,String model,String  year){
        this.registrationNumber = regNum;
        this.make = make;
        this.model = model;
        this.color="Black";
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        try{
        this.year = sdf.parse(year);
        }catch(ParseException pxe){System.out.println("Parse exception occured");}
    }
    
    public void setRateModel(RateModel model){
        this.rateModel = model;
    }
    
    public RateModel getRateModel(){
        return  rateModel;
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
//
//    /**
//     * @return the features
//     */
//    public String getFeatures() {
//        return features;
//    }
//
//    /**
//     * @param features the features to set
//     */
//    public void setFeatures(String features) {
//        this.features = features;
//    }

    /**
     * @return the teaserImg
     */
    public String getTeaserImg() {
        return teaserImg;
    }

    /**
     * @param teaserImg the teaserImg to set
     */
    public void setTeaserImg(String teaserImg) {
        this.teaserImg = teaserImg;
    }

    /**
     * @return the detailImg
     */
    public String getDetailImg() {
        return detailImg;
    }

    /**
     * @param detailImg the detailImg to set
     */
    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    /**
     * @return the thumbnail1Img
     */
    public String getThumbnail1Img() {
        return thumbnail1Img;
    }

    /**
     * @param thumbnail1Img the thumbnail1Img to set
     */
    public void setThumbnail1Img(String thumbnail1Img) {
        this.thumbnail1Img = thumbnail1Img;
    }

    /**
     * @return the thumbnail2Img
     */
    public String getThumbnail2Img() {
        return thumbnail2Img;
    }

    /**
     * @param thumbnail2Img the thumbnail2Img to set
     */
    public void setThumbnail2Img(String thumbnail2Img) {
        this.thumbnail2Img = thumbnail2Img;
    }

    /**
     * @return the thumbnail3Img
     */
    public String getThumbnail3Img() {
        return thumbnail3Img;
    }

    /**
     * @param thumbnail3Img the thumbnail3Img to set
     */
    public void setThumbnail3Img(String thumbnail3Img) {
        this.thumbnail3Img = thumbnail3Img;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

   
}
