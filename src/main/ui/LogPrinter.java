package ui;

import model.Event;
import model.EventLog;

// Represents a LogPrinter that prints logs out to console.
public class LogPrinter {

    //EFFECTS: prints out each log as a string to console.
    public void printLog(EventLog e) {
        for (Event next : e) {
            System.out.println(next.toString());
        }
    }
}
