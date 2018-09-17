package application.model;
/**
 * 
 * @author Aaron Cohen
 * @author Dillan Mcormick
 * @author Hunter Fox
 * @author Eduardo Rodriguez 
 * @author Taylor Riley 
 * @author Zachary Gonzalez 
 * Assignment object contains String name, String date, and a String course name
 * for assignments. 
 */
public class Assignment{
	
	private String date;
	private String name;
	private String course;
/**
 * Constructor for the class 	
 * @param nameStr String to set as name 
 * @param dateStr String to set as the date 
 * @param courseStr String for the course name
 */
	
	public Assignment(String nameStr, String dateStr, String courseStr){
		this.date = dateStr;
		this.course = courseStr;
		this.name = nameStr;
	}
	/**
	 * Returns date as String 
	 * @return String date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Pass in a String to set as date
	 * @param date String to set date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * Returns name as String
	 * @return String name 
	 */
	public String getName() {
		return name;
	}
	/**
	 * Pass in a String to set as name
	 * @param name String to set name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns Course name as String
	 * @return String course name
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * Pass in String to set course name 
	 * @param course String to set as course name 
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/*public static Comparator<Assignment> getAssginmentComparator() {
		return AssginmentComparator;
	}

	public static void setAssginmentComparator(Comparator<Assignment> assginmentComparator) {
		AssginmentComparator = assginmentComparator;
	}*/
	
	
}