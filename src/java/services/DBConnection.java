/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 4:26:46 PM  : May 29, 2015
 */

package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Connect to the MySQL database.
 * @author kelli
 */
public class DBConnection {
    
        //Database connection strings:
    private static Connection conn;
    private static DriverManager driverManager;
    
    /** Connection to the database for all transactions
     * 
     * @param user db user 
     * @param pass password
     * @return status
     */
     public static Connection getDBConnection(String url,String user, String pass){

        boolean connected = false;
        try {
            /*Load the mysql driver*/
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            /*Connect to the database*/
            conn = DriverManager.getConnection(url, user, pass);
            if(conn!=null){
                connected = true;
                Logger.getLogger(DBConnection.class.getName()).log(Level.INFO,  "connection to database created!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex.getStackTrace());
        }
        return conn;
    }
    
     
    public static boolean closeConnection(){
        return true;
    }
    
//
//    public static void main(String[] args) {
//      DBConnection.getDBConnection("jdbc:mysql://localhost:3306/chabrin_db","root", "bluehair123");
//    }
    
}
