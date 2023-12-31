package com.example.workappjx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class MainPanel implements Initializable{
    @FXML
    public Pane mainPanel;

    @FXML
    public Pane salaryPanel;
    @FXML
    public Pane personInfoPanel;

    @FXML
    public Pane settingsPanel;

    @FXML
    public Pane workersPane;

    @FXML
    public Pane addressPane;

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

    @FXML
    public VBox workersVBox;

    @FXML
    public VBox addressVBox;

    @FXML
    public ComboBox monthList;

    @FXML
    public Text testAdmin;

    @FXML
    public Text SalaryHours;
    @FXML
    public Text SalaryBrutto;

    @FXML
    public Text salaryLabel;

    @FXML
    public PasswordField oldPass;
    @FXML
    public PasswordField newPass;

    @FXML
    public PasswordField reNewPass;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Text callendarError;

    @FXML
    public Text passwordError;

    private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    public int startHour;
    public int startMinuts;
    public int endHour;
    public int endMinuts;

    public int id;

    public int personID;

    public static int workTimeID;

    public static Stage stage;

    public Scene scene;

    public static VBox mainPanelVBox;

    @FXML
    public Button workersPanelButton;

    @FXML
    public Button addressPanelButton;

    @FXML
    public Text workersText;

    @FXML
    public Text addressText;

    public Image deleteImage = new Image("file:src/main/resources/com/example/workappjx/images/delete.png");
    public Image optionsImage = new Image("file:src/main/resources/com/example/workappjx/images/raport.png");


    public MainPanel(){

    }


    public void createMainPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainPanel.fxml"));
        scene = new Scene(fxmlLoader.load(), 450, 400);
        stage = new Stage();
        stage.setTitle("WorkApp");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        person = LoginPanelController.getPersonData();
    }

    public Scene getScene(){
        return this.scene;
    }


    public void showPersonInfo() {
        mainPanel.setVisible(false);
        personInfoPanel.setVisible(true);
        setPersonData();

    }


    public void personPanelBack(){
        mainPanel.setVisible(true);
        personInfoPanel.setVisible(false);
    }


    public void showWorkPanel() {
        showWorkTimeData();
        dateTime.setValue(LocalDate.now());
        mainPanel.setVisible(false);
        workPanel.setVisible(true);
    }

    public void showWorkersPanel() {
        mainPanel.setVisible(false);
        workersPane.setVisible(true);
        workersVBox.getChildren().clear();
        showWorkersList();
    }

    public void workersPanelBack(){
        mainPanel.setVisible(true);
        workersPane.setVisible(false);
    }

    public void workPanelBack(){
        mainPanel.setVisible(true);
        workPanel.setVisible(false);
    }

    public void showSalaryPanel(){
        monthList.setValue(null);
        SalaryHours.setText("Hours: ");
        SalaryBrutto.setText("Brutto: ");
        mainPanel.setVisible(false);
        salaryPanel.setVisible(true);
    }

    public void salaryPanelBack(){
        mainPanel.setVisible(true);
        salaryPanel.setVisible(false);
    }

    public void showSettingsPanel(){
        oldPass.clear();
        newPass.clear();
        reNewPass.clear();
        passwordError.setText("");
        mainPanel.setVisible(false);
        settingsPanel.setVisible(true);
    }
    public void settingPanelBack(){
        mainPanel.setVisible(true);
        settingsPanel.setVisible(false);
    }

    public void showAddressPanel(){
        mainPanel.setVisible(false);
        addressPane.setVisible(true);
        showAddressList();
    }

    public void addressPanelBack(){
        mainPanel.setVisible(true);
        addressPane.setVisible(false);
    }

    public void setPersonData() {
        Person person = LoginPanelController.getPersonData();
         firstNameLabel.setText("First Name: " + person.getFirstName());
         lastNameLabel.setText("Last Name: " + person.getLastName());
         phoneNumberLabel.setText("Phone Number: " + person.getPhoneNumber());
         peselLabel.setText("Pesel: " + person.getPesel());
        salaryLabel.setText("Salary per hour: " + person.getSalaryPerHour());
    }

    public LocalDate getDataTime(){
        getLocalDate = dateTime.getValue();
        return getLocalDate;
    }

    // Show work times data in panel from selected date in datePicker for logged user
    public void showWorkTimeData(){
        Person person = LoginPanelController.getPersonData();
        LocalDate localDate = getDataTime();
        if(localDate == null) return;
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.dbConnection(person.getId(), localDate);
        workTimeDataPanel.getChildren().clear();


        for(WorkTime workTime : workTimeList){
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

            // open edit work time when clicked on work time panel
            pane.setOnMouseClicked(event -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("editWorkTime.fxml"));
                Scene secondScene;
                try {
                    secondScene = new Scene(fxmlLoader.load(), 300, 460);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage Secondstage = new Stage();
                Secondstage.setResizable(false);
                Secondstage.setScene(secondScene);
                Secondstage.show();


                startHour = workTime.getStart_time().getHour();
                startMinuts = workTime.getStart_time().getMinute();
                endHour = workTime.getEnd_time().getHour();
                endMinuts = workTime.getEnd_time().getMinute();
                id = workTime.getUser_id();

                EditWorkTime editWorkTime = fxmlLoader.getController();
                editWorkTime.setData(startHour, startMinuts, endHour, endMinuts, workTime.getAddress(), workTime.getComment());
                editWorkTime.setMainPanel(this);
                workTimeID = workTime.getId();


            });

            workTimeDataPanel.getChildren().add(pane);

        }
    }


    @FXML
    public void createWorkPanel() {

        try {
            callendarError.setText("");
            callendarError.setFill(Color.BLACK);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addWorkTime.fxml"));
            Scene secondScene;
            secondScene = new Scene(fxmlLoader.load(), 312, 504);
            Stage Secondstage = new Stage();
            Secondstage.setResizable(false);
            Secondstage.initModality(Modality.APPLICATION_MODAL);
            Secondstage.setTitle("Add Work Time");
            Secondstage.setScene(secondScene);
            Secondstage.show();

            AddWorkTime addWorkTime = fxmlLoader.getController();
            addWorkTime.setMainPanelController(this);
        } catch (Exception e) {
            callendarError.setText("Select date!");
            callendarError.setFill(Color.RED);
            throw new RuntimeException(e);
        }
    }




    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        Person person = LoginPanelController.getPersonData();
        if (!person.getAdmin()){
            workersPanelButton.setVisible(false);
            addressPanelButton.setVisible(false);
            workersText.setVisible(false);
            addressText.setVisible(false);
        }else{
            workersPanelButton.setVisible(true);
            addressPanelButton.setVisible(true);
            workersText.setVisible(true);
            addressText.setVisible(true);
        }
        for (String month : months) {
            monthList.getItems().add(month);
        }
    }

    // Get summary of worked hours and salary brutto in selected month
    public void summaryOfTheMonth(){
        int month = 0;
        Person person = LoginPanelController.getPersonData();
        LoadWorkTimeData loadWorkTimeData = new LoadWorkTimeData();
        List<WorkTime> workTimeList = loadWorkTimeData.getDataFromWorkTime(person.getId());
        for (int i = 0; i < months.length; ++i){
            if(months[i].equals(monthList.getValue())){
                ++i;
                month=i;
                break;
            }
        }
        long SumOfHours = 0;
        for (WorkTime workTime : workTimeList) {
            if (workTime.getDate().getMonthValue() == month) {
                LocalTime time1 = workTime.getStart_time();
                LocalTime time2 = workTime.getEnd_time();
                Duration duration = Duration.between(time1, time2);
                SumOfHours += duration.toMinutes();
            }
        }
        SalaryHours.setText("");
        SalaryHours.setText("Hours: " + SumOfHours/60 + "h " + SumOfHours%60 + "min");
        SalaryBrutto.setText("");
        SalaryBrutto.setText("Brutto: " + (int)((SumOfHours/60)*person.getSalaryPerHour() + ((float)(SumOfHours%60)/60)*person.getSalaryPerHour()) + " kr");
    }

    public void changePass() throws SQLException {
        String oldPassword = LoginPanelController.getMd5(oldPass.getText());
        String newPassword = LoginPanelController.getMd5(newPass.getText());
        Person person = LoginPanelController.getPersonData();
        LoadPersonData loadPersonData = new LoadPersonData();
        boolean passwordCorrect = true;
        if(person.getPassword().equals(oldPassword) && !oldPass.getText().equals(newPass.getText())){
            if(!newPass.getText().equals(reNewPass.getText())){
                passwordCorrect = false;
                passwordError.setText("New Password and Repeat Password must be the same!");
                passwordError.setFill(Color.RED);
            }
            if(newPass.getText().length() < 8){
                passwordCorrect = false;
                passwordError.setText("The new password must be longer than 8 characters!");
                passwordError.setFill(Color.RED);
            }
            if (passwordCorrect){
                loadPersonData.changePassword(newPassword, person.getId());
                passwordError.setText("Password changed!");
                passwordError.setFill(Color.GREEN);
            }
        }else{
            passwordError.setText("The password cannot be the same as the old password!");
            passwordError.setFill(Color.RED);
        }

    }

    // get workers list from database and add it to the panel
    public void showWorkersList(){
        workersVBox.getChildren().clear();
        Person listOfPerson = new Person();
        List<Person> personList = listOfPerson.getPersonList();

        for(Person person : personList){
            personID = person.getId();
            Person loggedWorker = LoginPanelController.getPersonData();

            Text name = new Text(person.getFirstName() + " " + person.getLastName());
            name.setTextAlignment(TextAlignment.CENTER);
            TextFlow namePane = new TextFlow();
            TextFlow buttonPane = new TextFlow();
            TextFlow optionsPane = new TextFlow();
            ImageView view = new ImageView(deleteImage);
            view.setFitHeight(10);
            view.setPreserveRatio(true);

            ImageView optionsView = new ImageView(optionsImage);
            optionsView.setFitHeight(12);
            optionsView.setPreserveRatio(true);

            Separator separator = new Separator(Orientation.HORIZONTAL);

            Button deleteWorker = new Button();
            deleteWorker.setGraphic(view);
            deleteWorker.setPrefSize(10,10);

            if(personID == loggedWorker.getId()){
                deleteWorker.setDisable(true);
            }

            Button moreOptions = new Button();
            moreOptions.setGraphic(optionsView);
            moreOptions.setPrefSize(10, 10);

            optionsPane.getChildren().add(moreOptions);
            optionsPane.setLayoutX(30);
            optionsPane.setPadding(new Insets(8));

            buttonPane.getChildren().add(deleteWorker);
            buttonPane.setPadding(new Insets(8));

            namePane.setTextAlignment(TextAlignment.CENTER);
            namePane.setPrefSize(400, 39);
            namePane.getChildren().add(name);
            namePane.setLayoutX(0);
            namePane.setPadding(new Insets(12));

            Pane pane = new Pane();
            pane.setPrefWidth(426);
            pane.setPrefHeight(40);
            pane.setLayoutY(pane.getLayoutY() + 3);

            pane.getChildren().addAll(namePane, buttonPane, optionsPane);

            pane.setStyle("-fx-border-color: grey;" +
                    "-fx-border-style: solid none solid none;" +
                    "-fx-border-width: 2;" +
                    "-fx-background-color: lightgrey;");

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

            // Open edit user panel when click on user panel on the list
            namePane.setOnMouseClicked(event -> {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("workerPanel.fxml"));
                Scene secondScene;
                try {
                    secondScene = new Scene(fxmlLoader.load(), 326, 425);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage Secondstage = new Stage();
                Secondstage.initModality(Modality.APPLICATION_MODAL);
                Secondstage.setResizable(false);
                Secondstage.setScene(secondScene);
                Secondstage.show();
                WorkerPanel workerPanel = fxmlLoader.getController();
                workerPanel.setMainPanel(this);
                workerPanel.setPersonID(person.getId());
                workerPanel.setDataInPanel(person.getFirstName(), person.getLastName(), person.getPesel(), person.getPhoneNumber(), person.getSalaryPerHour(), person.getAdmin());
//                workersPanelBack();
            });

            // delete selected user on click
            deleteWorker.setOnMouseClicked(event -> {
                Stage popupWindow = new Stage();
                popupWindow.setResizable(false);
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Delete Worker");

                StackPane popupRoot = new StackPane();
                VBox popupVBox = new VBox(10);
                popupVBox.setPadding(new Insets(10));

                Label popupLabel = new Label("Do you want delete this worker?");
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


                buttonYes.setOnMouseClicked(e -> {
                    String url = "jdbc:mysql://localhost/Persons";
                    String username = "root";
                    String passwordDb = "1234qwer";

                    try {
                        Connection connection = DriverManager.getConnection(url, username, passwordDb);

                        String sql = "DELETE FROM workers WHERE id = '" + person.getId() + "';";
                        System.out.println("Person id: " + person.getId());

                        PreparedStatement statement = connection.prepareStatement(sql);


                        statement.executeUpdate();

                        statement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    workersVBox.getChildren().clear();
                    showWorkersList();
                    popupWindow.close();
                });

                buttonNo.setOnMouseClicked(e-> popupWindow.close());



            });

            // get report from selected user in selected month and save it to the WorkerRaport.txt
            moreOptions.setOnMouseClicked(event -> {
                Stage popupWindow = new Stage();
                popupWindow.setResizable(false);
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Worker Raport");

                VBox popupVBox = new VBox(10);
                popupVBox.setPadding(new Insets(10));

                ComboBox monthsList = new ComboBox(FXCollections.observableArrayList(months));
                monthsList.setPrefWidth(200);

                Button buttonSave = new Button("GET RAPORT");
                buttonSave.setPrefSize(100,40);
                popupVBox.getChildren().addAll(monthsList, buttonSave);
                popupVBox.setAlignment(Pos.CENTER);

                VBox popupPane = new VBox(10);
                popupPane.getChildren().add(popupVBox);
                popupPane.setAlignment(Pos.CENTER);

                Scene popupScene = new Scene(popupPane, 400, 150);
                popupWindow.setScene(popupScene);
                popupWindow.show();

                buttonSave.setOnMouseClicked(e ->{
                    int selectedMonth = monthsList.getSelectionModel().getSelectedIndex()+1;
                    String sql = null;
                    DatabaseConnector connect = new DatabaseConnector();
                    if(selectedMonth == 4 || selectedMonth == 6 || selectedMonth == 9){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-0" + selectedMonth + "-01' AND data <= '2023-0" + selectedMonth + "-30') AND user_id = '" + person.getId() +"';";
                    }else if(selectedMonth == 1 || selectedMonth == 3 || selectedMonth == 5 || selectedMonth == 7 || selectedMonth == 8){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-0" + selectedMonth + "-01' AND data <= '2023-0" + selectedMonth + "-31') AND user_id = '" + person.getId() +"';";
                    }else if(selectedMonth == 10 || selectedMonth == 12){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-" + selectedMonth + "-01' AND data <= '2023-" + selectedMonth + "-31') AND user_id = '" + person.getId() +"';";
                    }else if(selectedMonth == 11){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-" + selectedMonth + "-01' AND data <= '2023-" + selectedMonth + "-30') AND user_id = '" + person.getId() +"';";
                    }else if(selectedMonth == 2){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-" + selectedMonth + "-01' AND data <= '2023-" + selectedMonth + "-28') AND user_id = '" + person.getId() +"';";
                    }else{
                        System.out.println("DATE ERROR!");
                    }

                    ResultSet result = null;
                    try {
                        result = connect.executeQuery(sql);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    int idList = 1;
                    AddWorkTime addWorkTime = null;
                    addWorkTime = new AddWorkTime();
                    ArrayList<String> getAddressList = addWorkTime.addressList();
                    ArrayList<Integer> getWorkHour = new ArrayList<>(Collections.nCopies(getAddressList.size(), 0));
                    try (FileWriter fileWriter = new FileWriter("WorkerRaport.txt");
                         PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        printWriter.println(person.getFirstName() + " " + person.getLastName() + " | " + monthsList.getValue());
                        printWriter.println("ID | DATE | Address | Worker | WORK HOURS | Comment");
                        while (result.next()) {
                            String workDate = result.getString("data");
                            LocalTime startTime = result.getTime("start_time").toLocalTime();
                            LocalTime endTime = result.getTime("end_time").toLocalTime();
                            Duration workTime = Duration.between(startTime,  endTime);

                            String address = result.getString("address");
                            long hours = workTime.toHours();
                            long minuts = workTime.toMinutesPart()%60;
                            int workTimeToInt = (int)(hours*60 + minuts);
                            String comment = result.getString("comment");

                            printWriter.println(idList + " | " + workDate + " | " + address + " | "+ person.getFirstName() + " " + person.getLastName() + " | " + hours + "h " + minuts+"Min" +" | "+ comment);
                            idList++;

                            for(int i = 0; i < getAddressList.size(); ++i){
                                if(getAddressList.get(i).equals(address)){
                                    getWorkHour.set(i, getWorkHour.get(i) + workTimeToInt);
                                }
                            }

                        }

                        printWriter.println("-------------------------------------------- \n Work Hours Address Summary \n --------------------------------------------");
                        int idWorkHour = 1;
                        for(int j = 0; j < getAddressList.size(); ++j){
                            if(getWorkHour.get(j) == 0){
                                continue;
                            }
                            printWriter.println(idWorkHour + " | " + getAddressList.get(j) + " | " + getWorkHour.get(j)/60 + "h " + getWorkHour.get(j)%60 + "min");
                            idWorkHour++;
                        }

                        result.close();
                        connect.close();
                    } catch (IOException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    popupWindow.close();
                });

            });


            workersVBox.getChildren().addAll(separator,pane);

        }




    }


    public void showAddNewWorkerPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newWorkerPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 462, 521);
        AddNewWorker addNewWorker = fxmlLoader.getController();
        addNewWorker.setMainPanel(this);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add New Worker");
        stage.setScene(scene);
        stage.show();
    }

    // get list of address from database and add them to the panel
    public void showAddressList(){
        addressVBox.getChildren().clear();
        DatabaseConnector connector = new DatabaseConnector();
        String address;
        int id;
        boolean active;
        List<AddNewAddress> addressList = new ArrayList<>();
        String query = "SELECT * FROM Persons.address;";
        // Execute query and get result
        ResultSet resultSet = null;
        try {
            resultSet = connector.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                address = resultSet.getString("address");
                active = resultSet.getBoolean("active");

                AddNewAddress newAddress = new AddNewAddress(id, address, active);
                addressList.add(newAddress);

            }
            resultSet.close();
            connector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(AddNewAddress addAddress : addressList) {

            Text addressName = new Text(addAddress.getAddress());
            addressName.setTextAlignment(TextAlignment.CENTER);
            TextFlow addressPane = new TextFlow();
            TextFlow deletePane = new TextFlow();
            TextFlow optionsPane = new TextFlow();
            TextFlow checkBoxPane = new TextFlow();
            Separator separator = new Separator(Orientation.HORIZONTAL);

            ImageView deleteView = new ImageView(deleteImage);
            deleteView.setFitHeight(10);
            deleteView.setPreserveRatio(true);

            ImageView optionsView = new ImageView(optionsImage);
            optionsView.setFitHeight(12);
            optionsView.setPreserveRatio(true);

            Button deleteAddress = new Button();
            deleteAddress.setGraphic(deleteView);
            deleteAddress.setPrefSize(10, 10);

            Button moreOptions = new Button();
            moreOptions.setGraphic(optionsView);
            moreOptions.setPrefSize(10, 10);

            CheckBox activeAddress = new CheckBox("Active");

            deletePane.getChildren().add(deleteAddress);
            deletePane.setPadding(new Insets(8));

            optionsPane.getChildren().add(moreOptions);
            optionsPane.setLayoutX(30);
            optionsPane.setPadding(new Insets(8));

            checkBoxPane.getChildren().add(activeAddress);
            checkBoxPane.setLayoutX(330);
            checkBoxPane.setPadding(new Insets(8));

            addressPane.setTextAlignment(TextAlignment.CENTER);
            addressPane.setPrefSize(300, 39);
            addressPane.getChildren().add(addressName);
            addressPane.setLayoutX(0);
            addressPane.setPadding(new Insets(12));

            Pane pane = new Pane();
            pane.setPrefWidth(426);
            pane.setPrefHeight(40);
            pane.setLayoutY(pane.getLayoutY() + 3);
            pane.getChildren().addAll(addressPane, deletePane, optionsPane, checkBoxPane);
            if(!addAddress.getActive()){
                addressPane.setDisable(true);
                activeAddress.setSelected(false);
                pane.setStyle("-fx-border-color: lightgrey;" +
                        "-fx-border-style: solid none solid none;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: gainsboro;");
                addressName.setFill(Color.GREY);
                deleteAddress.setOnMouseEntered(event -> deleteAddress.setCursor(Cursor.HAND));

                deleteAddress.setOnMouseExited(event -> deleteAddress.setCursor(Cursor.DEFAULT));

            }else{
                addressPane.setDisable(false);
                activeAddress.setSelected(true);
                pane.setStyle("-fx-border-color: grey;" +
                        "-fx-border-style: solid none solid none;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: lightgrey;"+
                        "-fx-text-fill: black");
                addressName.setFill(Color.BLACK);
                pane.setOnMouseEntered(event -> {
                    pane.setStyle("-fx-border-color: lightgrey;" +
                            "-fx-border-style: solid none solid none;" +
                            "-fx-border-width: 2;" +
                            "-fx-background-color: grey;");
                    pane.setCursor(Cursor.HAND);
                });

                pane.setOnMouseExited(event -> {
                    pane.setStyle("-fx-border-color: grey;" +
                            "-fx-border-style: solid none solid none;" +
                            "-fx-border-width: 2;" +
                            "-fx-background-color: lightgrey;");
                    pane.setCursor(Cursor.DEFAULT);
                });
            }

            // change address between enable and disable
            activeAddress.setOnMouseClicked(e -> {

                String url = "jdbc:mysql://localhost/persons";
                String username = "root";
                String passwordDb = "1234qwer";
                int isActive;
                if(activeAddress.isSelected()){
                    isActive = 1;
                }else{
                    isActive = 0;
                }
                try {
                    Connection connection = DriverManager.getConnection(url, username, passwordDb);
                    String sql = "UPDATE Persons.address SET active = '" + isActive + "' WHERE id = " + addAddress.getID() + ";";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    int rowsInserted = statement.executeUpdate();

                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                showAddressPanel();

                addressPane.setDisable(!addressPane.isDisabled());
                if(addressPane.isDisable()){
                    addressPane.setDisable(true);
                    addAddress.setActive(false);
                }else{
                    addressPane.setDisable(false);
                    addAddress.setActive(true);
                }

            });

            // remove address from database
            deleteAddress.setOnMouseClicked(event -> {
                Stage popupWindow = new Stage();
                popupWindow.setResizable(false);
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Delete Worker");

                StackPane popupRoot = new StackPane();
                VBox popupVBox = new VBox(10);
                popupVBox.setPadding(new Insets(10));

                Label popupLabel = new Label("Do you want delete this address?");
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


                buttonYes.setOnMouseClicked(e ->{
                    String url = "jdbc:mysql://localhost/persons";
                    String username = "root";
                    String passwordDb = "1234qwer";
                    try {
                        Connection connection = DriverManager.getConnection(url, username, passwordDb);
                        String sql = "DELETE FROM address WHERE id = '" + addAddress.getID() + "';";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.executeUpdate();
                        statement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    addressVBox.getChildren().clear();
                    showAddressList();
                    popupWindow.close();
                });

                buttonNo.setOnMouseClicked(e -> popupWindow.close());



            });

            // get report from selected addres in selected month and save it to the AddressRaport.txt
            moreOptions.setOnMouseClicked(event -> {
                String selectedAddress = addressName.getText();

                Stage popupWindow = new Stage();
                popupWindow.setResizable(false);
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Address Raport");

                StackPane popupRoot = new StackPane();
                VBox popupVBox = new VBox(10);
                popupVBox.setPadding(new Insets(10));

                ComboBox monthsList = new ComboBox(FXCollections.observableArrayList(months));
                monthsList.setPrefWidth(200);

                Button buttonSave = new Button("GET RAPORT");
                buttonSave.setPrefSize(100,40);
                popupVBox.getChildren().addAll(monthsList, buttonSave);
                popupVBox.setAlignment(Pos.CENTER);

                VBox popupPane = new VBox(10);
                popupPane.getChildren().add(popupVBox);
                popupPane.setAlignment(Pos.CENTER);

                Scene popupScene = new Scene(popupPane, 400, 150);
                popupWindow.setScene(popupScene);
                popupWindow.show();

                buttonSave.setOnMouseClicked(e ->{
                    int selectedMonth = monthsList.getSelectionModel().getSelectedIndex()+1;
                    String sql = null;
                    DatabaseConnector connect = new DatabaseConnector();

                    if(selectedMonth == 4 || selectedMonth == 6 || selectedMonth == 9){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-0" + selectedMonth + "-01' AND data <= '2023-0" + selectedMonth + "-30') AND address = '" + selectedAddress +"';";
                    }else if(selectedMonth == 1 || selectedMonth == 3 || selectedMonth == 5 || selectedMonth == 7 || selectedMonth == 8){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-0" + selectedMonth + "-01' AND data <= '2023-0" + selectedMonth + "-31') AND address = '" + selectedAddress +"';";
                    }else if(selectedMonth == 10 || selectedMonth == 12){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-" + selectedMonth + "-01' AND data <= '2023-" + selectedMonth + "-31') AND address = '" + selectedAddress +"';";
                    }else if(selectedMonth == 11){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-" + selectedMonth + "-01' AND data <= '2023-" + selectedMonth + "-30') AND address = '" + selectedAddress +"';";
                    }else if(selectedMonth == 2){
                        sql = "SELECT * FROM persons.workhours  WHERE (data >= '2023-" + selectedMonth + "-01' AND data <= '2023-" + selectedMonth + "-28') AND address = '" + selectedAddress +"';";
                    }else{
                        System.out.println("DATE ERROR!");
                    }

                    ResultSet result = null;
                    try {
                        result = connect.executeQuery(sql);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    Person listOfPerson = new Person();
                    List<Person> personList = listOfPerson.getPersonList();
                    int idList = 1;
                    try (FileWriter fileWriter = new FileWriter("AddressRaport.txt");
                         PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        printWriter.println(selectedAddress + " | " + monthsList.getValue());
                        printWriter.println("ID | DATE | WORKER| WORK HOURS");
                        int sumOfHours = 0;
                        while (result.next()) {
                            String workDate = result.getString("data");
                            int userID = result.getInt("user_id");
                            LocalTime startTime = result.getTime("start_time").toLocalTime();
                            LocalTime endTime = result.getTime("end_time").toLocalTime();
                            Duration workTime = Duration.between(startTime,  endTime);
                            sumOfHours += workTime.toMinutes();
                            long hours = workTime.toHours();
                            long minuts = workTime.toMinutesPart()%60;
                            Person worker = null;
                            for (Person person : personList){
                                if(person.getId() == userID){
                                    worker = person;
                                    break;
                                }
                            }
                            assert worker != null;
                            printWriter.println(idList + " | " + workDate + " | " + worker.getFirstName() + " " + worker.getLastName() + " | " + hours + "h " + minuts+"Min");
                            idList++;


                        }
                        printWriter.println("---------------------------------- WORK HOURS SUM ----------------------------------");
                        printWriter.println(selectedAddress + ": " + sumOfHours/60 + "h " + sumOfHours%60 + "min");
                        result.close();
                        connect.close();
                    } catch (IOException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    popupWindow.close();
                });
            });
            addressVBox.getChildren().addAll(separator,pane);
        }
    }

    // Add new addres in database
    public void addNewAddress(){
        Stage popupWindow = new Stage();
        popupWindow.setResizable(false);
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Add New Address");

        StackPane popupRoot = new StackPane();
        VBox popupVBox = new VBox(10);
        popupVBox.setPadding(new Insets(10));

        TextField addresField = new TextField();
        addresField.setPrefSize(350, 30);
        CheckBox addressActive = new CheckBox("Active");

        Text addressText = new Text("Address:");

        Button buttonSave = new Button("SAVE");
        buttonSave.setPrefSize(70,40);
        popupVBox.getChildren().addAll(addressText, addresField,addressActive ,buttonSave);
        popupVBox.setAlignment(Pos.CENTER);


        VBox popupPane = new VBox(10);
        popupPane.getChildren().add(popupVBox);
        popupPane.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(popupPane, 400, 150);
        popupWindow.setScene(popupScene);
        popupWindow.show();

        buttonSave.setOnMouseClicked(ev ->{
            String url = "jdbc:mysql://localhost/persons";
            String username = "root";
            String dbPassword = "1234qwer";
            if(addresField.getText().isEmpty()){
                addresField.setText("Address field can not be empty!");
                addressText.setFill(Color.RED);
                addresField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                return;
            }else{
                addressText.setFill(Color.BLACK);
                addresField.setStyle("-fx-border-color: none ;");
            }

            try {
                Connection connection = DriverManager.getConnection(url, username, dbPassword);
                String sql = "INSERT INTO address (address, active) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, addresField.getText());
                statement.setBoolean(2, addressActive.isSelected());

                statement.executeUpdate();

                statement.close();
                connection.close();
                popupWindow.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            showAddressList();
        });
    }
}