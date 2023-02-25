package model;

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
}
