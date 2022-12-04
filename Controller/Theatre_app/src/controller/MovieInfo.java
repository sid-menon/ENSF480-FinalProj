package controller;


import java.sql.Time;
import java.util.Date;

public class MovieInfo {

    private int id;
    private String movieName;
    private Date dateOfAnnounce;
    private Time duration;



    public MovieInfo(int id,String movieName, Date dateOfAnnounce,Time duration) {
        this.id=id;
        this.movieName = movieName;
        this.dateOfAnnounce = dateOfAnnounce;
        this.duration=duration;
    }

    public String getMovieName() {
        return movieName;
    }

    public Date getDateOfAnnounce() {
        return dateOfAnnounce;
    }

    public Time getDuration() {

        return duration;

    }

    public int getId() {
        return id;
    }

    public String toString(){
//        System.out.println("The movie span "+duration.getHours()+ " Hours"+ duration.getMinutes()+" minutes\n");
        return (movieName+", "+duration);
    }
}
