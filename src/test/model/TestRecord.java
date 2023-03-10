package model;

import org.json.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestRecord {
    private Record record;

    @Test
    void testRecordValues() {
        record = new Record('d', 100);
        assertEquals('d', record.getKey());
        assertEquals(100, record.getTime());
    }

    @Test
    void testToJson() {
        record = new Record('d', 100);
//        JSONObject json = new JSONObject();
//        json.put("key", "d");
//        json.put("time", 100);
        assertEquals(2, record.toJson().length());
        assertEquals("d", record.toJson().getString("key"));
        assertEquals(100, record.toJson().getInt("time"));
    }
}
