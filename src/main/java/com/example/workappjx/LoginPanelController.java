package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
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

    public void logIn() throws IOException, SQLException {
        LoadPersonData getPersonData = new LoadPersonData();
        String login = loginField.getText();
        String pass = getMd5(passwordField.getText());
        Person person = getPersonData.logInToApp(login, pass, errorText);
        if(person != null){
            System.out.println("Login succesful!");
            personData = person;

//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
//            Scene secondScene;
//            secondScene = new Scene(fxmlLoader.load(), 450, 400);
//            Stage Secondstage = new Stage();
//            Secondstage.setTitle("WorkApp");
//            Secondstage.setScene(secondScene);
//            Secondstage.show();
//
//            MainPanel mainPanel = fxmlLoader.getController();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
            Parent root = loader.load();
            MainPanel createMainPanel = loader.getController();
//            Scene mainScene = new Scene(loader.load(), 450, 400);
//            Stage mainStage = new Stage();
            //E:/Nauka/Java/workApp/workAppJX/src/main/resources/
//            mainStage.setTitle("WorkApp");
//            mainStage.setScene(mainScene);
//            mainStage.show();
            Stage stage = (Stage) logInButton.getScene().getWindow();
            stage.close();
//            mainStage.close();
            createMainPanel.createMainPanel();
        }else{

            errorText.setText("Invalid login or password!");
            errorText.setFill(Color.RED);
            System.out.println("LogIn failed!");
        }
//        List<Person> personList = getPersonData.dbConnection();

//        System.out.println("Login: " + loginField.getText() + "\n Password: " + getMd5(passwordField.getText()));
//        for (Person person : personList) {
////            System.out.println("Person Login: " + person.getLogin() + "| Person Password: " + getMd5(person.getPassword()));
//            if (person.getLogin().equals(loginField.getText()) && person.getPassword().equals(getMd5(passwordField.getText()))){
//                System.out.println("Login succesful!");
//                personData = person;
////                System.out.println("ID: " + personData.getId());
//
////                MainPanel createMainPanel = new MainPanel();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
//                Parent root = loader.load();
//                MainPanel createMainPanel = loader.getController();
//                Stage stage = (Stage) logInButton.getScene().getWindow();
//                stage.close();
//                createMainPanel.mainPanel();
//                break;
//            }else{
//                System.out.println("Login Failed!");
//            }
//        }


    }

}