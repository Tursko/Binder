package application.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import application.Main;
import application.model.Assignment;
import application.model.Course;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * This method represents a Controller for List.fxml
 *
 */
public class ListController implements EventHandler<ActionEvent> {
	@FXML
	private TabPane listPane;
	//hey
	@FXML
	private AnchorPane courses_pane;
	
	@FXML
	private Button homeButton, listButton, coursesButton, calcButton, settingsButton, completeButton, addAssignButton, removeButton, logoutButton, refreshButton;
	
	@FXML
	TableView<Assignment> dateTable;
	
	@FXML
	TableView<Assignment> courseTable;
	@FXML
	TableView<Assignment> completedTable;
	
	@FXML 
	TableColumn<Assignment, String> dateCol;
	@FXML
	TableColumn<Assignment, String> assignCol;
	@FXML
	TableColumn<Assignment, String> compAssCol;
	@FXML
	TableColumn<Assignment, String> compDateCol;
	@FXML
	TableColumn<Assignment, String> courseCol;
	@FXML
	TableColumn<Assignment, String> courseAssCol;
	@FXML
	TableColumn<Assignment, String> courseDateCol;
	@FXML
	Text howdyText;
	
	/**
	 * This method initializes some of the initial text and tableview information.
	 */
	@FXML
	private void initialize() {
		howdyText.setText("Howdy, " + Main.user.getDisplayName());
		completedTable.setPlaceholder(new Label("No completed assignments yet.... Get to work!"));
		courseTable.setPlaceholder(new Label("No assignments available."));
		dateTable.setPlaceholder(new Label("No assignments available."));
		//ItemCol.setMinWidth(200);
		dateCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("date"));
		//QuanCol.setMinWidth(70);
		assignCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
		
		compAssCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
		
		compDateCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("date"));
		
		courseCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("course"));
		
		courseAssCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
		
		courseDateCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("date"));
		
		dateTable.setItems(Main.user.getAssignmentTableList());
		completedTable.setItems(Main.user.getCompletedTableList());
		courseTable.setItems(Main.user.getAssignmentTableList());
		
		dateTable.getSortOrder().add(dateCol);
		courseTable.getSortOrder().add(courseCol);
		completedTable.getSortOrder().add(compDateCol);
		
		dateTable.getSelectionModel().select(null);
		completedTable.getSelectionModel().select(null);
		courseTable.getSelectionModel().select(null);
		//Main.user.writeData();
		
	}
	
	/*
	 * This method refreshes the dateTable
	 */
	public void refreshTables() {
		dateTable.refresh();
		
	}
	
	/**
	 * This method initializes some of the initial text and tableview information.
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == homeButton) {
			try {
				initialize();
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
		
		if(event.getSource() == addAssignButton) {
			try {
				Parent root1 = FXMLLoader.load(getClass().getResource("/AddAssignment.fxml"));
		                Stage stage = new Stage();
		                stage.setTitle("Add An Assignment | Binder");
		                stage.setResizable(false);
		                stage.setScene(new Scene(root1));  
		                stage.show();
		        } catch(Exception e) {
		           e.printStackTrace();
		          }
		}
	
		
		//LIST PANE 0 IS THE FIRST PANE (THE PANE SORTED BY DATE)
		if(listPane.getSelectionModel().getSelectedIndex() == 0) {
			if(event.getSource() == removeButton) {
				for(Course name : Main.user.getCourseList()) {
					if(name.getName().equals(dateTable.getSelectionModel().getSelectedItem().getCourse()) )
					{
						name.removeAssignment(dateTable.getSelectionModel().getSelectedItem());
					}
					//Main.user.writeData();
				}
				Main.user.removeAssignment(dateTable.getSelectionModel().getSelectedItem());
				dateTable.getItems().removeAll(
						dateTable.getSelectionModel().getSelectedItems()
						);
				System.out.println("remove");
				initialize();
			}
			if(event.getSource() == completeButton) {
			
				Main.user.addCompleted(dateTable.getSelectionModel().getSelectedItem());
				Main.user.removeAssignment(dateTable.getSelectionModel().getSelectedItem());
			
				dateTable.getItems().removeAll(
						dateTable.getSelectionModel().getSelectedItems()
						);
			
				initialize();
				//Main.user.writeData();
			}
		}
		//LIST PANE BY COURSE
		if(listPane.getSelectionModel().getSelectedIndex() == 1) {
			if(event.getSource() == removeButton) {
				for(Course name : Main.user.getCourseList()) {
					if(name.getName().equals(courseTable.getSelectionModel().getSelectedItem().getCourse()) )
					{
						name.removeAssignment(courseTable.getSelectionModel().getSelectedItem());
					}
				}
				Main.user.removeAssignment(courseTable.getSelectionModel().getSelectedItem());
				courseTable.getItems().removeAll(
						courseTable.getSelectionModel().getSelectedItems()
						);
				System.out.println("remove");
				initialize();
				//Main.user.writeData();
			}
			if(event.getSource() == completeButton) {
			
				Main.user.addCompleted(courseTable.getSelectionModel().getSelectedItem());
				Main.user.removeAssignment(courseTable.getSelectionModel().getSelectedItem());
			
				dateTable.getItems().removeAll(
						dateTable.getSelectionModel().getSelectedItems()
						);
				//
				initialize();
				//Main.user.writeData();
			}
		}
		//LIST PANE (COMPLETED)
		if(listPane.getSelectionModel().getSelectedIndex() == 2) {
			if(event.getSource() == removeButton) {
				for(Course name : Main.user.getCourseList()) {
					if(name.getName().equals(completedTable.getSelectionModel().getSelectedItem().getCourse()) )
					{
						name.removeAssignment(completedTable.getSelectionModel().getSelectedItem());
					}
					//Main.user.writeData();
				}
				Main.user.removeCompletedAssignment(completedTable.getSelectionModel().getSelectedItem());
				Main.user.removeAssignment(completedTable.getSelectionModel().getSelectedItem());
				completedTable.getItems().removeAll(
						completedTable.getSelectionModel().getSelectedItems()
						);
				System.out.println("remove");
				initialize();
				
			}
		}
	}
	
}



