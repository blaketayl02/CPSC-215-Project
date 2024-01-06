package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AppointmentsTest {
    private Appointments app;
    private int day;
    private String month;
    private int time;

    @BeforeEach
    void setup() {
        app = new Appointments();
    }

    @Test
    void testSetMonth() {
        app.setMonth(month);
        assertEquals(month, app.getMonth());
    }

    @Test
    void testSetDate() {
        String date = "January 10th 2002";
        app.setDate(date);
        assertEquals(date, app.getDate());
    }

    @Test
    void testSetDay() {
        app.setDay(day);
        assertEquals(day, app.getDay());
    }

    @Test
    void testSetTime() {
        app.setTime(time);
        assertEquals(time, app.getTime());
    }

    @Test
    void testIssue() {
        app.setIssue("PC Wont turn on!");
        assertEquals("PC Wont turn on!", app.getIssue());
    }

}
