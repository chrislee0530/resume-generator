package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents the list of skills of the user
public class Skills implements Writable {
    private ArrayList<Skill> skills;

    // EFFECTS: constructs an empty list of skills
    public Skills() {
        skills = new ArrayList<Skill>();
    }

    // REQUIRES: skills.size() >= 0
    // MODIFIES: this
    // EFFECTS: reorder the skills list from highest to lowest level and
    // return the first num number of skills in the list
    public ArrayList<Skill> topSkills(int num) {
        ArrayList<Skill> topSkills = new ArrayList<>();
        ArrayList<Skill> clone = new ArrayList<>(skills);

        if (clone.isEmpty()) {
            return topSkills;
        }

        int max = clone.get(0).getLevel();
        Skill topSkill = clone.get(0);

        while (topSkills.size() < num) {
            for (Skill skill : clone) {
                if (skill.getLevel() > max) {
                    max = skill.getLevel();
                    topSkill = skill;
                }
            }
            topSkills.add(topSkill);
            clone.remove(topSkill);
            max = 0;
        }

        return topSkills;
    }

    // MODIFIES: this
    // EFFECTS: adds a new skill to the list, unless there already exists one
    public void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a skill from the list if it exists
    public void removeSkill(Skill skill) {
        if (skills.contains(skill)) {
            skills.remove(skill);
        }
    }

    // EFFECTS: returns the list of skills
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    @Override
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("skills", toJsonArray());
        return json;
    }

    // EFFECTS: returns skills as a JSON array
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    private JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Skill s : skills) {
            jsonArray.put(s.toJson());
        }
        
        return jsonArray;
    }

}
