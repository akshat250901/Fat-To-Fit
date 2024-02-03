package persistence;

import org.json.JSONObject;

// This was taken from the JsonSerializationDemo repository on github CPSC 210
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
