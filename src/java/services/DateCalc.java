/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 3:35:34 PM  : Jun 7, 2015
 */

package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlets.ChargesCalculator;

/**
 *
 * @author kelli
 */
public class DateCalc {
    
    /** 
     * Get user date string and convert into date instance.
     * @param s the date string
     * @return date object 
     */
     public static Date stringToDate(String s){
            Date d = null;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
//            Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO, "Date is: "+d);
        try{
            d= sdf.parse(s);
        }catch(ParseException pse){
            Logger.getLogger(ChargesCalculator.class.getName()).log(Level.INFO, "Parse Exception error occured: "+pse);
        }
        return d;
    }
     
     /**
      *  Compare the elapsed milliseconds between 2 dates.
      * @param date1
      * @param date2
      * @return time in milliseconds 
      */
    public static long  compareDates(Date date1, Date date2){
        long difference = 0;
        if(date1!=null && date2!=null){
          difference = date2.getTime()-date1.getTime();
        }
        return difference;
    }
    
    public static long getDays(long time){
        long days = time/(1000*60*60*24);
        return days;
    }
    public static long getHours(long time){
         long days = time/(1000*60*60);
        return days;
    }
    public static long getWeeks(long time){
         long days = time/(1000*60*60*24*7);
        return days;
    }

    private static String  formatDate(Date d){
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yy hh:mm a");
        String  date = null;
            date = sd.format(d);
            return date;
    }
    
    private static Date getYear(String date){
    Date carYear = null;
    SimpleDateFormat df = new SimpleDateFormat();
        try {
            carYear = df.parse(date);
            Logger.getLogger("DateTest").log(Level.INFO, "Year in given date is {0}",carYear);
            
        } catch (ParseException ex) {
            Logger.getLogger("DateTest").log(Level.SEVERE, null, ex);
        }
        return carYear;
}
    
    public static void main(String[] args) {
//        String then = "02/02/15 23:45";
//        String now = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//        System.out.println("Now is: "+now);
        
        Calendar calendar = new GregorianCalendar();
        
        calendar.set(Calendar.YEAR, 2013);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        System.out.println("Year: "+year);
        System.out.println("Day of week:: "+day);
        System.out.println("Hour: "+hour);
        System.out.println("Minute: "+minute);
        
        SimpleDateFormat df = new SimpleDateFormat("YYYY");
        String result = df.format(calendar.getTime());
        String result2 = df.format(new Date());
        System.out.println("Year after format: "+result);
        System.out.println("Year2 after format: "+result2);
        
        
//        
//       Date d1 = stringToDate(then);
//        System.out.println("Date 1: "+formatDate(d1));
//       Date d2 = stringToDate(now);
//        System.out.println("Date 2: "+formatDate(d2));
//
//        System.out.println(getDays(compareDates(d1, d2))+" days");
//        System.out.println(getHours(compareDates(d1, d2))+" hours");
//        System.out.println(getWeeks(compareDates(d1, d2))+" weeks");
        
//        getYear(now);
    }
}
