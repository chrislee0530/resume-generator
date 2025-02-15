package model;

import java.util.ArrayList;

// Represents the list of skills of the user
public class Skills {
    private ArrayList<Skill> skills;

    // EFFECTS: constructs an empty list of skills
    public Skills() {
        skills = new ArrayList<Skill>();
    }

    // REQUIRES: skills.size() > 0
    // MODIFIES: this
    // EFFECTS: reorder the skills list from highest to lowest level and
    //          return the first num number of skills in the list
    public ArrayList<Skill> topSkills(int num) {
        ArrayList<Skill> topSkills = new ArrayList<>();

        if (skills.isEmpty()) {
            return topSkills;
        }
        
        int max = skills.get(0).getLevel();
        Skill topSkill = skills.get(0);

        while (topSkills.size() < num) {
            for (Skill skill : skills) {
                if (skill.getLevel() > max) {
                    max = skill.getLevel();
                    topSkill = skill;
                }
            }
            topSkills.add(topSkill);
            skills.remove(topSkill);
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

}
