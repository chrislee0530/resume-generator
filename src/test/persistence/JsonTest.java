package persistence;

import model.Experience;
import model.Education;
import model.Skill;
import model.Profile;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: ADOPTED FROM DEMO APPLICATION

public class JsonTest {

    protected void checkProfile(String name, String number, String email, String address, String objective,
            Profile profile) {
        assertEquals(name, profile.getName());
        assertEquals(number, profile.getNumber());
        assertEquals(email, profile.getEmail());
        assertEquals(address, profile.getAddress());
        assertEquals(objective, profile.getObjective());
    }

    protected void checkExperience(String position, String institution, String location, String startYear,
            String startMonth, String endYear, String endMonth, String description,
            Experience experience) {
        assertEquals(position, experience.getPosition());
        assertEquals(institution, experience.getInstitution());
        assertEquals(location, experience.getLocation());
        assertEquals(startYear, experience.getStartYear());
        assertEquals(startMonth, experience.getStartMonth());
        assertEquals(endYear, experience.getEndYear());
        assertEquals(endMonth, experience.getEndMonth());
        assertEquals(description, experience.getDescription());
    }

    protected void checkEducation(String gpa, String institution, String location, String startYear,
            String startMonth, String endYear, String endMonth, String description,
            Education education) {
        assertEquals(gpa, education.getGpa());
        assertEquals(institution, education.getInstitution());
        assertEquals(location, education.getLocation());
        assertEquals(startYear, education.getStartYear());
        assertEquals(startMonth, education.getStartMonth());
        assertEquals(endYear, education.getEndYear());
        assertEquals(endMonth, education.getEndMonth());
        assertEquals(description, education.getDescription());
    }

    protected void checkSkill(String title, int level, Skill skill) {
        assertEquals(title, skill.getTitle());
        assertEquals(level, skill.getLevel());
    }
}
