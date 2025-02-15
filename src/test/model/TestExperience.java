package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestExperience {
    private Experience productManager;
    private Experience professor;

    @BeforeEach
    void runBefore() {
        productManager = new Experience("Product Manager", 
        "Microsoft", 
        "Microsoft", 
        2020, 8, 
        2023, 12,
        "Copilot prototyping");

        professor = new Experience("Professor", 
        "UBC Sauder", 
        "Vancouver", 
        2005, 9, 
        0, 0,
        "COMM 190 Professor");
    }

    @Test
    void testConstructor() {
        assertEquals("Microsoft", productManager.getInstitution());
        assertEquals("Microsoft", productManager.getLocation());
        assertEquals(2020, productManager.getStartYear());
        assertEquals(8, productManager.getStartMonth());
        assertEquals(2023, productManager.getEndYear());
        assertEquals(12, productManager.getEndMonth());
        assertEquals("Copilot prototyping", productManager.getDescription());
    }

    @Test
    void testSetPosition() {
        productManager.setPosition("Project Manager");
        assertEquals("Project Manager", productManager.getPosition());
    }

    @Test
    void testSetInstitution() {
        productManager.setInstitution("Google");
        assertEquals("Google", productManager.getInstitution());
    }

    @Test
    void testSetLocation() {
        productManager.setInstitution("Seattle");
        assertEquals("Seattle", productManager.getLocation());
    }

    @Test
    void testSetStartYear() {
        productManager.setStartYear(2024);
        assertEquals(2024, productManager.getStartYear());
    }

    @Test
    void testSetStartMonth() {
        productManager.setStartYear(5);
        assertEquals(5, productManager.getStartMonth());
    }

    @Test
    void testSetEndYear() {
        productManager.setEndYear(2028);
        assertEquals(2028, productManager.getEndYear());
    }

    @Test
    void testSetEndMonth() {
        productManager.setEndMonth(3);
        assertEquals(3, productManager.getEndMonth());
    }

    @Test
    void testSetDescription() {
        productManager.setDescription("Google Project Manager");
        assertEquals("Google Project Manager", productManager.getDescription());
    }

}

    