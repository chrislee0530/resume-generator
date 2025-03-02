package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestResume {
    private Resume resume;
    private Profile profile;
    private Experience experience;
    private Education education;
    private Skill skill;

    @BeforeEach
    void runBefore() {
        resume = new Resume();
        profile = new Profile("Chris", "7781234567", "cpsc210@gmail.com", "Vancouver, BC", "Software Engineer");
        experience = new Experience("Software Engineer", "Google", "Seattle", "2020", "6", "2023", "12", "Worked on AI projects.");
        education = new Education("4.0", "UBC", "Vancouver", "2016", "9", "2020", "5", "BCom Major");
        skill = new Skill("Java", 5);
    }

    @Test
    void testConstructor() {
        assertNull(resume.getProfile());
        assertTrue(resume.getExperienceList().getExperiences().isEmpty());
        assertTrue(resume.getEducationList().getEducations().isEmpty());
        assertTrue(resume.getSkillsList().getSkills().isEmpty());
    }

    @Test
    void testSetAndGetProfile() {
        resume.setProfile(profile);
        assertEquals(profile, resume.getProfile());
    }

    @Test
    void testAddAndRemoveExperience() {
        resume.addExperience(experience);
        assertEquals(1, resume.getExperienceList().getExperiences().size());
        assertEquals(experience, resume.getExperienceList().getExperiences().get(0));

        resume.removeExperience(experience);
        assertTrue(resume.getExperienceList().getExperiences().isEmpty());
    }

    @Test
    void testAddAndRemoveEducation() {
        resume.addEducation(education);
        assertEquals(1, resume.getEducationList().getEducations().size());
        assertEquals(education, resume.getEducationList().getEducations().get(0));

        resume.removeEducation(education);
        assertTrue(resume.getEducationList().getEducations().isEmpty());
    }

    @Test
    void testAddAndRemoveSkill() {
        resume.addSkill(skill);
        assertEquals(1, resume.getSkillsList().getSkills().size());
        assertEquals(skill, resume.getSkillsList().getSkills().get(0));

        resume.removeSkill(skill);
        assertTrue(resume.getSkillsList().getSkills().isEmpty());
    }
}
