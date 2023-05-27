package com.example.workappjx;

import java.sql.*;
import java.time.LocalDate;

public class SaveTimeWorkData {

    private Connection connection;
    private Statement statement;
    public void connectWorkTimeDatabase(WorkTime worktime){
        String url = "jdbc:mysql://localhost/persons";
        String username = "root";
        String password = "1234qwer";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            LocalDate testLocalDate = worktime.getLocalDate();
            System.out.println("SaveTimeWorkData Data: " + testLocalDate);
            String sql = "INSERT INTO workhours (address, data, start_time, end_time, comment, user_id) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, worktime.getAddress());
            LocalDate workTimeDate = worktime.getDate();
//            java.sql.Date data = java.sql.Date.valueOf(worktime.getDate());
            statement.setDate(2, Date.valueOf(worktime.getDate()));
//            java.sql.Time start_time = java.sql.Time.valueOf(worktime.getStart_time());
//            java.sql.Time end_time = java.sql.Time.valueOf(worktime.getEnd_time());
            statement.setTime(3, Time.valueOf(worktime.getStart_time()));
            statement.setTime(4, Time.valueOf(worktime.getEnd_time()));
            statement.setString(5, worktime.getComment());
            statement.setInt(6, worktime.getUser_id());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Dane zostały dodane do bazy.");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
