package com.example.workappjx;

import java.sql.*;

public class DatabaseConnector {
    private Connection connection;
    private Statement statement;




    public DatabaseConnector() {
        try {
            // Ustal połączenie z bazą danych
            String url = "jdbc:mysql://localhost/persons";
            String user = "root";
            String password = "1234qwer";
            connection = DriverManager.getConnection(url, user, password);

            // Utwórz obiekt Statement
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }


}
