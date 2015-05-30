/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 4:10:23 PM  : May 29, 2015
 */
package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelli
 */
public class LoginControl {

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

    /**
     *
     * @param userPassword
     * @param <error>
     * @param retrievedPasswordHash
     * @param retrivedSalt
     * @return
     *
     * 1. Connect to database and get the stored hash and salt value for this
     * user. 2. Get the password supplied by the user and generate a hash from
     * it using the retrieved salt value. 3. Compare the retrieved and generated
     * hashes.
     */
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
}
