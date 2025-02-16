package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEducationList {
    private Education educationA;
    private Education educationB;
    private Education educationC;
    private Education educationD;
    private EducationList educationList;

    @BeforeEach
    public void runBefore() {
        educationA = new Education("1", 
        "educationA", 
        "educationA", 
        "2005", "9", 
        "2010", "12",
        "educationA");

        educationB = new Education("2", 
        "educationB", 
        "educationB", 
        "2020", "8", 
        "2023", "12",
        "educationB");

        educationC = new Education("3", 
        "educationC", 
        "educationC", 
        "2022", "8", 
        "0", "0",
        "educationC");

        educationD = new Education("4", 
        "educationD", 
        "educationD", 
        "2019", "8", 
        "2023", "8",
        "educationD");

        educationList = new EducationList();
    }

    @Test
    public void testConstructor() {
        assertTrue(educationList.getEducations().isEmpty());
        assertEquals(0, educationList.getEducations().size());
    }

    @Test
    public void testMostRecentExperiences() {
        educationList.addEducation(educationA);
        educationList.addEducation(educationB);
        educationList.addEducation(educationC);
        educationList.addEducation(educationD);

        educationList.mostRecentEducations();
        ArrayList<Education> experiences = educationList.getEducations();

        assertEquals(educationC, experiences.get(0));
        assertEquals(educationB, experiences.get(1));
        assertEquals(educationD, experiences.get(2));
        assertEquals(educationA, experiences.get(3));
    }

    @Test
    public void testAddExperience() {
        educationList.addEducation(educationA);
        educationList.addEducation(educationB);

        ArrayList<Education> educations = educationList.getEducations();
        assertEquals(2, educations.size());
        assertEquals(educationA, educations.get(0));
        assertEquals(educationB, educations.get(1));
    }

    @Test
    public void testAddExperienceDuplicate() {
        educationList.addEducation(educationA);
        educationList.addEducation(educationA);

        ArrayList<Education> educations = educationList.getEducations();
        assertEquals(1, educations.size());
        assertEquals(educationA, educations.get(0));
    }

    @Test
    public void testRemoveExperience() {
        educationList.addEducation(educationA);
        educationList.addEducation(educationB);

        educationList.removeEducation(educationA);

        ArrayList<Education> educations = educationList.getEducations();
        assertEquals(1, educations.size());
        assertEquals(educationB, educations.get(0));
    }

    @Test
    public void testRemoveExperienceDoesNotExist() {
        educationList.addEducation(educationA);
        educationList.addEducation(educationB);

        educationList.removeEducation(educationC);

        ArrayList<Education> experiences = educationList.getEducations();
        assertEquals(2, experiences.size());
        assertEquals(educationA, experiences.get(0));
        assertEquals(educationB, experiences.get(1));
    }

    

}
