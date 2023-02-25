package model;

import org.junit.jupiter.api.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {
    private Drums drums;
    private Player player;

    @BeforeEach
    void setup() {
        drums = new Drums();
        player = new Player();
    }

    @Test
    void testRecordMultiplle() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
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

}
