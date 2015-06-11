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
    
    private int userId;
    private String fName;
    private String lName;
    private String userName;
    private String passHash;
    private String salt;
    private String email;
    private String phone;
    private String type;
    
    public User(){
        
    }
    
    /**
     * Create user with full details in registration
     */
        public User(String fName,String lName,String email,String phone,String userName,String passHash,String salt, String type){
        
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.passHash = passHash;
        this.salt = salt;
        
    }
    
        /**
         * Create visitor user for vehicle booking...only critical info stored
         * @param fName
         * @param lName
         * @param email
         * @param phone 
         */
    public User(String fName,String lName,String email,String phone){
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
    }

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

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String  type) {
        this.type = type;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    
}
