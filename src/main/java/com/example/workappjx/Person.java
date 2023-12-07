package com.example.workappjx;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*

The class is made to create new person. In this class exist getters and setters for every data.

 */

public class Person {
    int id;
    String login;
    String password;
    String firstName;
    String lastName;
    BigInteger pesel;
    int phoneNumber;
    int SalaryPerHour;
    boolean admin;

    public Person(int id, String login, String password, String firstName, String lastName, BigInteger pesel, int phoneNumber, int SalaryPerHour, boolean admin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.SalaryPerHour = SalaryPerHour;
        this.admin = admin;
    }

    public Person() {

    }

    public int getId(){return id;}

    public void setId(){this.id = id;}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigInteger getPesel() {
        return pesel;
    }

    public void setPesel(BigInteger pesel) {
        this.pesel = pesel;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalaryPerHour() {
        return SalaryPerHour;
    }

    public void setSalaryPerHour(int salaryPerHour) {
        SalaryPerHour = salaryPerHour;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Person> getPersonList(){
        DatabaseConnector connector = new DatabaseConnector();

        int id;
        String login;
        String password = null;
        String firstName = null;
        String lastName = null;
        BigInteger pesel = null;
        int phoneNumber = 0;
        int SalaryPerHour = 0;
        boolean admin = false;
//        Person user = null;

        List<Person> personList = new ArrayList<>();

        // SQL query
//        String query = "SELECT * FROM workers";
        String query = "SELECT * FROM Persons.workers;";


        // Execute query and get result
        ResultSet resultSet = null;
        try {
            resultSet = connector.executeQuery(query);
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
//                Person person = new Person(id, login, password, firstName, lastName, pesel, phoneNumber);
                personList.add(person);
            }
            resultSet.close();
            connector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;

    }


}

