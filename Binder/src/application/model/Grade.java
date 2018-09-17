package application.model;
/**
 * 
 * @author Aaron Cohen
 * @author Dillan Mcormick
 * @author Hunter Fox
 * @author Eduardo Rodriguez 
 * @author Taylor Riley 
 * @author Zachary Gonzalez 
 * Grade object contains double grade, and a double weight
 * that is used to determine the doulbe weighted grade(final grade)
 */
public class Grade {

	private double grade;
	private double weight; 
	private double weightedGrade;
	/**
	 * @param grade to set in Grade object
	 * @param tempWeight to set in Grade object
	 */
	public Grade(double grade, double tempWeight) {
		this.grade = grade;
		this.weight = tempWeight/100;
		this.weightedGrade = grade*weight;
	}
	/**
	 * return the grade as double 
	 * @return double grade 
	 */
	public double getGrade() {
		return grade;
	}
	/**
	 * Method to set grade
	 * @param grade double to set as grade
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}
	/**
	 * returns the weight for the Grade object
	 * @return double weight for Grade object
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * Method to set the weight
	 * @param weight double to set for the Grade object
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * Method to return the weighted grade of Grade object 
	 * @return double weighted grade of the Grade object 
	 */
	public double getWeightedGrade() {
		return weightedGrade;
	}
	/**
	 * Method to set weightedGrade
	 * @param weightedGrade double to set weightedGrade 
	 */
	public void setWeightedGrade(double weightedGrade) {
		this.weightedGrade = weightedGrade;
	}
}
