package model;

// Represents a general section of a resume
public abstract class ResumeSection {
    String institution;
    String location;
    String startYear;
    String endYear;
    String description;
    

    // EFFECTS: Constructs a ResumeSection with the given institution, location, start year, end year, and description
    public ResumeSection(String institution, String location, String startYear, String endYear, String description) {
        this.institution = institution;
        this.location = location;
        this.startYear = startYear;
        this.endYear = endYear;
        this.description = description;
    }

    // EFFECTS: Returns the institution name
    public String getInstitution() {
        return institution;
    }

    // MODIFIES: this
    // EFFECTS: sets the institution name to the given value
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    // EFFECTS: Returns the location
    public String getLocation() {
        return location;
    }

    // MODIFIES: this
    // EFFECTS: sets the location to the given value
    public void setLocation(String location) {
        this.location = location;
    }

    // EFFECTS: Returns the start year
    public String getStartYear() {
        return startYear;
    }

    // MODIFIES: this
    // EFFECTS: sets the start year to the given value
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    // EFFECTS: Returns the end year
    public String getEndYear() {
        return endYear;
    }

    // MODIFIES: this
    // EFFECTS: sets the end year to the given value
    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    // EFFECTS: Returns the description of the section
    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: sets the description to the given value
    public void setDescription(String description) {
        this.description = description;
    }


}
