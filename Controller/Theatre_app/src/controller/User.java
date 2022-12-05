package controller;

public abstract class User {

    protected String email;
    protected String password;
    protected String userType;

    protected PaymentInfo paymentInfo;

    public User(String email,String password,String userType){

        this.email=email;
        this.password=password;

        this.userType=userType;
    }




    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
