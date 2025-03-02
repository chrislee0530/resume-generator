package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents work experiences of the user
public class Experience implements Writable{
    String position;
    String institution;
    String location;
    String startYear;
    String startMonth;
    String endYear;
    String endMonth;
    String description;

    // EFFECTS: Constructs a Experience with position, institution, location, start year, end year, and description
    public Experience(String position, String institution, String location, 
                String startYear, String startMonth, String endYear, String endMonth, String description) {
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
        int endY;
        int startY;
        int endM;
        int startM;
        endY = Integer.parseInt(endYear);
        startY = Integer.parseInt(startYear);
        endM = Integer.parseInt(endMonth);
        startM = Integer.parseInt(startMonth);

        if (endY == 0) {
            return totalDuration;
        } else {
            totalDuration = ((endY - startY) * 12) + (endM - startM);
        }

        return totalDuration;
    }

    // EFFECTS: converts this experience into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position", position);
        json.put("institution", institution);
        json.put("location", location);
        json.put("startYear", startYear);
        json.put("startMonth", startMonth);
        json.put("endYear", endYear);
        json.put("endMonth", endMonth);
        json.put("description", description);
        return json;
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
    public String getStartYear() {
        return startYear;
    }

    // MODIFIES: this
    // EFFECTS: sets the start year to the given value
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    // EFFECTS: Returns the start month
    public String getStartMonth() {
        return startMonth;
    }

    // REQUIRES: 0 < startMonth < 13
    // MODIFIES: this
    // EFFECTS: sets the start month to startMonth
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
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

    // EFFECTS: Returns the end month
    public String getEndMonth() {
        return endMonth;
    }

    // REQUIRES: 0 < endMonth < 13
    // MODIFIES: this
    // EFFECTS: sets the end month to endMonth
    public void setEndMonth(String endMonth) {
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


