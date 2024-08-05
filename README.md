# ENSF380 Summer Final Project - Subway Screen 
 Group Number: 26
 
 Group Members: Shahed Issa, Debo Dam, Arnav Mittal
 
## SQL Connection
1) Configure your username and password for your SQL file, and add them to the AdvertisementPanel file in the designated variable

2) Run the following command in your workbench or terminal:
	
		CREATE DATABASE IF NOT EXISTS train_station_ads;
		USE train_station_ads;
		CREATE TABLE IF NOT EXISTS advertisements (
		    ad_id INT AUTO_INCREMENT PRIMARY KEY,
		    title VARCHAR(255) NOT NULL,
		    text_content TEXT,
		    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		    image LONGBLOB
		);
		
3) Add your username and password to the following script, and ensure the folder variable is connected to the ads directory present in the github repo:
	
		import java.io.File;
		import java.io.FileInputStream;
		import java.io.IOException;
		import java.nio.file.Files;
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.SQLException;
		
		public class BulkImageUploader {
		    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station_ads";
		    private static final String USER = "Your Username Here"; 
		    private static final String PASS = "Your Password Here";

		   public static void main(String[] args) {
		        File folder = new File("Your ads directory here"); 
		
		        if (!folder.isDirectory()) {
		            System.out.println("The provided path is not a directory.");
		            return;
		        }
		
		        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
		            File[] files = folder.listFiles();
		            if (files != null) {
		                for (File file : files) {
		                    if (file.isFile() && isImage(file)) {
		                        insertImage(conn, file);
		                    }
		                }
		            }
		        } catch (SQLException | IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		
		    private static boolean isImage(File file) throws IOException {
		        String mimeType = Files.probeContentType(file.toPath());
		        return mimeType != null && mimeType.startsWith("image");
		    }
		
		    private static void insertImage(Connection conn, File file) {
		        String sql = "INSERT INTO advertisements (title, text_content, image) VALUES (?, ?, ?)";
		
		        try (FileInputStream fis = new FileInputStream(file);
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		
		            pstmt.setString(1, file.getName());
		            pstmt.setString(2, "Sample text content for " + file.getName());
		            pstmt.setBinaryStream(3, fis, (int) file.length());
		
		            int row = pstmt.executeUpdate();
		            if (row > 0) {
		                System.out.println("Inserted " + file.getName() + " into the database.");
		            }
		
		        } catch (SQLException | IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		}

4) Ensure you have the mysql-connector (https://dev.mysql.com/downloads/connector/j/) installed in your lib folder

5) your SQL connection is ready to go!

		

## Weather JAR File 
1) Open your command line terminal and navigate to the directory containing the WeatherReport file called "WeatherReportFiles"

2) Compile the WeatherReport file using the command: 

	javac WeatherReport.java

3) Package the class into a JAR using this command: 

	jar cf WeatherReport.jar WeatherReport.class

4) In eclipse, right click on your project and select "Build Path" --> "Configure Build Path"

5) Select the "Libraries" tab, click on Classpath, then click "Add External JAR File"

6) Navigate to where you saved your JAR file and select then, then click "Apply and Close"

7) Your JAR file is in and your code is ready to fetch the weather report!
	

## Subway Simulator
1) Open your command line terminal and navigate to the SubwaySimulatorFiles directory 

2) compile all the classes in the folder using the command: 
	
	javac -d ./build *.java

3) Navigate into the new "build" directory and run the following command to create the JAR file:

	jar cvf SubwaySimulator.jar *
	
4) Move the JAR file into the exe folder present in the project

5) Navigate into the main project directory and run the following command:

	run java -jar .\exe\SubwaySimulator.jar --in ".\data\subway.csv" --out ".\out"
	
6) This will print the position and direction of all the trains to your console, as well as a copy of it in a csv file in the out folder.