/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 8:55:50 PM  : Jun 11, 2015
 */
package servlets;

import domain.Vehicle;
import domain.VehicleDescription;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.DbRequestService;

/**
 *
 * @author kelli
 */
public class NewCar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String UPLOAD_DIRECTORY = "var/alexi_images";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; //3Mb
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; //10Mb
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 30; //30Mb

    //on submission parse request data to read upload data and save the file on disk
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Read connection object from context
        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("connector");

        LinkedHashMap<String, String> formParams = new LinkedHashMap<>();
        LinkedHashMap<String, String> imageFiles = new LinkedHashMap<>();

        //check if the request contains upload data...
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter p = response.getWriter();
            p.println("form has no enctype=multipart/form-data");
            p.flush();
            return;
        }

        //configure upload settings 
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //set the memory threshold...bigger files will be stored on disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //set the temporary location to store the files 
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        Logger.getLogger("Revealer").log(Level.INFO, new File(System.getProperty("java.io.tmpdir")).getAbsolutePath());

        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //set the max size of the upload file 
        fileUpload.setFileSizeMax(MAX_FILE_SIZE);
        //set the maximum size of the request (file size + form data)
        fileUpload.setSizeMax(MAX_REQUEST_SIZE);

        //create a path (relative to the application's directory to store the upload files)
//        String uploadPath = getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
        String uploadPath = "/" + File.separator + UPLOAD_DIRECTORY;
        //create the directory if it does not exist
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdir();
        }

        try {
            //parse the request's content to extract the file data
            List<FileItem> formItems = fileUpload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                //iterate over the foem fields 
                for (FileItem current : formItems) {
                    //process fields that are not form fields
                    if (!current.isFormField()) {
                        String fileName = new File(current.getName()).getName();
                        //save original filename in client's filesystem
                        imageFiles.put(current.getFieldName(), current.getName());
                        String filepath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filepath);

                        //save the file to disk
                        current.write(storeFile);
                        request.setAttribute("upload_message", "Upload sucess");
//                            getServletContext().getRequestDispatcher("/confirmation.jsp").forward(request, response);
                    } else if (current.isFormField()) {
//process the form fields by...put into map
                        String fName = current.getFieldName();
                        String value = current.getString();
                        formParams.put(fName, value);
                        Logger.getLogger(NewCar.class.getName()).log(Level.INFO, "Field Name: {0} Value: {1}", new Object[]{fName, value});
                    }//end else
                } //end for
            }//end try
        } catch (Exception fuex) {
            request.setAttribute("upload_message", "some error occured in uplaod:" + fuex.getLocalizedMessage());
        }

        //working with form parameters...
        String navPath = "";
        //create vehicle and its features using description, features and images
        Vehicle vehicle = createVehicle(formParams, imageFiles);
        VehicleDescription description = createFeatures(formParams);

        Logger.getLogger(NewCar.class.getName()).log(Level.INFO, vehicle.getRegistrationNumber());

        if (DbRequestService.addVehicle(connection, vehicle, description)) {
            request.setAttribute("addMsg", "Vehicle successfully to db");
            navPath = "vlisting";
        } else {
            request.setAttribute("addError", "Error occured adding vehicle to db");
            navPath = "/newcar.jsp";
        }

        getServletContext().getRequestDispatcher(navPath).forward(request, response);
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

    private Vehicle createVehicle(LinkedHashMap<String, String> description, LinkedHashMap<String, String> images) {
        Vehicle vehicle = new Vehicle(description.get("tx_regnum"), description.get("drp_carmake"),
                description.get("tx_model"), description.get("drp_caryear"));
        vehicle.setTeaserImg(images.get("tx_teaserimg"));
        vehicle.setDetailImg(images.get("tx_detailsimg"));
        vehicle.setThumbnail1Img(images.get("tx_thumb1"));
        vehicle.setThumbnail2Img(images.get("tx_thumb2"));
        vehicle.setThumbnail3Img(images.get("tx_thumb2"));
        return vehicle;
    }

    private VehicleDescription createFeatures(LinkedHashMap<String, String> features) {
        Logger.getLogger(NewCar.class.getName()).log(Level.INFO, "Seating Capacity {0}", features.get("drp_scapacity"));

        VehicleDescription vehicleDescription = new VehicleDescription(features.get("tx_fueleconomy"),
                features.get("tx_fuelcapacity"), features.get("drp_transmission"), Integer.parseInt(features.get("drp_scapacity")),
                features.get("txb_convenience"), features.get("txb_safetysec"), features.get("txb_entertainment"),
                features.get("txb_telematics"), features.get("txb_tireswheels"));
        return vehicleDescription;
    }

}
