package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a skill of the user
public class Skill implements Writable {
    private String title;
    private int level;

    // EFFECTS: constructs a new skill with title and level
    public Skill(String title, int level) {
        this.title = title;
        this.level = level;
    }

    // EFFECTS: converts this skill into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("level", level);
        return json;
    }

    // EFFECTS: Returns the title of the skill
    public String getTitle() {
        return title;
    }

    // MODIFIES: this
    // EFFECTS: sets the title of the skill
    public void setTitle(String title) {
        this.title = title;
    }

    // EFFECTS: Returns level of the skill
    public int getLevel() {
        return level;
    }

    // MODIFIES: this
    // EFFECTS: sets the level of the skill
    public void setLevel(int level) {
        this.level = level;
    }

}
