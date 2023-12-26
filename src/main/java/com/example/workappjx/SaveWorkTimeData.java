package com.example.workappjx;

import java.sql.*;
import java.time.LocalDate;

/*

Methods:
    * void connectWorkTimeDatabase(WorkTime) - the method is to connect with work time database. The method get one WorkTime argument which are used to get data from this class.

 */

public class SaveWorkTimeData {

    public void connectWorkTimeDatabase(WorkTime worktime){
        String url = "jdbc:mysql://localhost/Persons";
        String username = "root";
        String password = "1234qwer";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO workhours (address, data, start_time, end_time, comment, user_id) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, worktime.getAddress());
            statement.setDate(2, Date.valueOf(worktime.getDate()));
            statement.setTime(3, Time.valueOf(worktime.getStart_time()));
            statement.setTime(4, Time.valueOf(worktime.getEnd_time()));
            statement.setString(5, worktime.getComment());
            statement.setInt(6, worktime.getUser_id());

            statement.executeUpdate();
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

            String sql = "DELETE FROM persons.workhours WHERE id = " + id;

            PreparedStatement statement = connection.prepareStatement(sql);


            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
