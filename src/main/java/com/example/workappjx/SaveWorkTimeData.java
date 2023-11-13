package com.example.workappjx;

import java.sql.*;
import java.time.LocalDate;

/*

Methods:
    * void connectWorkTimeDatabase(WorkTime worktime) - the method is to connect with work time database. The method get one WorkTime argument which are used to get data from this class.

 */

public class SaveWorkTimeData {

    public void connectWorkTimeDatabase(WorkTime worktime){
        String url = "jdbc:mysql://localhost/persons";
        String username = "root";
        String password = "1234qwer";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            LocalDate testLocalDate = worktime.getLocalDate();
//            System.out.println("SaveTimeWorkData Data: " + testLocalDate);
//            System.out.println("Start time: " + worktime.getStart_time());
            String sql = "INSERT INTO workhours (address, data, start_time, end_time, comment, user_id) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, worktime.getAddress());
            statement.setDate(2, Date.valueOf(worktime.getDate()));
            statement.setTime(3, Time.valueOf(worktime.getStart_time()));
            statement.setTime(4, Time.valueOf(worktime.getEnd_time()));
            statement.setString(5, worktime.getComment());
            statement.setInt(6, worktime.getUser_id());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
//                System.out.println("Dane zostały dodane do bazy.");
            }
            MainPanel mainPanel = new MainPanel();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDataFromDataBase(int id){
        String url = "jdbc:mysql://localhost/persons";
        String username = "root";
        String password = "1234qwer";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("second id workTime: " + id);
            String sql = "DELETE FROM persons.workhours WHERE id = " + id;

            PreparedStatement statement = connection.prepareStatement(sql);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
//                System.out.println("Dane zostały dodane do bazy.");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
