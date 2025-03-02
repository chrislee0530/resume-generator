package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an entire resume, containing profile, experiences, education, and skills
public class Resume implements Writable{
    private Profile profile;
    private ExperienceList experienceList;
    private EducationList educationList;
    private Skills skillsList;

    // EFFECTS: constructs an empty resume with no profile, experiences, education, or skills
    public Resume() {
        this.profile = null;
        this.experienceList = new ExperienceList();
        this.educationList = new EducationList();
        this.skillsList = new Skills();
    }

    // EFFECTS: converts this resume into a JSON object
    // NOTE: CODE BASED OFF OF DEMO APPLICATION
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        if (profile != null) {
            json.put("profile", profile.toJson());
        }
        json.put("experiences", experienceList.toJson());
        json.put("educations", educationList.toJson());
        json.put("skills", skillsList.toJson());
        return json;
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
    public void addExperience(Experience experience) {
        experienceList.addExperience(experience);
    }

    // MODIFIES: this
    // EFFECTS: removes an experience from the resume
    public void removeExperience(Experience experience) {
        experienceList.removeExperience(experience);
    }

    // EFFECTS: returns the experience list
    public ExperienceList getExperienceList() {
        return experienceList;
    }

    // MODIFIES: this
    // EFFECTS: adds an education entry to the resume
    public void addEducation(Education education) {
        educationList.addEducation(education);
    }

    // MODIFIES: this
    // EFFECTS: removes an education entry from the resume
    public void removeEducation(Education education) {
        educationList.removeEducation(education);
    }

    // EFFECTS: returns the education list
    public EducationList getEducationList() {
        return educationList;
    }

    // MODIFIES: this
    // EFFECTS: adds a skill to the resume
    public void addSkill(Skill skill) {
        skillsList.addSkill(skill);
    }

    // MODIFIES: this
    // EFFECTS: removes a skill from the resume
    public void removeSkill(Skill skill) {
        skillsList.removeSkill(skill);
    }

    // EFFECTS: returns the list of skills
    public Skills getSkillsList() {
        return skillsList;
    }
}
