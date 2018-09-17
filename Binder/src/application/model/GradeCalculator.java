package application.model;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Taylor Riley
 * @author Aaron Cohen
 * @author Hunter Fox
 * @author Eduardo Rodriguez
 * @author Zachary Gonzales
 * @author Dylan McCormick
 * 
 * This class represents the GradeCalculator Object. Allows the user to calculate their average grade.
 */
public class GradeCalculator {
	
	private ArrayList<Grade> gradeList = new ArrayList<Grade>();
	private double finalGrade;
	
	/**
	 * Constructor for a new GradeCalculator Object.
	 */
	public GradeCalculator() {
		finalGrade = 0;
	}
	
	/**
	 * Loops through gradeList and calculates the final grade.
	 * @return double finalGrade
	 */
	public double calculateFinalGrade() {
		finalGrade = 0;
		for(int i = 0; i < gradeList.size(); i++)
		{
			finalGrade += gradeList.get(i).getWeightedGrade();
		}
		System.out.println();
		return finalGrade;
	}

	/**
	 * This method adds a grade to the gradeList
	 * @param grade to add (Grade)
	 */
	public void addGradeCalculatorObject(Grade grade) {
		gradeList.add(grade);
	}
	
	/**
	 * This method removes a grade from the gradeList
	 * @param grade to remove (Grade)
	 */
	public void removeGrade(Grade grade) {
		gradeList.remove(grade);
	}
	
	/**
	 * This method returns the gradeList
	 * @return ArrayList gradeList
	 */
	public ArrayList<Grade> getGradeList() {
		return gradeList;
	}
	
	/**
	 * This method sets the gradeList
	 * @param gradeList to set (ArrayList)
	 */
	public void setGradeList(ArrayList<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	/**
	 * This method returns the Object's finalGrade
	 * @return double finalGrade
	 */
	public double getFinalGrade() {
		return finalGrade;
	}

	/**
	 * This method sets the finalGrade
	 * @param finalGrade to set (double)
	 */
	public void setFinalGrade(double finalGrade) {
		this.finalGrade = finalGrade;
	}
	
	/**
	 * Returns a list of assignments for the grade calculator
	 * @return newList 
	 */
	public ObservableList<Grade> getObservableGradeList() {
		ObservableList<Grade> newList = FXCollections.observableArrayList();
		newList.clear();
		for(int i = 0; i < gradeList.size(); i++) {
			newList.add(gradeList.get(i));
		}
		return newList;
	}
}
