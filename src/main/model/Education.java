package model;

// Represents education history of the user
public class Education extends ResumeSection{
    String gpa;

    // EFFECTS: Constructs a new experience with institution, locaiton, startYear, endYear, and description
    public Education(String institution, String location, String startYear, String endYear, String description) {
		super(institution, location, startYear, endYear, description);
		
	}

    // EFFECTS: returns the gpa
    public String getGpa() {
        return "";
    }

    // EFFECTS: sets the gpa with given value
    public void setGpa(String gpa) {
        // stub
    }

}
