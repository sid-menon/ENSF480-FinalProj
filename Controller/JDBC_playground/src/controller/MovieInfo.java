package controller;

import java.util.Date;

public class MovieInfo {
    private String movieName;
    private Date dateOfAnnounce;



    public MovieInfo(String movieName, Date dateOfAnnounce) {
        this.movieName = movieName;
        this.dateOfAnnounce = dateOfAnnounce;
    }

    public String getMovieName() {
        return movieName;
    }

    public Date getDateOfAnnounce() {
        return dateOfAnnounce;
    }
}
