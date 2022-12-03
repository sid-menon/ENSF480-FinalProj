package controller;

public class RegisteredUser extends User {



    public RegisteredUser(String email, String password, String paymentInfo) {
        super(email,password,paymentInfo,"registered");

    }


}
