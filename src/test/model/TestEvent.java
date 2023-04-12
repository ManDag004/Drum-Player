package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {
    private Event e1;
    private Event e2;
    private Event e3;
    private int i = 1;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e1 = new Event("Added a song");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Added a song", e1.getDescription());
        assertEquals(d.toString(), e1.getDate().toString());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Added a song", e1.toString());
    }

    @Test
    public void testEqualsAndHash() {
        e1 = new Event("Added a song");
        e2 = new Event("Added a song");
        e3 = new Event("Added a different song");
        Event e4 = null;

        assertEquals(e1, e2);     // Same time, Same description
        assertNotEquals(e1, e3);  // Same time, Different description

        assertNotEquals(e1, e4);   // Comparing with null
        Boolean b = e1 == e2;
        e4 = new Event("Added a song");
        assertNotEquals(e1, e4);  // Different time, Same description
        assertNotEquals(e4, e3);  // Different time, Different description
        assertNotEquals(e1, i);   // Comparing with a different class

        assertEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1.hashCode(), e3.hashCode());
    }
}
