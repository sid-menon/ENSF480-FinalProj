package controller;
/**
 *        File Name: Theater.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */
public class Theater {
    private int id;
    private String name;
    private String address;

    public Theater(int id, String name, String address) //constructor
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() //getter for ID
    {
        return id;
    }

    public String getName()//getter for name
    {
        return name;
    }

    public String getAddress() //getter for addresss
    {
        return address;
    }

    public String toString(){
        return name+", "+address;
    }
}
