package controller;

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
