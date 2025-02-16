package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEducation {

    private Education bCom;
    private Education highSchool;

    @BeforeEach
    void runBefore() {
        bCom = new Education("4.0", 
        "UBC Sauder", 
        "Vancouver", 
        "2024", "9", 
        "2028", "5", 
        "BCom degree; Combined Major in Business and Computer Science");

        highSchool = new Education("98.9", 
        "Walnut Grove Secondary School", 
        "Langley", 
        "2019", "9", 
        "2024", "6", 
        "High School Diploma");
    }

    @Test
    void testConstructor() {
        assertEquals("4.0", bCom.getGpa());
        assertEquals("UBC Sauder", bCom.getInstitution());
        assertEquals("Vancouver", bCom.getLocation());
        assertEquals("2024", bCom.getStartYear());
        assertEquals("9", bCom.getStartMonth());
        assertEquals("2028", bCom.getEndYear());
        assertEquals("5", bCom.getEndMonth());
        assertEquals("BCom degree; Combined Major in Business and Computer Science", bCom.getDescription());
    }

    @Test
    void testSetGpa() {
        bCom.setGpa("3.75");
        assertEquals("3.75", bCom.getGpa());
    }

    @Test
    void testSetInstitution() {
        bCom.setInstitution("UBC Okanagan");
        assertEquals("UBC Okanagan", bCom.getInstitution());
    }

    @Test
    void testSetLocation() {
        bCom.setLocation("Okanagan");
        assertEquals("Okanagan", bCom.getLocation());
    }

    @Test
    void testSetStartYear() {
        bCom.setStartYear("2026");
        assertEquals("2026", bCom.getStartYear());
    }

    @Test
    void testSetStartMonth() {
        bCom.setStartMonth("11");
        assertEquals("11", bCom.getStartMonth());
    }

    @Test
    void testSetEndYear() {
        bCom.setEndYear("2030");
        assertEquals("2030", bCom.getEndYear());
    }

    @Test
    void testSetEndMonth() {
        bCom.setEndMonth("1");
        assertEquals("1", bCom.getEndMonth());
    }

    @Test
    void testSetDescription() {
        bCom.setDescription("BCom degree; Finance Major");
        assertEquals("BCom degree; Finance Major", bCom.getDescription());
    }

}
