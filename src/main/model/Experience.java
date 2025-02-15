package model;

// Represents work experiences of the user
public class Experience {
    String position;
    String institution;
    String location;
    int startYear;
    int startMonth;
    int endYear;
    int endMonth;
    String description;

    // EFFECTS: Constructs a Experience with position, institution, location, start year, end year, and description
    public Experience (String position, String institution, String location, 
    int startYear, int startMonth, int endYear, int endMonth, String description) {
        this.position = position;
        this.institution = institution;
        this.location = location;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.description = description;
    }

    // EFFECTS: Returns the total duration of the experience in months
    public int getTotalDuration() {
        int totalDuration = 0;

        if (endYear == 0) {
            return totalDuration;
        } else {
            totalDuration = ((endYear - startYear) * 12) + (endMonth - startMonth);
        }

        return totalDuration;
    }


    // EFFECTS: Returns the position name
    public String getPosition() {
        return position;
    }

    // MODIFIES: this
    // EFFECTS: sets the position name to the given value
    public void setPosition(String position) {
        this.position = position;
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
    public int getStartYear() {
        return startYear;
    }

    // MODIFIES: this
    // EFFECTS: sets the start year to the given value
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    // EFFECTS: Returns the start month
    public int getStartMonth() {
        return startMonth;
    }

    // REQUIRES: 0 < startMonth < 13
    // MODIFIES: this
    // EFFECTS: sets the start month to startMonth
    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    // EFFECTS: Returns the end year
    public int getEndYear() {
        return endYear;
    }

    // MODIFIES: this
    // EFFECTS: sets the end year to the given value
    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    // EFFECTS: Returns the end month
    public int getEndMonth() {
        return endMonth;
    }

    // REQUIRES: 0 < endMonth < 13
    // MODIFIES: this
    // EFFECTS: sets the end month to endMonth
    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
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


