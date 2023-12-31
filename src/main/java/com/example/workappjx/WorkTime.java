package com.example.workappjx;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkTime {

    private int id;
    private String address;
    private LocalDate date;
    private LocalTime start_time;
    private LocalTime end_time;
    private String comment = "";
    private int user_id;

    public WorkTime() {
    }

    public WorkTime(String address, LocalDate date, LocalTime start_time, LocalTime end_time, String comment, int user_id) {
        this.address = address;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.comment = comment;
        this.user_id = user_id;
    }
    public WorkTime(int id, String address, LocalDate date, LocalTime start_time, LocalTime end_time, String comment, int user_id) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.comment = comment;
        this.user_id = user_id;
    }

    public int getId(){return id;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public String getComment() {
        return comment;
    }

    public int getUser_id() {
        return user_id;
    }

    public Duration getHoursWork(){
        return Duration.between(start_time, end_time);
    }

    public String timeToStrikg(){
        int hours = (int) getHoursWork().toHours();
        int minutes = (int) getHoursWork().toMinutes()-(hours*60);

        return hours + "H " + minutes + "M";
    }
}
