package com.example.workappjx;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkTime {
    private String address;
    private LocalDate date;
    private LocalTime start_time;
    private LocalTime end_time;
    private String comment = "";
    private int user_id;

    public WorkTime(String address, LocalDate date, LocalTime start_time, LocalTime end_time, String comment, int user_id) {
        this.address = address;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.comment = comment;
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getLocalDate(){
        MainPanel mainPanel = new MainPanel();
        LocalDate testData = MainPanel.getLocalDate;
        System.out.println("Data: " + testData);
        return testData;
    }
}
