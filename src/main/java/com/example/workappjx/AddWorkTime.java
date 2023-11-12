package com.example.workappjx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @FXML public Text date;

    @FXML public DatePicker dateTime;


    private Text dupa = new Text("dupa");

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
        date.setText(MainPanel.getLocalDate.toString());
//date.setText("Dupa2");
        for(int k = 0; k<ListOfAddress.size(); ++k ){
            addressComboBox.getItems().add(ListOfAddress.get(k));
        }

    }

    public void setStartHourBox(int hour) {
        this.startHourBox.setValue(hour);
    }

    public void setStartMinutsBox(int Minuts){
        this.startMinutsBox.setValue(Minuts);
    }

    public void setEndHourBox(int Hour){
        this.endHourBox.setValue(Hour);
    }

    public void setEndMinutsBox(int Minuts){
        this.endMinutsBox.setValue(Minuts);
    }

    public void setAddressComboBox(String address){
        addressComboBox.setValue(address);
    }

    public void setCommentField(String comment){
        this.commentField.setText(comment);
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

    public void addWorkTimeWindow(LocalDate localDate) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addWorkTime.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load(), 300, 460);
        Stage Secondstage = new Stage();
        Secondstage.setTitle("Add Work Time");
        Secondstage.setScene(secondScene);
        Secondstage.show();
    }

    public void setDateText(String text){
        date.setText(text);
    }

    public void getWorkTime(){
        //MainPanel mainPanel = new MainPanel();
        //get start and end time from panel
        int startH = (int) startHourBox.getValue();
        int startM = (int) startMinutsBox.getValue();
        int endH = (int) endHourBox.getValue();
        int endM = (int) endMinutsBox.getValue();




        //  temporarily set address
        String address = (String)addressComboBox.getValue();
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
        //Duration duration = Duration.between(startWork, endWork);

        // create new WorkTime from data and save it to database
        WorkTime workTime = new WorkTime(address,localDate,startWork, endWork,comment,user_id);
        saveTimeWorkData.connectWorkTimeDatabase(workTime);

        // close panel after add new data to database
        Stage stage = (Stage) addDataButton.getScene().getWindow();
        stage.close();

        showWorkTimeData();
        System.out.println("Work Time Saved!");



    }

    public void showWorkTimeData(){
        MainPanel mainPanel = new MainPanel();
        WorkTime getLocalDataFromWorkTime = new WorkTime();
        Person person = LoginPanelController.getPersonData();
        LocalDate localDate = getLocalDataFromWorkTime.getLocalDate();
        if(localDate == null) return;
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId(), localDate);
        mainPanel.showWorkTimeData();
    }

    @FXML
    private void submitButton(ActionEvent event) throws IOException, InterruptedException {


        //MainPanel mainPanel = new MainPanel();
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
        //Duration duration = Duration.between(startWork, endWork);

        // create new WorkTime from data and save it to database
        WorkTime workTime = new WorkTime(address,localDate,startWork, endWork,comment,user_id);
        saveTimeWorkData.connectWorkTimeDatabase(workTime);

        // close panel after add new data to database
        Stage stage = (Stage) addDataButton.getScene().getWindow();

        stage.close();




    }

    public void testDataUpdate() throws IOException {
        System.out.println("Test czy działą button!");
        Person person = LoginPanelController.getPersonData();
        LocalDate localDate = MainPanel.getLocalDate;
        System.out.println("LocalDate: " + localDate);
        if(localDate == null) return;
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId(), localDate);
        System.out.println("workTimeList length: " + workTimeList.size());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
        Parent root = loader.load();
        MainPanel mainPanel = loader.getController();

        mainPanel.clearPane();
        System.out.println("CLEAR!");

        for(WorkTime workTime : workTimeList){
            if (localDate.isEqual(workTime.getDate())){
                System.out.println("Data exist!");

                Text address = new Text(workTime.getAddress());
                Text time = new Text(workTime.timeToStrikg());
                address.setTextAlignment(TextAlignment.CENTER);
                time.setTextAlignment(TextAlignment.CENTER);

                TextFlow dataPane = new TextFlow();
                TextFlow timePane = new TextFlow();

                dataPane.setTextAlignment(TextAlignment.CENTER);
                dataPane.setPrefSize(285, 39);

                timePane.setTextAlignment(TextAlignment.CENTER);
                timePane.setPrefSize(95, 39);

                dataPane.getChildren().add(address);
                dataPane.setLayoutX(0);

                timePane.getChildren().add(time);
                timePane.setLayoutX(285);

                Pane pane = new Pane();
                pane.setPrefWidth(380);
                pane.setPrefHeight(40);
                pane.getChildren().addAll(dataPane, timePane);

                pane.setStyle("-fx-border-color: grey;" +
                        "-fx-border-style: solid none solid none;" +
                        "-fx-border-width: 2;");

                dataPane.setStyle("-fx-border-color: grey;" +
                        "-fx-border-style: hidden solid hidden hidden;" +
                        "-fx-border-width: 4;");

                mainPanel.workTimeDataPanel.getChildren().add(pane);
                Text testText = new Text("testowy text");
                mainPanel.workTimeDataPanel.getChildren().add(testText);

            }
        }
        System.out.println("Work Panel length: " + mainPanel.workTimeDataPanel.getChildren().size());

    }
}
