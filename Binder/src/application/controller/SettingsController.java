package application.controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * 
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * This class represents a Controller for Settings.fxml
 *
 */
public class SettingsController implements EventHandler<ActionEvent> {
	
	@FXML
	private Button homeButton, listButton, coursesButton, calcButton, settingsButton, logoutButton, setNameButton,
					setEmailButton, setPasswordButton;
	@FXML
	TextField nameField;
	@FXML
	TextField emailField;
	@FXML
	TextField confirmEmailField;
	@FXML
	PasswordField oldPasswordField;
	@FXML
	PasswordField newPasswordField;
	@FXML
	PasswordField confirmPasswordField;
	@FXML
	Text howdyText;
	
	/**
	 * This method initializes some of the initial text and tableview information.
	 */
	@FXML
	private void initialize() {
		howdyText.setText("Howdy, " + Main.user.getDisplayName());
	}
	
	/**
	 * This method handles button clicks
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == setNameButton) {
			Main.user.setDisplayName(nameField.getText());
			howdyText.setText( "Howdy, " + nameField.getText() );
		}
		
		if(event.getSource() == setEmailButton) {
			if(emailField.getText().equals(confirmEmailField.getText()))
			{
				Main.user.initializeHashMap();
				for (String key : Main.user.getUserHashMap().keySet())
				{
					if (key.equals(Main.user.getUserName()))
					{
						String password = Main.user.getUserHashMap().get(key);
						Main.user.getUserHashMap().remove(key);
						Main.user.getUserHashMap().put(emailField.getText(), password);
						Main.user.setUserName(emailField.getText());
					}
				}
				Main.user.writeMaptoFile();
			}
			else
			{
				System.out.println("Emails do not match");
			}
			emailField.clear();
			confirmEmailField.clear();
		}
		
		if(event.getSource() == setPasswordButton) {
			if(newPasswordField.getText().equals(confirmPasswordField.getText()) && 
					Main.user.getPassword().equals(oldPasswordField.getText()))
			{
				Main.user.setPassword(newPasswordField.getText());
				Main.user.initializeHashMap();
				for (String key : Main.user.getUserHashMap().keySet())
				{
					if (key.equals(Main.user.getUserName()))
					{
						Main.user.getUserHashMap().put(key, newPasswordField.getText());
					}
				}
				Main.user.writeMaptoFile();
			}
			else
			{
				System.out.println("Passwords do not match");
			}
			newPasswordField.clear();
			oldPasswordField.clear();
			confirmPasswordField.clear();
		}
		
		
		if(event.getSource() == homeButton) {
			//System.out.println("Home button clicked");
			try {
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/Home.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setTitle("List | Binder");
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setHeight(600.00);
				Main.stage.setWidth(1000.00);
			}catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
		if(event.getSource() == listButton) {
			System.out.println("list button clicked");
			try {
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/List.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setTitle("List | Binder");
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setHeight(600.00);
				Main.stage.setWidth(1000.00);
			}catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
		if(event.getSource() == coursesButton) {
			System.out.println("courses button clicked");
			try {
				//Load the FXML document
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(LoginController.class.getResource("/Courses.fxml"));
				
				AnchorPane layout = (AnchorPane) loader.load();
				Scene scene = new Scene(layout);
				
				Main.stage.setTitle("Courses | Binder");
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setHeight(600.00);
				Main.stage.setWidth(1000.00);
		
			}
			catch( IOException e) {
				e.printStackTrace();
			}
			
		}
		
		if(event.getSource() == calcButton) {
			System.out.println("calc button clicked");
			try {
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/GradeCalculator.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setTitle("Grade Calculator | Binder");
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setHeight(600.00);
				Main.stage.setWidth(1000.00);
			}catch( IOException e ) {
				e.printStackTrace();
			}
		}
		if(event.getSource() == logoutButton) {
			try {
				Main.user.writeData();
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/Login.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setTitle("Login | Binder");
				Main.stage.setResizable(false);
				Main.stage.setScene(scene);
				Main.stage.setMaxHeight(323.00);
				Main.stage.setMaxWidth(345.00);
			}catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
	}
	
}


