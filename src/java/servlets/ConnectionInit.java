/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:53:08 PM  : May 29, 2015
 */

package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import services.DBConnection;

/**
 *
 * @author kelli
 */
public class ConnectionInit extends GenericServlet {
    
    protected String url,user,password;
    protected  Connection connection;
    /**Overide the init method to create database connection object and load them
     * to the servletcontext
     * @param configt*/
    @Override
    
    public  void init(ServletConfig config) throws ServletException{
        this.url= config.getInitParameter("url");
        Logger.getLogger(ConnectionInit.class.getName()).log(Level.INFO,"url = "+url);
        this.user= config.getInitParameter("user");
        Logger.getLogger(ConnectionInit.class.getName()).log(Level.INFO,"user= : "+user);
        this.password= config.getInitParameter("password");
        Logger.getLogger(ConnectionInit.class.getName()).log(Level.INFO,"password=: "+password);
        
        //create the database connection...
        connection = DBConnection.getDBConnection(url, user, password);
        //push to ServletContext....
        ServletContext context =config.getServletContext();
       
        context.setAttribute("connector",connection);
        Logger.getLogger(ConnectionInit.class.getName()).log(Level.INFO, "Database connection "
                + "attribute set= "+context.getAttribute("connector"));
        
        
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        
    }

   

}
