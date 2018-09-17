package application.controller;

import java.io.IOException;

import application.model.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import application.Main;
import javafx.scene.text.Text;
/**
* @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * This method is the Controller for Login.fxml
 */
public class LoginController implements EventHandler{
	

	@FXML 
	Button loginButton;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private TextField passwordField;
	
	@FXML
	Text failedLoginText;
	
	@FXML
	Button signUpButton;
	
	private String userName, password;
	/**
	 * Method to handle events for inputing the wrong password/user does not exist and 
	 * for successful logins
	 */
	@Override
	public void handle(Event event) {
		
		if(event.getSource() == loginButton) {
			userName = usernameField.getText();
			password = passwordField.getText();
			
			userName.trim();
			password.trim();
			
			Main.user =  User.authenticate(userName, password);
			System.out.println("Button clicked");
			
			if(Main.user != null)
			{
				System.out.println("Login Successfull");
				Main.user.readData();
				try {
					// Load the FXML document (we created with SceneBuilder)
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation( LoginController.class.getResource("/Home.fxml") );
					
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
					
				}catch( IOException e ) {
					e.printStackTrace();
				}
	
			}
			else
			{
				System.out.println("Login Failed");
				failedLoginText.setOpacity(1);
			}
		}
		
		if(event.getSource() == signUpButton) {
			try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( LoginController.class.getResource("/Register.fxml") );
			
			// Load the layout from the FXML and add it to the scene
			AnchorPane layout = (AnchorPane) loader.load();				
			Scene scene = new Scene( layout );
			
			// Set the scene to stage and show the stage to the user
			Main.stage.setTitle("Register | Binder");
			Main.stage.setResizable(false);
			Main.stage.setScene(scene);
			Main.stage.setHeight(550.00);
			Main.stage.setWidth(600.00);
			//MAX AND MIN SIZE OF REGULAR STAGE
			}catch(IOException e ){
				e.printStackTrace();
			}
		}
	}

}
