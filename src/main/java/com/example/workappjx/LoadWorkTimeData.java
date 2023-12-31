package com.example.workappjx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class LoadWorkTimeData {


    public List<WorkTime> dbConnection(int userID, LocalDate data){
        // Initialize database connection
        DatabaseConnector connector = new DatabaseConnector();

        String address;
        LocalDate date;
        LocalTime start_time;
        LocalTime end_time;
        String comment;
        int user_id;
        int id;


        List<WorkTime> workTimeList = new ArrayList<>();

        // SQL query to select work time when user_id is equal userID
        String query = "SELECT * FROM persons.workhours WHERE user_id = " + userID  + " AND data = \""  + data + "\"";

        try {
            // execute query and get result
            ResultSet resultSet = connector.executeQuery(query);
            while (resultSet.next()) {
                address = resultSet.getString("address");
                date = resultSet.getDate("data").toLocalDate();
                start_time = resultSet.getTime("start_time").toLocalTime();
                end_time = resultSet.getTime("end_time").toLocalTime();
                comment = resultSet.getString("comment");
                user_id = resultSet.getInt("user_id");
                id = resultSet.getInt("id");

                WorkTime workTime = new WorkTime(id, address, date, start_time, end_time, comment, user_id);
                workTimeList.add(workTime);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // close connection
        try {
            connector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workTimeList;
    }


    public List<WorkTime> getDataFromWorkTime(int userID){
        // Initialize database connection
        DatabaseConnector connector = new DatabaseConnector();

        String address;
        LocalDate date;
        LocalTime start_time;
        LocalTime end_time;
        String comment;
        int user_id;


        List<WorkTime> workTimeList = new ArrayList<>();

        // SQL query to select work time when user_id is equal userID
        String query = "SELECT * FROM workhours WHERE user_id = " + userID;

        try {
            // execute query and get result
            ResultSet resultSet = connector.executeQuery(query);
            while (resultSet.next()) {
                address = resultSet.getString("address");
                date = resultSet.getDate("data").toLocalDate();
                start_time = resultSet.getTime("start_time").toLocalTime();
                end_time = resultSet.getTime("end_time").toLocalTime();
                comment = resultSet.getString("comment");
                user_id = resultSet.getInt("user_id");

                WorkTime workTime = new WorkTime(address, date, start_time, end_time, comment, user_id);
                workTimeList.add(workTime);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // close connection
        try {
            connector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workTimeList;
    }
}
