package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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

public class EditWorkTime implements Initializable {

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


//    @FXML
//    public Pane dataPane;

    @FXML public Button saveData;

    @FXML public Button deleteData;

    @FXML
    public ComboBox addressComboBox;

    @FXML public Text date;

//    @FXML public DatePicker dateTime;

//    public int testHour;


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
        date.setText("EDIT\n" + MainPanel.getLocalDate.toString());
//        date.setText("DDUPA");
//        startHourBox.setValue(1);
        for(int k = 0; k<ListOfAddress.size(); ++k ){
            addressComboBox.getItems().add(ListOfAddress.get(k));
        }
    }

    public void setData(int startH, int startM, int endH, int endM, String address, String comm){
        startHourBox.setValue(startH);
        startMinutsBox.setValue(startM);
        endHourBox.setValue(endH);
        endMinutsBox.setValue(endM);
        addressComboBox.setValue(address);
        commentField.setText(comm);

    }

//    public void setEndHour(int hour){
//        endHourBox.setValue(hour);
//    }


//    public EditWorkTime(int testHour){
//        this.testHour = testHour;
//    }

//    public void initScene() throws IOException {
//
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
//        Scene secondScene = new Scene(fxmlLoader.load(), 300, 460);
//        Stage Secondstage = new Stage();
////        startHourBox.setValue(testHour);
//        Secondstage.setScene(secondScene);
//        Secondstage.show();
////        date.setText(localDate.toString());
////        startHourBox.setValue(startHour);
////        startMinutsBox.setValue(startMin);
////        endHourBox.setValue(endHour);
////        endMinutsBox.setValue(endMin);
//        Secondstage.setTitle("Edit Work Time");
//
//    }

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

//    public void editWorkTimeWindow() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
//        Scene secondScene = new Scene(fxmlLoader.load(), 300, 460);
//        Stage Secondstage = new Stage();
//        Secondstage.setScene(secondScene);
//        Secondstage.show();
////        date.setText(localDate.toString());
////        startHourBox.setValue(startHour);
////        startMinutsBox.setValue(startMin);
////        endHourBox.setValue(endHour);
////        endMinutsBox.setValue(endMin);
//        Secondstage.setTitle("Edit Work Time");
//    }

//    public void setStartHourBox(int start){
//        this.startHourBox.setValue(start);
//    }

    public void addNewWorkTime(){
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
        SaveWorkTimeData saveTimeWorkData = new SaveWorkTimeData();
        saveTimeWorkData.connectWorkTimeDatabase(workTime);

        // close panel after add new data to database
        Stage stage = (Stage) deleteData.getScene().getWindow();

        stage.close();
    }

    public void deleteWorkTime(){
        Stage popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Delete");

        StackPane popupRoot = new StackPane();
        VBox popupVBox = new VBox(10);
        popupVBox.setPadding(new Insets(10));

        Label popupLabel = new Label("Do you want delete?");
        Button buttonYes = new Button("YES");
        Button buttonNo = new Button("NO");
        HBox buttonsHBox = new HBox(10);
        popupVBox.getChildren().add(popupLabel);
        popupVBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(buttonYes, buttonNo);
        buttonsHBox.setAlignment(Pos.CENTER);

        VBox popupPane = new VBox(10);
        popupPane.getChildren().addAll(popupVBox, buttonsHBox);
        popupPane.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(popupPane, 300, 200);
        popupWindow.setScene(popupScene);
        popupWindow.show();

        buttonNo.setOnMouseClicked(e ->{
            popupWindow.close();
        });

        buttonYes.setOnMouseClicked(ev ->{
            int workTimeID = MainPanel.workTimeID;
            SaveWorkTimeData saveWorkTimeData = new SaveWorkTimeData();
            System.out.println("Delete record where id = " + workTimeID);
            saveWorkTimeData.removeDataFromDataBase(workTimeID);
            popupWindow.close();
            Stage stage = (Stage) deleteData.getScene().getWindow();
            stage.close();
        });
    }

    public void testEditButton(){
        addNewWorkTime();
        SaveWorkTimeData saveWorkTimeData = new SaveWorkTimeData();
        int id = MainPanel.workTimeID;
        System.out.println("get id worktime: " + id);
        saveWorkTimeData.removeDataFromDataBase(id);
        Stage stage = (Stage) saveData.getScene().getWindow();
        stage.close();

//        System.out.println("Edit!");
    }


}



