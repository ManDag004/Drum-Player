package persistence;

import org.json.JSONObject;

// REFERENCE : code below was referred from the following project :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

/*
 *  Represents a Writable Interface that that is implemented by classes that convert their data to JSONObject
 */
public interface Writable {
    // Effects: meant to convert the object from which it is called to JSONObject
    JSONObject toJson();
}
