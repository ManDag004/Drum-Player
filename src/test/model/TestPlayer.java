package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

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

    @Test
    void testRecordsToJson() {
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

        JSONObject json = player.toJson();
        JSONArray songs = player.toJson().getJSONArray("songs");

        JSONObject song1 = songs.getJSONObject(0);
        JSONObject song2 = songs.getJSONObject(1);

        JSONArray records1 = song1.getJSONArray("records");
        JSONArray records2 = song2.getJSONArray("records");

        assertEquals(1, json.length());
        assertEquals(2, songs.length());

        assertEquals(1, song1.getInt("id"));
        assertEquals(2, song2.getInt("id"));

        assertEquals(3, records1.length());
        assertEquals(4, records2.length());

        assertEquals("d", records1.getJSONObject(0).getString("key"));
        assertEquals(0, records1.getJSONObject(0).getInt("time"));
        assertEquals("j", records1.getJSONObject(1).getString("key"));
        assertEquals(250, records1.getJSONObject(1).getInt("time"));
        assertEquals("f", records1.getJSONObject(2).getString("key"));
        assertEquals(500, records1.getJSONObject(2).getInt("time"));

        assertEquals("f", records2.getJSONObject(0).getString("key"));
        assertEquals(0, records2.getJSONObject(0).getInt("time"));
        assertEquals("g", records2.getJSONObject(1).getString("key"));
        assertEquals(125, records2.getJSONObject(1).getInt("time"));
        assertEquals("h", records2.getJSONObject(2).getString("key"));
        assertEquals(300, records2.getJSONObject(2).getInt("time"));
        assertEquals("j", records2.getJSONObject(3).getString("key"));
        assertEquals(450, records2.getJSONObject(3).getInt("time"));

    }


}
