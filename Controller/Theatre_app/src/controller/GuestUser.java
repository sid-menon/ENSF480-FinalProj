package controller;

public class GuestUser extends User{

    public GuestUser(String email) {
        super(email, "0000","guest" );
    }
}
