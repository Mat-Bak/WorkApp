package com.example.workappjx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
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
    public Text workTimeError;


//    @FXML
//    public Pane dataPane;

    @FXML public Button addDataButton;

    @FXML
    public ComboBox addressComboBox;

    @FXML public Text date;

    public LocalDate datePicker;

    private Stage addWorkTimeStage;
    private Stage Secondstage;

    public VBox firstWindowVBox;

    private Stage stageTest;
    private MainPanel mainPanel;

//    @FXML public DatePicker dateTime;


//    private Text dupa = new Text("dupa");

    SaveWorkTimeData saveTimeWorkData = new SaveWorkTimeData();

    public AddWorkTime( ){

    }

    public AddWorkTime(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addWorkTime.fxml"));
        loader.setController(this);

        try {
            VBox secondLayout = loader.load();
            stageTest = new Stage();
            stageTest.setTitle("Drugie okno");
            stageTest.setScene(new Scene(secondLayout));
            stageTest.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


    }

    public void showAndWait() {
        if(stageTest!= null){
            stageTest.showAndWait();
        }

    }

    @FXML
    private void closeAndInvokeFunction() {
        // Zamknij drugie okno
        if(stageTest!= null){
            stageTest.close();
        }

        // Wywołaj funkcję z pierwszego okna
        mainPanel.changeColor();
    }

    public void setStage(Stage stage){
        this.Secondstage = stage;
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
//date.setText("Dupa2");
        for(int k = 0; k<ListOfAddress.size(); ++k ){
            addressComboBox.getItems().add(ListOfAddress.get(k));
        }
        datePicker = MainPanel.getLocalDate;
        firstWindowVBox = MainPanel.mainPanelVBox;
    }

//    public void setStartHourBox(int hour) {
//        this.startHourBox.setValue(hour);
//    }
//
//    public void setStartMinutsBox(int Minuts){
//        this.startMinutsBox.setValue(Minuts);
//    }
//
//    public void setEndHourBox(int Hour){
//        this.endHourBox.setValue(Hour);
//    }
//
//    public void setEndMinutsBox(int Minuts){
//        this.endMinutsBox.setValue(Minuts);
//    }
//
//    public void setAddressComboBox(String address){
//        addressComboBox.setValue(address);
//    }
//
//    public void setCommentField(String comment){
//        this.commentField.setText(comment);
//    }


    public List<String> ActiveAddressList(){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        String address;
        int id;

        List<String> addressList = new ArrayList<>();

        String query = "SELECT * FROM Persons.address WHERE active = 1";

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

    public ArrayList<String> addressList(){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        String address;
        int id;

        ArrayList<String> addressList = new ArrayList<>();

        String query = "SELECT * FROM Persons.address";

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

    public Stage getStage(){
        return stageTest;
    }
    /*
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
    */

    public void setMainPanelController(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }

    @FXML
    private void submitButton() throws IOException {

        workTimeError.setText("");
        //MainPanel mainPanel = new MainPanel();
        //get start and end time from panel
        int startH = (int) startHourBox.getValue();
        int startM = (int) startMinutsBox.getValue();
        int endH = (int) endHourBox.getValue();
        int endM = (int) endMinutsBox.getValue();
        boolean dataCorrect = true;

        if(startH > endH || (startH == endH && startM >= endM)){
            workTimeError.setText("Wrong work time!");
            workTimeError.setFill(Color.RED);
            dataCorrect = false;
        }else if(addressComboBox.getValue()==null){
            workTimeError.setText("Select address!");
            workTimeError.setFill(Color.RED);
            dataCorrect = false;
        }

        if (dataCorrect){
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

//            mainPanel.refreshData.fire();
            mainPanel.showWorkTimeData();

            Stage stage = (Stage) addDataButton.getScene().getWindow();

            stage.close();



//
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
//            MainPanel mainPanel = fxmlLoader.getController();
//            Stage primaryStage = mainPanel.getStage();
//            if(primaryStage!=null){
//                primaryStage.show();
//                mainPanel.refreshData.fire();
//                System.out.println("FIRE!!!!!!!!!!!!");
//
//            }else{
//                System.out.println("ERROR!!!!!!!");
//            }
//
//            Stage mainPanelStage = new Stage();
//            fxmlLoader.setController();
//            Stage stage = mainPanel.getStage();
//            stage.getUserData();
//            mainPanel.updateData();
//            mainPanel.showWorkTimeData();

//            mainPanel.refreshData.fire();
//            refreashWorkTimeData(datePicker, mainPanel.workTimeDataPanel);


            // close panel after add new data to database


//            stage.setOnHidden(ev -> {
//                mainPanel.changeColorVBox();
//                System.out.println("stage close 1!");
//            });



//            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
////            Parent root = loader.load();
//            MainPanel mainPanel = loader.getController();
//            System.out.println("mainPanel.getDataTime(): " + mainPanel.getDataTime() +
//                    "\n mainPanel.dateTime: " + mainPanel.dateTime +
//                    "\n mainPanel.DateTime.getValue(): " + mainPanel.dateTime.getValue() +
//                    "\n AddWorkTime datePicker: " + datePicker
//            );
//            mainPanel.changeColorVBox();

//            addWorkTimeStage = (Stage) addDataButton.getScene().getWindow();



//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
//            Parent root = fxmlLoader.load();
//            MainPanel mainPanel = fxmlLoader.getController();
//            Scene scene = new Scene(fxmlLoader.load(), 450, 400);

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
//            Parent root = loader.load();
//            MainPanel mainPanel = loader.getController();
//            Scene scene = new Scene(loader.load(), 450, 400);
            //E:/Nauka/Java/workApp/workAppJX/src/main/resources/
//            Secondstage.setTitle("WorkApp");
//            Secondstage.setScene(scene);
//            Secondstage.show();
//            mainPanel.changeColorVBox();

//
//            firstWindowVBox.setStyle("-fx-border-color: blue;" +
//                    "-fx-border-style: solid;" +
//                    "-fx-border-width: 2;");

//            addWorkTimeStage.setOnHidden(e -> {
//                ((MainPanel)stage.getUserData()).updateData();
//                System.out.println("Hidde addworktimePanel !!!!!");
//            });
        }

    }

    public void refreshDataVBox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
        Parent root = fxmlLoader.load();
        MainPanel mainPanel = fxmlLoader.getController();
//        Scene scene = mainPanel.getScene();
//        Stage mainPanelStage = mainPanel.getStage();
//        //E:/Nauka/Java/workApp/workAppJX/src/main/resources/
//        mainPanelStage.setScene(scene);
//        mainPanelStage.show();
        mainPanel.refreshData.fire();
    }

    public void initData(VBox firstWindowVBox) {
        this.firstWindowVBox = firstWindowVBox;
    }

    public void refreashWorkTimeData(LocalDate localDate, VBox workTimeBox){
        System.out.println("Test czy działa button! 22222");
        Person person = LoginPanelController.getPersonData();
        System.out.println("LocalDate: " + localDate);
        if(localDate == null) return;
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId(), localDate);
        workTimeBox.getChildren().clear();

        System.out.println("1");
        for(WorkTime workTime : workTimeList){
            System.out.println("2");
            Text address = new Text(workTime.getAddress());
            Text time = new Text(workTime.timeToStrikg());
            address.setTextAlignment(TextAlignment.CENTER);
            time.setTextAlignment(TextAlignment.CENTER);

            TextFlow dataPane = new TextFlow();
            TextFlow timePane = new TextFlow();

            dataPane.setTextAlignment(TextAlignment.CENTER);
            dataPane.setPrefSize(285, 39);
            dataPane.getChildren().add(address);
            dataPane.setLayoutX(0);
            dataPane.setPadding(new Insets(5,5,5,5));

            timePane.setTextAlignment(TextAlignment.CENTER);
            timePane.setPrefSize(94, 39);
            timePane.getChildren().add(time);
            timePane.setLayoutX(285);
            timePane.setPadding(new Insets(5,5,5,5));

            Pane pane = new Pane();
            pane.setPrefWidth(380);
            pane.setPrefHeight(40);
            pane.getChildren().addAll(dataPane, timePane);

            pane.setStyle("-fx-border-color: grey;" +
                    "-fx-border-style: solid none solid none;" +
                    "-fx-border-width: 2;" +
                    "-fx-background-color: lightgrey;");

            dataPane.setStyle("-fx-border-color: grey;" +
                    "-fx-border-style: hidden solid hidden hidden;" +
                    "-fx-border-width: 4;");


            pane.setOnMouseEntered(event ->{
                pane.setStyle("-fx-border-color: lightgrey;" +
                        "-fx-border-style: solid none solid none;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: grey;");
                pane.setCursor(Cursor.HAND);
            });

            pane.setOnMouseExited(event ->{
                pane.setStyle("-fx-border-color: grey;" +
                        "-fx-border-style: solid none solid none;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: lightgrey;");
                pane.setCursor(Cursor.DEFAULT);
            });

            pane.setOnMouseClicked(event -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
                Scene secondScene;
                try {
                    secondScene = new Scene(fxmlLoader.load(), 300, 460);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Secondstage = new Stage();
                Secondstage.setScene(secondScene);
                Secondstage.show();

                int startHour = workTime.getStart_time().getHour();
                int startMinuts = workTime.getStart_time().getMinute();
                int endHour = workTime.getEnd_time().getHour();
                int endMinuts = workTime.getEnd_time().getMinute();
                int id = workTime.getUser_id();

                EditWorkTime editWorkTime = fxmlLoader.getController();
                editWorkTime.setData(startHour, startMinuts, endHour, endMinuts, workTime.getAddress(), workTime.getComment());
//                workTimeID = workTime.getId();
            });

            workTimeBox.getChildren().add(pane);

        }
        System.out.println("3");
    }

    /*
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

     */
}
