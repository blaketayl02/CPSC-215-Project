package persistence;

import org.json.JSONObject;

//Writes file as a JSON object

public interface WriteFile {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}

