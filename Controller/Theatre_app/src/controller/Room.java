package controller;

import java.sql.Time;
import java.sql.Timestamp;

public class Room {
    private int id;
    private int theaterID;
    private int roomNumber;
    private Timestamp nextAvailableTime;

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

    public Timestamp getNextAvailableTime() {
        return nextAvailableTime;
    }

    public void updateNextAvailableTime(Time time){
        nextAvailableTime.setTime(nextAvailableTime.getTime()+time.getTime());
        System.out.println(nextAvailableTime);
    }

    public String toString(){
        return (""+roomNumber);
    }
}
