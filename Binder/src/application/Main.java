package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import application.model.Assignment;
import application.model.Course;
import application.model.GradeCalculator;
import application.model.User;
/**
 * Author: The Binder Team
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * Main method for Binder application
 */
public class Main extends Application {
	
	//Needed to setup a static stage so we can swap between views -- Taylor
	public static Stage stage;
	
	public static User user;
	public static GradeCalculator list;
	/**
	 *Method to start the application 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setWidth(856.00);
			//primaryStage.setHeight(396.00);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login | Binder"); //sets title name
			primaryStage.setResizable(false); //doesn't allow resizing.
			primaryStage.show(); //opens the window
			list = new GradeCalculator();
			//user = new User("test", "test");
			//user.writeData();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		stage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	if(user != null)
		       user.writeData(); 
		    }
		}));
	}
	
	/**
	 * This method checks to see if a username Exists
	 * @param username to look for
	 * @return boolean true or false
	 */
	public static boolean checkUsername(String username) {
		try {
			Scanner scan = new Scanner(new File("users.txt"));
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				
				String[] token = line.split(",");
				
				if(token[0].equals(username)) {
					System.out.println("username already exists");
					return false;
				}
			
			}
			scan.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
}
