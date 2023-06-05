package com.example.workappjx;

import java.math.BigInteger;

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

    public Person(int id, String login, String password, String firstName, String lastName, BigInteger pesel, int phoneNumber) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
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
}

