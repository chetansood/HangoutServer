package com.example.supersaiyans.hangout.model;

import java.io.Serializable;

public class User implements Serializable{
    private int ID;
    private String name;
    private Double[] location;
    private String email;
    private int[] events[];
    private String fb_id;

    static final long serialVersionUID = 42L;

    public User(int ID, String name, Double[] location) {
        this.ID = ID;
        this.name = name;
        this.location = location;
    }
    
    public User(int ID, String name, Double[] location,String fb_id) {
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.fb_id=fb_id;
    }

    public User(int id, String fb_id, String name) {
        this.ID = id;
        this.fb_id = fb_id;
        this.name = name;
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

    public String toString() {
        return "user ID: " + ID + ", fb_id: " + fb_id + ", name: " + name;
    }

	public String getFb_id() {
		return fb_id;
	}

	public void setFb_id(String fb_id) {
		this.fb_id = fb_id;
	}

}
