package persistence;

import model.Player;
import model.Record;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Player player = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlayer.json");
        try {
            Player player = reader.read();
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

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
