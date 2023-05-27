package com.example.workappjx;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadPersonData {
    private DatabaseConnector connector;


    public List<Person> dbConnection(){
        // Inicjalizacja połączenia z bazą danych
        connector = new DatabaseConnector();

        int id;
        String login;
        String password;
        String firstName;
        String lastName;
        BigInteger pesel;
        int phoneNumber;

        List<Person> personList = new ArrayList<>();

        // Przykładowe zapytanie SQL
        String query = "SELECT * FROM workers";

        try {
            // Wykonaj zapytanie i odczytaj wyniki
            ResultSet resultSet = connector.executeQuery(query);
            while (resultSet.next()) {
                login = resultSet.getString("login");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                pesel = BigInteger.valueOf(resultSet.getLong("pesel"));
                phoneNumber = resultSet.getInt("phoneNumber");
                id = resultSet.getInt("id");
                Person person = new Person(id, login, password, firstName, lastName, pesel, phoneNumber);
                personList.add(person);
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
        return personList;
    }
}
