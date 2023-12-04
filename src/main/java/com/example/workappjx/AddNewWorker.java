package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class AddNewWorker {

    @FXML
    public TextField loginField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField rePasswordField;

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
    public CheckBox adminCheckBox;

    @FXML
    public Button confirmButton;

    public void createNewWorker(){
        String login = loginField.getText();
        String password = passwordField.getText().toString();
        String rePassword = rePasswordField.getText().toString();
        if(!password.equals(rePassword)){
            System.out.println("password != repassword \n Password: " + password + "\n rePassword: " + rePassword);
            return;}
        System.out.println("Password: " + password);
        LoginPanelController loginPanelController = new LoginPanelController();
        password = loginPanelController.getMd5(password);
        System.out.println("Password after MD5: " + password);
        String name = nameField.getText();
        String lastName = surnameField.getText();
        String phoneNumber = phoneField.getText();
        String pesel = peselField.getText();
        String salary = salaryField.getText();
        boolean admin = adminCheckBox.isSelected();

        String url = "jdbc:mysql://localhost/persons";
        String username = "root";
        String dbPassword = "1234qwer";

        try {
            Connection connection = DriverManager.getConnection(url, username, dbPassword);
//            LocalDate testLocalDate = worktime.getLocalDate();
//            System.out.println("SaveTimeWorkData Data: " + testLocalDate);
//            System.out.println("Start time: " + worktime.getStart_time());
            String sql = "INSERT INTO workers (login, password, firstName, lastName, phoneNumber, pesel, SalaryPerHour, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, lastName);
            statement.setString(5, phoneNumber);
            statement.setString(6, pesel);
            statement.setString(7, salary);
            statement.setBoolean(8, admin);


            int rowsInserted = statement.executeUpdate();
//            if (rowsInserted > 0) {
////                System.out.println("Dane zostały dodane do bazy.");
//            }
//            MainPanel mainPanel = new MainPanel();

            statement.close();
            connection.close();
            Stage stage = (Stage) confirmButton.getScene().getWindow();

            stage.close();
            System.out.println("Worker created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
