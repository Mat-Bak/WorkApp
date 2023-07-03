package com.example.workappjx;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        int tax;

        List<Person> personList = new ArrayList<>();

        // SQL query
//        String query = "SELECT * FROM workers";
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
                tax = resultSet.getInt("tax");

                Person person = new Person(id, login, password, firstName, lastName, pesel, phoneNumber, SalaryPerHour, tax);
//                Person person = new Person(id, login, password, firstName, lastName, pesel, phoneNumber);
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
}
