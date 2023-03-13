package persistence;

import model.Player;
import model.Record;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// REFERENCE : code below was referred from the following project :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Player player = new Player();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterGeneralPlayer() {
        try {
            Player player = new Player();
            player.record('d', 0);
            player.record('j', 250);
            player.record('f', 500);

            player.addToSongs();
            player.deleteRecords();

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlayer.json");
            writer.open();
            writer.write(player);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlayer.json");
            player = reader.read();
            assertEquals(1, player.getNumOfSongs());
            List<Record> records = player.getSong(1);
            assertEquals(3, records.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
