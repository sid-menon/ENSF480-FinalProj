package controller;

public class AdminUser extends User {



    public AdminUser(String email, String password, String paymentInfo) {
        super(email, password, paymentInfo,"admin");
    }


}
