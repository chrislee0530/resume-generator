package persistence;

import model.Resume;
import model.Profile;
import model.Experience;
import model.Education;
import model.Skill;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a reader that reads workroom from JSON data stored in file

// NOTE: ENTIRE CLASS IS ADOPTED FROM THE DEMO APPLICATION

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads resume from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Resume read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseResume(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses resume from JSON object and returns it
    private Resume parseResume(JSONObject jsonObject) {
        Resume resume = new Resume();

        JSONObject profileJson = jsonObject.optJSONObject("profile");
        if (profileJson != null) {
            resume.setProfile(parseProfile(profileJson));
        }

        JSONArray experiencesArray = jsonObject.optJSONArray("experiences");
        if (experiencesArray != null) {
            parseExperiences(experiencesArray, resume);
        }

        JSONArray educationsArray = jsonObject.optJSONArray("educations");
        if (educationsArray != null) {
            parseEducations(educationsArray, resume);
        }

        JSONArray skillsArray = jsonObject.optJSONArray("skills");
        if (skillsArray != null) {
            parseSkills(skillsArray, resume);
        }

        return resume;
    }

    // EFFECTS: parses profile from JSON object and returns it
    private Profile parseProfile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String number = jsonObject.getString("number");
        String email = jsonObject.getString("email");
        String address = jsonObject.getString("address");
        String objective = jsonObject.getString("objective");

        return new Profile(name, number, email, address, objective);
    }

    // MODIFIES: resume
    // EFFECTS: parses experiences from JSON array and adds them to resume
    private void parseExperiences(JSONArray jsonArray, Resume resume) {
        for (Object json : jsonArray) {
            JSONObject nextExp = (JSONObject) json;
            resume.addExperience(parseExperience(nextExp));
        }
    }

    // EFFECTS: parses a single experience from JSON object and returns it
    private Experience parseExperience(JSONObject jsonObject) {
        return new Experience(
                jsonObject.getString("position"),
                jsonObject.getString("institution"),
                jsonObject.getString("location"),
                jsonObject.getString("startYear"),
                jsonObject.getString("startMonth"),
                jsonObject.getString("endYear"),
                jsonObject.getString("endMonth"),
                jsonObject.getString("description"));
    }

    // MODIFIES: resume
    // EFFECTS: parses educations from JSON array and adds them to resume
    private void parseEducations(JSONArray jsonArray, Resume resume) {
        for (Object json : jsonArray) {
            JSONObject nextEdu = (JSONObject) json;
            resume.addEducation(parseEducation(nextEdu));
        }
    }

    // EFFECTS: parses a single education from JSON object and returns it
    private Education parseEducation(JSONObject jsonObject) {
        return new Education(
                jsonObject.getString("gpa"),
                jsonObject.getString("institution"),
                jsonObject.getString("location"),
                jsonObject.getString("startYear"),
                jsonObject.getString("startMonth"),
                jsonObject.getString("endYear"),
                jsonObject.getString("endMonth"),
                jsonObject.getString("description"));
    }

    // MODIFIES: resume
    // EFFECTS: parses skills from JSON array and adds them to resume
    private void parseSkills(JSONArray jsonArray, Resume resume) {
        for (Object json : jsonArray) {
            JSONObject nextSkill = (JSONObject) json;
            resume.addSkill(parseSkill(nextSkill));
        }
    }

    // EFFECTS: parses a single skill from JSON object and returns it
    private Skill parseSkill(JSONObject jsonObject) {
        return new Skill(
                jsonObject.getString("title"),
                jsonObject.getInt("level"));
    }

}
