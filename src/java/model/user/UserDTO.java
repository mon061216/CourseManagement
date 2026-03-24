package model.user;

import java.util.Date;

public class UserDTO {
    private String userID;
    private String roleID;
    private String username;
    private String passwordHash;
    private String fullname;
    private Date dob;
    private String address;
    private String mail;
    private String phoneNumber;
    private boolean userState;

    public UserDTO() {
    }

    public UserDTO(String userID, String roleID, String username, String passwordHash, String fullname, Date dob, String address, String mail, String phoneNumber, boolean userState) {
        this.userID = userID;
        this.roleID = roleID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.userState = userState;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", roleID=" + roleID + ", username=" + username + ", passwordHash=" + passwordHash + ", fullname=" + fullname + ", dob=" + dob + ", address=" + address + ", mail=" + mail + ", phoneNumber=" + phoneNumber + ", userState=" + userState + '}';
    }

    

}
