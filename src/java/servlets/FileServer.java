/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:26:18 AM  : May 31, 2015
 */

package servlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kelli
 */
public class FileServer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("image/png ");
                OutputStream os  = response.getOutputStream();
            String imageFile = request.getParameter("param1");
            sendFile(imageFile,os);
    }
    
    public static void sendFile(String name,OutputStream os){
        //works with bytes
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(name);
            Logger.getLogger(FileServer.class.getCanonicalName()).log(Level.INFO, "good so far....");
            byte[] buffer = new byte[4*1024]; //4Kb buffer
            int bytesRead;
            while ((bytesRead = fis.read(buffer))!=-1){
                os.write(buffer,0, bytesRead);
            }
        }catch(FileNotFoundException fex){
            Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, fex.getMessage());
            
        }catch(IOException ioe){
            Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, ioe.getLocalizedMessage());
        }
        finally{
            if(fis!=null){
                try{
                fis.close();
                    
                }catch(IOException ioe){}
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
