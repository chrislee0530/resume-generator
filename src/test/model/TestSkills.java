package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSkills {
    private Skills skills;
    private Skill a;
    private Skill b;
    private Skill c;
    private Skill d;

    
    @BeforeEach
    void runBefore() {
        skills = new Skills();
        a = new Skill("a", 2);
        b = new Skill("b", 3);
        c = new Skill("c", 4);
        d = new Skill("d", 5);

    }

    @Test
    void testConstructor() {
        assertTrue(skills.getSkills().isEmpty());

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(0, skillsList.size());
    }

    @Test
    void testTopSkillsEmptySkillsList() {
        ArrayList<Skill> topSkills = new ArrayList<>();
        assertEquals(topSkills, skills.topSkills(3));
    }

    @Test
    void testTopSkillsReorder() {
        skills.addSkill(a);
        skills.addSkill(b);
        skills.addSkill(c);

        ArrayList<Skill> topSkills;
        topSkills = skills.topSkills(3);

        assertEquals(c, topSkills.get(0));
        assertEquals(b, topSkills.get(1));
        assertEquals(a, topSkills.get(2));

        assertEquals(skills.getSkills().size(), 3);
    }

    @Test
    void testAddSkill() {
        skills.addSkill(a);
        skills.addSkill(b);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(2, skillsList.size());
        assertEquals(a, skillsList.get(0));
        assertEquals(b, skillsList.get(1));
    }

    @Test
    void testAddSkillDuplicate() {
        skills.addSkill(a);
        skills.addSkill(a);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(1, skillsList.size());
        assertEquals(a, skillsList.get(0));
    }

    @Test
    void testRemoveSkill() {
        skills.addSkill(a);
        skills.addSkill(b);

        skills.removeSkill(a);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(1, skillsList.size());
        assertEquals(b, skillsList.get(0));
    }

    @Test
    void testRemoveSkillDoesNotExist() {
        skills.addSkill(a);
        skills.addSkill(b);

        skills.removeSkill(d);

        ArrayList<Skill> skillsList = skills.getSkills();
        assertEquals(2, skillsList.size());
        assertEquals(a, skillsList.get(0));
        assertEquals(b, skillsList.get(1));
    }

    @Test
    void testToJson() {
        skills.addSkill(a);
        skills.addSkill(b);

        JSONObject json = skills.toJson();
        assertNotNull(json);
        assertTrue(json.has("skills"));

        JSONArray jsonArray = json.getJSONArray("skills");
        assertEquals(2, jsonArray.length());

        JSONObject skillJson1 = jsonArray.getJSONObject(0);
        assertEquals("a", skillJson1.getString("title"));
        assertEquals(2, skillJson1.getInt("level"));

        JSONObject skillJson2 = jsonArray.getJSONObject(1);
        assertEquals("b", skillJson2.getString("title"));
        assertEquals(3, skillJson2.getInt("level"));
    }

    @Test
    void testToJsonArrayEmpty() {
        JSONArray jsonArray = skills.toJsonArray();
        assertNotNull(jsonArray);
        assertEquals(0, jsonArray.length());
    }

    @Test
    void testToJsonArrayWithSkills() {
        skills.addSkill(a);
        skills.addSkill(b);

        JSONArray jsonArray = skills.toJsonArray();
        assertNotNull(jsonArray);
        assertEquals(2, jsonArray.length());

        JSONObject skillJson1 = jsonArray.getJSONObject(0);
        assertEquals("a", skillJson1.getString("title"));
        assertEquals(2, skillJson1.getInt("level"));

        JSONObject skillJson2 = jsonArray.getJSONObject(1);
        assertEquals("b", skillJson2.getString("title"));
        assertEquals(3, skillJson2.getInt("level"));
    }
}
