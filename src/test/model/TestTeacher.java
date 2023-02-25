package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestTeacher {
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = new Teacher();
    }

    @Test
    void testGetBeat() {
        assertEquals("df", teacher.getBeat(1));
        assertEquals("djfj", teacher.getBeat(2));
    }

    @Test
    void testCheckCorrectnessWhenCorrect() {
        assertEquals(1, teacher.checkCorrectness(0, "df", 'd'));
        assertEquals(1, teacher.checkCorrectness(3, "df", 'f'));
        assertEquals(1, teacher.checkCorrectness(6, "dfjf", 'j'));
    }

    @Test
    void testCheckCorrectnessWhenIncorrect() {
        assertEquals(0, teacher.checkCorrectness(1, "df", 'd'));
        assertEquals(0, teacher.checkCorrectness(2, "df", 'f'));
        assertEquals(0, teacher.checkCorrectness(6, "dfjf", 'f'));
    }
}
