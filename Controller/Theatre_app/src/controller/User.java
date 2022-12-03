package controller;

public abstract class User {

    protected String email;
    protected String password;
    protected String paymentInfo;

    protected String userType;

    public User(String email,String password,String paymentInfo,String userType){

        this.email=email;
        this.password=password;
        this.paymentInfo=paymentInfo;
        this.userType=userType;
    }




    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }
}
