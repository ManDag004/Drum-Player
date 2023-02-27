package model;

import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestDrums {
    private Drums drums;

    @BeforeEach
    void setup() {
        drums = new Drums();
    }

    @Test
    void testPlay(){
        assertEquals(new File("src/Bass.wav"), drums.getSound('d'));
        assertEquals(new File("src/Snare.wav"), drums.getSound('f'));
        assertEquals(new File("src/Hi-Hat.wav"), drums.getSound('j'));
        assertEquals(new File("src/Crash.wav"), drums.getSound('k'));
        assertEquals(new File("src/Floor-Tom.wav"), drums.getSound('s'));
        assertEquals(new File("src/Small-Tom.wav"), drums.getSound('g'));
        assertEquals(new File("src/Medium-Tom.wav"), drums.getSound('h'));
    }
}
