package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSkills {
    private Skills skills;
    
    @BeforeEach
    void runBefore() {
        skills = new Skills();
    }

    @Test
    void testConstructor() {
        assertNull(skills.getSkills());

        ArrayList<String> skillsList = skills.getSkills();
        assertEquals(0, skillsList.size());
    }

    @Test
    void testAddSkill() {
        skills.addSkill("Java");
        skills.addSkill("Python");

        ArrayList<String> skillsList = skills.getSkills();
        assertEquals(2, skillsList.size());
        assertEquals("Java", skillsList.get(0));
        assertEquals("Python", skillsList.get(1));
    }

    @Test
    void testAddSkillDuplicate() {
        skills.addSkill("Java");
        skills.addSkill("Java");

        ArrayList<String> skillsList = skills.getSkills();
        assertEquals(1, skillsList.size());
        assertEquals("Java", skillsList.get(0));
    }
}
