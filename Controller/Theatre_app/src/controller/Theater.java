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

    public Theater(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String toString(){
        return name+", "+address;
    }
}
