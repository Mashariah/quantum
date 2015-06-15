/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 4:10:23 PM  : May 29, 2015
 */
package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelli
 */
public class LoginControl {
        private static final int SALT_BYTE_SIZE = 32;

    /**
     * Create 32 bit random string to use a salt for the hash
     */
    public static String getSalt() {
        byte[] bytes = new byte[SALT_BYTE_SIZE]; //create an array for salt
        try {
            //provide a cryptographically secure random number generator 
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //use SHA-1 message digest algorithm
            random.nextBytes(bytes); //get random salt
        } catch (NoSuchAlgorithmException exception) {
            System.out.println("No such Argument Exception");
        }
        System.out.println("Salt: " + bytes.toString());
        return bytes.toString();
    }
    
    
    public static String generateHashFromPassword(String password, String salt) {
        String generatedPassword = null;
        try {
            //create message digest  instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add salt bytes to digest
            md.update(salt.getBytes());
            //get the bytes of the hash after digest generates it.
            byte[] bytes = md.digest(password.getBytes());

            //convert the bytes in decimal format to hex.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException nre) {
            System.out.println("NoSuchArument: " + nre.getLocalizedMessage());
        }
        return generatedPassword;
    }

    public static boolean validatePasswordHash(String userPassword, String retrievedPasswordHash, String retrivedSalt) {

        boolean valid = false;

        String createdHash = generateHashFromPassword(userPassword, retrivedSalt);
        if (createdHash.equals(retrievedPasswordHash)) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.INFO, "The Hashes match");
            valid = true;
        } else {
            Logger.getLogger(LoginControl.class.getName()).log(Level.INFO, "The Hashes dont match");
        }
        return valid;
    }
    
    
    
    public static void main(String[] args) {
        LoginControl.getSalt();
        LoginControl.getSalt();
        LoginControl.getSalt();
    }
}
