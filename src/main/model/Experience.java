package model;

// Represents work experiences of the user
public class Experience extends ResumeSection {
    String position;

    // EFFECTS: Constructs a new experience with institution, locaiton, startYear, endYear, and description
    public Experience(String institution, String location, String startYear, String endYear, String description) {
		super(institution, location, startYear, endYear, description);
	}

    // EFFECTS: returns the position
    public String getPosition() {
        return "";
    }

    // EFFECTS: sets the position with given value
    public void setPosition(String position) {
        // stub
    }

}


