package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;

public class WorkerPanel {

    @FXML
    public TextField nameField;

    @FXML
    public TextField surnameField;

    @FXML
    public TextField phoneField;

    @FXML
    public TextField peselField;

    @FXML
    public TextField salaryField;

    @FXML
    public CheckBox adminCheck;

    @FXML
    public Button saveButton;

    public int personId;

    public void setDataInPanel(String firstName, String lastName, BigInteger pesel, int phoneNumber, int salary, boolean admin){

        nameField.setText(firstName);
        surnameField.setText(lastName);
        phoneField.setText(String.valueOf(phoneNumber));
        peselField.setText(String.valueOf(pesel));
        salaryField.setText(String.valueOf(salary));
        adminCheck.setSelected(admin);


//        String firstName;
//        String lastName;
//        BigInteger pesel;
//        int phoneNumber;
//        int SalaryPerHour;
//        boolean admin;

    }

    public void setPersonID(int id){
        personId = id;
    }

    public void updateWorkerData() throws SQLException, InterruptedException {
        String firstName = nameField.getText();
        String lastName = surnameField.getText();
        BigInteger pesel = new BigInteger(peselField.getText());
        int phoneNumber = Integer.valueOf(phoneField.getText());
        int salary =Integer.valueOf(salaryField.getText());
        boolean admin = adminCheck.isSelected();

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
//        MainPanel mainPanel = fxmlLoader.getController();

//        DatabaseConnector connector = new DatabaseConnector();
        System.out.println(firstName + " " + lastName + " \n" + pesel + " \n" + phoneNumber + " \n" + salary + " \n" + admin);
//        int id = mainPanel.personID;
        System.out.println("Person ID: " + personId);
        String query = "UPDATE Persons.workers SET firstName = ?, lastName = ?, phoneNumber = ?, pesel = ?, salaryPerHour = ?, admin = ? WHERE id = ?;";
        String url = "jdbc:mysql://localhost/Persons";
        String username = "root";
        String password = "1234qwer";
        Connection connection = DriverManager.getConnection(url, username, password);
//        DatabaseConnector connector = new DatabaseConnector();

        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setString(1, firstName);
        prepStatement.setString(2, lastName);
        prepStatement.setInt(3, phoneNumber);
        prepStatement.setBigDecimal(4, new BigDecimal(pesel));
        prepStatement.setInt(5, salary);
        prepStatement.setBoolean(6, admin);
        prepStatement.setInt(7, personId);

        prepStatement.executeUpdate();
//        Statement statement = connection.createStatement();

//        UPDATE Persons.workers SET phoneNumber = '111222333', pesel = ' 11111122223', salaryPerHour = '160', admin = '0' WHERE id = '2';
//        ResultSet resultSet = connection.executeQuery(query);

        connection.close();

        Stage stage = (Stage) saveButton.getScene().getWindow();

        stage.close();

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
//        Scene secondScene = null;
//        try {
//            secondScene = new Scene(fxmlLoader.load(), 326, 425);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        MainPanel mainPanel = fxmlLoader.getController();
////        Thread.sleep(1000);
//        mainPanel.workersPanelBack();
//        mainPanel.anchorPane.setVisible(true);
////        prepStatement.close();
////        statement.close();
    }

}
