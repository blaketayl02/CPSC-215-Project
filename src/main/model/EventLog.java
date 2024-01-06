package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of customer events.
public class EventLog implements Iterable<Event> {
    private static EventLog log;
    private Collection<Event> events;


    private EventLog() {
        events = new ArrayList<Event>();
    }


    //MODIFIES: this
    //EFFECTS: gets the instance of the log, if its empty create a new event log.
    public static EventLog getInstance() {
        if (log == null) {
            log = new EventLog();
        }
        return log;
    }

    //MODIFIES: this
    //EFFECTS: adds an event to log
    public void logEvent(Event e) {
        events.add(e);
    }

    //MODIFIES: this
    //EFFECTS: clears the event log.
    public void clear() {
        events.clear();
        logEvent(new Event("Log cleared!"));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }

}
