package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;

public class UserCredentials {

    private int Id;
    private String Username;
    private String Password;
    private boolean isActive;

    /* Constructor */
    UserCredentials(int id, String username, String password, boolean isActive) {
        this.Id = id;
        this.Username = username;
        this.Password = password;
        this.isActive = isActive();
    }

    public int getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
