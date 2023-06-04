package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MainPanel{
    @FXML
    public Pane mainPanel;
    @FXML
    public Pane personInfoPanel;
    @FXML
    public TextArea personInfo;

    @FXML
    public Text firstNameLabel;
    @FXML
    public Text lastNameLabel;
    @FXML
    public Text phoneNumberLabel;
    @FXML
    public Text peselLabel;
    @FXML
    public Pane workPanel;

    @FXML
    public Button addWorkTimeButton;

    @FXML
    public DatePicker dateTime;
    public Person person;

    public static LocalDate getLocalDate;

    @FXML
    public VBox workTimeDataPanel;



    public Person getPerson() {
        return person;
    }


    public void mainPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 400);
        Stage stage = new Stage();
        stage.setTitle("WorkApp");
        stage.setScene(scene);
        stage.show();


    }

    // Show panel with person info
    public void showPersonInfo() throws IOException {
        mainPanel.setVisible(false);
        personInfoPanel.setVisible(true);
        setPersonData();

    }

    // back to main panel from person info panel
    public void personPanelBack(){
        mainPanel.setVisible(true);
        personInfoPanel.setVisible(false);
    }

    //show work panel
    public void showWorkPanel() throws IOException {
        mainPanel.setVisible(false);
        workPanel.setVisible(true);
    }

    // back to main panel from work panel
    public void workPanelBack(){
        mainPanel.setVisible(true);
        workPanel.setVisible(false);
    }

    // set data in person info panel
    public void setPersonData() throws IOException {
        Person person = LoginPanelController.getPersonData();

         firstNameLabel.setText("First Name: " + person.getFirstName());
         lastNameLabel.setText("Last Name: " + person.getLastName());
         phoneNumberLabel.setText("Phone Number: " + person.getPhoneNumber());
         peselLabel.setText("Pesel: " + person.getPesel());

    }

    public void getDataTime(){

//        getLocalDate = dateTime.getValue();
        getLocalDate = dateTime.getValue();
        System.out.println("MainPanel Data: " + getLocalDate);

//        if(localDate != null){
//            System.out.println("Data: " + localDate);
//            System.out.println("Day: " + localDate.getDayOfMonth());
//            System.out.println("Month: " + localDate.getMonth().toString());
//            System.out.println("Year: " +localDate.getYear());
//        }

    }


    public void showWorkTimeData(){
        Person person = LoginPanelController.getPersonData();
        LocalDate localDate = dateTime.getValue();
        if(localDate == null) return;
        System.out.println("Person ID: " + person.getId());
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId());
        System.out.println("Dlugość tablicy workTime: " + workTimeList.size());
        workTimeDataPanel.getChildren().clear();
        for (WorkTime workTime : workTimeList) {
            if(localDate.isEqual(workTime.getDate())){
                FlowPane pane = new FlowPane();
                Text data = new Text("DATA \n" + workTime.getDate());
                Text startTime = new Text("START \n" + workTime.getStart_time());
                Text endTime = new Text("END \n" + workTime.getEnd_time());
                pane.getChildren().add(data);
                pane.getChildren().add(startTime);
                pane.getChildren().add(endTime);
                workTimeDataPanel.getChildren().add(pane);
            }

        }
    }

    public void createWorkPanel() throws IOException {

        AddWorkTime workTimePanel = new AddWorkTime();
        getLocalDate = dateTime.getValue();
        workTimePanel.addWorkTimeWindow();
    }


}
