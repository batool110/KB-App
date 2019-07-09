package com.example.dell.kbapp;

/**
 * Created by dell on 5/4/2018.
 */

public class UserProfile {

    public String userName;
    public String userAge;
    public String userEmail;

    public UserProfile(){

    }

    public UserProfile(String userName, String userAge, String userEmail) {
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserAge() {

        return userAge;
    }

    public void setUserAge(String userAge) {

        this.userAge = userAge;
    }

    public String getUsreEmail() {

        return userEmail;
    }

    public void setUsreEmail(String usreEmail) {

        this.userEmail = usreEmail;
    }
}
