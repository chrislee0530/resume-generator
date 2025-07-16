package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import persistence.Writable;

// Represents an entire resume, containing profile, experiences, education, and skills
public class Resume implements Writable {
    private Profile profile;
    private List<Experience> experiences;
    private List<Education> educations;
    private List<Skill> skills;

    // EFFECTS: constructs an empty resume with no profile, experiences, education,
    // or skills
    public Resume() {
        this.profile = null;
        this.experiences = new ArrayList<>();
        educations = new ArrayList<>();
        skills = new ArrayList<>();
    }

    // EFFECTS: converts this resume into a JSON object
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        if (profile != null) {
            json.put("profile", profile.toJson());
        }

        json.put("experiences", toJsonArray(experiences));
        json.put("educations", toJsonArray(educations));
        json.put("skills", toJsonArray(skills));

        return json;
    }

    // helper method
    private <T extends Writable> org.json.JSONArray toJsonArray(List<T> list) {
        org.json.JSONArray array = new org.json.JSONArray();
        for (T item : list) {
            array.put(item.toJson());
        }
        return array;
    }

    // MODIFIES: this
    // EFFECTS: sets the profile information for the resume
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // EFFECTS: returns the profile
    public Profile getProfile() {
        return profile;
    }

    // MODIFIES: this
    // EFFECTS: adds an experience to the resume
    public void addExperience(Experience exp) {
        experiences.add(exp);
    }

    // MODIFIES: this
    // EFFECTS: removes an experience from the resume
    public void removeExperience(Experience experience) {
        experiences.remove(experience);
    }

    // EFFECTS: returns the experience list
    public List<Experience> getExperiences() {
        return experiences;
    }

    // MODIFIES: this
    // EFFECTS: adds an education entry to the resume
    public void addEducation(Education edu) {
        educations.add(edu);
    }

    // MODIFIES: this
    // EFFECTS: removes an education entry from the resume
    public void removeEducation(Education education) {
        educations.remove(education);
    }

    // EFFECTS: returns the education list
    public List<Education> getEducations() {
        return educations;
    }

    // MODIFIES: this
    // EFFECTS: adds a skill to the resume
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    // MODIFIES: this
    // EFFECTS: removes a skill from the resume
    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }

    // EFFECTS: returns the list of skills
    public List<Skill> getSkills() {
        return skills;
    }
}
