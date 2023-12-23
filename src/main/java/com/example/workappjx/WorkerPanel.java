package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    }

    public void setPersonID(int id){
        personId = id;
    }

    public void updateWorkerData() throws SQLException {
        String firstName = nameField.getText();
        String lastName = surnameField.getText();
        BigInteger pesel = new BigInteger(peselField.getText());
        int phoneNumber = Integer.parseInt(phoneField.getText());
        int salary =Integer.parseInt(salaryField.getText());
        boolean admin = adminCheck.isSelected();
        String query = "UPDATE Persons.workers SET firstName = ?, lastName = ?, phoneNumber = ?, pesel = ?, salaryPerHour = ?, admin = ? WHERE id = ?;";
        String url = "jdbc:mysql://localhost/Persons";
        String username = "root";
        String password = "1234qwer";
        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setString(1, firstName);
        prepStatement.setString(2, lastName);
        prepStatement.setInt(3, phoneNumber);
        prepStatement.setBigDecimal(4, new BigDecimal(pesel));
        prepStatement.setInt(5, salary);
        prepStatement.setBoolean(6, admin);
        prepStatement.setInt(7, personId);

        prepStatement.executeUpdate();
        connection.close();

        Stage stage = (Stage) saveButton.getScene().getWindow();

        stage.close();
    }

}
