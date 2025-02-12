package model;

import java.util.ArrayList;

// Represents the list of skills of the user
public class Skills {
    private ArrayList<String> skills;

    // EFFECTS: constructs an empty skills section
    public Skills() {
        skills = new ArrayList<String>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new skill to the list, if there exists a same skill, don't
    // add
    public void addSkill(String skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a skill from the list if it exists
    public void removeSkill(String skill) {
        if (skills.contains(skill)) {
            skills.remove(skill);
        }
    }

    // EFFECTS: returns the list of skills
    public ArrayList<String> getSkills() {
        return skills;
    }

}
