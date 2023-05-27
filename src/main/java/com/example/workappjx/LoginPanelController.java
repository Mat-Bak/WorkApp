package com.example.workappjx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LoginPanelController {
    @FXML

    public TextField loginField;
    @FXML
    private Label welcomeText;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;

    @FXML
    public TextArea personInfo;

    public static Person personData;
    public int user_id;







    public static String getMd5( String source ) {
        try {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            byte[] bytes = md.digest( source.getBytes("UTF-8") );
            return getString( bytes );
        } catch( Exception e )  {
            e.printStackTrace();
            return null;
        }
    }

    private static String getString( byte[] bytes ) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            String hex = Integer.toHexString((int) 0x00FF & b);
            if (hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static Person getPersonData(){
        return personData;
    }

    public void logIn() throws IOException {
        LoadPersonData getPersonData = new LoadPersonData();
        List<Person> personList = getPersonData.dbConnection();

        System.out.println("Login: " + loginField.getText() + "\n Password: " + getMd5(passwordField.getText()));
        for (Person person : personList) {
            System.out.println("Person Login: " + person.getLogin() + "| Person Password: " + getMd5(person.getPassword()));
            if (person.getLogin().equals(loginField.getText()) && person.getPassword().equals(getMd5(passwordField.getText()))){
                System.out.println("Login succesful!");
                personData = person;
                System.out.println("ID: " + personData.getId());
//                personInfo.setText("Hello!");

//                getPerson = new Person(person.getLogin(), person.getPassword(), person.getFirstName(), person.getLastName(), person.getPesel(), person.getPhoneNumber());
                MainPanel createMainPanel = new MainPanel();
                Stage stage = (Stage) logInButton.getScene().getWindow();
                stage.close();
                createMainPanel.mainPanel();
//                createMainPanel.setPersonData(person.getFirstName(), person.getLastName(), person.getPhoneNumber(), person.getPesel());
//                personInfo.setText("First Name: " + person.getFirstName() +
//                        "\n Last Name: " + person.getLastName() +
//                        "\n Phone Number: " + person.getPhoneNumber() +
//                        "\n Pesel: " + person.getPesel()
//                );

                break;
            }else{
                System.out.println("Login Failed!");
            }
        }


//       return getPerson;
    }

}