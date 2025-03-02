// package persistence;

// import model.Resume;
// import model.Profile;
// import model.Experience;
// import model.ExperienceList;
// import model.Education;
// import model.EducationList;
// import model.Skill;
// import model.Skills;

// import java.io.IOException;
// import java.util.List;

// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

// // NOTE: ADOPTED FROM DEMO APPLICATION

// public class JsonWriterTest extends JsonTest{

//     @Test
//     void testWriterInvalidFile() {
//         try {
//             Resume resume = new Resume();
//             JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
//             writer.open();
//             fail("IOException was expected");
//         } catch (IOException e) {
//             // pass
//         }
//     }

//     @Test
//     void testWriterEmptyResume() {
//         try {
//             Resume resume = new Resume();
//             JsonWriter writer = new JsonWriter("./data/testWriterEmptyResume.json");
//             writer.open();
//             writer.write(resume);
//             writer.close();

//             JsonReader reader = new JsonReader("./data/testWriterEmptyResume.json");
//             resume = reader.read();
//             assertNull(resume.getProfile());
//             assertTrue(resume.getExperienceList().getExperiences().isEmpty());
//             assertTrue(resume.getEducationList().getEducations().isEmpty());
//             assertTrue(resume.getSkillsList().getSkills().isEmpty());
//         } catch (IOException e) {
//             fail("Exception should not have been thrown");
//         }
//     }

//     @Test
//     void testWriterGeneralResume() {
//         try {
//             Resume resume = new Resume();
//             resume.setProfile(new Profile("Chris", "7781234567", "cpsc210@gmail.com", "Vancouver, BC", "Software Engineer"));
//             resume.addExperience(new Experience("Software Engineer", "Google", "Seattle", "2020", "6", "2023", "12", "Software Engineer"));
//             resume.addEducation(new Education("4.0", "UBC", "Vancouver", "2024", "09", "2028", "04", "BCom degree"));
//             resume.addSkill(new Skill("Java", 5));
//             JsonWriter writer = new JsonWriter("./data/testWriterGeneralResume.json");
//             writer.open();
//             writer.write(resume);
//             writer.close();

//             JsonReader reader = new JsonReader("./data/testWriterGeneralResume.json");
//             resume = reader.read();

//             assertNotNull(resume.getProfile());
//             checkProfile("Chris", "7781234567", "cpsc210@gmail.com", "Vancouver, BC", "Software Engineer", resume.getProfile());
            
//             assertEquals(1, resume.getExperienceList().getExperiences().size());
//             checkExperience("Software Engineer", "Google", "Seattle", "2020", "6", "2023", "12", "Software Engineer",
//                             resume.getExperienceList().getExperiences().get(0));

//             assertEquals(1, resume.getEducationList().getEducations().size());
//             checkEducation("4.0", "UBC", "Vancouver", "2024", "09", "2028", "04", "BCom degree",
//                            resume.getEducationList().getEducations().get(0));

//             assertEquals(1, resume.getSkillsList().getSkills().size());
//             checkSkill("Java", 5, resume.getSkillsList().getSkills().get(0));

//         } catch (IOException e) {
//             fail("Exception should not have been thrown");
//         }
//     }

// }
