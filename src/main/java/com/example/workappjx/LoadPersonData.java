package com.example.workappjx;

import javafx.scene.text.Text;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Methods:
    * List<Person> dbConnection() - Connect to employers database and get every person data from this base

The class contains only one method that allows you to connect to the database where the employer's data is located

 */

public class LoadPersonData {


    public List<Person> dbConnection(){
        // Initialize connection to database
        DatabaseConnector connector = new DatabaseConnector();

        int id;
        String login;
        String password;
        String firstName;
        String lastName;
        BigInteger pesel;
        int phoneNumber;
        int SalaryPerHour;
        boolean admin;

        List<Person> personList = new ArrayList<>();

        String query = "SELECT * FROM Persons.workers;";

        try {
            // Execute query and get result
            ResultSet resultSet = connector.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                login = resultSet.getString("login");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                phoneNumber = resultSet.getInt("phoneNumber");
                pesel = BigInteger.valueOf(resultSet.getLong("pesel"));
                SalaryPerHour = resultSet.getInt("SalaryPerHour");
                admin = resultSet.getBoolean("admin");

                Person person = new Person(id, login, password, firstName, lastName, pesel, phoneNumber, SalaryPerHour, admin);
                personList.add(person);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close database connection
        try {
            connector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }

    public Person logInToApp(String userLogin, String userPassword, Text textError) throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();

        int id;
        String login;
        String password;
        String firstName;
        String lastName;
        BigInteger pesel;
        int phoneNumber;
        int SalaryPerHour;
        boolean admin;

        List<Person> personList = new ArrayList<>();
        String query = "SELECT * FROM Persons.workers WHERE login = '" + userLogin + "' AND password = '" + userPassword + "';";
            // Execute query and get result
            ResultSet resultSet = connector.executeQuery(query);
            if(resultSet.next()){
                id = resultSet.getInt("id");
                login = resultSet.getString("login");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                phoneNumber = resultSet.getInt("phoneNumber");
                pesel = BigInteger.valueOf(resultSet.getLong("pesel"));
                SalaryPerHour = resultSet.getInt("SalaryPerHour");
                admin = resultSet.getBoolean("admin");
                Person user = new Person(id, login, password, firstName, lastName, pesel, phoneNumber, SalaryPerHour, admin);
                resultSet.close();
                connector.close();
                return user;
            }else{
                resultSet.close();
                connector.close();
                return null;
            }

    }

    public void changePassword(String pass, int id) throws SQLException {
        String url = "jdbc:mysql://localhost/persons";
        String username = "root";
        String password = "1234qwer";
        Connection connection = DriverManager.getConnection(url, username, password);
        String query = "UPDATE Persons.workers SET password = '" + pass + "' WHERE id = " + id + ";";
        PreparedStatement statement = connection.prepareStatement(query);
        int rowsInserted = statement.executeUpdate();
        statement.close();
        connection.close();
    }
}