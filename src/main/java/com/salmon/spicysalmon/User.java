package com.salmon.spicysalmon;

public abstract class User {
    final private int userID;
    private String password;
    private String firstName;
    private String lastName;
    final private int socialSecurityNumber;

    public User(int userID, String password, String firstName, String lastName, int socialSecurityNumber) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public boolean verifyPassword(String Password, int UserId){
        if(Password==UserId.getPassword){
            return true;
        }else {
            return false;
        }
    }

    public void changePassword(String OldPassword, String NewPassword, int UserId){
        if (OldPassword == NewPassword){
            System.out.println("Error : You wrote the same password (old password)");
        }
        UserId.setPassword = NewPassword;
    }
}