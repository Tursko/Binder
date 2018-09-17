package application.controller;

import java.io.IOException;

import application.Main;
import application.model.Assignment;
import application.model.GradeCalculator;
import application.model.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

/**
 * 
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * This Class represents a controller for GradeCalculator.fxml
 *
 */
public class GradeCalculatorController implements EventHandler<ActionEvent> {
	
	@FXML
	private Button homeButton, listButton, coursesButton, calcButton, logoutButton, settingsButton, addButton, calculateButton;
	@FXML
	private Button removeButton;

	@FXML
	TextField gradeField, weightField, gradeOutput;
	@FXML
	TableView<Grade> gradeCalcTable;
	
	@FXML 
	TableColumn<Grade, Double> gradeCol;
	@FXML
	TableColumn<Grade, Double> weightCol;
	@FXML
	Label needGradeLabel, needWeightLabel;
	@FXML
	Text howdyText;
	
	/**
	 * This method initializes some of the initial text and tableview information.
	 */
	@FXML
	private void initialize() {
		howdyText.setText("Howdy, " + Main.user.getDisplayName());
		gradeCalcTable.setPlaceholder(new Label("No grades entered."));
		gradeCol.setCellValueFactory(new PropertyValueFactory<Grade, Double>("grade"));
		weightCol.setCellValueFactory(new PropertyValueFactory<Grade, Double>("weight"));
		//list.addGradeCalculatorObject(new GradeCalculatorList(2.0,2.0));
		gradeCalcTable.setItems(Main.list.getObservableGradeList());
		needWeightLabel.setOpacity(0);
		needGradeLabel.setOpacity(0);
	}
	
	/**
	 * This method initializes some of the initial text and tableview information.
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == addButton) {
			if(gradeField.getText().length() == 0 || weightField.getText().length() == 0) {
				if(gradeField.getText().length() == 0)
					needGradeLabel.setOpacity(1);
				if(weightField.getText().length() == 0)
					needWeightLabel.setOpacity(1);
			}
			else{
				Main.list.addGradeCalculatorObject(new Grade(Double.valueOf(gradeField.getText()), Double.valueOf(weightField.getText())));
				gradeField.clear();
				weightField.clear();
				initialize();
			}
		}
		if(event.getSource() == removeButton) {
			Main.list.removeGrade(gradeCalcTable.getSelectionModel().getSelectedItem());
			gradeCalcTable.getItems().removeAll(
					gradeCalcTable.getSelectionModel().getSelectedItems()
					);
			
			initialize();
		}
		if(event.getSource() == calculateButton) {
			gradeOutput.setText(String.valueOf(Main.list.calculateFinalGrade()));
		}
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
			try {
				initialize(); 
				// Load the FXML document (we created with SceneBuilder)
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation( LoginController.class.getResource("/List.fxml") );
				
				// Load the layout from the FXML and add it to the scene
				AnchorPane layout = (AnchorPane) loader.load();				
				Scene scene = new Scene( layout );
				
				// Set the scene to stage and show the stage to the user
				Main.stage.setResizable(true);
				Main.stage.setScene(scene);
				Main.stage.setTitle("List | Binder");
				Main.stage.setMinHeight(600.00);
				Main.stage.setMinWidth(1000.00);
				
			}catch( IOException e ) {
				e.printStackTrace();
			}
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
		
		if(event.getSource() == calcButton) {
			System.out.println("calc button clicked");
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


