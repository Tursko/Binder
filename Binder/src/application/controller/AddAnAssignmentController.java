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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
/**
 * 
 * @author Aaron Cohen
 * @author Dillan Mcormick
 * @author Hunter Fox
 * @author Eduardo Rodriguez 
 * @author Taylor Riley 
 * @author Zachary Gonzalez 
 * AddAnAssignmentController contains courseBox:ChoiceBox, addButton:Buton,
 * cancelButton:Button, titleField:TextField, dateBox:DatePicker. 
 * The controller is in charge of adding assignments based off what the user 
 * inputs in the courseBox:ChoiceBox. 
 */
public class AddAnAssignmentController implements EventHandler<ActionEvent> {
	
	@FXML
	ChoiceBox<String> courseBox;
	@FXML
	Button addButton, cancelButton;
	@FXML
	TextField titleField;
	@FXML
	DatePicker dateBox;
	@FXML 
	Label needCourseLabel, needTitleLabel, needDateLabel;
	/**
	 * Method to setup the choice box with Main.user.getObservableCourseList()
	 */
	@FXML
	private void initialize() {
		courseBox.setItems(Main.user.getObservableCourseList());
		System.out.println(Main.user.getObservableCourseList());
	}
	/**
	 * Method to add assignments to main list 
	 * @param event ActionEvent to determine which button was pressed 
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == addButton) {
			//if(titleField.getText().length() > 0) {
				//TODO: We need to make sure the user enters all fields before clicking add
			if((titleField.getText().length() == 0) || (dateBox.getValue() == null) || (courseBox.getSelectionModel().getSelectedItem() == null)){
				//System.out.println("Enter all items");
				if(titleField.getText().length() == 0) {
					System.out.println("no title");
					needTitleLabel.setOpacity(1);
				}
				if(dateBox.getValue() == null)
					needDateLabel.setOpacity(1);
				if(courseBox.getSelectionModel().getSelectedItem() == null)
					needCourseLabel.setOpacity(1);
				}
			else{
				String date = dateBox.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				//adds new assignment to main list in User class
				Assignment assign = new Assignment(titleField.getText(),date,courseBox.getSelectionModel().getSelectedItem());
				Main.user.addAssignment(assign);
				
				//adds new assignment to specific course assignment list
				for (Course name: Main.user.getCourseList()) {
					if(name.getName().equals(courseBox.getSelectionModel().getSelectedItem())){
						name.addAssignment(assign);
					}
				}
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
				System.out.println(courseBox.getSelectionModel().getSelectedItem());
				System.out.println(dateBox.getValue() + date);
				System.out.println(titleField.getText());
				Stage stage = (Stage) addButton.getScene().getWindow(); 
				stage.close();
				}
		//Main.user.writeData();	
		}
		if(event.getSource() == cancelButton) {
			//closes window
			Stage stage = (Stage) cancelButton.getScene().getWindow(); 
			stage.close();
		}
	}
}


