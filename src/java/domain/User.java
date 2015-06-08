/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:29:45 PM  : Jun 7, 2015
 */

package domain;

/**
 *
 * @author kelli
 */
public class User {
    
    private String fName;
    private String lName;
    private String userName;
    private String passHash;
    private String email;
    private String phone;

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passHash
     */
    public String getPassHash() {
        return passHash;
    }

    /**
     * @param passHash the passHash to set
     */
    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
