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
11. Przykładowe użycie  



<h1 align="center">Work App</h1>
<h2>
<ol>
<li><a href="#About">About the application </a></li>
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

<h2 id="#About">1. About the application</h2>
<p>
The main idea of writing this program was to develop my programming skills and learn something new. The idea to create such an application arose when I came home from work every day and had to record my working hours in Excel. saving all the data manually was a bit frustrating, so I decided to create an app where I could save my data more easily. When writing the code, I thought about developing this program so that it would be used not only by me, but also by other employees and people running their own businesses. Over time, new ideas for the development of the application appeared, which allowed me to expand my knowledge, but at the same time extended the time spent working on it.  
<br>
The program was written in the Java programming language using the javafx tool, which helps create a user interface and a mySQL database in which all employees' data and working times are stored.
</p>

<h2>2. System requirements</h2>
<p>
<ul>
<li> Java: 20.0.1  </li>
<li> Maven compiler: 3.11.0  </li>
<li> MySQL: 8.2.0  </li>
<li> Javafx-maven-plugin: 0.0.8  </li>
</ul>
</p>

<h2>3. Instalation and lunching</h2>
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
<h2>4. File structure</h2>
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
</ol>
