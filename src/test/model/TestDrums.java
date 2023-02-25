package model;

import org.junit.jupiter.api.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestDrums {
    private Drums drums;

    @BeforeEach
    void setup() {
        drums = new Drums();
    }

    @Test
    void testPlay() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        assertEquals(new File("src/Bass.wav"), drums.play('d'));
        assertEquals(new File("src/Snare.wav"), drums.play('f'));
        assertEquals(new File("src/Hi-Hat.wav"), drums.play('j'));
        assertEquals(new File("src/Crash.wav"), drums.play('k'));

    }
}
