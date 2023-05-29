package com.example.workappjx;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LoadWorkTimeData {


    public List<WorkTime> dbConnection(int userID){
        // Inicjalizacja połączenia z bazą danych
        DatabaseConnector connector = new DatabaseConnector();

        String address;
        LocalDate date;
        LocalTime start_time;
        LocalTime end_time;
        String comment;
        int user_id;


        List<WorkTime> workTimeList = new ArrayList<>();

        // Przykładowe zapytanie SQL
        String query = "SELECT * FROM workhours WHERE user_id = " + userID;

        try {
            // Wykonaj zapytanie i odczytaj wyniki
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

        // Zamknij połączenie z bazą danych
        try {
            connector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workTimeList;
    }
}
