/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 1:58:56 PM  : Jun 10, 2015
 */

package services;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *  Send confirmation email and text messages for car bookings 
 * @author kelli
 */
public class MailMan {
    
    String host;
    String subject="BOOKING CONFIRMATION";
    
    public  boolean sendConfirmation(String to, String subject, String msg ) throws 
            AddressException, MessagingException{
                  Logger.getLogger(MailMan.class.getName()).log(Level.INFO, "Inside sender...");

        //set SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        
        //create session with an authenticator
//        Authenticator authenticator = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication(){
//                return new PasswordAuthentication(subject, password);
//            }
//        }
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication(){
                  Logger.getLogger(MailMan.class.getName()).log(Level.INFO, "Setting up authenticator");
               return new PasswordAuthentication("muhindi09","joanloved09");
           }
        });
        
        //create new email message
        Message message = new MimeMessage(session);
      Logger.getLogger(MailMan.class.getName()).log(Level.INFO, "setting up message");
        message.setFrom(new InternetAddress("muhindi09"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(msg);
        
        Transport.send(message);
        boolean sent = false;
        
        return sent;
    }
}
