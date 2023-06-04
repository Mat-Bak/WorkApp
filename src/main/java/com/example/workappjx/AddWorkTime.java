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
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

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

    public void showData(){
        MainPanel mainPanel = new MainPanel();
        System.out.println("Get date from main panel: " + MainPanel.getLocalDate);

    }

    public void getWorkTime(){
        int startH = (int) startHourBox.getValue();
        int startM = (int) startMinutsBox.getValue();
        int endH = (int) endHourBox.getValue();
        int endM = (int) endMinutsBox.getValue();



        String address = "test";
        MainPanel mainPanel = new MainPanel();
//        LocalDate localDate = dateTime.getValue();
        String comment = commentField.getText();
        LocalDate localDate = MainPanel.getLocalDate;
        Person person = LoginPanelController.getPersonData();
        int user_id = person.getId();


        LocalTime startWork = LocalTime.of(startH, startM);
        LocalTime endWork = LocalTime.of(endH, endM);

        Duration duration = Duration.between(startWork, endWork);
        long hours = duration.toHours();
        long minuts = duration.toMinutes()%60;
        WorkTime workTime = new WorkTime(address,localDate,startWork, endWork,comment,user_id);
        saveTimeWorkData.connectWorkTimeDatabase(workTime);

        dataPane = new Pane();
        dataPane.prefWidth(400);
        dataPane.prefHeight(54);
        Text start_H = new Text(startWork.toString());
        Text end_H = new Text(endWork.toString());
        dataPane.getChildren().add(start_H);
        dataPane.getChildren().add(end_H);

        Stage stage = (Stage) addDataButton.getScene().getWindow();
        stage.close();


        System.out.println("Work Time Saved!");
    }
}
