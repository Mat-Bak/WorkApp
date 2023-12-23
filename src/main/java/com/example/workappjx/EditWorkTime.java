package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML public Button saveData;

    @FXML public Button deleteData;

    @FXML
    public ComboBox addressComboBox;

    @FXML public Text date;

    public MainPanel mainPanel;

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
        for (String listOfAddress : ListOfAddress) {
            addressComboBox.getItems().add(listOfAddress);
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

    public List<String> addressList(){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        String address;

        List<String> addressList = new ArrayList<>();

        String query = "SELECT * FROM Persons.address;";

        try {
            // Execute query and get result
            ResultSet resultSet = databaseConnector.executeQuery(query);
            while (resultSet.next()) {
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

    public void addNewWorkTime(){
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
        popupWindow.setResizable(false);
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

        buttonNo.setOnMouseClicked(e -> popupWindow.close());

        buttonYes.setOnMouseClicked(ev ->{
            int workTimeID = MainPanel.workTimeID;
            SaveWorkTimeData saveWorkTimeData = new SaveWorkTimeData();
            saveWorkTimeData.removeDataFromDataBase(workTimeID);
            popupWindow.close();
            Stage stage = (Stage) deleteData.getScene().getWindow();
            stage.close();
        });
    }

    public void setMainPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }

    public void editWorkTimeData(){
        addNewWorkTime();
        SaveWorkTimeData saveWorkTimeData = new SaveWorkTimeData();
        int id = MainPanel.workTimeID;
        mainPanel.showWorkTimeData();
        saveWorkTimeData.removeDataFromDataBase(id);
        Stage stage = (Stage) saveData.getScene().getWindow();
        stage.close();
    }
}