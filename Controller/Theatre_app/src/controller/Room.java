package controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Room {
    private int id;
    private int theaterID;
    private int roomNumber;
    private Date nextAvailableTime;

    public Room(int id, int theaterID, int roomNumber,Timestamp nextAvailableTime) {
        this.id = id;
        this.theaterID = theaterID;
        this.roomNumber=roomNumber;
        this.nextAvailableTime=nextAvailableTime;
    }

    public int getId() {
        return id;
    }

    public int getTheaterID() {
        return theaterID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getNextAvailableTime() {
        return nextAvailableTime;
    }

    public void updateNextAvailableTime(Time time){
        Calendar calendar=Calendar.getInstance();

        calendar.setTime(nextAvailableTime);
        calendar.add(Calendar.HOUR,time.getHours());
        calendar.add(Calendar.MINUTE,time.getMinutes());

        nextAvailableTime=calendar.getTime();
    }

    public String toString(){
        return (""+roomNumber);
    }
}
