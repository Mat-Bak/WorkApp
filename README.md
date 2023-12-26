1. Ogólny opis
	-krótki opis programu i jego celu
2. Wymagania systemowe (java, mysql itp)
3. instalacja
4. Struktura projektu
5. Uruchamianie aplikacji
6. Funkcjonalność aplikacji 
7. integracja z bazą danych (tabelki)
8. Obsługa błędów 
9. Testowanie
10. Bezpieczeństwo


<h1 align="center" style="font-size:40px" >WorkApp</h1>

1. <span style="font-size:20px;"> [About the application](#About) </span>
1. <span style="font-size:20px;">[System requirements](#requirements)</span>
1. <span style="font-size:20px;">[Instalation and lunching](#insalation)</span>
1. <span style="font-size:20px;">[File structure](#structure)</span>
1. Application functionality
1. Database integration
1. Error handling
1. Testing
1. Security


<h2>
<ol>
<li>About the application</li>
<li>System requirements</li>
<li>Instalation and lunching</li>
<li>File structure</li>
<li>Application functionality</li>
<li>Database integration</li>
<li>Error handling</li>
<li>Testing</li>
<li>Security</li>
<li>Example usage</li>
</ol>
</h2>
<hr>

<h2 id="About">1. About the application</h2>
<p>
The application was created for companiens that needs to menage employees and the workplaces in which currently work. Thanks to this application, the employee can easily record his working hours at a given address and the employer has easy control over the emplyees and their work. 
<br>
The program was written in the Java programming language using the JavaFX framework to create user interface. This project was created using the dependency management tool, Maven, which allowed for easier library management and project building. Additionaly the project uses a MySQL database to store informations about employees and their working hours. The application allows to login to the main panel where, depending on user's permissions, he has access to various functions. The employee has access to to functions such as:
<ul>
	<li> Panel with personal data, where the user can check his data 
	</li>
	<li>A panel with working hours, where the user can view saved working hours or add new ones by selecting a date in the calendar
	</li>  
	<li> Password change pannel where the user can change the password for his account
	</li>
	<li>Earnings panel, where the user selecting a given month, will receive information about the number of hours worked and the amount of gross remuneration he will receive for the hours worked.
	</li>
</ul>
There are also two additional panels that can only be seen by a user with "admin" permision:
<ul>
	<li>Panel with employees, where you can view list of all employees, change their data, generate a report of hours worked in a given month and add new employees. 
	</li>
	<li>Panel with addresses, where you can view addresses of workplaces, generate a report for a given month or add new address.
	</li>
</ul>

<h2 id="requirements">2. System requirements</h2>
<p>
<ul>
<li> Java: 20.0.1  </li>
<li> Maven compiler: 3.11.0  </li>
<li> MySQL: 8.2.0  </li>
<li> Javafx-maven-plugin: 0.0.8  </li>
</ul>
</p>

<h2 id="insalation">3. Instalation and lunching</h2>
<ul>
<li>
<p>
Before start program is needed create database and import data to it. To do this open terminal and create database using this command <i style="background-color:#262626; padding:1px 10px">mysql -u 'user_name' -p -e "CREATE DATABASE persons;</i> 
<br>
Where <i>user_name</i> is name of computer user and <i>persons</i> is name of database (It can be change becase the same name is implemented in code) 
</li>

<li>
Next what is needed is to import data from file to created database. To do this in terminal use this command <i style="background-color:#262626; padding:1px 10px"> mysql -u 'user_name' -p persons < dump.sql </i>
<br>
Where <i>user_name</i> is name of computer use (like abow) <i>persons</i> is created database and <i>dump.sql</i> is file with data. The file is included in application files in MySqlFiles folder (if you are in the same path in terminal as dump.sql file then you must provide the full path to the file).
<br>
After that you have database with every important data to start using application.
</li>
<br>
<li>
Now you can open IDE (for example IntelliJ), open project, comile and run it. 
</li>
</ul>
</p>
<h2 id="structure">4. File structure</h2>
<p>The programm consist of 14 classes</p>
<ol>
	<li>
	AddNewAddress
		<ul>	
			<li>AddNewAddress(int id, String address, boolean active)</li>
			<li> getID() </li>
			<li> getAddress() </li>
			<li> getActive() </li>
			<li> setActive(boolean act) </li>
		</ul>
	</li>
	<li>
	AddNewWorker
		<ul>
			<li> createNewWorker() </li>
		</ul>
	</li>
	<li>
	AddWorkTime
		<ul>
			<li> AddWorkTime() </li>
			<li> initialize(URL arg0, ResourceBundle arg1) </li>
			<li> ActiveAddressList() </li>
			<li> addressList() </li>
			<li> setMainPanelController(MainPanel mainPanel) </li>
			<li> submitButton() </li>
		</ul>
	</li>
	<li>
	DatabaseConnector
		<ul>
			<li> DatabaseConnector() </li>
			<li> executeQuery(String query) </li>
			<li> close() </li>
		</ul>	
	</li>
	<li>
	EditWorkTime
		<ul>
			<li> initialize(URL arg0, ResourceBundle arg1) </li>
			<li> setData(int startH, int startM, int endH, int endM, String address, String comm) </li>
			<li> addressList() </li>
			<li> addNewWorkTime() </li>
			<li> deleteWorkTime() </li>
			<li> setMainPanel(MainPanel mainPanel) </li>
			<li> editWorkTimeData() </li>
		</ul>
	</li>
	<li>
	LoadPersonData
		<ul>
			<li> dbConnection() </li>
			<li> logInToApp(String userLogin, String userPassword, Text textError) </li>
			<li> changePassword(String pass, int id) </li>
		</ul>
	</li>
	<li>
	LoadWorkTimeData
		<ul>
			<li> dbConnection(int userID, LocalDate data) </li>
			<li> getDataFromWorkTime(int userID) </li>
		</ul>
	</li>
	<li>
	LoginPanelController
		<ul>
			<li> getMd5( String source ) </li>
			<li> getString( byte[] bytes ) </li>
			<li> getPersonData() </li>
			<li> logIn() </li>
		</ul>
	</li>
	<li>
	MainPanel
		<ul>
			<li> MainPanel() </li>
			<li> createMainPanel() </li>
			<li> getScene() </li>
			<li> showAdminTest() </li>
			<li> hideAdminTest() </li>
			<li> showPersonInfo() </li>
			<li> personPanelBack() </li>
			<li> showWorkPanel() </li>
			<li> showWorkersPanel() </li>
			<li> workersPanelBack() </li>
			<li> workPanelBack() </li>
			<li> showSalaryPanel() </li>
			<li> salaryPanelBack() </li>
			<li> showSettingsPanel() </li>
			<li> settingPanelBack() </li>
			<li> showAddressPanel() </li>
			<li> addressPanelBack() </li>
			<li> setPersonData() </li>
			<li> getDataTime() </li>
			<li> showWorkTimeData() </li>
			<li> createWorkPanel() </li>
			<li> initialize(URL arg0, ResourceBundle arg1) </li>
			<li> summaryOfTheMonth() </li>
			<li> changePass() </li>
			<li> showWorkersList() </li>
			<li> showAddNewWorkerPanel() </li>
			<li> showAddressList() </li>
			<li> addNewAddress() </li>
		</ul>
	</li>
	<li>
	Person
		<ul>
			<li> Person(int id, String login, String password, String firstName, String lastName, BigInteger pesel, int phoneNumber, int SalaryPerHour, boolean admin) </li>
			<li> Person() </li>
			<li> getId() </li>
			<li> getPassword() </li>
			<li> setPassword(String password) </li>
			<li> getFirstName() </li>
			<li> getLastName() </li>
			<li> getPesel() </li>
			<li> getPhoneNumber() </li>
			<li> getSalaryPerHour() </li>
			<li> getAdmin() </li>
			<li> setAdmin(boolean admin) </li>
			<li> getPersonList() </li>
		</ul>
	</li>
	<li>
	SaveWorkTimeData
		<ul>
			<li>  connectWorkTimeDatabase(WorkTime worktime) </li>
			<li> removeDataFromDataBase(int id) </li>
		</ul>
	</li>
	<li>
	WorkApp
		<ul>
			<li> start(Stage primaryStage) </li>
			<li> main(String[] args) </li>
		</ul>
	</li>
	<li>
	WorkerPanel
		<ul>
			<li> setDataInPanel(String firstName, String lastName, BigInteger pesel, int phoneNumber, int salary, boolean admin) </li>
			<li> setPersonID(int id) </li>
			<li> updateWorkerData() </li>
		</ul>
	</li>
	<li>
	WorkTime
		<ul>
			<li> WorkTime() </li>
			<li> WorkTime(String address, LocalDate date, LocalTime start_time, LocalTime end_time, String comment, int user_id) </li>
			<li> WorkTime(int id, String address, LocalDate date, LocalTime start_time, LocalTime end_time, String comment, int user_id) </li>
			<li> getId() </li>
			<li> getAddress() </li>
			<li> setAddress(String address) </li>
			<li> getDate() </li>
			<li> getStart_time() </li>
			<li> getEnd_time() </li>
			<li> getComment() </li>
			<li> getUser_id() </li>
			<li> getHoursWork() </li>
			<li> timeToStrikg() </li>
		</ul>
	</li>
</ol>

<h2 id="test">5. Application functionality </h2>

