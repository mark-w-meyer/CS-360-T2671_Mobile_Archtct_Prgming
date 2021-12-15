package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;


public class EventManager {

    private int E_Id;
    private int E_position;
    private String E_name;
    private String E_date;
    private String E_time;
    private String E_notes;

    /* Overloaded Constructor */
    EventManager(int position,
                 String name,
                 String date,
                 String time,
                 String notes) {
        this.E_position = position;
        this.E_name = name;
        this.E_date = date;
        this.E_time = time;
        this.E_notes = notes;
    };

    public int getE_Id() { return E_Id ;}

    public int getE_position() {
        return E_position;
    }

    public void setE_position(int e_position) {
        E_position = e_position;
    }

    public String getE_name() {
        return E_name;
    }

    public void setE_name(String e_name) {
        E_name = e_name;
    }

    public String getE_date() {
        return E_date;
    }

    public void setE_date(String e_date) {
        E_date = e_date;
    }

    public String getE_time() {
        return E_time;
    }

    public void setE_time(String e_time) {
        E_time = e_time;
    }

    public String getE_notes() {
        return E_notes;
    }

    public void setE_notes(String e_notes) {
        E_notes = e_notes;
    }

}

