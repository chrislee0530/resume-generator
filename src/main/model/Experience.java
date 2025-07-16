package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents work experiences of the user
public class Experience extends Component {
    String position;
    String institution;

    // EFFECTS: Constructs a Experience with position, institution, location, start
    // year, end year, and description
    public Experience(String position, String institution, String location,
            String startYear, String startMonth, String endYear, String endMonth, String description) {
        super(location, startYear, startMonth, endYear, endMonth, description);
        this.position = position;
        this.institution = institution;

    }

    // EFFECTS: Returns the total duration of the experience in months
    public int getTotalDuration() {
        int start = Integer.parseInt(startYear) * 12 + Integer.parseInt(startMonth);
        int end = Integer.parseInt(endYear) * 12 + Integer.parseInt(endMonth);
        return end - start;
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

}
