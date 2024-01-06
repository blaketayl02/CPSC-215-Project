package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event e;
    private Date d;


    @BeforeEach
    public void setup() {
        e = new Event("Item added!");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Item added!", e.getDescription());
        assertEquals(d, e.getDateLogged());
    }

    @Test
    public void testHashCode() {
        assertEquals(13 * d.hashCode() + e.getDescription().hashCode(),e.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(e.equals(e));
        assertFalse(e.equals(d));
        Event e1 = null;
        assertFalse(e.equals(e1));
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Item added!", e.toString());
    }
}