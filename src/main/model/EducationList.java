package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents all educations of the user
public class EducationList implements Writable {
    private ArrayList<Education> educationList;

    // EFFECTS: constructs an empty list of educations
    public EducationList() {
        educationList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: reorders the list from the most recent to the least recent education
    public void mostRecentEducations() {
        ArrayList<Education> holder = new ArrayList<>();

        while (!educationList.isEmpty()) {
            int maxMonths = -1;
            Education recentEducation = null;
            for (Education education : educationList) {
                String endYString = education.getEndYear();
                int endY = Integer.parseInt(endYString);
                String endMString = education.getEndMonth();
                int endM = Integer.parseInt(endMString);

                int educationMonths = endY * 12 + endM;
                if (endY == 0) {
                    recentEducation = education;
                    break;
                }
                if (educationMonths > maxMonths) {
                    maxMonths = educationMonths;
                    recentEducation = education;
                }
            }
            holder.add(recentEducation);
            educationList.remove(recentEducation);
        }
        educationList.addAll(holder);
    }

    // MODIFIES: this
    // EFFECTS: adds a new education to the list, unless there already exists one
    public void addEducation(Education education) {
        if (!educationList.contains(education)) {
            educationList.add(education);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an education from the list if it exists
    public void removeEducation(Education education) {
        if (educationList.contains(education)) {
            educationList.remove(education);
        }
    }

    // EFFECTS: returns the list of education
    public ArrayList<Education> getEducations() {
        return educationList;
    }

    @Override
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("educations", toJsonArray());
        return json;
    }

    // EFFECTS: returns educations as a JSON array
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Education e : educationList) {
            jsonArray.put(e.toJson());
        }
        
        return jsonArray;
    }

}
