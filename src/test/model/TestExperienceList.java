package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestExperienceList {
    private Experience experienceA;
    private Experience experienceB;
    private Experience experienceC;
    private Experience experienceD;
    private ExperienceList experienceList;

    @BeforeEach
    public void runBefore() {
        experienceA = new Experience("Professor", 
        "UBC Sauder", 
        "Vancouver", 
        "2005", "9", 
        "2010", "12",
        "COMM 190 Professor");

        experienceB = new Experience("Product Manager", 
        "Microsoft", 
        "Seattle", 
        "2020", "8", 
        "2023", "12",
        "Copilot prototyping");

        experienceC = new Experience("experienceC", 
        "experienceC", 
        "experienceC", 
        "2022", "8", 
        "0", "0",
        "experienceC");

        experienceD = new Experience("experienceD", 
        "experienceD", 
        "experienceD", 
        "2019", "8", 
        "2023", "8",
        "experienceD");

        experienceList = new ExperienceList();
    }

    @Test
    public void testConstructor() {
        assertTrue(experienceList.getExperiences().isEmpty());
        assertEquals(0, experienceList.getExperiences().size());
    }

    @Test
    public void testMostRecentExperiences() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);
        experienceList.addExperience(experienceC);
        experienceList.addExperience(experienceD);

        experienceList.mostRecentExperiences();
        ArrayList<Experience> experiences = experienceList.getExperiences();

        assertEquals(experienceC, experiences.get(0));
        assertEquals(experienceB, experiences.get(1));
        assertEquals(experienceD, experiences.get(2));
        assertEquals(experienceA, experiences.get(3));
    }

    @Test
    public void testMostExperiencedExperiences() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);
        experienceList.addExperience(experienceC);
        experienceList.addExperience(experienceD);

        experienceList.mostExperiencedExperiences();
        ArrayList<Experience> experiences = experienceList.getExperiences();

        assertEquals(experienceA, experiences.get(0));
        assertEquals(experienceD, experiences.get(1));
        assertEquals(experienceB, experiences.get(2));
        assertEquals(experienceC, experiences.get(3));
    }

    @Test
    public void testFirstNumLongExperiences() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);
        experienceList.addExperience(experienceC);
        experienceList.addExperience(experienceD);

        ArrayList<Experience> experiences = experienceList.getFirstNumLongList(3);
        
        assertEquals(3, experiences.size());
        assertEquals(experienceA, experiences.get(0));
        assertEquals(experienceB, experiences.get(1));
        assertEquals(experienceC, experiences.get(2));
    }

    @Test
    public void testAddExperience() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);

        ArrayList<Experience> experiences = experienceList.getExperiences();
        assertEquals(2, experiences.size());
        assertEquals(experienceA, experiences.get(0));
        assertEquals(experienceB, experiences.get(1));
    }

    @Test
    public void testAddExperienceDuplicate() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceA);

        ArrayList<Experience> experiences = experienceList.getExperiences();
        assertEquals(1, experiences.size());
        assertEquals(experienceA, experiences.get(0));
    }

    @Test
    public void testRemoveExperience() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);

        experienceList.removeExperience(experienceA);

        ArrayList<Experience> experiences = experienceList.getExperiences();
        assertEquals(1, experiences.size());
        assertEquals(experienceB, experiences.get(0));
    }

    @Test
    public void testRemoveExperienceDoesNotExist() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);

        experienceList.removeExperience(experienceC);

        ArrayList<Experience> experiences = experienceList.getExperiences();
        assertEquals(2, experiences.size());
        assertEquals(experienceA, experiences.get(0));
        assertEquals(experienceB, experiences.get(1));
    }

    @Test
    public void testToJson() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);

        JSONObject json = experienceList.toJson();
        assertNotNull(json);
        assertTrue(json.has("experiences"));

        JSONArray jsonArray = json.getJSONArray("experiences");
        assertEquals(2, jsonArray.length());

        JSONObject experienceA = jsonArray.getJSONObject(0);
        assertEquals("Professor", experienceA.getString("position"));
        assertEquals("UBC Sauder", experienceA.getString("institution"));
        assertEquals("Vancouver", experienceA.getString("location"));
        assertEquals("2005", experienceA.getString("startYear"));
        assertEquals("9", experienceA.getString("startMonth"));
        assertEquals("2010", experienceA.getString("endYear"));
        assertEquals("12", experienceA.getString("endMonth"));
        assertEquals("COMM 190 Professor", experienceA.getString("description"));

        JSONObject experienceB = jsonArray.getJSONObject(1);
        assertEquals("Product Manager", experienceB.getString("position"));
        assertEquals("Microsoft", experienceB.getString("institution"));
        assertEquals("Seattle", experienceB.getString("location"));
        assertEquals("2020", experienceB.getString("startYear"));
        assertEquals("8", experienceB.getString("startMonth"));
        assertEquals("2023", experienceB.getString("endYear"));
        assertEquals("12", experienceB.getString("endMonth"));
        assertEquals("Copilot prototyping", experienceB.getString("description"));
    }

    @Test
    public void testToJsonArrayEmpty() {
        JSONArray jsonArray = experienceList.toJsonArray();
        assertNotNull(jsonArray);
        assertEquals(0, jsonArray.length());
    }

    @Test
    public void testToJsonArrayWithExperiences() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);

        JSONArray jsonArray = experienceList.toJsonArray();
        assertNotNull(jsonArray);
        assertEquals(2, jsonArray.length());

        JSONObject experienceA = jsonArray.getJSONObject(0);
        assertEquals("Professor", experienceA.getString("position"));
        assertEquals("UBC Sauder", experienceA.getString("institution"));
        assertEquals("Vancouver", experienceA.getString("location"));
        assertEquals("2005", experienceA.getString("startYear"));
        assertEquals("9", experienceA.getString("startMonth"));
        assertEquals("2010", experienceA.getString("endYear"));
        assertEquals("12", experienceA.getString("endMonth"));
        assertEquals("COMM 190 Professor", experienceA.getString("description"));
    }

    @Test
    public void testRemoveExperiences() {
        experienceList.addExperience(experienceA);
        experienceList.addExperience(experienceB);

        experienceList.removeExperiences();
        assertTrue(experienceList.getExperiences().isEmpty());
    }


}
