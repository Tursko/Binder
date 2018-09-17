package application.controller;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import javafx.scene.text.Text;

import application.Main;
/**
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * Controller for Register.fxml 
 */
public class RegisterController implements EventHandler<ActionEvent>{
	
	@FXML
	TextField emailField;
	@FXML
	TextField passwordField;
	@FXML
	TextField confirmPasswordField;
	@FXML
	Button signUpButton;
	@FXML
	Button cancelButton;
	@FXML
	Text emailReq, passReq, confirmReq, matchReq, usernameReq;
	/**
	 * Method for handling user registration 
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == signUpButton) {
			if(emailField.getText().length() == 0 || passwordField.getText().length() == 0 || confirmPasswordField.getText().length() == 0)
			{
				if(emailField.getText().length() == 0) {
					System.out.println("no username");
					emailReq.setOpacity(1);
				}
				if(passwordField.getText().length() == 0) {
					System.out.println("no password");
					passReq.setOpacity(1);
				}
				if(confirmPasswordField.getText().length() == 0) {
					System.out.println("no confirm");
					confirmReq.setOpacity(1);
				}					
			}
			else {
				System.out.println("all exist");
				String username = emailField.getText();
				String password = passwordField.getText();
				String confirmPass = confirmPasswordField.getText();
				System.out.println("variables set");
				
				if(Main.checkUsername(username)) {
					System.out.println("after check");
					if(password.equals(confirmPass)){
						System.out.println("passwords match");
						String out = "\n" + username + "," + password;
						try {
							Writer output;
							output = new BufferedWriter(new FileWriter("users.txt", true)); 
							output.append(out);
							System.out.println(out);
							output.close();
							
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation( LoginController.class.getResource("/Login.fxml") );
							
							// Load the layout from the FXML and add it to the scene
							AnchorPane layout = (AnchorPane) loader.load();				
							Scene scene = new Scene( layout );
							
							// Set the scene to stage and show the stage to the user
							Main.stage.setTitle("Binder");
							Main.stage.setResizable(true);
							Main.stage.setScene(scene);
							Main.stage.setTitle("Binder");
							Main.stage.setHeight(600.00);
							Main.stage.setWidth(1000.00);
							//MAX AND MIN SIZE OF REGULAR STAGE
							Main.stage.setMinHeight(550.00);
							Main.stage.setMinWidth(800.00);
							}catch(IOException e ){
								e.printStackTrace();
							}
					}
					else {
						System.out.println("passwords do not match");
						matchReq.setOpacity(1);
					}
				}
				else {
					System.out.println("username exsists");
					usernameReq.setOpacity(1);
				}
				
				}
			}
		
		if(event.getSource() == cancelButton) {
			System.out.println("Cancel");
			
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/Login.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setTitle("Binder");
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setTitle("Binder");
				Main.stage.setHeight(600.00);
				Main.stage.setWidth(1000.00);
				//MAX AND MIN SIZE OF REGULAR STAGE
				Main.stage.setMinHeight(550.00);
				Main.stage.setMinWidth(800.00);
				}catch(IOException e ){
					e.printStackTrace();
				}
		}
	}
}
