package model;

public class Event {
	private String name;
    private int ID;
    private Double[] location;
    private String organizer;

    public Event(String name, int ID, Double[] location, String organizer) {
        this.name = name;
        this.ID = ID;
        this.location = location;
        this.organizer = organizer;
    }

    public Event(String name, int ID, Double[] location) {
        this.name = name;
        this.ID = ID;
        this.location = location;
        this.organizer = organizer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Double[] getLocation() {
        return location;
    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}
