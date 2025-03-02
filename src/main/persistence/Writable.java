package persistence;

import org.json.JSONObject;

// NOTE: CODE BASED OFF OF DEMO APPLICATION
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
