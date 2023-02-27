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
    void testRecordMultiple() {
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

        player.addToSongs();

        Record testRecord = player.getRecord(0, 1);
        assertEquals('d', testRecord.getKey());
        assertEquals(250, testRecord.getTime());
    }

    @Test
    void testGetFirstTwoRecord() {
        player.record('d', 0);
        player.record('j', 250);
        player.record('f', 500);

        player.addToSongs();

        Record testRecord = player.getRecord(0, 1);
        assertEquals('d', testRecord.getKey());
        assertEquals(250, testRecord.getTime());

        testRecord = player.getRecord(1, 1);
        assertEquals('j', testRecord.getKey());
        assertEquals(500, testRecord.getTime());
    }

    @Test
    void testGetLastRecord() {
        player.record('d', 0);
        player.record('j', 250);
        player.record('f', 500);

        player.addToSongs();

        Record testRecord = player.getRecord(2, 1);
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

    @Test
    void addMultipleToSongs() {
        player.record('d', 0);
        player.record('j', 250);
        player.record('f', 500);

        player.addToSongs();
        player.deleteRecords();
        assertEquals(1, player.getNumOfSongs());

        player.record('f', 0);
        player.record('g', 125);
        player.record('h', 300);
        player.record('j', 450);

        player.addToSongs();
        player.deleteRecords();
        assertEquals(2, player.getNumOfSongs());

        assertEquals(3, player.getSong(1).size());
        assertEquals(4, player.getSong(2).size());

        assertEquals('d', player.getSong(1).get(0).getKey());
        assertEquals('j', player.getSong(1).get(1).getKey());
        assertEquals('f', player.getSong(1).get(2).getKey());
        assertEquals(0, player.getSong(1).get(0).getTime());
        assertEquals(250, player.getSong(1).get(1).getTime());
        assertEquals(500, player.getSong(1).get(2).getTime());

        assertEquals('f', player.getSong(2).get(0).getKey());
        assertEquals('g', player.getSong(2).get(1).getKey());
        assertEquals('h', player.getSong(2).get(2).getKey());
        assertEquals('j', player.getSong(2).get(3).getKey());
        assertEquals(0, player.getSong(2).get(0).getTime());
        assertEquals(125, player.getSong(2).get(1).getTime());
        assertEquals(300, player.getSong(2).get(2).getTime());
        assertEquals(450, player.getSong(2).get(3).getTime());

    }


}
