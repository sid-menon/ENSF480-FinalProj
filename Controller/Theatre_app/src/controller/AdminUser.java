package controller;

/**
 *        File Name: AdminUser.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */

public class AdminUser extends User {

    private Theater[] theaters;

    public AdminUser(String email, String password) {
        super(email, password,"admin");
    }

    public Theater[] getTheaters() {
        return theaters;
    }

    public void setTheaters(Theater[] theaters) {
        this.theaters = theaters;
    }



}
