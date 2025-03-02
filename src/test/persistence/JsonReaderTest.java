package persistence;

import model.Resume;
import model.Profile;
import model.Experience;
import model.Education;
import model.Skill;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: THIS CLASS ADOBTS METHODS FROM THE DEMO APPLICATION

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Resume resume = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyResume() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyResume.json");
        try {
            Resume resume = reader.read();
            assertNull(resume.getProfile());
            assertTrue(resume.getExperienceList().getExperiences().isEmpty());
            assertTrue(resume.getEducationList().getEducations().isEmpty());
            assertTrue(resume.getSkillsList().getSkills().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralResume() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralResume.json");
        try {
            Resume resume = reader.read();

            assertNotNull(resume.getProfile());
            checkProfile("Chris",
                    "7781234567",
                    "cpsc210@gmail.com",
                    "Vancouver, BC",
                    "Software Engineer", resume.getProfile());

            assertEquals(1, resume.getExperienceList().getExperiences().size());
            checkExperience("Software Engineer",
                    "Google",
                    "Seattle",
                    "2020",
                    "6",
                    "2023",
                    "12",
                    "Software Engineer",
                    resume.getExperienceList().getExperiences().get(0));

            assertEquals(1, resume.getEducationList().getEducations().size());
            checkEducation("4.0",
                    "UBC",
                    "Vancouver",
                    "2024",
                    "09",
                    "2028",
                    "05",
                    "BCom degree",
                    resume.getEducationList().getEducations().get(0));

            assertEquals(1, resume.getSkillsList().getSkills().size());
            checkSkill("Java", 5, resume.getSkillsList().getSkills().get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

}
