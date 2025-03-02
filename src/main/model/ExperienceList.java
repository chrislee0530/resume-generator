package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents all experiences of the user
public class ExperienceList implements Writable{
    private ArrayList<Experience> experienceList;

    // EFFECTS: constructs an empty list of experiences
    public ExperienceList() {
        experienceList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: reorders the list from the most recent to the least recent experience
    public void mostRecentExperiences() {
        ArrayList<Experience> holder = new ArrayList<>();

        while (!experienceList.isEmpty()) {
            int maxMonths = -1;
            Experience recentExperience = null;
            for (Experience experience : experienceList) {
                String endYString = experience.getEndYear();
                int endY = Integer.parseInt(endYString);
                String endMString = experience.getEndMonth();
                int endM = Integer.parseInt(endMString);

                int experienceMonths = endY * 12 + endM;
                if (endY == 0) {
                    recentExperience = experience;
                    break;
                }
                if (experienceMonths > maxMonths) {
                    maxMonths = experienceMonths;
                    recentExperience = experience;
                }
            }
            holder.add(recentExperience);
            experienceList.remove(recentExperience);
        }
        experienceList.addAll(holder);
    }

    // MODIFIES: this
    // EFFECTS: reorders the list from the most experienced to the least least experienced
    public void mostExperiencedExperiences() {
        ArrayList<Experience> holder = new ArrayList<>();

        while (!experienceList.isEmpty()) {
            int maxMonths = -1;
            Experience experiencedExperience = null;
            for (Experience experience : experienceList) {
                if (experience.getTotalDuration() > maxMonths) {
                    maxMonths = experience.getTotalDuration();
                    experiencedExperience = experience;
                }
            }
            holder.add(experiencedExperience);
            experienceList.remove(experiencedExperience);
        }
        experienceList.addAll(holder);
    }

    // EFFECTS: returns the first num number of experiences
    public ArrayList<Experience> getFirstNumLongList(int num) {
        ArrayList<Experience> result = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            result.add(experienceList.get(i));
        }

        return result;
    }

    // MODIFIES: this
    // EFFECTS: adds a new experience to the list, unless there already exists one
    public void addExperience(Experience experience) {
        if (!experienceList.contains(experience)) {
            experienceList.add(experience);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an experience from the list if it exists
    public void removeExperience(Experience experience) {
        if (experienceList.contains(experience)) {
            experienceList.remove(experience);
        }
    }

    // EFFECTS: returns the list of experience
    public ArrayList<Experience> getExperiences() {
        return experienceList;
    }

    @Override
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("experiences", toJsonArray());
        return json;
    }

    // EFFECTS: returns experiences as a JSON array
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    private JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Experience e : experienceList) {
            jsonArray.put(e.toJson());
        }
        
        return jsonArray;
    }

}
