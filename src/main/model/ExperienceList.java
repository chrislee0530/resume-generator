package model;

import java.util.ArrayList;

// Represents all experiences of the user
public class ExperienceList {
    private ArrayList<Experience> experienceList;

    // EFFECTS: constructs an empty list of experiences
    public ExperienceList() {
        experienceList = new ArrayList<Experience>();
    }

    // MODIFIES: this
    // EFFECTS: reorders the list from the most recent to the least recent experience
    public void mostRecentExperiences() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: reorders the list from the most experienced to the least least experienced
    public void mostExperiencedExperiences() {
        // stub
    }

    // EFFECTS: returns the first num number of experiences
    public ArrayList<Experience> firstNumLongList() {
        return null;// stub
    }

    // MODIFIES: this
    // EFFECTS: adds a new experience to the list, unless there already exists one
    public void addExperience(Experience experience) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes an experience from the list if it exists
    public void removeExperience (Experience experience) {
        // stub
    }

    // EFFECTS: returns the list of experience
    public ArrayList<Experience> getExperiences() {
        return null;
    }

}
