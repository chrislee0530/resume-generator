package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSkill {
    private Skill java;
    private Skill python;

    @BeforeEach
    void runBefore() {
        java = new Skill("Java", 2);
    }

    @Test
    void testConstructor() {
        assertEquals("Java", java.getTitle());
        assertEquals(2, java.getLevel());
    }

    @Test
    void testSetTitle() {
        java.setTitle("Programming in Java");
        assertEquals("Programming in Java", java.getTitle());
    }

    @Test
    void testSetLevel() {
        java.setLevel(3);
        assertEquals(3, java.getLevel());
    }

}
