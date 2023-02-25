package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {
    private Player player;

    @BeforeEach
    void setup() {
        player = new Player();
    }

    @Test
    void testRecordMultiplle() {
        assertEquals(0, player.getRecords().size());

        player.record('d', 0);
        assertEquals(1, player.getRecords().size());
        assertEquals('d', player.getRecords().get(0).getKey());
        assertEquals(0, player.getRecords().get(0).getTime());

        player.record('j', 250);
        assertEquals(2, player.getRecords().size());
        assertEquals('d', player.getRecords().get(0).getKey());
        assertEquals(0, player.getRecords().get(0).getTime());
        assertEquals('j', player.getRecords().get(1).getKey());
        assertEquals(250, player.getRecords().get(1).getTime());

        player.record('f', 500);
        assertEquals(3, player.getRecords().size());
        assertEquals('f', player.getRecords().get(2).getKey());
        assertEquals(500, player.getRecords().get(2).getTime());
    }

    @Test
    void testGetFirstRecord() {
        player.record('d', 0);
        player.record('j', 250);
        player.record('f', 500);

        Record testRecord = player.getRecord(0);
        assertEquals('d', testRecord.getKey());
        assertEquals(250, testRecord.getTime());
    }

    @Test
    void testGetFirstwoRecord() {
        player.record('d', 0);
        player.record('j', 250);
        player.record('f', 500);

        Record testRecord = player.getRecord(0);
        assertEquals('d', testRecord.getKey());
        assertEquals(250, testRecord.getTime());

        testRecord = player.getRecord(1);
        assertEquals('j', testRecord.getKey());
        assertEquals(500, testRecord.getTime());
    }

    @Test
    void testGetLastRecord() {
        player.record('d', 0);
        player.record('j', 250);
        player.record('f', 500);

        Record testRecord = player.getRecord(2);
        assertEquals('f', testRecord.getKey());
        assertEquals(0, testRecord.getTime());
    }

    @Test
    void testDeleteRecords() {
        player.record('d', 0);
        player.record('j', 250);
        player.record('f', 500);
        assertEquals(3, player.getRecords().size());

        player.deleteRecords();
        assertEquals(0, player.getRecords().size());
    }


}
