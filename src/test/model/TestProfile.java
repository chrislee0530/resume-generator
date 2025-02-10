package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProfile {
    private Profile profile;

    @BeforeEach
    void runBefore() {
        profile = new Profile("Chris", "7781234567", "cpsc210@gmail.com", "Vancouver, BC V6T 1Z4",
        "Passionate BUCS student with a strong foundation in computational problem solving and Java programming.");
    }

    @Test
    void testConstructor() {
        assertEquals("Chris", profile.getName());
        assertEquals("7781234567", profile.getNumber());
        assertEquals("cpsc210@gmail.com", profile.getEmail());
        assertEquals("Vancouver, BC V6T 1Z4", profile.getAddress());
        assertEquals(
            "Passionate BUCS student with a strong foundation in computational problem solving and Java programming.",
            profile.getSummary());

    }

    @Test
    void testSetName() {
        profile.setName("Brian");
        assertEquals("Brian", profile.getName());
    }

    @Test
    void testSetNumber() {
        profile.setNumber("7780000000");
        assertEquals("7780000000", profile.getNumber());
    }

    @Test
    void testSetEmail() {
        profile.setEmail("cpsc110@gmail.com");
        assertEquals("cpsc110@gmail.com", profile.getEmail());
    }

    @Test
    void testSetAddress() {
        profile.setAddress("1234 Walnut Grove Dr");
        assertEquals("1234 Walnut Grove Dr", profile.getAddress());
    }

    @Test
    void testSetObjective() {
        profile.setObjective("No objective");
        assertEquals("No objective", profile.getSummary());   
    }

}
