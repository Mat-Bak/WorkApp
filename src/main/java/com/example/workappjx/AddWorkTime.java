package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*
Methods:
    * Void initialize() - Initialize time numbers in panel where user can add new work time
    * Void addWorkTimeWindow() - Create panel where user can add work time
    * Void getWorkTime() - Get data from panel and add it to database

Main functionality of this class is created new panel where user can set work time (start and end) on selected date and add this data to database

 */

public class AddWorkTime implements Initializable {

    @FXML
    public ComboBox startHourBox;
    @FXML
    public ComboBox endHourBox;
    @FXML
    public ComboBox startMinutsBox;
    @FXML
    public ComboBox endMinutsBox;

    @FXML
    public TextArea commentField;


    @FXML
    public Pane dataPane;

    @FXML public Button addDataButton;

    @FXML
    public ComboBox addressComboBox;

    SaveWorkTimeData saveTimeWorkData = new SaveWorkTimeData();

    public AddWorkTime() {

    }
@Override
    public void initialize(URL arg0, ResourceBundle arg1){
        for(int i = 0; i < 13; ++i){
            startHourBox.getItems().add(i);
            endHourBox.getItems().add(i);
        }
        for(int j = 0; j < 60; ++j){
            startMinutsBox.getItems().add(j);
            endMinutsBox.getItems().add(j);
        }
        List<String> ListOfAddress = addressList();
        for(int k = 0; k<ListOfAddress.size(); ++k ){
            addressComboBox.getItems().add(ListOfAddress.get(k));
        }

    }

    public List<String> addressList(){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        String address;
        int id;

        List<String> addressList = new ArrayList<>();

        String query = "SELECT * FROM Persons.address;";

        try {
            // Execute query and get result
            ResultSet resultSet = databaseConnector.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                address = resultSet.getString("address");
                addressList.add(address);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close database connection
        try {
            databaseConnector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addressList;

    }

    public void addWorkTimeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addWorkTime.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load(), 300, 400);
        Stage Secondstage = new Stage();
        Secondstage.setTitle("Add Work Time");
        Secondstage.setScene(secondScene);
        Secondstage.show();
    }

    public void getWorkTime(){
        //get start and end time from panel
        int startH = (int) startHourBox.getValue();
        int startM = (int) startMinutsBox.getValue();
        int endH = (int) endHourBox.getValue();
        int endM = (int) endMinutsBox.getValue();



        //  temporarily set address
        String address = (String)addressComboBox.getValue();
        //        LocalDate localDate = dateTime.getValue();
        // get comment from panel
        String comment = commentField.getText();
        // get selected date
        LocalDate localDate = MainPanel.getLocalDate;

        // get user id
        Person person = LoginPanelController.getPersonData();
        int user_id = person.getId();

        // transform time from panel to LocalTime
        LocalTime startWork = LocalTime.of(startH, startM);
        LocalTime endWork = LocalTime.of(endH, endM);

        // calculate work time duration
        Duration duration = Duration.between(startWork, endWork);

        // create new WorkTime from data and save it to database
        WorkTime workTime = new WorkTime(address,localDate,startWork, endWork,comment,user_id);
        saveTimeWorkData.connectWorkTimeDatabase(workTime);

//
//        dataPane = new Pane();
//        dataPane.prefWidth(400);
//        dataPane.prefHeight(54);
//        Text start_H = new Text(startWork.toString());
//        Text end_H = new Text(endWork.toString());
//        dataPane.getChildren().add(start_H);
//        dataPane.getChildren().add(end_H);

        // close panel after add new data to database
        Stage stage = (Stage) addDataButton.getScene().getWindow();
        stage.close();


        System.out.println("Work Time Saved!");
    }
}
