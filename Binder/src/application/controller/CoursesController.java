package application.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

import java.io.IOException;

import application.Main;
import application.model.Assignment;
import application.model.Course;
import application.model.Grade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * 
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 *
 * This class acts as a Controller for Courses.fxml 
 */
public class CoursesController implements EventHandler<ActionEvent>{
	
	@FXML
	private Button homeButton, listButton, coursesButton, calcButton, settingsButton, logoutButton, openButton, addCourse, removeCourse;
	@FXML
	TableView<Course> courseTable;
	@FXML
	TableView<Assignment> dueDateTable;
	@FXML
	TableColumn<Course, String> courseCol;
	@FXML
	TableColumn<Assignment, String> assignCol;
	@FXML
	TableColumn<Assignment, String> dateCol;
	@FXML
	TextField courseTextField;
	@FXML
	Text howdyText;
	
	/**
	 * Initializes data and fields for the scene.
	 */
	public void initialize() {
		howdyText.setText("Howdy, " + Main.user.getDisplayName());
		courseTable.setPlaceholder(new Label("No courses yet!"));
		dueDateTable.setPlaceholder(new Label("No assignments."));
		courseCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
		
		assignCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("date"));
		
		dueDateTable.getSortOrder().add(dateCol);
		
		courseTable.setItems(Main.user.getCourseTable());
		dueDateTable.setItems(null);

	}
	
	/*
	 * This method handles events, such as button clicks. 
	 * Everything from adding courses, to changing scenes is controlled here.
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() ==  openButton){
			if(courseTable.getSelectionModel().getSelectedItem() == null) {
				System.out.println("no selection");
			}
			else {
				System.out.println(courseTable.getSelectionModel().getSelectedItem().getName());
			
				for( Course name : Main.user.getCourseList()) {
					if(name.getName().equals(courseTable.getSelectionModel().getSelectedItem().getName()))
					{
						dueDateTable.setItems(name.getAssignmentTableList());
					}
				}
			}
		}
		
		if(event.getSource() == addCourse) {
			System.out.println("add course button" + courseTextField.getText());
			if(courseTextField.getText().length() == 0) {
					System.out.println("no course");
			}
			else{
				Main.user.addCourse(new Course(courseTextField.getText()));
			}
				courseTextField.clear();
				initialize();
				//Main.user.writeData();
			}
		
		if(event.getSource() == removeCourse) {
			if(courseTable.getSelectionModel().getSelectedItem() == null)
				System.out.println("no selection");
			Main.user.removeCourse(courseTable.getSelectionModel().getSelectedItem());
			initialize();
			//Main.user.writeData();
		}	
		
		if(event.getSource() == homeButton) {
			try {
				//initialize();
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/Home.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setTitle("Home | Binder");
				Main.stage.setMinHeight(600.00);
				Main.stage.setMinWidth(1000.00);
				
			}catch( IOException e ) {
				e.printStackTrace();
			}
			System.out.println("Home button clicked");
		}
		
		if(event.getSource() == listButton) {
			System.out.println("list button clicked");
			try {
				//Load the FXML document
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(LoginController.class.getResource("/List.fxml"));
				
				AnchorPane layout = (AnchorPane) loader.load();
				Scene scene = new Scene(layout);
				
				Main.stage.setTitle("List | Binder");
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setHeight(600.00);
				Main.stage.setWidth(1000.00);
		
			}
			catch( IOException e) {
				e.printStackTrace();
			}
		}
		
		if(event.getSource() == coursesButton) {
			System.out.println("courses button clicked");
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
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/Login.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setTitle("Binder");
				Main.stage.setResizable(false);
				Main.stage.setScene(scene);
				Main.stage.setTitle("Login | Binder");
				Main.stage.setHeight(323.00);
				Main.stage.setWidth(345.00);
			}catch( IOException e ) {
				e.printStackTrace();
			}
		}
	}
}
