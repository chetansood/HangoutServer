package model;

public class Event {
	private String name;
    private int ID;
    private Double[] location;
    private int organizer;
	private String description;
    private int participants;
    private String time;
    

    public Event(String name, int ID, Double[] location, int organizer) {
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

    public int getOrganizer() {
        return organizer;
    }

    public void setOrganizer(int organizer) {
        this.organizer = organizer;
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
