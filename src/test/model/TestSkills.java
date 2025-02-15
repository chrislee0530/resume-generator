package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSkills {
    private Skills skills;
    private Skill java;
    private Skill python;
    private Skill analytics;

    
    @BeforeEach
    void runBefore() {
        skills = new Skills();
        java = new Skill("Java", 2);
        python = new Skill("Python", 3);
        analytics = new Skill("Analytics", 5);
    }

    @Test
    void testConstructor() {
        assertTrue(skills.getSkills().isEmpty());

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(0, skillsList.size());
    }

    @Test
    void testAddSkill() {
        skills.addSkill(java);
        skills.addSkill(python);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(2, skillsList.size());
        assertEquals(java, skillsList.get(0));
        assertEquals(python, skillsList.get(1));
    }

    @Test
    void testAddSkillDuplicate() {
        skills.addSkill(java);
        skills.addSkill(java);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(1, skillsList.size());
        assertEquals(java, skillsList.get(0));
    }

    @Test
    void testRemoveSkill() {
        skills.addSkill(java);
        skills.addSkill(python);

        skills.removeSkill(java);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(1, skillsList.size());
        assertEquals(python, skillsList.get(0));
    }

    @Test
    void testRemoveSkillDoesNotExist() {
        skills.addSkill(java);
        skills.addSkill(python);

        skills.removeSkill(analytics);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(2, skillsList.size());
        assertEquals(java, skillsList.get(0));
        assertEquals(python, skillsList.get(1));
    }
}
