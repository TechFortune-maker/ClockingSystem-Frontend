package za.ac.tut.model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserReport {

    private String username;
    private String role;
    private String gender;
    private Timestamp clockInTime;
    private Timestamp clockOutTime;

    public UserReport(String username, String role, String gender, Timestamp clockInTime, Timestamp clockOutTime) {

        this.username = username;
        this.role = role;
        this.gender = gender;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
    }

    public String formatTime(Timestamp time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");

        if (time != null) {
            String formattedTime = timeFormat.format(time);

            return formattedTime;
        } else {
            return "Not available";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Timestamp clockInTime) {
        this.clockInTime = clockInTime;
    }

    public Timestamp getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(Timestamp clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    @Override
    public String toString() {
        return String.format("%-22s %-26s %-22s %-22s %-22s",
                username, role, gender, formatTime(clockInTime), formatTime(clockOutTime));
    }
}
