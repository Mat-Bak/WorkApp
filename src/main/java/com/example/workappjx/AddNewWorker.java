package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddNewWorker implements Initializable {

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

    @FXML
    public Text newWorkerError;

    public MainPanel mainPanel;

    public void setMainPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        newWorkerError.setText("");


        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
                String filteredValue = newValue.replaceAll("[^\\d]", "");
                if (filteredValue.length() > 9) {
                    filteredValue = filteredValue.substring(0, 9);
                }
                phoneField.setText(filteredValue);
        });

        peselField.textProperty().addListener((observable, oldValue, newValue) -> {
            String filteredValue = newValue.replaceAll("[^\\d]", "");
            if (filteredValue.length() > 11) {
                filteredValue = filteredValue.substring(0, 11);
            }
            peselField.setText(filteredValue);
        });


        salaryField.textProperty().addListener((observable, oldValue, newValue) -> {
            String filteredValue = newValue.replaceAll("[^\\d]", "");
            salaryField.setText(filteredValue);
        });
    }


    // Create new user and add it to the database
    public void createNewWorker() throws SQLException {
        String login = loginField.getText();
        String password = passwordField.getText();
        String rePassword = rePasswordField.getText();
        boolean errors = false;
        if(!password.equals(rePassword)){
            newWorkerError.setText("password and repassword must be the same!");
            return;
        }
        LoginPanelController loginPanelController = new LoginPanelController();
        password = loginPanelController.getMd5(password);
        String name = nameField.getText();
        String lastName = surnameField.getText();
        String phoneNumber = phoneField.getText();
        String pesel = peselField.getText();
        String salary = salaryField.getText();
        if(phoneNumber.length()<9){
            newWorkerError.setText("The phone number must be 9 long");
            return;
        }
        if(pesel.length()<11){
            newWorkerError.setText("The pesel must be 11 long");
            return;
        }
        boolean admin = adminCheckBox.isSelected();

        if(login.isEmpty() || password.isEmpty() || rePassword.isEmpty() || name.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || pesel.isEmpty() || salary.isEmpty()){
            newWorkerError.setText("Fields cannot be empty!");
            return;
        }

        String url = "jdbc:mysql://localhost/persons";
        String username = "root";
        String dbPassword = "1234qwer";
        Connection connection = DriverManager.getConnection(url, username, dbPassword);
        // Chceck if user with this login exist. If exist then don't create new user
        try{
            String sql = "SELECT * FROM workers WHERE login = " + login + ";";
            PreparedStatement statement = connection.prepareStatement(sql);
            int rowsInserted = statement.executeUpdate();
            statement.close();
            connection.close();
            loginField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            newWorkerError.setText("Login already exist!");
            newWorkerError.setFill(Color.RED);
            errors = true;
        } catch (SQLException ignored) {
        }

        // Check if user with this pesel exist. If exist then don't create new user
        try{
            String sql = "SELECT * FROM workers WHERE pesel = " + pesel + ";";
            PreparedStatement statement = connection.prepareStatement(sql);
            int rowsInserted = statement.executeUpdate();
            statement.close();
            connection.close();
            peselField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            newWorkerError.setText("Pesel already exist!");
            errors = true;
        } catch (SQLException ignored) {
        }
        if (errors){
            return;
        }

        try {
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



            statement.executeUpdate();

            statement.close();
            connection.close();
            Stage stage = (Stage) confirmButton.getScene().getWindow();

            stage.close();
            mainPanel.showWorkersList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
