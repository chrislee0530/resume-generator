package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents education history of the user
public class Education extends Component {
    String gpa;
    String institution;

    // EFFECTS: Constructs a new experience with institution, locaiton, startYear,
    // endYear, and description
    public Education(String gpa, String institution, String location,
            String startYear, String startMonth, String endYear, String endMonth, String description) {
        super(location,
                startYear, startMonth, endYear, endMonth, description);
        this.gpa = gpa;
        this.institution = institution;
    }

    // EFFECTS: converts this education into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gpa", gpa);
        json.put("institution", institution);
        json.put("location", location);
        json.put("startYear", startYear);
        json.put("startMonth", startMonth);
        json.put("endYear", endYear);
        json.put("endMonth", endMonth);
        json.put("description", description);
        return json;
    }

    // EFFECTS: returns the gpa
    public String getGpa() {
        return gpa;
    }

    // EFFECTS: sets the gpa with given value
    public void setGpa(String gpa) {
        this.gpa = gpa;
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
