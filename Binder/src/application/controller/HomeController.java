package application.controller;

import java.io.IOException;


import application.Main;
import application.model.Assignment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
/**
 * @author Aaron Cohen
 * @author Dillan Mcormick
 * @author Hunter Fox
 * @author Eduardo Rodriguez 
 * @author Taylor Riley 
 * @author Zachary Gonzalez 
 * This class is the Controller for Home.fxml 
 */


public class HomeController implements EventHandler<ActionEvent> {
	@FXML
	private PieChart homePieChart;
	
	@FXML
	private Button homeButton, listButton, coursesButton, calcButton, settingsButton, logoutButton;
	
	@FXML
	TableView<Assignment> upcomingTable;
	@FXML
	TableColumn<Assignment, String> dateCol;
	@FXML
	TableColumn<Assignment, String> assignCol;
	@FXML
	Text howdyText;
	/**
	 * Initializes title and sets the data for the fields
	 */
	@FXML
	private void initialize() {
		howdyText.setText("Howdy, " + Main.user.getDisplayName());
		upcomingTable.setPlaceholder(new Label("No upcoming Assignments."));
		homePieChart.setData(Main.user.pieData());
		
		dateCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("date"));
		assignCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
		
		upcomingTable.setItems(Main.user.weeklyAssignments());
		
	}
	/**
	 * Method to handle what view to show based off what button 
	 * the user selects 
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == homeButton) {
			//System.out.println("Home button clicked");
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
		if(event.getSource() == settingsButton) {
			//System.out.println("Home button clicked");
			try {
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/Settings.fxml") );
				
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
		if(event.getSource() == logoutButton) {
			try {
				Main.user.writeData();
				// Load the FXML document (we created with SceneBuilder)
				Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
				Scene scene = new Scene(root);
				
				// Load the layout from the FXML and add it to the scene
				Main.stage.setScene(scene);
				Main.stage.setWidth(scene.getWidth());
				Main.stage.setHeight(scene.getHeight());
				Main.stage.setTitle("Login | Binder");
				Main.stage.setResizable(false);
				//Main.stage.setScene(scene);
			}catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
	}
}


