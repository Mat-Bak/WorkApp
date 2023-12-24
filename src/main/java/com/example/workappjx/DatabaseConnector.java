package com.example.workappjx;

import java.sql.*;

/*

Methids:
    * Constructor DatabaseConnector() - this constructor allow to connect to database
    * ResultSet executeQuery(String query) - allow to execute query and get result of this query
    * Void close() - just close statement and connection

A simple class that is a connector to the employer database

 */

public class DatabaseConnector {
    private Connection connection;
    private Statement statement;

    public DatabaseConnector() {
        try {
            // connect to database
            String url = "jdbc:mysql://localhost/Persons";
            String user = "root";
            String password = "1234qwer";
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // execute the query and return result
    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }


}
