package com.example.workappjx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
    public Text workTimeError;

    @FXML public Button addDataButton;

    @FXML
    public ComboBox addressComboBox;

    @FXML public Text date;

    public LocalDate datePicker;

    public VBox firstWindowVBox;

    private MainPanel mainPanel;
    SaveWorkTimeData saveTimeWorkData = new SaveWorkTimeData();

    public AddWorkTime( ){

    }

@Override
    public void initialize(URL arg0, ResourceBundle arg1){
        for(int i = 0; i < 25; ++i){
            startHourBox.getItems().add(i);
            endHourBox.getItems().add(i);
        }
        for(int j = 0; j < 60; ++j){
            startMinutsBox.getItems().add(j);
            endMinutsBox.getItems().add(j);
        }
        List<String> ListOfAddress = ActiveAddressList();
        date.setText(MainPanel.getLocalDate.toString());
    for (String listOfAddress : ListOfAddress) {
        addressComboBox.getItems().add(listOfAddress);
    }
        datePicker = MainPanel.getLocalDate;
        firstWindowVBox = MainPanel.mainPanelVBox;
    }

    // Get list with acive address from database
    public List<String> ActiveAddressList(){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        String address;

        List<String> addressList = new ArrayList<>();

        String query = "SELECT * FROM Persons.address WHERE active = 1";

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

    // Get every address from database
    public ArrayList<String> addressList(){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        String address;

        ArrayList<String> addressList = new ArrayList<>();

        String query = "SELECT * FROM Persons.address";

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


    public void setMainPanelController(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }

    // Add new working time when submit button is clicked and if every data are correct
    @FXML
    private void submitButton() {

        workTimeError.setText("");
        int startH = (int) startHourBox.getValue();
        int startM = (int) startMinutsBox.getValue();
        int endH = (int) endHourBox.getValue();
        int endM = (int) endMinutsBox.getValue();
        boolean dataCorrect = true;

        if(startH > endH || (startH == endH && startM >= endM) || (startH == endH && startM == endM)){
            workTimeError.setText("Wrong work time!");
            workTimeError.setFill(Color.RED);
            dataCorrect = false;
        }else if(addressComboBox.getValue()==null){
            workTimeError.setText("Select address!");
            workTimeError.setFill(Color.RED);
            dataCorrect = false;
        }

        if (dataCorrect){
            System.out.println("create work time!");
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
            saveTimeWorkData.connectWorkTimeDatabase(workTime);

            mainPanel.showWorkTimeData();

            Stage stage = (Stage) addDataButton.getScene().getWindow();

            stage.close();
        }

    }
}
