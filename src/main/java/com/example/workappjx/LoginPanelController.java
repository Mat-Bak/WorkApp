package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.SQLException;

/*

Methods:
    * String getMd5(String source) - (Message Digest Algorithm 5) encryption method String to bytes. It is helpful in encrypting passwords
    * String getString( byte[] bytes ) - convert byte array to String
    * Person getPersonData() - just return person data
    * void logIn() - get data from the login panel and check whether these data exist in the employee database. If it exists and everything is OK, log into the system

The main task of this class is logging into the system using the data from the fields of the login panel. It also uses MDA5 to encrypt the password, making it harder to break

 */

public class LoginPanelController {
    @FXML
    public TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;

    @FXML
    private Text errorText;



    public static Person personData;
//    public int user_id;

    public static String getMd5( String source ) {
        try {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            byte[] bytes = md.digest( source.getBytes(StandardCharsets.UTF_8) );
            return getString( bytes );
        } catch( Exception e )  {
            e.printStackTrace();
            return null;
        }
    }

    private static String getString( byte[] bytes ) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0x00FF & b);
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

    public void logIn() throws IOException, SQLException {
        LoadPersonData getPersonData = new LoadPersonData();
        String login = loginField.getText();
        String pass = getMd5(passwordField.getText());
        Person person = getPersonData.logInToApp(login, pass, errorText);
        if(person != null){
            personData = person;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
            Parent root = loader.load();
            MainPanel createMainPanel = loader.getController();
            Stage stage = (Stage) logInButton.getScene().getWindow();
            stage.close();
            createMainPanel.createMainPanel();
        }else{

            errorText.setText("Invalid login or password!");
            errorText.setFill(Color.RED);
        }
    }

}