package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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


    @FXML
    public Pane dataPane;

    @FXML public Button addDataButton;

    @FXML
    public ComboBox addressComboBox;

    @FXML public Text date;

    @FXML public DatePicker dateTime;

    public int testHour;


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
//        date.setText(MainPanel.getLocalDate.toString());
//        date.setText("DDUPA");
//        startHourBox.setValue(1);
        for(int k = 0; k<ListOfAddress.size(); ++k ){
            addressComboBox.getItems().add(ListOfAddress.get(k));
        }
    }

//    public void setEndHour(int hour){
//        endHourBox.setValue(hour);
//    }


//    public EditWorkTime(int testHour){
//        this.testHour = testHour;
//    }

    public void initScene() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load(), 300, 460);
        Stage Secondstage = new Stage();
//        startHourBox.setValue(testHour);
        Secondstage.setScene(secondScene);
        Secondstage.show();
//        date.setText(localDate.toString());
//        startHourBox.setValue(startHour);
//        startMinutsBox.setValue(startMin);
//        endHourBox.setValue(endHour);
//        endMinutsBox.setValue(endMin);
        Secondstage.setTitle("Edit Work Time");

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

    public void editWorkTimeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load(), 300, 460);
        Stage Secondstage = new Stage();
        Secondstage.setScene(secondScene);
        Secondstage.show();
//        date.setText(localDate.toString());
//        startHourBox.setValue(startHour);
//        startMinutsBox.setValue(startMin);
//        endHourBox.setValue(endHour);
//        endMinutsBox.setValue(endMin);
        Secondstage.setTitle("Edit Work Time");
    }

    public void setStartHourBox(int start){
        this.startHourBox.setValue(start);
    }

    public void testEditButton(){
        System.out.println("Edit!");
    }


}



