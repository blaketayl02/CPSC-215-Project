package model;


// Represents appointments created by the customer.
public class Appointments {
    private String month;
    private int day;
    private int time;
    private String issue;
    private String date;


    public Appointments() {
    }

    public String getMonth() {
        return month;
    }

    public String getIssue() {
        return issue;
    }

    public int getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    //REQUIRES: valid month
    //MODIFIES: this
    //EFFECTS: sets a month for the appointment
    public void setMonth(String month) {
        this.month = month;
    }

    //REQUIRES: valid month
    //MODIFIES: this
    //EFFECTS: sets a general date for the appointment
    public void setDate(String date) {
        this.date = date;
    }

    //REQUIRES: valid day of the given month
    //MODIFIES: this
    //EFFECTS: sets a day for the appointment
    public void setDay(int day) {
        this.day = day;
    }

    //REQUIRES: valid time in 24 hours
    //MODIFIES: this
    //EFFECTS: sets a time for the appointment
    public void setTime(int time) {
        this.time = time;
    }


    //REQUIRES: valid string > 0
    //MODIFIES: this
    //EFFECTS: sets an issue for the corresponding appointment
    public void setIssue(String issue) {
        this.issue = issue;
        EventLog.getInstance().logEvent((
                new Event("Appointment created! " + getDate() + " " + getIssue())));

    }

}
