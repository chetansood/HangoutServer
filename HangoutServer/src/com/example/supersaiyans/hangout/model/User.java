package com.example.supersaiyans.hangout.model;

public class User {
	private int ID;
    private String name;
    private Double[] location;
    private String email;
    private int[] events[];

    public User(int ID, String name, Double[] location, String email) {
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double[] getLocation() {
        return location;
    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int[][] getEvents() {
        return events;
    }

    public void setEvents(int[][] events) {
        this.events = events;
    }

}
