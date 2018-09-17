package application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * This class represents course Object. The Course Object has an 
 * ArrayList of Assignments and Completed Assignments
 */
public class Course {
	private String name;
	private ArrayList<Assignment> assignmentList;
	private ArrayList<Assignment> completedAssignment;
	
	/**
	 * Constructor for a Course Object
	 * @param name to set on the Course
	 */
	public Course(String name) {
		this.name = name;
		assignmentList = new ArrayList<Assignment>();
		completedAssignment = new ArrayList <Assignment>();
	}
	
	/**
	 * This method adds an Assignment to the assignmentList
	 * @param toAdd to add to list (Assignment)
	 */
	public void addAssignment(Assignment toAdd){
		assignmentList.add(toAdd);
	}
	
	/**
	 * This method adds an Assignment to the completedAssignments list
	 * @param toAdd to add to the list (Assignment)
	 */
	public void addCompleted(Assignment toAdd) {
		completedAssignment.add(toAdd);
	}
	
	/**
	 * This method finds the Index of an Assignment
	 * @param obj to look for (Assignment)
	 * @return index of the Assignment Object
	 */
	public int findAssignment(Assignment obj){
		int index = 0;
		for(Assignment a: this.assignmentList){
			if(a.getName() != null && a.getName().compareTo(obj.getName()) == 0)
				break;
			index++;
		}
		return index;
	}
	
	/**
	 * This method moves an Assignment from the assignmentList to the completedAssignment list
	 * @param obj to move (Assignment)
	 */
	public void moveAssignment(Assignment obj){
		int index = findAssignment(obj);
		this.assignmentList.remove(index);
		this.completedAssignment.add(obj);
	}
	
	/**
	 * This method returns an ObservableList of Assignments for a tableview.
	 * @return ObservableList tableList
	 */
	public ObservableList<Assignment> getAssignmentTableList(){
		ObservableList<Assignment> tableList = FXCollections.observableArrayList();
			
		for(int i = 0; i < this.getAssignmentList().size(); i++) {
			tableList.add(assignmentList.get(i));
			}
		
			return tableList;
		}
	
	/**
	 * This method removes an Assignment from the assignmentList
	 * @param assignment (Assignment)
	 */
	public void removeAssignment(Assignment assignment) {
		this.assignmentList.remove(assignment);
	}
	
	/**
	 * This method returns the Courses name
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method set the Courses name
	 * @param name to set (String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method returns the assignmentList
	 * @return ArrayList assignmentList
	 */
	public ArrayList<Assignment> getAssignmentList() {
		return assignmentList;
	}
	
	/**
	 * This method sets the assignmentList
	 * @param assignmentList to set (ArrayList)
	 */
	public void setAssignmentList(ArrayList<Assignment> assignmentList) {
		this.assignmentList = assignmentList;
	}

	/**
	 * This method returns the compeletedAssignment list
	 * @return ArrayList completedAssignment
	 */
	public ArrayList<Assignment> getCompletedAssignment() {
		return completedAssignment;
	}
	
	/**
	 * This method sets the completedAssignment list
	 * @param completedAssignment to set (ArrayList)
	 */
	public void setCompletedAssignment(ArrayList<Assignment> completedAssignment) {
		this.completedAssignment = completedAssignment;
	}

}
