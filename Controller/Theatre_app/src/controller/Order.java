package controller;

import java.sql.Timestamp;

public class Order {
    private MovieInfo movie;
    private Theater theater;

    private Timestamp showTime;

    private Room room;

    private Seat seat;




    public MovieInfo getMovie() {
        return movie;
    }

    public void setMovie(MovieInfo movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Timestamp getShowTime() {
        return showTime;
    }

    public void setShowTime(Timestamp showTime) {
        this.showTime = showTime;
    }
}